import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
public class BookingsController {

public static void AddNewBooking(Database database, Scanner s) throws Exception {
    System.out.println("Enter flight id (int): \n(-1 to show all flights)");
    int flightID = s.nextInt();
    if (flightID == -1) {
        FlightsController.ShowAllFlights(database);
        System.out.println("Enter flight id (int): ");
        flightID = s.nextInt();
    }
    
    System.out.println("Enter passenger id (int): \n(-1 to show all passengers)");
    int passID = s.nextInt();
    if (passID == -1) {
        PassengersController.PrintAllPassengers(database);
        System.out.println("Enter passenger id (int): ");
        passID = s.nextInt();
    }
    
    System.out.println("Enter seat id (int): \n(-1 to show all seats)");
    int seatID = s.nextInt();
    if (seatID == -1) {
        SeatsController.ShowAllSeats(database);
        System.out.println("Enter seat id (int): ");
        seatID = s.nextInt();
    } else {
        SeatsController.getSeatByID(database, seatID);
    }
    
    Seat seat = getSeatByID(database, seatID);
    if (seat != null && seat.getAvailability().equalsIgnoreCase("Available")) {
        Booking booking = new Booking();
        booking.setFlightid(flightID);
        booking.setPassengerid(passID);
        booking.setSeatid(seatID);

        String insert = "INSERT INTO `airlinereservation`.`bookings`(`Id`, `Flight Id`, `Passenger Id`, `Seat Id`, `Status`) " +
                        "VALUES ('0', '" + booking.getFlightid() + "', '" + booking.getPassengerid() + "', '" + booking.getSeatid() + "','Booked')";
        database.getStatement().executeUpdate(insert);

        String updateSeat = "UPDATE `airlinereservation`.`seats` SET `Availability` = 'Not Available' WHERE `Id`=" + seatID + ";";
        database.getStatement().executeUpdate(updateSeat);

        System.out.println("Your booking was successful!");
    } else {
        System.out.println("Sorry, the selected seat is not available.please do check other seats...........");
    }
}

public static ArrayList<Booking> getAllBookings(Database database) throws SQLException {
    ArrayList<Booking> bookings = new ArrayList<>();
    String select = "SELECT * FROM bookings;";
    ResultSet rs = database.getStatement().executeQuery(select);

    ArrayList<Integer> IDs = new ArrayList<>();
    ArrayList<Integer> flightIDs = new ArrayList<>();
    ArrayList<Integer> passIDs = new ArrayList<>();
    ArrayList<Integer> seatIDs = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();

    while(rs.next()){

       IDs.add(rs.getInt("Id"));
       flightIDs.add(rs.getInt("Flight Id"));
       passIDs.add(rs.getInt("Passenger Id"));
       seatIDs.add(rs.getInt("Seat Id"));
       status.add(rs.getString("Status"));

    }

        for(int i=0;i<IDs.size();i++){
           Booking b = new Booking();
        b.setId(IDs.get(i)); 

       int flightID = flightIDs.get(i);
       int passID= passIDs.get(i);
       int seatID= seatIDs.get(i);
       String st= status.get(i);

       b.setFlightid(flightID);
       b.setPassengerid(passID);
       b.setSeatid(seatID);
       b.setStatus(st);

        bookings.add(b);
        }
    return bookings;
   }

public static Seat getSeatByID(Database database, int seatID) throws SQLException {
    String get = "SELECT * FROM `seats` WHERE `Id` = " + seatID + ";";
    ResultSet rs = database.getStatement().executeQuery(get);
    if (rs.next()) {
        Seat seat = new Seat();
        seat.setId(rs.getInt("Id"));
        seat.setFlightid(rs.getInt("Flight Id"));
        seat.setSeatnumber(rs.getString("Seat Number"));
        seat.setClasstype(rs.getString("Class_Type"));
        seat.setAvailability(rs.getString("Availability"));
        return seat;
    }
    return null;
}

public static void ShowAllBookings(Database database) throws SQLException {
            ArrayList<Booking> booking = getAllBookings(database);
            System.out.println("Id\tFlight Id\tPassenger Id\tSeat Id\t\tStatus");
            for(Booking f : booking){
                f.print();
            }
        }

        public static void CancelBooking(Database database , Scanner s) throws SQLException {
            System.out.println("Enter booking id (int): \n(-1 to show all booking)");
        int id =s.nextInt();
        if(id == -1){
            ShowAllBookings(database);
            System.out.println("Enter booking id (int): ");
            id = s.nextInt();
        }
    
        String delete = "DELETE FROM bookings WHERE `Id`="+id+";";
        database.getStatement().execute(delete);
        System.out.println("Booking cancelled sucessfully!");
        }

}
