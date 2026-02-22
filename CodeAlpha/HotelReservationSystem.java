package CodeAlpha;

import java.util.*;
import java.io.*;

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

    public String toString() {
        return "Customer: " + name +
               ", Room No: " + roomNo +
               ", Category: " + category +
               ", Paid: $" + amount;
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();

    Hotel() {
        loadRooms();
    }

    // Load or initialize rooms
    void loadRooms() {
        File file = new File("rooms.txt");

        if (!file.exists()) {
            int rn = 101;
            for (int i = 0; i < 10; i++)
                rooms.add(new Room(rn++, "Standard", 100, false));

            rn = 201;
            for (int i = 0; i < 10; i++)
                rooms.add(new Room(rn++, "Deluxe", 180, false));

            rn = 301;
            for (int i = 0; i < 10; i++)
                rooms.add(new Room(rn++, "Suite", 300, false));

            saveRooms();
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                rooms.add(new Room(
                        Integer.parseInt(d[0]),
                        d[1],
                        Double.parseDouble(d[2]),
                        Boolean.parseBoolean(d[3])
                ));
            }
        } catch (Exception e) {
            System.out.println("Error loading rooms.");
        }
    }

    void saveRooms() {
        try (PrintWriter pw = new PrintWriter("rooms.txt")) {
            for (Room r : rooms)
                pw.println(r.toFile());
        } catch (Exception e) {
            System.out.println("Error saving rooms.");
        }
    }

    // SEARCH AVAILABLE ROOMS
    void searchRooms(String category) {
        boolean found = false;
        System.out.println("\nAvailable " + category + " Rooms:");
        for (Room r : rooms) {
            if (!r.booked && r.category.equalsIgnoreCase(category)) {
                System.out.println("Room No: " + r.roomNo + " | Price: $" + r.price);
                found = true;
            }
        }
        if (!found)
            System.out.println("No available rooms.");
    }

    // BOOK ROOM WITH PAYMENT
    void bookRoom(String name, int roomNo, Scanner sc) {
        for (Room r : rooms) {
            if (r.roomNo == roomNo && !r.booked) {

                System.out.println("Room Price: $" + r.price);
                System.out.print("Confirm payment (yes/no): ");
                String pay = sc.next();

                if (pay.equalsIgnoreCase("yes")) {
                    r.booked = true;
                    saveRooms();
                    saveBooking(new Booking(name, r.roomNo, r.category, r.price));
                    System.out.println("Payment successful. Room booked!");
                } else {
                    System.out.println("Payment cancelled.");
                }
                return;
            }
        }
        System.out.println("Room not available or invalid room number.");
    }

    // CANCEL BOOKING
    void cancelBooking(int roomNo) {
        for (Room r : rooms) {
            if (r.roomNo == roomNo && r.booked) {
                r.booked = false;
                saveRooms();
                System.out.println("Reservation cancelled.");
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    // VIEW BOOKINGS
    void viewBookings() {
        System.out.println("\n--- Booking Details ---");
        try (Scanner sc = new Scanner(new File("bookings.txt"))) {
            while (sc.hasNextLine())
                System.out.println(sc.nextLine());
        } catch (Exception e) {
            System.out.println("No bookings available.");
        }
    }

    void saveBooking(Booking b) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("bookings.txt", true))) {
            pw.println(b);
        } catch (Exception e) {
            System.out.println("Error saving booking.");
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter category (Standard/Deluxe/Suite): ");
                    hotel.searchRooms(sc.next());
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = sc.next();
                    System.out.print("Enter room number to book: ");
                    hotel.bookRoom(name, sc.nextInt(), sc);
                    break;

                case 3:
                    System.out.print("Enter room number to cancel: ");
                    hotel.cancelBooking(sc.nextInt());
                    break;

                case 4:
                    hotel.viewBookings();
                    break;

                case 5:
                    System.out.println("System exited.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
11
