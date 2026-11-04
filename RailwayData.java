package com.railway.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.railway.model.Passenger;
import com.railway.model.Train;

public class RailwayData {

    // Store trains using train number
    private Map<Integer, Train> trainMap = new HashMap<>();

    // Store passengers using passenger ID
    private Map<Integer, Passenger> passengerMap = new HashMap<>();

    // Store unique train numbers
    private Set<Integer> trainIds = new HashSet<>();

    // Add Train
    public void addTrain(Train train) {
        trainMap.put(train.getTrainNumber(), train);
        trainIds.add(train.getTrainNumber());

        System.out.println("Train added to collection.");
    }

    // Add Passenger
    public void addPassenger(Passenger passenger) {
        passengerMap.put(passenger.getPassengerId(), passenger);

        System.out.println("Passenger added to collection.");
    }

    // Find Train
    public Train findTrain(int trainNumber) {
        return trainMap.get(trainNumber);
    }

    // Find Passenger
    public Passenger findPassenger(int passengerId) {
        return passengerMap.get(passengerId);
    }

    // View all trains
    public void viewAllTrains() {
        if (trainMap.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        System.out.println("\n===== ALL TRAINS =====");

        for (Train train : trainMap.values()) {
            System.out.println(train);
        }
    }

    // View all passengers
    public void viewAllPassengers() {
        if (passengerMap.isEmpty()) {
            System.out.println("No passengers available.");
            return;
        }

        System.out.println("\n===== ALL PASSENGERS =====");

        for (Passenger passenger : passengerMap.values()) {
            System.out.println(passenger);
        }
    }

    // Get train list
    public List<Train> getTrainList() {
        return new ArrayList<>(trainMap.values());
    }

    // Get passenger list
    public List<Passenger> getPassengerList() {
        return new ArrayList<>(passengerMap.values());
    }

    // Get number of trains
    public int getTrainCount() {
        return trainMap.size();
    }

    // Get number of passengers
    public int getPassengerCount() {
        return passengerMap.size();
    }
}