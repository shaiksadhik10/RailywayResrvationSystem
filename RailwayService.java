package com.railway.service;

import com.railway.model.*;
import com.railway.util.JsonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RailwayService {

    private final Map<Integer, Train> trains = new HashMap<>();
    private final Map<Integer, Passenger> passengers = new HashMap<>();
    private final List<Ticket> tickets = new ArrayList<>();

    private int nextTicketId = 1;

    private static final String[] COACHES = {"S1", "S2", "S3"};
    private static final int SEATS_PER_COACH = 72;

    // Add train
    public void addTrain(Train train) {
        if (train == null) {
            System.out.println("Train cannot be null.");
            return;
        }

        trains.put(train.getTrainNumber(), train);
        System.out.println("Train added successfully.");
    }

    // Find train
    public Train findTrain(int trainNumber) {
        return trains.get(trainNumber);
    }

    // Display all trains
    public void displayAllTrains() {
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        for (Train train : trains.values()) {
            System.out.println(train);
        }
    }

    // Add passenger
    public void addPassenger(Passenger passenger) {
        if (passenger == null) {
            System.out.println("Passenger cannot be null.");
            return;
        }

        passengers.put(passenger.getPassengerId(), passenger);
        System.out.println("Passenger added successfully.");
    }

    // Find passenger
    public Passenger findPassenger(int passengerId) {
        return passengers.get(passengerId);
    }

    // Display passengers
    public void displayAllPassengers() {
        if (passengers.isEmpty()) {
            System.out.println("No passengers available.");
            return;
        }

        for (Passenger passenger : passengers.values()) {
            System.out.println(passenger);
        }
    }

    // Book ticket with automatic coach allocation
    public synchronized void bookTicket(
            int passengerId,
            int trainNumber,
            int seatsBooked,
            double fare) {

        Passenger passenger = passengers.get(passengerId);
        Train train = trains.get(trainNumber);

        if (passenger == null) {
            System.out.println("Passenger not found.");
            return;
        }

        if (train == null) {
            System.out.println("Train not found.");
            return;
        }

        if (seatsBooked <= 0 || fare < 0) {
            System.out.println("Invalid seats or fare.");
            return;
        }

        if (seatsBooked > train.getAvailableSeats()) {
            System.out.println("Not enough available seats.");
            return;
        }

        // Allocate seats from S1, S2 and S3
        List<Seat> allocatedSeats =
                allocateSeats(trainNumber, seatsBooked);

        if (allocatedSeats.size() != seatsBooked) {
            System.out.println("Requested seats are not available.");
            return;
        }

        Ticket ticket = new Ticket(
                nextTicketId,
                passenger,
                train,
                seatsBooked,
                fare
        );

        ticket.setBookedSeats(allocatedSeats);

        train.setAvailableSeats(
                train.getAvailableSeats() - seatsBooked
        );

        tickets.add(ticket);
        nextTicketId++;

        System.out.println("\n===== TICKET BOOKED SUCCESSFULLY =====");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Passenger: " + passenger.getPassengerName());
        System.out.println("Train: " + train.getTrainName());

        System.out.println("Allocated Coach and Seats:");

        for (Seat seat : allocatedSeats) {
            System.out.println(
                    seat.getCoachNumber() + " - Seat "
                    + seat.getSeatNumber()
            );
        }

        System.out.println("Total Seats: " + seatsBooked);
        System.out.println("Total Fare: " + fare);
        System.out.println("======================================");
    }

    // Find available seats
    private List<Seat> allocateSeats(int trainNumber, int count) {

        List<Seat> allocated = new ArrayList<>();

        for (String coach : COACHES) {

            for (int seatNo = 1;
                    seatNo <= SEATS_PER_COACH;
                    seatNo++) {

                boolean alreadyBooked = false;

                for (Ticket ticket : tickets) {

                    if (ticket.getTrain() == null ||
                            ticket.getTrain().getTrainNumber()
                                    != trainNumber) {
                        continue;
                    }

                    List<Seat> existingSeats =
                            ticket.getBookedSeats();

                    if (existingSeats == null) {
                        continue;
                    }

                    for (Seat seat : existingSeats) {

                        if (seat != null
                                && coach.equals(seat.getCoachNumber())
                                && seatNo == seat.getSeatNumber()) {

                            alreadyBooked = true;
                            break;
                        }
                    }

                    if (alreadyBooked) {
                        break;
                    }
                }

                if (!alreadyBooked) {

                    allocated.add(new Seat(seatNo, coach));

                    if (allocated.size() == count) {
                        return allocated;
                    }
                }
            }
        }

        return allocated;
    }

    // Cancel ticket and release its seats
    public synchronized void cancelTicket(int ticketId) {

        for (int i = 0; i < tickets.size(); i++) {

            Ticket ticket = tickets.get(i);

            if (ticket.getTicketId() == ticketId) {

                Train train = ticket.getTrain();

                if (train != null) {
                    train.setAvailableSeats(
                            train.getAvailableSeats()
                            + ticket.getSeatsBooked()
                    );
                }

                tickets.remove(i);

                System.out.println("Ticket cancelled successfully.");
                System.out.println("Released seats: "
                        + ticket.getBookedSeats());

                return;
            }
        }

        System.out.println("Ticket not found.");
    }

    // Display tickets
    public void displayAllTickets() {

        if (tickets.isEmpty()) {
            System.out.println("No tickets available.");
            return;
        }

        System.out.println("\n===== ALL TICKETS =====");

        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    // Sort trains by name
    public void sortTrainsByName() {

        List<Train> trainList =
                new ArrayList<>(trains.values());

        trainList.sort(
                Comparator.comparing(
                        Train::getTrainName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        System.out.println("\nTrains sorted by name:");

        for (Train train : trainList) {
            System.out.println(train);
        }
    }

    // Save data
    public void saveData() {

        JsonUtil.save(
                new ArrayList<>(trains.values()),
                "trains.json"
        );

        JsonUtil.save(
                new ArrayList<>(passengers.values()),
                "passengers.json"
        );

        JsonUtil.save(tickets, "tickets.json");

        System.out.println("Railway data saved successfully.");
    }

    // Load data
    public void loadData() {

        File trainFile = new File("trains.json");
        File passengerFile = new File("passengers.json");
        File ticketFile = new File("tickets.json");

        if (trainFile.exists()) {

            Train[] savedTrains =
                    JsonUtil.load("trains.json", Train[].class);

            if (savedTrains != null) {
                for (Train train : savedTrains) {
                    trains.put(train.getTrainNumber(), train);
                }
            }
        }

        if (passengerFile.exists()) {

            Passenger[] savedPassengers =
                    JsonUtil.load("passengers.json", Passenger[].class);

            if (savedPassengers != null) {
                for (Passenger passenger : savedPassengers) {
                    passengers.put(
                            passenger.getPassengerId(), passenger);
                }
            }
        }

        if (ticketFile.exists()) {

            Ticket[] savedTickets =
                    JsonUtil.load("tickets.json", Ticket[].class);

            if (savedTickets != null) {

                tickets.clear();

                for (Ticket ticket : savedTickets) {

                    // Handle tickets without saved seat lists
                    if (ticket.getBookedSeats() == null) {
                        ticket.setBookedSeats(new ArrayList<Seat>());
                    }

                    tickets.add(ticket);

                    if (ticket.getTicketId() >= nextTicketId) {
                        nextTicketId = ticket.getTicketId() + 1;
                    }
                }
            }
        }

        System.out.println("Railway data loaded successfully.");
    }

    // Get train data
    public Map<Integer, Train> getTrains() {
        return trains;
    }

    // Get passenger data
    public Map<Integer, Passenger> getPassengers() {
        return passengers;
    }

    // Get ticket data
    public List<Ticket> getTickets() {
        return tickets;
    }
}
