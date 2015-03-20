package com.kamcord.metrics;

import com.kamcord.util.MetricsConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by sujatamenon on 3/18/15.
 */

//POJO for the Metrics Event
public class MetricsEvent implements MetricsConstants {


    private String eventId;
    private int eventCount;
    private UI_EVENT_NAME eventName;
    private LocalDateTime eventDateTime;
    private OS_NAME osName;
    private String sdkVersion;
    private String userId;

    public MetricsEvent(String eventId, String eventCount, String eventName, String eventDateTime,
                        String osName, String sdkVersion, String userId) {

        setEventId(eventId);
        setEventCount(eventCount);
        setEventName(eventName);
        setEventDateTime(eventDateTime);
        setOsName(osName);
        setSdkVersion(sdkVersion);
        setUserId(userId);

    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(String eventCount) {
        this.eventCount = Integer.parseInt(eventCount);
    }

    public UI_EVENT_NAME getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = UI_EVENT_NAME.valueOf(eventName);
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventTime) {
        DateTimeFormatter formatter;
        LocalDateTime dateTime;
        try {
            formatter = DateTimeFormatter.ofPattern(CSV_DATE_TIME_FORMAT);
            dateTime = LocalDateTime.parse(eventTime, formatter);
            this.eventDateTime = dateTime;
        } catch (java.time.format.DateTimeParseException dtpe) {
            try {
                //There are 3 types of formats in the CSV file.
                //Using the exception model to avoid checking format every time.
                formatter = DateTimeFormatter.ofPattern(CSV_ISO_OFFSET_FORMAT);
                dateTime = LocalDateTime.parse(eventTime, formatter);
                this.eventDateTime = dateTime;
            } catch (java.time.format.DateTimeParseException dtpe2) {
                formatter = DateTimeFormatter.ofPattern(CSV_DATE_TIME_ISO_OFFSET_FORMAT);
                dateTime = LocalDateTime.parse(eventTime, formatter);
                this.eventDateTime = dateTime;
            }
        }

    }

    public OS_NAME getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = OS_NAME.valueOf(osName);
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
