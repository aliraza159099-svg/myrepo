package CodeAlpha;

import java.util.*;
import java.io.*;

// Requirement: Add room categorization (e.g., Standard, Deluxe, Suite)
class Room {
    int roomNo;
    String category;
    double price;
    boolean booked;

    Room(int roomNo, String category, double price, boolean booked) {
        this.roomNo = roomNo;
        this.category = category;
        this.price = price;
        this.booked = booked;
    }

    public String toFile() {
        return roomNo + "," + category + "," + price + "," + booked;
    }
}

// Requirement: Implement booking details view
class Booking {
    String name;
    int roomNo;
    String category;
    double amount;

    Booking(String name, int roomNo, String category, double amount) {
        this.name = name;
        this.roomNo = roomNo;
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Guest: " + name + " | Room: " + roomNo + " (" + category + ") | Amount Paid: $" + amount;
    }
}

// Requirement: Use OOP + database/File I/O for storing bookings and availability
class Hotel {
    List<Room> rooms = new ArrayList<>();

    Hotel() {
        loadRooms();
    }

    void loadRooms() {
        File file = new File("rooms.txt");
        if (!file.exists()) {
            // Requirement: Practical hotel having 50 rooms (Room numbers 1 to 50)
            for (int i = 1; i <= 25; i++) rooms.add(new Room(i, "Standard", 100.0, false));
            for (int i = 26; i <= 40; i++) rooms.add(new Room(i, "Deluxe", 180.0, false));
            for (int i = 41; i <= 50; i++) rooms.add(new Room(i, "Suite", 300.0, false));
            saveRooms();
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                rooms.add(new Room(Integer.parseInt(d[0]), d[1], Double.parseDouble(d[2]), Boolean.parseBoolean(d[3])));
            }
        } catch (Exception e) {
            System.out.println("System Error: Could not load room database.");
        }
    }

    void saveRooms() {
        try (PrintWriter pw = new PrintWriter("rooms.txt")) {
            for (Room r : rooms) pw.println(r.toFile());
        } catch (Exception e) {
            System.out.println("System Error: Could not save room status.");
        }
    }

    // Requirement: Search hotel rooms
    void searchRooms(String category) {
        System.out.println("\n--- Available Rooms Directory ---");
        boolean found = false;
        for (Room r : rooms) {
            if (!r.booked && (category.equalsIgnoreCase("All") || r.category.equalsIgnoreCase(category))) {
                System.out.println("Room No: " + r.roomNo + " | Category: " + r.category + " | Price: $" + r.price);
                found = true;
            }
        }
        if (!found) System.out.println("No rooms available matching your request.");
    }

    // Requirement: Book room and Implement payment simulation
    void bookRoom(String name, int roomNo, Scanner sc) {
        for (Room r : rooms) {
            if (r.roomNo == roomNo && !r.booked) {
                System.out.println(">>> Payment Simulation <<<");
                System.out.println("Total Amount Due: $" + r.price);
                System.out.print("Authorize payment? (yes/no): ");
                String pay = sc.next();

                if (pay.equalsIgnoreCase("yes")) {
                    r.booked = true;
                    saveRooms();
                    saveBooking(new Booking(name, r.roomNo, r.category, r.price));
                    System.out.println("Payment Confirmed. Room " + roomNo + " booked successfully.");
                } else {
                    System.out.println("Payment Declined. Booking aborted.");
                }
                return;
            }
        }
        System.out.println("Error: Room " + roomNo + " is either unavailable or does not exist.");
    }

    // Requirement: Cancel reservations
    void cancelBooking(int roomNo) {
        for (Room r : rooms) {
            if (r.roomNo == roomNo && r.booked) {
                r.booked = false;
                saveRooms();
                System.out.println("Success: Reservation for Room " + roomNo + " has been cancelled.");
                return;
            }
        }
        System.out.println("Error: No active booking found for Room " + roomNo + ".");
    }

    // Requirement: Booking details view
    void viewBookings() {
        System.out.println("\n--- Current Hotel Reservations ---");
        try (Scanner sc = new Scanner(new File("bookings.txt"))) {
            while (sc.hasNextLine()) System.out.println(sc.nextLine());
        } catch (Exception e) {
            System.out.println("No booking records exist yet.");
        }
    }

    void saveBooking(Booking b) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("bookings.txt", true))) {
            pw.println(b);
        } catch (Exception e) {
            System.out.println("System Error: Failed to record booking details.");
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n=== HOTEL MANAGEMENT SYSTEM ===");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation (Book)");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View All Booking Details");
            System.out.println("5. Exit System");
            System.out.print("Action: ");

            int ch = sc.hasNextInt() ? sc.nextInt() : 0;
            if (ch == 0) { sc.next(); continue; }

            switch (ch) {
                case 1:
                    System.out.print("Search category (Standard/Deluxe/Suite) or 'All': ");
                    hotel.searchRooms(sc.next());
                    break;
                case 2:
                    System.out.print("Enter Guest Name: ");
                    String name = sc.next();
                    System.out.print("Enter Room Number: ");
                    hotel.bookRoom(name, sc.nextInt(), sc);
                    break;
                case 3:
                    System.out.print("Enter Room Number to cancel: ");
                    hotel.cancelBooking(sc.nextInt());
                    break;
                case 4:
                    hotel.viewBookings();
                    break;
                case 5:
                    System.out.println("System Shutting Down...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
}
