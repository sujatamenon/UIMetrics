package com.kamcord.util;

/**
 * Created by sujatamenon on 3/18/15.
 */
//All CONSTANTS use caps where possible and underscore in between
public interface MetricsConstants {

    //Tracking all constants used in the application
    public enum UI_EVENT_NAME {
        UI_OPEN_COUNT, VIDEO_VIEW_COUNT
    }

    public enum OS_NAME {
        IOS, android
    } //android is not capitalized to be in sync with csv file

    String CSV_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    String CSV_ISO_OFFSET_FORMAT = "yyyy-MM-dd HH:mm:ss+00:00";
    String CSV_DATE_TIME_ISO_OFFSET_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS+00:00";

}
