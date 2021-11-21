package FlightTicketBooking;

public class booking {
    static int id = 1;

    int bookingId, flightId, totalSeats;
    char operation, sClass, meal;
    int[] seats = new int[18];

    booking(int flightId, int sClass, int totalSeats, int meal, int[] seats) {
        this.bookingId = id++;
        this.flightId = flightId;
        this.operation = 'B';
        this.seats = seats;
        this.totalSeats = totalSeats;
        if (sClass == 1)
            this.sClass = 'B';
        else if (sClass == 1)
            this.sClass = 'E';
        if (meal == 1)
            this.meal = 'Y';
        else if (meal == 1)
            this.meal = 'N';
    }

    void cancelBooking() {
        this.operation = 'C';
        // this.meal = ' ';
        // this.sClass = ' ';
        // this.totalSeats = 0;
    }
}
