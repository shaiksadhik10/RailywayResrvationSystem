
package com.railway.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private int ticketId;
    private Passenger passenger;
    private Train train;
    private int seatsBooked;
    private double fare;

    private List<Seat> bookedSeats = new ArrayList<>();

    public Ticket() {
    }

    public Ticket(int ticketId, Passenger passenger, Train train,
                  int seatsBooked, double fare) {

        this.ticketId = ticketId;
        this.passenger = passenger;
        this.train = train;
        this.seatsBooked = seatsBooked;
        this.fare = fare;
        this.bookedSeats = new ArrayList<>();
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId
                + "\nPassenger: " + passenger
                + "\nTrain: " + train
                + "\nSeats Booked: " + seatsBooked
                + "\nFare: " + fare
                + "\nAllocated Seats: " + bookedSeats;
    }
}
