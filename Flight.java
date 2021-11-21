import java.util.*;

class Customer {
	int bookingId;
	int flightNo;
	String seatClass;
	int seatCount;
	String allocatedSeats = "";
	char meal;
	int price;
	int surgeForEC = 0;
	int surgeForBC = 0;

	Customer(int id, int fno, String sClass, int count, char meal) {
		bookingId = id;
		flightNo = fno;
		seatClass = sClass;
		seatCount = count;
		this.meal = meal;
		price = this.calculatPrice();
	}

	void setAllocatedSeates(String S) {
		allocatedSeats = S;
	}

	String getAllocatedSeates() {
		return allocatedSeats;
	}

	int calculatPrice() {
		int P, mealCost = 0;
		if (meal == 'Y')
			mealCost = seatCount * 200;

		if (seatClass.equals("EC")) {
			P = (seatCount * 1000) + mealCost + surgeForEC;
			surgeForEC += 100;
		} else {
			P = (seatCount * 2000) + mealCost + surgeForBC;
			surgeForBC += 200;
		}
		return P;
	}

	int getPrice() {
		return price;
	}
}

public class Flight {
	static Scanner input = new Scanner(System.in);
	private static int bookingId = 1;
	static ArrayList<Customer> cus = new ArrayList<Customer>();
	static ArrayList<Flight> flightList = new ArrayList<>();
	int flightNo;
	int bclass = 6, eclass = 12;
	int totalseats = bclass + eclass;
	Customer availSeats[] = new Customer[19];
	int availEC = 12, availBC = 6;
	int totalCost = 0;

	public Flight(int flightNo) {
		this.flightNo = flightNo;
	}

	public String toString() {
		return String.valueOf(flightNo);
	}

	static void booking() {
		// System.out.println("Enter the Operation(B/C):");
		// char op = input.next().charAt(0);
		System.out.println("Enter Flight NO:");
		int flightno = input.nextInt();
		System.out.println("Enter the seat class (EC/BC):");
		String seatClass = input.next();
		System.out.println("Enter the no of seats(0<EC<=12,1<BC<=6):");
		int seatCount = input.nextInt();
		System.out.println("Do you want Meal(Y/N)?");
		char meal = input.next().charAt(0);
		Customer c = null;
		boolean flag = false;
		String alloc = "";
		Flight fl = null;
		for (Flight flt : flightList) {
			if (flt.flightNo == flightno) {
				fl = flt;
				break;
			}
		}

		if (seatClass.equals("BC") && seatCount <= fl.availBC) {
			c = new Customer(bookingId++, fl.flightNo, seatClass, seatCount, meal);
			fl.totalCost += c.getPrice();
			int count = 0;
			for (int i = 1; (i <= 6 && count < seatCount); i++) {
				if (fl.availSeats[i] == null) {
					count++;
					alloc += String.valueOf(i) + ",";
					fl.availSeats[i] = c;
					fl.availBC--;
				}

			}
			flag = true;
			// break;
		} else if (seatClass.equals("EC") && seatCount <= fl.availEC) {
			c = new Customer(bookingId++, fl.flightNo, seatClass, seatCount, meal);
			fl.totalCost += c.getPrice();
			int count = 0;
			for (int i = 7; (i <= 18 && count < seatCount); i++) {
				if (fl.availSeats[i] == null) {
					count++;
					alloc += String.valueOf(i) + ",";
					fl.availSeats[i] = c;
					fl.availEC--;
				}
			}
			flag = true;

		}

		if (flag && c != null) {
			System.out.println("Booking Id:" + c.bookingId);
			System.out.println("Flight Number" + c.flightNo);
			/// System.out.print(alloc);
			c.setAllocatedSeates(alloc.substring(0, (alloc.length() - 1)));
			if (c.seatClass.equals("EC"))
				System.out.println("Economy Class:" + c.getAllocatedSeates());
			else {
				System.out.println("Business Class:" + c.getAllocatedSeates());
			}
			System.out.println("Meals Required:" + c.meal);
			System.out.println("Total Cost:" + c.getPrice());
			cus.add(c);
		} else {
			System.out.println("Not enough seats are available");
		}
	}

	static void cancel() {
		System.out.println("Enter the Bookind Id:");
		int id = input.nextInt();
		System.out.println("Flight No:");
		int no = input.nextInt();
		int noOfSeats = 0;
		for (Flight fl : flightList) {
			if (fl.flightNo == no) {

				for (int i = 1; i <= 18; i++) {
					if (fl.availSeats[i] != null && fl.availSeats[i].bookingId == id) {

						noOfSeats = fl.availSeats[i].seatCount;
						fl.availSeats[i] = null;
						if (i <= 6) {
							fl.availBC++;
						} else {
							fl.availEC++;
						}
					}
				}
				fl.totalCost -= (noOfSeats * 200);
				break;
			}

		}
		System.out.println("Booking Id:" + id);
		System.out.println("Flight Number" + no);
		System.out.println("Cancelled");
		System.out.println("Total Cost:" + (noOfSeats * 200));
	}

	static void printMealSeats() {
		for (Flight fl : flightList) {
			System.out.println("Meal details for Flight:" + fl.flightNo);
			for (int i = 1; i <= 18; i++) {

				if (fl.availSeats[i] != null) {

					if (fl.availSeats[i].meal == 'Y') {
						if (i != 18) {
							System.out.print(i + ",");
						} else {
							System.out.print(i);
						}
					}

				}
			}
			System.out.println();

		}
	}

	static void printAvailSeats() {
		System.out.println("Available Seats:");
		for (Flight fl : flightList) {
			availSeats(fl);
		}
	}

	static void availSeats(Flight fl) {
		System.out.println("Available seats in Flight " + fl.flightNo);
		for (int i = 1; i <= 18; i++) {
			if (fl.availSeats[i] == null) {
				if (i != 19)
					System.out.print(i + ",");
				else
					System.out.println(i);
			}
		}
		System.out.println();
	}

	static void printFlightSummary() {
		for (Flight fl : flightList) {
			System.out.println("Summary for Flight Number:" + fl.flightNo);

			System.out.println("Total Cost:" + fl.totalCost);
			availSeats(fl);
		}
	}

	public static void main(String args[]) {
		int flightNo = 101;

		System.out.println("Enter the no of Flights:");
		int N = input.nextInt();
		for (int i = 0; i < N; i++) {
			flightList.add(new Flight(flightNo++));
		}
		System.out.println(flightList);
		char ch = 'y';
		do {
			System.out.println("1.Handle Booking");
			System.out.println("2.Handle cancellation");
			System.out.println("3.print all available seats for each flights");
			System.out.println("4.print seat number for which flight meal is ordered");
			System.out.println("5.Flight summary");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				booking();
				break;
			case 2: {
				cancel();
				break;
			}
			case 3:
				printAvailSeats();
				break;
			case 4:
				printMealSeats();
				break;
			case 5:
				printFlightSummary();
				break;
			default:
				System.out.println("Invalid chioce.Try again...");
			}
			System.out.println("Do you want to continue y/n?");
			ch = input.next().charAt(0);
		} while (ch == 'y');

		input.close();
	}
}
