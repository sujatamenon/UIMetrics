package com.kamcord.main;

import com.kamcord.metrics.UIRetention;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by sujatamenon on 3/18/15.
 */

public class MetricsMain {

    public MetricsMain() {
        //No functionality needed here.
    }

    public static void main (String[] args) throws Exception {
        System.out.println("testing kamcord...");
        try {
            UIRetention uiRetention = new UIRetention("/Users/sujatamenon/Downloads/sujata.csv");
            //Substitute different filename and compile to test
            float percentage = uiRetention.nthDayRetentionRange(LocalDate.of(2014, Month.OCTOBER, 1), null, 7, null, null);
            System.out.println("percentage is " + percentage);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Input file name is incorrect, try again");
        }

    }

}