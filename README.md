
---

### **Code Explanation**

#### **1. Class Definitions**
```java
public class HotelManagementSystem {
```
- This is the **main class** where everything happens. It contains helper classes (`Room`, `Booking`, and `Hotel`) and the `main` method for running the program.

---

#### **2. Room Class**
```java
static class Room {
    int roomNumber;
    String type;
    double price;
    boolean isAvailable;
```
- Defines the **Room** class.
  - `roomNumber`: Stores the room number (e.g., 101).
  - `type`: Stores the type of room (e.g., "Single", "Double").
  - `price`: Stores the price per night.
  - `isAvailable`: Tracks if the room is available (`true`) or booked (`false`).

---

```java
    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true; // Rooms start as available
    }
```
- This is the **constructor** for the `Room` class. It initializes the room details like the room number, type, price, and marks the room as available by default.

---

```java
    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }
```
- `bookRoom()`: Marks the room as **not available**.
- `releaseRoom()`: Marks the room as **available** again.

---

```java
    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ", $" + price + " per night) - " +
               (isAvailable ? "Available" : "Booked");
    }
```
- `toString()` is used to **convert a room object to a readable string** when printed. For example:
  - **Available Room:** `Room 101 (Single, $100 per night) - Available`
  - **Booked Room:** `Room 101 (Single, $100 per night) - Booked`

---

#### **3. Booking Class**
```java
static class Booking {
    String customerName;
    Room room;
    int numberOfDays;
```
- Defines the **Booking** class.
  - `customerName`: Name of the person who booked the room.
  - `room`: The `Room` object associated with the booking.
  - `numberOfDays`: Number of days the room is booked for.

---

```java
    public Booking(String customerName, Room room, int numberOfDays) {
        this.customerName = customerName;
        this.room = room;
        this.numberOfDays = numberOfDays;
    }
```
- The **constructor** initializes booking details with the customer name, room, and number of days.

---

```java
    public double calculateTotalCost() {
        return room.price * numberOfDays;
    }
```
- Calculates the total cost for the booking by multiplying the room's price per night by the number of days.

---

```java
    @Override
    public String toString() {
        return "Customer: " + customerName + ", Room: " + room.roomNumber +
               ", Days: " + numberOfDays + ", Total Cost: $" + calculateTotalCost();
    }
```
- Converts a `Booking` object into a readable string like:
  - `Customer: John, Room: 101, Days: 3, Total Cost: $300`

---

#### **4. Hotel Class**
```java
static class Hotel {
    Room[] rooms = {
        new Room(101, "Single", 100),
        new Room(102, "Double", 150),
        new Room(103, "Suite", 250)
    };
    Booking[] bookings = new Booking[10];
    int bookingCount = 0;
```
- **Hotel** class manages rooms and bookings.
  - `rooms`: An array holding all the rooms available in the hotel.
  - `bookings`: A fixed-size array (10 bookings max) to store all bookings.
  - `bookingCount`: Keeps track of how many bookings have been made.

---

```java
    public void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }
```
- **Displays all available rooms** by looping through the `rooms` array and printing only those that are marked as `isAvailable`.

---

```java
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
```
- **Books a room** by:
  1. Finding a room with the given number that is available.
  2. Creating a new `Booking` object if there’s space in the `bookings` array.
  3. Returns `true` if booking is successful, otherwise `false`.

---

```java
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
```
- Displays booking details for a specific room by searching the `bookings` array.

---

```java
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
```
- Releases (frees up) a booked room by marking it as available.

---

#### **5. Main Method**
```java
public static void main(String[] args) {
```
- The entry point of the program where user interaction happens.

---

```java
        while (true) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Show Booking Details");
            System.out.println("4. Release a Room");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
```
- Displays a menu for the user to interact with the hotel system.

---

```java
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;

                case 2:
                    System.out.print("Enter Room Number to Book: ");
                    int roomToBook = scanner.nextInt();
                    scanner.nextLine();
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
```
- Allows the user to **book a room** by:
  1. Asking for room number, customer name, and days.
  2. Calling the `bookRoom()` method.

---

```java
                case 3:
                    System.out.print("Enter Room Number to Check Booking: ");
                    int checkRoomNumber = scanner.nextInt();
                    hotel.showBookingDetails(checkRoomNumber);
                    break;
```
- Allows the user to **view booking details** for a specific room.

---

```java
                case 4:
                    System.out.print("Enter Room Number to Release: ");
                    int roomToRelease = scanner.nextInt();
                    if (!hotel.releaseRoom(roomToRelease)) {
                        System.out.println("Room " + roomToRelease + " is not currently booked.");
                    }
                    break;
```
- Lets the user **release a booked room**.

---

```java
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
```
- Exits the program when the user selects option 5.

---
