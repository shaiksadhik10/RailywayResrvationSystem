
package com.railway.model;

import com.railway.util.JsonUtil;

public class TrainTest {

    public static void main(String[] args) {

        // Create two train objects
        Train train1 = new Train(
            101, "Express", "Hyderabad", "Chennai", 100
        );

        Train train2 = new Train(
            102, "Superfast", "Vijayawada", "Bengaluru", 80
        );

        // Save the train objects to a JSON file
        Train[] trains = {train1, train2};

        JsonUtil.save(trains, "trains.json");

        // Load the train objects from the JSON file
        Train[] loadedTrains =
            JsonUtil.load("trains.json", Train[].class);

        // Display the loaded trains
        if (loadedTrains != null) {
            System.out.println("\nLoaded Train Details:");

            for (Train train : loadedTrains) {
                System.out.println(train);
            }
        }
    }
}
