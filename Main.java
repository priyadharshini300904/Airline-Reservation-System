import java.util.Scanner;

public class Main {
    

    public static void main (String[]args) throws Exception {
        Database database = new Database();
        Scanner s = new Scanner(System.in);
        int i=0;
        while(i!=22) {
            System.out.println("Welcome to Airline Reservation System ");
            System.out.println("01.Add new passenger");
            System.out.println("02.Get seat by id");
            System.out.println("03.Print all passengers");
            System.out.println("04.Edit passenger");
            System.out.println("05.Delete passenger");
            System.out.println("06.Add new airport");
            System.out.println("07.Print all airports");
            System.out.println("08.Edit airport");
            System.out.println("09.Delete airport");
            System.out.println("10.Create new flight");
            System.out.println("11.Show all flights");
            System.out.println("12.Cancel flight");
            System.out.println("13.Add new seat");
            System.out.println("14.Show all seats");
            System.out.println("15.Cancel seat");
            System.out.println("16.Add new booking");
            System.out.println("17.Show all bookings");
            System.out.println("18.Cancel booking");
            System.out.println("19.Add transaction");
            System.out.println("20.Show all transactions");
            System.out.println("21.Cancel transactions");
            System.out.println("22.Quit");

            i = s.nextInt();
            switch(i){
                case 1:
                PassengersController.AddNewPassenger(database, s);
                break;
                case 2:
                SeatsController.getSeatByID(database, i);
                break;
                case 3:
                PassengersController.PrintAllPassengers(database);
                break;
                case 4:
                PassengersController.EditPassenger(database, s);
                break;
                case 5:
                PassengersController.DeletePassenger(database , s);
                break;
                case 6:
                AirportsController.AddNewAirport(database , s);
                break;
                case 7:
                AirportsController.PrintAllAirports(database);
                break;
                case 8:
                AirportsController.EditAirport(database, s);
                break;
                case 9:
                AirportsController.DeleteAirport(database, s);
                break;
                case 10:
                FlightsController.AddNewFlight(database, s);
                break;
                case 11:
                FlightsController.ShowAllFlights(database);
                break;
                case 12:
                FlightsController.CancelFlight(database, s);
                break;
                case 13:
                SeatsController.AddNewSeat(database, s);
                break;
                case 14:
                SeatsController.ShowAllSeats(database);
                break;
                case 15:
                SeatsController.CancelSeat(database, s);
                break;
                case 16:
                BookingsController.AddNewBooking(database, s);
                break;
                case 17:
                BookingsController.ShowAllBookings(database);
                break;
                case 18:
                BookingsController.CancelBooking(database, s);
                break;
                case 19:
                TransactionsController.AddNewTransaction(database, s);
                break;
                case 20:
                TransactionsController.ShowAllTransactions(database);
                break;
                case 21:
                TransactionsController.CancelTransaction(database, s);
                break;
            }
        }
    }
}
