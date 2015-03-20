package com.kamcord.metrics;

import com.kamcord.util.MetricsConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.*;

/**
 * Created by sujatamenon on 3/18/15.
 */

/** UIRetention contains all methods to compute user retention for UI
 *
 */
public class UIRetention implements  Retention {

    private ArrayList<MetricsEvent> metricEvents = new ArrayList();
    private HashMap<String, HashMap<String,MetricsEvent>> openEvents, reOpenEvents;
    private HashMap<String, MetricsEvent> openEventUserMap, reOpenEventUserMap;
    private String csvFilePath;


    public UIRetention() {}

    public UIRetention(String csvFilePath) throws Exception {
        this.csvFilePath = csvFilePath;
        loadData();
    }

    public void loadData() throws Exception {
        loadFromCSVFile();
    }

    /**
    *loadFromCSVFile loads the CSV file into a local ArrayList
    *Segregate data into 2 HashMap- one containing open events and the other containing reopen events
    */
    private void loadFromCSVFile() throws FileNotFoundException {
        String sCurrentLine;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(csvFilePath));
            //Read the first line of CSV file and ignore it as it contains column descriptions
            sCurrentLine = bufferedReader.readLine();
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                String[] entry = sCurrentLine.split(",");
                String eventId = entry[0];
                String eventCount = entry[1];
                String eventName = entry[2];
                String eventTime = entry[3];
                String osName = entry[4];
                String sdkVersion = entry[5];
                String userId = entry[6];

                metricEvents.add(new MetricsEvent(eventId, eventCount, eventName, eventTime, osName, sdkVersion, userId));

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("IOException while loading CSV File " + ioe.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.println("IOException while loading CSV File " + ioe.getMessage());
            }
        }

        System.out.println("Number of lines in the CSV file are " + metricEvents.size());

        System.out.println(((MetricsEvent)metricEvents.get(0)).getEventDateTime().toLocalDate().toString());

        /* We will segregate the events in the ArrayList into 2 HashMap with key as the date and the value as a HashMap
        * of userid as key and MetricsEvent as the value. It is a HashMap of user events by day. 2 data structures are maintained to
        * track open and reopen to find intersection of the users on a specific day.
        * Key is the date and the value is a set of all events on this date. Assumption made is that Duplicates are ignored.
        */
        openEvents = new HashMap<>();
        reOpenEvents = new HashMap<>();

        //O(n) = n + 2n
        for (MetricsEvent event : metricEvents) {

            String keyDate = event.getEventDateTime().toLocalDate().toString();
            System.out.println("Event type is " + event.getEventName() + " " + keyDate);

            if (event.getEventName().equals(MetricsConstants.UI_EVENT_NAME.UI_OPEN_COUNT)) {
                HashMap<String, MetricsEvent> dateMap = openEvents.get(keyDate);
                if (dateMap == null) {
                    //We are seeing this date for the first time
                    System.out.println("HashMap is null for date " + keyDate);
                    dateMap = new HashMap<>();
                    dateMap.put(event.getUserId(), event);
                    openEvents.put(keyDate, dateMap);
                    dateMap = openEvents.get(keyDate);
                    System.out.println("dateMap is " + dateMap.size());

                } else {
                    System.out.println("Adding to the same hashmap for date " + keyDate);
                    dateMap.put(event.getUserId(), event);
                    openEvents.put(keyDate, dateMap);
                }
            } else if (event.getEventName().equals(MetricsConstants.UI_EVENT_NAME.VIDEO_VIEW_COUNT)) {
                HashMap<String, MetricsEvent> dateMap = reOpenEvents.get(keyDate);
                if (dateMap == null) {
                    //We are seeing this date for the first time
                    dateMap = new HashMap<>();
                    dateMap.put(event.getUserId(), event);
                    reOpenEvents.put(keyDate, dateMap);
                } else {
                    dateMap.put(event.getUserId(), event);
                    reOpenEvents.put(keyDate, dateMap);
                }

            }

        }

        System.out.println("Number of Days in Open events is " + openEvents.size());
        System.out.println("Number of Days in Re-Open events is " + reOpenEvents.size());

    }

    public float nthDayRetentionForMonth(Month month, Year year, int nthDay,
                                       MetricsConstants.OS_NAME osName, String sdkVersion) {
        LocalDate localDate = LocalDate.of(year.getValue(), month, 01);
        int length = localDate.getMonth().length(true);
        return nthDayRetentionRange(LocalDate.of(year.getValue(), month, 1),
                                    LocalDate.of(year.getValue(), month, length),
                                    nthDay, osName, sdkVersion);
    }

    /* Every filter adds n operation of if check. 2 filters for OS and SDK will add 2n if conditions
    * and n times checking against the reopen events.
    * The outer For loop for hashmap for days will be reached 1 time per day. If a search is made for 5 days,
    * the outerloop for range will iterate for 5 times. For each outer loop iteration, 3n checks will be made for
    * filters of OS,SDK and check if a user reopened the app.
    * Worst case: All days are needed in the Hashmap and all filters are used.
    * Space constraints: Array List of original data, HashMap of day and user hashmap.
    */
    public float nthDayRetentionRange(LocalDate startDate, LocalDate endDate, int nthDay,
                                    MetricsConstants.OS_NAME osName, String sdkVersion) {

        System.out.println("endDate is " + endDate);
        boolean range = false;
        boolean osFilter = false;
        boolean sdkFilter = false;
        int totalCount = 0;
        int intersectionCount = 0;
        LocalDate targetDate, startDateIterator;

        if ((startDate == null) || ((startDate == null) && (endDate == null))) return -1;
        if ((startDate != null) && (endDate != null)) {
            if (endDate.isBefore(startDate)) return -2; //Can be made better to throw specific exception if needed
            range = true;
        }
        if (osName != null) osFilter = true;
        if (sdkVersion != null) sdkFilter = true;

        openEventUserMap = openEvents.get(startDate.toString());
        targetDate = startDate.plusDays(nthDay);
        reOpenEventUserMap = reOpenEvents.get(targetDate.toString());


        //Filter for range alone
        if (range) {
            int days = Period.between(startDate, endDate).getDays() + 1;
            System.out.println("Days in range is " + days);

            if ((!osFilter) && (!sdkFilter)) {

                startDateIterator = startDate;

                //Worst case: When range is the entire data set, all data must be traversed.
                //Entire Hashmap is traversed and intersection of Users is performed for open and reopen events
                for (int i = 0; i < days; i++) {
                    targetDate = startDateIterator.plusDays(nthDay);
                    openEventUserMap = openEvents.get(startDateIterator.toString());

                    if (openEventUserMap != null) {
                        totalCount += openEventUserMap.size();
                        reOpenEventUserMap = reOpenEvents.get(targetDate.toString());

                        if (reOpenEventUserMap != null) {
                            for (String user : openEventUserMap.keySet()) {
                                if (reOpenEventUserMap.containsKey(user)) intersectionCount++;
                            }
                        }
                    }

                    startDateIterator = startDateIterator.plusDays(1);
                }

                System.out.println("intersectionCount with range is " + intersectionCount);

            } else if ((osFilter) && (!sdkFilter)) {
                startDateIterator = startDate;

                //Using double for loop for range of days and filter further for users who opened the app
                //After we find the users who reopened, we go through the user data to check filters
                for (int i = 0; i < days; i++) {
                    targetDate = startDateIterator.plusDays(nthDay);

                    openEventUserMap = openEvents.get(startDateIterator.toString());
                    if (openEventUserMap != null) {
                        reOpenEventUserMap = reOpenEvents.get(targetDate.toString());
                        if (reOpenEventUserMap != null) {
                            for (String user : openEventUserMap.keySet()) {
                                //Assumption: Only user is checked and not filters across common data set
                                if (openEventUserMap.get(user).getOsName().equals(osName)) {
                                    totalCount++;
                                    if (reOpenEventUserMap.containsKey(user)) intersectionCount++;
                                }
                            }
                        }
                    }

                    startDateIterator = startDateIterator.plusDays(1);
                }

                System.out.println("intersectionCount with range and osFilter is " + intersectionCount);

            } else if ((!osFilter) && (sdkFilter)) {
                startDateIterator = startDate;
                //Using double for loop for range of days and filter further for users who opened the app
                for (int i = 0; i < days; i++) {
                    targetDate = startDateIterator.plusDays(nthDay);
                    openEventUserMap = openEvents.get(startDateIterator.toString());

                    if (openEventUserMap != null) {
                        reOpenEventUserMap = reOpenEvents.get(targetDate.toString());

                        if (reOpenEventUserMap != null) {
                            for (String user : openEventUserMap.keySet()) {
                                if ((openEventUserMap.get(user).getSdkVersion().equals(sdkVersion))) {
                                    totalCount++;
                                    if (reOpenEventUserMap.containsKey(user)) intersectionCount++;
                                }
                            }
                        }
                    }

                    startDateIterator = startDateIterator.plusDays(1);
                }

                System.out.println("intersectionCount with range and osFilter is " + intersectionCount);


            } else if ((osFilter) && (sdkFilter)) {
                startDateIterator = startDate;
                for (int i = 0; i < days; i++) {
                    targetDate = startDateIterator.plusDays(nthDay);

                    openEventUserMap = openEvents.get(startDateIterator.toString());
                    if (openEventUserMap != null) {
                        reOpenEventUserMap = reOpenEvents.get(targetDate.toString());

                        if (reOpenEventUserMap != null) {
                            for (String user : openEventUserMap.keySet()) {
                                if ((openEventUserMap.get(user).getOsName().equals(osName)) &&
                                        (openEventUserMap.get(user).getSdkVersion().equals(sdkVersion))) {
                                    totalCount++;
                                    if (reOpenEventUserMap.containsKey(user)) intersectionCount++;

                                }
                            }
                        }
                    }

                    startDateIterator = startDateIterator.plusDays(1);
                }

                System.out.println("intersectionCount with range, osFilter and sdkVersion is " + intersectionCount);

            }

        } else {
            //This is not range. This is a single specific date UI retention

            if (openEventUserMap == null) return 0; //No data found for specified date
            System.out.println("totalcount without range for specific date is " + openEventUserMap.size());
            if (reOpenEventUserMap == null) return 0; //No reopen data found for target date

            if ((!osFilter) && (!sdkFilter)) {
                totalCount = openEventUserMap.size(); //All Open events
                for (String user : openEventUserMap.keySet()) {
                    if (reOpenEventUserMap.containsKey(user)) {
                        intersectionCount++;
                    }
                }
                System.out.println("intersectionCount without range is " + intersectionCount);

            } else if ((osFilter) && (!sdkFilter)) {
                for (String user : openEventUserMap.keySet()) {
                    if (openEventUserMap.get(user).getOsName().equals(osName)) {
                        totalCount++;
                        if (reOpenEventUserMap.containsKey(user)) {
                            intersectionCount++;
                        }
                    }
                }
                System.out.println("intersectionCount with NO range and osFilter is " + intersectionCount);

            } else if ((!osFilter) && (sdkFilter)) {
                for (String user : openEventUserMap.keySet()) {
                    if (openEventUserMap.get(user).getSdkVersion().equals(sdkVersion)) {
                        totalCount++;
                        if (reOpenEventUserMap.containsKey(user)) intersectionCount++;
                    }
                }
                System.out.println("intersectionCount with NO range and sdkVersion is " + intersectionCount);

            } else if ((osFilter) && (sdkFilter)) {
                for (String user : openEventUserMap.keySet()) {
                    if ((openEventUserMap.get(user).getOsName().equals(osName)) &&
                       (openEventUserMap.get(user).getSdkVersion().equals(sdkVersion))) {
                        totalCount++;
                        if (reOpenEventUserMap.containsKey(user)) intersectionCount++;
                    }
                }
                System.out.println("intersectionCount with NO range and osFilter and sdkVersion is " + intersectionCount);

            }

        }

        return calcPercentage(totalCount, intersectionCount);

    }

    private float calcPercentage(int totalCount, int intersectionCount) {
        System.out.println("Total Open Events is " + totalCount);
        System.out.println("Total Reopen Events that matched are " + intersectionCount);
        if (totalCount == 0) return 0; //avoid division by zero
        return ((intersectionCount * 100.0f) / totalCount);

    }

}
