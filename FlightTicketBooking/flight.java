package FlightTicketBooking;

public class flight {
    static int id = 101;

    int flightId, surgePrice;
    int[] seat, meal;

    int bSeats, eSeats;

    flight() {
        this.flightId = id++;
        this.seat = new int[18];
        this.meal = new int[18];
        this.bSeats = 6;
        this.eSeats = 12;
        this.surgePrice = 0;
    }

    boolean isAvailable(int i) {
        if (i >= 0 && i <= 17)
            return this.seat[i] == 0;
        return false;
    }

    void increaseSeats(char clas, int no) {
        if (clas == 'B')
            this.bSeats += no;
        else if (clas == 'E')
            this.eSeats += no;
    }

    void cancelTicket(int i) {
        this.seat[i] = 0;
        this.meal[i] = 0;
    }

    void bookSeat(int i) {
        if (i <= 5 && i >= 0)
            this.bSeats--;
        else if (i >= 6 && i <= 12)
            this.eSeats--;
        this.seat[i] = 1;
    }

    void bookMeal(int i) {
        this.meal[i] = 1;
    }
}
