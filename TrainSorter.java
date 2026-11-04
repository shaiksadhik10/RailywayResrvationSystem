
package com.railway.service;

import com.railway.model.Train;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrainSorter {

    // Sort trains by train number
    public static List<Train> sortByTrainNumber(List<Train> trains) {

        List<Train> sortedTrains = new ArrayList<>(trains);

        sortedTrains.sort(
                Comparator.comparingInt(Train::getTrainNumber)
        );

        return sortedTrains;
    }

    // Sort trains alphabetically by train name
    public static List<Train> sortByTrainName(List<Train> trains) {

        List<Train> sortedTrains = new ArrayList<>(trains);

        sortedTrains.sort(
                Comparator.comparing(
                        Train::getTrainName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return sortedTrains;
    }

    // Display sorted trains
    public static void displayTrains(List<Train> trains) {

        if (trains == null || trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        for (Train train : trains) {
            System.out.println(train);
        }
    }
}