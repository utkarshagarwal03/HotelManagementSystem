import java.util.Scanner;

public class HotelManagementSystem {


    static class Room {
        int roomNumber;
        String type;
        double price;
        boolean isAvailable;

        public Room(int roomNumber, String type, double price) {
            this.roomNumber = roomNumber;
            this.type = type;
            this.price = price;
            this.isAvailable = true;
        }

        public void bookRoom() {
            isAvailable = false;
        }

        public void releaseRoom() {
            isAvailable = true;
        }

        @Override
        public String toString() {
            return "Room " + roomNumber + " (" + type + ", $" + price + " per night) - " +
                   (isAvailable ? "Available" : "Booked");
        }
    }

 
    static class Booking {
        String customerName;
        Room room;
        int numberOfDays;

        public Booking(String customerName, Room room, int numberOfDays) {
            this.customerName = customerName;
            this.room = room;
            this.numberOfDays = numberOfDays;
        }

        public double calculateTotalCost() {
            return room.price * numberOfDays;
        }

        @Override
        public String toString() {
            return "Customer: " + customerName + ", Room: " + room.roomNumber +
                   ", Days: " + numberOfDays + ", Total Cost: $" + calculateTotalCost();
        }
    }


    static class Hotel {
        Room[] rooms = {
            new Room(101, "Single", 100),
            new Room(102, "Double", 150),
            new Room(103, "Suite", 250)
        };
        Booking[] bookings = new Booking[10]; 
        int bookingCount = 0;

        public void showAvailableRooms() {
            System.out.println("\nAvailable Rooms:");
            for (Room room : rooms) {
                if (room.isAvailable) {
                    System.out.println(room);
                }
            }
        }

        public boolean bookRoom(int roomNumber, String customerName, int numberOfDays) {
            for (Room room : rooms) {
                if (room.roomNumber == roomNumber && room.isAvailable) {
                    room.bookRoom();
                    if (bookingCount < bookings.length) {
                        bookings[bookingCount++] = new Booking(customerName, room, numberOfDays);
                        return true;
                    } else {
                        System.out.println("Booking limit reached.");
                        room.releaseRoom(); 
                        return false;
                    }
                }
            }
            return false; 
        }

        public void showBookingDetails(int roomNumber) {
            for (Booking booking : bookings) {
                if (booking != null && booking.room.roomNumber == roomNumber) {
                    System.out.println("\nBooking Details:");
                    System.out.println(booking);
                    return;
                }
            }
            System.out.println("No booking found for Room " + roomNumber + ".");
        }

        public boolean releaseRoom(int roomNumber) {
            for (Room room : rooms) {
                if (room.roomNumber == roomNumber && !room.isAvailable) {
                    room.releaseRoom();
                    System.out.println("Room " + roomNumber + " has been released.");
                    return true;
                }
            }
            return false; 
        }
    }

    
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Show Booking Details");
            System.out.println("4. Release a Room");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;

                case 2:
                    System.out.print("Enter Room Number to Book: ");
                    int roomToBook = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Number of Days: ");
                    int numberOfDays = scanner.nextInt();

                    if (hotel.bookRoom(roomToBook, customerName, numberOfDays)) {
                        System.out.println("Room " + roomToBook + " booked successfully!");
                    } else {
                        System.out.println("Room " + roomToBook + " is not available.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Room Number to Check Booking: ");
                    int checkRoomNumber = scanner.nextInt();
                    hotel.showBookingDetails(checkRoomNumber);
                    break;

                case 4:
                    System.out.print("Enter Room Number to Release: ");
                    int roomToRelease = scanner.nextInt();
                    if (!hotel.releaseRoom(roomToRelease)) {
                        System.out.println("Room " + roomToRelease + " is not currently booked.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
