package com.railway.service;

import com.railway.model.Passenger;
import com.railway.model.Seat;
import com.railway.model.Ticket;
import com.railway.model.Train;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReservationService {

    private final List<Ticket> tickets = new ArrayList<>();
    private int nextTicketId = 1;

    private static final String[] COACHES = {
        "S1", "S2", "S3"
    };

    private static final int SEATS_PER_COACH = 72;

    // Book a ticket
    public synchronized Ticket bookTicket(
            Train train,
            Passenger passenger,
            int seatsBooked,
            double fare) {

        if (train == null || passenger == null) {
            System.out.println("Train and passenger are required.");
            return null;
        }

        if (seatsBooked <= 0 || fare < 0) {
            System.out.println("Invalid number of seats or fare.");
            return null;
        }

        if (seatsBooked > COACHES.length * SEATS_PER_COACH) {
            System.out.println("Maximum 216 seats can be booked.");
            return null;
        }

        if (seatsBooked > train.getAvailableSeats()) {
            System.out.println("Not enough available seats.");
            return null;
        }

        // Find available seats
        List<Seat> allocatedSeats = allocateSeats(train, seatsBooked);

        if (allocatedSeats.size() != seatsBooked) {
            System.out.println("Requested seats are not available.");
            return null;
        }

        // Create ticket
        Ticket ticket = new Ticket(
                nextTicketId++,
                passenger,
                train,
                seatsBooked,
                fare
        );

        ticket.setBookedSeats(allocatedSeats);

        // Reduce available train seats
        train.setAvailableSeats(
                train.getAvailableSeats() - seatsBooked
        );

        tickets.add(ticket);

        System.out.println("\nTicket booked successfully!");
        System.out.println(ticket);

        return ticket;
    }

    // Allocate coach and seat numbers
    private List<Seat> allocateSeats(Train train, int count) {

        List<Seat> allocated = new ArrayList<>();

        for (String coach : COACHES) {

            for (int seatNo = 1; seatNo <= SEATS_PER_COACH; seatNo++) {

                boolean alreadyBooked = false;

                for (Ticket ticket : tickets) {

                    if (ticket.getTrain().getTrainNumber()
                            == train.getTrainNumber()) {

                        for (Seat seat : ticket.getBookedSeats()) {

                            if (coach.equals(seat.getCoachNumber())
                                    && seatNo == seat.getSeatNumber()) {

                                alreadyBooked = true;
                                break;
                            }
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

    // Display all tickets
    public synchronized void viewAllTickets() {

        if (tickets.isEmpty()) {
            System.out.println("No tickets available.");
            return;
        }

        System.out.println("\n===== ALL TICKETS =====");

        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    // Cancel ticket
    public synchronized void cancelTicket(int ticketId) {

        Iterator<Ticket> iterator = tickets.iterator();

        while (iterator.hasNext()) {

            Ticket ticket = iterator.next();

            if (ticket.getTicketId() == ticketId) {

                // Restore available seats
                Train train = ticket.getTrain();

                train.setAvailableSeats(
                        train.getAvailableSeats()
                        + ticket.getSeatsBooked()
                );

                iterator.remove();

                System.out.println(
                        "Ticket " + ticketId
                        + " cancelled successfully."
                );

                System.out.println("Seats released: "
                        + ticket.getBookedSeats());

                return;
            }
        }

        System.out.println("Ticket not found: " + ticketId);
    }

    // Get ticket count
    public synchronized int getTicketCount() {
        return tickets.size();
    }
}