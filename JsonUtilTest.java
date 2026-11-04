
package com.railway.util;

public class JsonUtilTest {

    public static void main(String[] args) {

        String[] trains = {
            "Train 101 - Express",
            "Train 102 - Superfast"
        };

        JsonUtil.save(trains, "trains.json");

        String[] loadedTrains =
                JsonUtil.load("trains.json", String[].class);

        if (loadedTrains != null) {
            System.out.println("Loaded railway data:");

            for (String train : loadedTrains) {
                System.out.println(train);
            }
        }
    }
}