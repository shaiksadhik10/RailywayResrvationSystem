

package com.railway;

import com.railway.model.Passenger;
import com.railway.model.Train;
import com.railway.service.RailwayService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RailwayService service = new RailwayService();

        // Load previously saved data
        service.loadData();

        boolean running = true;

        while (running) {

            System.out.println("\n===== RAILWAY RESERVATION SYSTEM =====");
            System.out.println("1. Add Train");
            System.out.println("2. Display All Trains");
            System.out.println("3. Search Train");
            System.out.println("4. Add Passenger");
            System.out.println("5. Display Passengers");
            System.out.println("6. Book Ticket");
            System.out.println("7. Cancel Ticket");
            System.out.println("8. Display Tickets");
            System.out.println("9. Sort Trains By Name");
            System.out.println("10. Save Data");
            System.out.println("0. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1: {
                    System.out.print("Train number: ");
                    int number = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Train name: ");
                    String name = sc.nextLine();

                    System.out.print("Source: ");
                    String source = sc.nextLine();

                    System.out.print("Destination: ");
                    String destination = sc.nextLine();

                    System.out.print("Available seats: ");
                    int seats = sc.nextInt();

                    if (number <= 0 || seats < 0) {
                        System.out.println(
                                "Train number must be positive and seats cannot be negative.");
                        break;
                    }

                    service.addTrain(new Train(
                            number, name, source, destination, seats));
                    break;
                }

                case 2:
                    service.displayAllTrains();
                    break;

                case 3: {
                    System.out.print("Enter train number: ");
                    int searchNumber = sc.nextInt();

                    Train found = service.findTrain(searchNumber);

                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Train not found.");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Passenger ID: ");
                    int passengerId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Passenger name: ");
                    String passengerName = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Gender: ");
                    String gender = sc.nextLine();

                    if (passengerId <= 0 || age < 0) {
                        System.out.println(
                                "Passenger ID must be positive and age cannot be negative.");
                        break;
                    }

                    service.addPassenger(new Passenger(
                            passengerId, passengerName, age, gender));
                    break;
                }

                case 5:
                    service.displayAllPassengers();
                    break;

                case 6: {
                    System.out.print("Passenger ID: ");
                    int pId = sc.nextInt();

                    System.out.print("Train number: ");
                    int trainNumber = sc.nextInt();

                    System.out.print("Number of seats: ");
                    int bookedSeats = sc.nextInt();

                    System.out.print("Total fare: ");
                    double fare = sc.nextDouble();

                    service.bookTicket(
                            pId, trainNumber, bookedSeats, fare);
                    break;
                }

                case 7: {
                    System.out.print("Ticket ID to cancel: ");
                    int ticketId = sc.nextInt();

                    service.cancelTicket(ticketId);
                    break;
                }

                case 8:
                    service.displayAllTickets();
                    break;

                case 9:
                    service.sortTrainsByName();
                    break;

                case 10:
                    service.saveData();
                    break;

                case 0:
                    service.saveData();
                    running = false;
                    System.out.println(
                            "Thank you for using the Railway Reservation System!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}