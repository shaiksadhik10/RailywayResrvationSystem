package com.railway.model;

public class Train {

    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int availableSeats;

    public Train() {
        // Required for JSON deserialization
    }

    public Train(int trainNumber, String trainName,
                 String source, String destination,
                 int availableSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Train Number: " + trainNumber
                + ", Name: " + trainName
                + ", Source: " + source
                + ", Destination: " + destination
                + ", Available Seats: " + availableSeats;
    }
}