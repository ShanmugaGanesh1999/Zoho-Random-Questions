package FlightTicketBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Assumptions,
// 1. unique flight no.
// 2. 6 business and 12 economy 
// 3. there are 6 seats in a*row(1 st row-business,2 nd&3 rd-economy)
// 4. base price economy-1000*and business-2000 
// 5. in a single booking any no.of tickets can be booked*in single class 
// 6. unique booking id 
// 7. surge price,increase economy-100, business-200 irrespective of cancellation per flight 
// 8. meal option 200 per*person must be for all ticket or none 
// 9. cancellation is allowed for booking*id but with the fee of 200 per seat

// Functions,
// 1. handle booking
// 2. handle cancellation
// 3. print all available seats for each flight
// 4. print seat no. for which meal is allowed
// 5. print individual and flight summary for all booking

public class flightTicketBooking {

    static Map<Integer, booking> bookingMap = new HashMap<>();
    static Map<Integer, flight> flightMap = new HashMap<>();

    static int aStart = 0, aStop = 17, bStart = 0, bStop = 5, eStart = 6, eStop = 17;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.print(
                    "\n1-Create Flight\n2-Book Flight\n3-Cancel flight\n4-Available Seats\n5-Seats with Meal\n6-Quit\n->");
            choice = scanner.nextInt();
            switch (choice) {
            case 1:
                createNewFlight(scanner);
                break;
            case 2:
                createBooking(scanner);
                break;
            case 3:
                cancelBooking(scanner);
                break;
            case 4:
                displayFlights();
                break;
            case 5:
                displaySeatsWithMeals();
                break;
            default:
                break;
            }
        } while (choice != 6);
        scanner.close();
    }

    private static void displaySeatsWithMeals() {
        if (bookingMap.size() == 0) {
            System.out.println("No meal was choosen");
        } else {
            System.out.println("Flight No.\tSeats with meals");
            for (booking booking : bookingMap.values()) {
                flight flight = flightMap.get(booking.flightId);
                System.out.print(flight.flightId + "\t\t");
                for (int i = aStart; i < aStop; i++) {
                    if (flight.meal[i] == 1)
                        System.out.print((i + 1) + ", ");
                }
            }
        }
    }

    private static void cancelBooking(Scanner scanner) {
        while (true) {
            displayBookings();
            System.out.print("Choose a correct booking Number\n0-Quit\n->");
            int bookingNo = scanner.nextInt();
            if (bookingNo != 0) {
                booking curBooking = bookingMap.get(bookingNo);
                if (curBooking != null) {
                    System.out.print("Are you sure want to cancel *(charges of 200 per seat)?\n1-Yes\n2-No\n->");
                    int option = scanner.nextInt();
                    if (option == 1) {
                        int totalCost = 0, charges = 0;
                        flight chooseFlight = flightMap.get(curBooking.flightId);
                        char clas = curBooking.sClass;
                        int bookedSeat = curBooking.totalSeats;
                        char meal = curBooking.meal;
                        if (clas == 'B')
                            totalCost += (bookedSeat * 2000);
                        else if (clas == 'E')
                            totalCost += (bookedSeat * 1000);
                        if (meal == 'Y')
                            totalCost += (bookedSeat * 200);
                        charges += (bookedSeat * 200);
                        curBooking.cancelBooking();
                        for (int i : curBooking.seats)
                            chooseFlight.cancelTicket(i);
                        chooseFlight.increaseSeats(clas, bookedSeat);
                        System.out.printf(
                                "Your total cost is %d\nCancellation fee is %d\nAmount you will receive = %d\n",
                                totalCost, charges, totalCost - charges);
                        System.out.println(".....Your ticket is successfully cancelled.....");
                        displayBookings();
                    }
                    break;
                } else {
                    System.out.print(
                            "No such booking numbr found\nChoose from first!\nWant to try again?\n1-Yes\n2-No\n->");
                    int option = scanner.nextInt();
                    if (option == 2)
                        break;
                }
            } else
                break;
        }
    }

    private static void createBooking(Scanner scanner) {
        boolean loop = true;
        while (loop) {
            displayFlights();
            System.out.print("Choose a correct flight Number\n1-View All Bookings\n0-Quit\n->");
            int flightNo = scanner.nextInt();
            if (flightNo == 0)
                loop = false;
            else if (flightNo == 1)
                displayBookings();
            else {
                flight chooseFlight = flightMap.get(flightNo);
                if (chooseFlight != null) {
                    int totalCost = 0, numberOfSeats = -1;
                    ArrayList<Integer> seatList = new ArrayList<>();
                    System.out.printf("No of seats available in business is %d and in economy is %d\n",
                            chooseFlight.bSeats, chooseFlight.eSeats);
                    System.out.print("\nChoose 1-business or 2-economy\n->");
                    int clas = scanner.nextInt();
                    if (clas == 1) {
                        availableSeats(chooseFlight, bStart, bStop);
                        System.out.print("\nChoose no. of seats to book\n->");
                        numberOfSeats = scanner.nextInt();
                        if (numberOfSeats <= chooseFlight.bSeats) {
                            for (int i = 0; i < numberOfSeats; i++) {
                                System.out.printf("Select seat %d ->", i + 1);
                                int seat = scanner.nextInt() - 1;
                                if (chooseFlight.isAvailable(seat) && seat <= bStop && seat >= bStart
                                        && !seatList.contains(seat)) {
                                    seatList.add(seat);
                                } else {
                                    System.out.printf("Seat Number %d is not available\n", (seat + 1));
                                    i--;
                                }
                            }
                        } else {
                            System.out.printf(
                                    "Only %d seats available in business class\nChoose from first!\nWant to try again?\n1-Yes\n2-No\n->",
                                    chooseFlight.eSeats);
                            numberOfSeats = -1;
                            int option = scanner.nextInt();
                            if (option == 2)
                                break;
                        }
                    } else if (clas == 2) {
                        availableSeats(chooseFlight, eStart, eStop);
                        System.out.print("\nChoose no. of seats to book\n->");
                        numberOfSeats = scanner.nextInt();
                        if (numberOfSeats <= chooseFlight.eSeats) {
                            for (int i = 0; i < numberOfSeats; i++) {
                                System.out.printf("Select seat %d ->", i + 1);
                                int seat = scanner.nextInt() - 1;
                                if (chooseFlight.isAvailable(seat) && seat <= eStop && seat >= eStart
                                        && !seatList.contains(seat)) {
                                    seatList.add(seat);
                                } else {
                                    System.out.printf("Seat Number %d is not available\n", (seat + 1));
                                    i--;
                                }
                            }
                        } else {
                            System.out.printf(
                                    "Only %d seats available in economy class\nChoose from first!\nWant to try again?\n1-Yes\n2-No\n->",
                                    chooseFlight.eSeats);
                            numberOfSeats = -1;
                            int option = scanner.nextInt();
                            if (option == 2)
                                break;
                        }
                    }
                    if (numberOfSeats != -1) {
                        System.out.print("\nWant to have meals for all the pasengers?\n1-Yes\n2-No\n->");
                        int isMeal = scanner.nextInt(), k = 0;
                        if (isMeal == 1)
                            totalCost += (numberOfSeats * 200);
                        int[] seatArray = new int[seatList.size()];
                        for (Integer seat : seatList) {
                            seatArray[k++] = seat;
                            chooseFlight.bookSeat(seat);
                            if (isMeal == 1)
                                chooseFlight.bookMeal(seat);
                            if (clas == 1)
                                totalCost += 2000 + (chooseFlight.surgePrice * 200);
                            else if (clas == 2)
                                totalCost += 1000 + (chooseFlight.surgePrice * 100);
                        }
                        booking newBooking = new booking(chooseFlight.flightId, clas, numberOfSeats, isMeal, seatArray);
                        bookingMap.put(newBooking.bookingId, newBooking);
                        chooseFlight.surgePrice++;
                        System.out.println(".....Successfully booked.....\nTotal Cost is " + totalCost);
                    }
                    displayBookings();
                    totalCost = 0;
                    seatList.clear();
                    break;
                } else {
                    System.out.print("There is no such flight\nWant to book again?\n1-Yes\n2-No\n->");
                    int option = scanner.nextInt();
                    if (option == 2)
                        break;
                }
            }
        }
    }

    public static void displayBookings() {
        if (bookingMap.size() == 0) {
            System.out.println("No Booking have been made");
        } else {
            System.out.println("Booking No.\tOperation\tFlight No.\tSeat Class\tTotal Seats\tMeal Pref");
            for (booking booking : bookingMap.values()) {
                System.out.print(booking.bookingId + "\t" + booking.operation + "\t" + booking.flightId + "\t"
                        + booking.sClass + "\t" + booking.totalSeats + "\t" + booking.meal + "\n");
            }
        }
    }

    public static void createNewFlight(Scanner scanner) {
        System.out.print("1-Create New Flight\n2-View All Flight\n->");
        int option = scanner.nextInt();
        if (option == 1) {
            flight newFlight = new flight();
            flightMap.put(newFlight.flightId, newFlight);
        }
        displayFlights();
    }

    public static void displayFlights() {
        if (flightMap.size() == 0) {
            System.out.println("No Flight has been created");
        } else {
            System.out.println("Flight No.\tAvailable Seats");
            for (flight flight : flightMap.values()) {
                System.out.print(flight.flightId + "\t\t");
                availableSeats(flight, aStart, aStop);
                System.out.println();
            }
        }
    }

    private static void availableSeats(flight flight, int start, int stop) {
        for (int i = start; i <= stop; i++) {
            if (flight.seat[i] == 0)
                System.out.print((i + 1) + ", ");
        }
    }
}