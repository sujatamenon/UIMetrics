package com.kamcord.tests;

import com.kamcord.metrics.UIRetention;
import com.kamcord.util.MetricsConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static org.junit.Assert.fail;

/**
 * Created by sujatamenon on 3/19/15.
 */
public class UIRetentionTest {

    @BeforeClass
    public static void setUp() {

    }
/*
    @Test
    //Local Test
    public void test7DayRetentionNoRangeNoFilters() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), null, 7, null, null);
            Assert.assertEquals(20.0f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionRangeNoFilters() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2014, Month.OCTOBER, 2), 7, null, null);
            Assert.assertEquals(28.0f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionNoRangeIOSFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), null, 7, MetricsConstants.OS_NAME.IOS, null);
            Assert.assertEquals(21.428571701049805f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionNoRangeIOSAndSDKFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), null, 7, MetricsConstants.OS_NAME.IOS, "1.7.5");
            Assert.assertEquals(28.571428298950195f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionNoRangeAndroidAndSDKFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), null, 7, MetricsConstants.OS_NAME.android, "1.4.4");
            Assert.assertEquals(16.66666603088379f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionNoRangeDummyFilters() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), null, 7, MetricsConstants.OS_NAME.IOS, "meow");
            Assert.assertEquals(0.0f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForRangeNoFilters() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2014, Month.OCTOBER, 2),
                    7, null, null);
            Assert.assertEquals(28.0f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForRangeIOSFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2014, Month.OCTOBER, 2),
                    7, MetricsConstants.OS_NAME.IOS, null);
            Assert.assertEquals(27.77777862548828f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForRangeSDKFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2014, Month.OCTOBER, 2),
                    7, null, "1.7.5");
            Assert.assertEquals(44.44444274902344f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForRangeOSAndSDKFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2014, Month.OCTOBER, 2),
                    7, MetricsConstants.OS_NAME.IOS, "1.7.5");
            Assert.assertEquals(44.44444274902344f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForRangeFiltersOSAndSDKVersion() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), LocalDate.of(2014, Month.OCTOBER, 2),
                    7, MetricsConstants.OS_NAME.IOS, "1.7.5");
            Assert.assertEquals(44.44444274902344f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionInvalidDate() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.SEPTEMBER, 1), null,
                    7, MetricsConstants.OS_NAME.IOS, "1.7.5");
            Assert.assertEquals(0f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForMonthNoFilters() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionForMonth(Month.OCTOBER, Year.of(2014), 7,
                    null, null);
            Assert.assertEquals(28.0f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForMonthIOSFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionForMonth(Month.OCTOBER, Year.of(2014), 7,
                    MetricsConstants.OS_NAME.IOS, null);
            Assert.assertEquals(27.77777862548828f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForMonthSDKFilter() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionForMonth(Month.OCTOBER, Year.of(2014), 7,
                    null, "1.7.5");
            Assert.assertEquals(44.44444274902344f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }
    @Test
    //Local Test
    public void test7DayRetentionForMonthIOSSDKFilterActualCSV() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/Analytics Coding Challenge Data.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionForMonth(Month.SEPTEMBER, Year.of(2014), 7,
                    MetricsConstants.OS_NAME.IOS, "1.7.5");
            Assert.assertEquals(14.520840644836426f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForRangeIOSSDKFilterActualCSV() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/Analytics Coding Challenge Data.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.SEPTEMBER, 1), LocalDate.of(2014, Month.SEPTEMBER, 30), 7,
                    MetricsConstants.OS_NAME.IOS, "1.7.5");
            Assert.assertEquals(14.520840644836426f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }
*/
    @Test
    //Local Test
    public void test7DayRetentionForSpecificDateIOSSDKFilterActualCSV() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/Analytics Coding Challenge Data.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.SEPTEMBER, 1), null, 7,
                    MetricsConstants.OS_NAME.IOS, "1.7.5");
            Assert.assertEquals(14.575826644897461f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    /*
    @Test
    //Local Test
    public void test7DayRetentionForMonthNoFilterActualCSV() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/Analytics Coding Challenge Data.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionForMonth(Month.SEPTEMBER, Year.of(2014), 7, null, null);
            Assert.assertEquals(9.881257057189941f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForSpecificDateNoFilterActualCSV() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/Analytics Coding Challenge Data.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.SEPTEMBER, 29), null,
                    7, null, null);
            Assert.assertEquals(0.0f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForMonthSDKFilterActualCSV() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/Analytics Coding Challenge Data.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionForMonth(Month.SEPTEMBER, Year.of(2014), 7,
                    null, "1.7.5");
            Assert.assertEquals(14.520840644836426f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }

    @Test
    //Local Test
    public void test7DayRetentionForRangeAndroidFilterActualCSV() {

        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/Analytics Coding Challenge Data.csv"); //Can be moved to properties file
            //Substitute different filename and compile to test
            float retention = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.SEPTEMBER, 8), LocalDate.of(2014, Month.SEPTEMBER, 10),
                    7, MetricsConstants.OS_NAME.android, null);
            Assert.assertEquals(44.44444274902344f, retention, 0.0002); // true
        } catch (Exception e) {
            e.printStackTrace();
            fail("Generic Exception " + e.getMessage());
        }

    }
    */

}

