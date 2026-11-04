package com.railway.model;

public class Seat {

    private int seatNumber;
    private String coachNumber;
    private boolean booked;

    public Seat() {
    }

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public Seat(int seatNumber, String coachNumber) {
        this.seatNumber = seatNumber;
        this.coachNumber = coachNumber;
        this.booked = true;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCoachNumber() {
        return coachNumber;
    }

    public void setCoachNumber(String coachNumber) {
        this.coachNumber = coachNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return coachNumber + "-" + seatNumber
                + " - " + (booked ? "Booked" : "Available");
    }
}