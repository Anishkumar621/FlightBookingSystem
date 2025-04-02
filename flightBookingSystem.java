

import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class flightBookingSystem {
    private String flightNumber;
    private String sourceLocation;
    private String destination;
    private String[] namesOfPersons;
    private int numberOfTickets;
    private double ticketFare;
    private boolean isPreviousCustomer;
    private String bookingId;

    public void inputFlightData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Flight Number: ");
        flightNumber = scanner.nextLine();
        System.out.print("Enter Source Location: ");
        sourceLocation = scanner.nextLine();
        System.out.print("Enter Destination: ");
        destination = scanner.nextLine();
        System.out.print("Enter Name of Persons : ");
        String namesInput = scanner.nextLine();
        namesOfPersons = namesInput.split(",");
        System.out.print("Enter Number of Tickets: ");
        numberOfTickets = scanner.nextInt();
        System.out.print("Enter Ticket Fare: ");
        ticketFare = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Are you a previous customer? (true/false): ");
        isPreviousCustomer = scanner.nextBoolean();

        bookingId = UUID.randomUUID().toString();
    }

    public double calculateTicketAmount() {
        double ticketAmount = numberOfTickets * ticketFare;
        double tax = 0.18 * ticketAmount;
        double discount = 0.05 * ticketAmount;
        if (isPreviousCustomer) {
            discount += 0.02 * ticketAmount;
        }
        return ticketAmount + tax - discount;
    }

    public void displayAvailableAirlines() {
        System.out.println("\nAvailable Indian Airlines:");
        HashMap<String, String[]> airlines = new HashMap<>();
        airlines.put("Delhi", new String[]{"Air India", "IndiGo"});
        airlines.put("Mumbai", new String[]{"SpiceJet", "IndiGo"});
        airlines.put("Ranchi", new String[]{"Air India", "SpiceJet", "IndiGo"});

        String[] availableAirlines = airlines.getOrDefault(destination, new String[]{"Airlines information not available for this destination."});
        for (int i = 0; i < availableAirlines.length; i++) {
            System.out.println((i + 1) + ". " + availableAirlines[i]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the name of the airline you want to choose: ");
        String choice = scanner.nextLine().toLowerCase();

        boolean airlineFound = false;
        for (String airline : availableAirlines) {
            if (airline.toLowerCase().equals(choice)) {
                bookTicket(airline);
                airlineFound = true;
                break;
            }
        }
        if (!airlineFound) {
            System.out.println("Invalid choice.");
        }
    }

    private void bookTicket(String airline) {
        System.out.println("\nBooking confirmed with " + airline);
        System.out.println("Booking ID: " + bookingId);
    }

    public void printTicketDetails() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Source Location: " + sourceLocation);
        System.out.println("Destination: " + destination);
        System.out.println("Name of Persons: " + String.join(", ", namesOfPersons));
        System.out.println("Number of Tickets: " + numberOfTickets);
        System.out.println("Ticket Fare: " + ticketFare);
        System.out.printf("Total Amount Payable: %.2f%n", calculateTicketAmount());
    }

    public static void main(String[] args) {
        flightBookingSystem flight = new flightBookingSystem();
        flight.inputFlightData();
        flight.displayAvailableAirlines();
        flight.printTicketDetails();
    }
}