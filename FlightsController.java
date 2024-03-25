
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightsController {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    public static void AddNewFlight(Database database , Scanner s) throws Exception {
        System.out.println("Enter flight number (int): )");
        int flightID = s.nextInt();
        System.out.println("Enter origin airport id (int): \n(-1 to show all airports)");
        int originID = s.nextInt();
        if(originID == -1){
            AirportsController.PrintAllAirports(database);
            System.out.println("Enter origin airport id (int): ");
            originID = s.nextInt();
        }
        Airport origin = AirportsController.GetAirport(database, originID);

        System.out.println("Enter destination id (int): \n(-1 to show all airports)");
        int destinationID = s.nextInt();
        if(destinationID == -1){
            AirportsController.PrintAllAirports(database);
            System.out.println("Enter destination id (int): ");
            destinationID = s.nextInt();
        }
        Airport destination = AirportsController.GetAirport(database, destinationID);

        System.out.println("Enter departure time (yyyy-MM-ddTHH:mm): ");
        String dTime = s.next();
        LocalDateTime departureTime = LocalDateTime.parse(dTime , formatter);

        System.out.println("Enter arrival time (yyyy-MM-ddTHH:mm): ");
        String aTime = s.next();
        LocalDateTime arrivalTime = LocalDateTime.parse(aTime , formatter);

        System.out.println("Enter number of economy seats (int): ");
        int es=s.nextInt();

        System.out.println("Enter number of business seats (int): ");
        int bs=s.nextInt();

        Flight flight = new Flight();
        flight.setFlightnumber(flightID);
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setEconomySeats(es);
        flight.setBusinessSeats(bs);

       ArrayList<Flight> flights = getAllFlights(database);
       int id;
       if (flights.size() != 0) {
           id = flights.get(flights.size() - 1).getId() + 1;
       } else {
           id = 0;
       }
       flight.setId(id);
   

        String insert = "INSERT INTO `airlinereservation`.`flights`(`Id`, `Flight Number`, `Origin`, `Destination`, `Departure`, `Arrival`,`Economy Seats`,`Business Seats`) VALUES ('0', '"+flightID+"', '"+originID+"', '"+destinationID+"', '"+departureTime+"', '"+arrivalTime+"','"+es+"','"+bs+"');";
        database.getStatement().executeUpdate(insert); 

        System.out.println("Flight added sucessfully!");
    }
        public static ArrayList<Flight> getAllFlights(Database database) throws SQLException {
         ArrayList<Flight> flights = new ArrayList<>();
         String select = "SELECT * FROM flights;";
         ResultSet rs = database.getStatement().executeQuery(select);

         ArrayList<Integer> IDs = new ArrayList<>();
         ArrayList<Integer> flightIDs = new ArrayList<>();
         ArrayList<Integer> originIDs = new ArrayList<>();
         ArrayList<Integer> destIDs = new ArrayList<>();
         ArrayList<String> depTimes = new ArrayList<>();
         ArrayList<String> arrTimes = new ArrayList<>();
         ArrayList<Integer> economy = new ArrayList<>();
         ArrayList<Integer> business = new ArrayList<>();
         while(rs.next()){

            IDs.add(rs.getInt("Id"));
            flightIDs.add(rs.getInt("Flight Number"));
            originIDs.add(rs.getInt("Origin"));
            destIDs.add(rs.getInt("Destination"));
            depTimes.add(rs.getString("Departure"));
            arrTimes.add(rs.getString("Arrival"));
            economy.add(rs.getInt("Economy Seats"));
            business.add(rs.getInt("Business Seats"));

         }

             for(int i=0;i<IDs.size();i++){
                Flight flight = new Flight();
             flight.setId(IDs.get(i)); 

            int flightID = flightIDs.get(i);
            int originID= originIDs.get(i);
            int destID = destIDs.get(i);
            String depTime = depTimes.get(i);
            String arrTime = arrTimes.get(i);
            int ESeats = economy.get(i);
            int BSeats = business.get(i);

            flight.setFlightnumber(flightID);
            flight.setOriginAirport(AirportsController.GetAirport(database, originID));
            flight.setDestinationAirport(AirportsController.GetAirport(database, destID));
            LocalDateTime departure = LocalDateTime.parse(depTime, formatter);
            flight.setDepartureTime(departure);
            LocalDateTime arrival = LocalDateTime.parse(arrTime, formatter);
            flight.setArrivalTime(arrival);
            flight.setEconomySeats(ESeats);
            flight.setBusinessSeats(BSeats);
             flights.add(flight);
             }
         return flights;
        }
public static void ShowAllFlights(Database database) throws SQLException {
    ArrayList<Flight> flights = getAllFlights(database);
    System.out.println("Id\tFlight Number\tOrigin\tDestination\tDeparture"
    +"\t\tArrival\t\t\tEconomySeats\tBusinessSeats");
    for(Flight f : flights){
        f.print();
    }
}

public static Flight getFlight(Database database , int id) throws SQLException {
    Flight flight = new Flight();
    String get = "select * from `flights` where `Id` = "+id+";";
    ResultSet rs = database.getStatement().executeQuery(get);
   while(rs.next()){
    int ID = rs.getInt("Id");
    int flightID = rs.getInt("Flight Number");
    int originID= rs.getInt("Origin");
    int destID = rs.getInt("Destination");
    String depTime = rs.getString("Departure");
    String arrTime = rs.getString("Arrival");
    int es = rs.getInt("Economy Seats");
    int bs = rs.getInt("Business Seats");

    flight.setId(ID);
    flight.setFlightnumber(flightID);
    flight.setOriginAirport(AirportsController.GetAirport(database, originID));
    flight.setDestinationAirport(AirportsController.GetAirport(database, destID));
    LocalDateTime departure = LocalDateTime.parse(depTime, formatter);
    flight.setDepartureTime(departure);
    LocalDateTime arrival = LocalDateTime.parse(arrTime, formatter);
    flight.setArrivalTime(arrival);
    flight.setEconomySeats(es);
    flight.setBusinessSeats(bs);
   }
    return flight;
}

    public static void CancelFlight(Database database , Scanner s) throws SQLException {
        System.out.println("Enter flight id (int): \n(-1 to show all flights)");
    int id =s.nextInt();
    if(id == -1){
        ShowAllFlights(database);
        System.out.println("Enter flight id (int): ");
        id = s.nextInt();
    }

    String delete = "DELETE FROM flights WHERE `Id`="+id+";";
    database.getStatement().execute(delete);
    System.out.println("Flight cancelled sucessfully!");
    }
}
