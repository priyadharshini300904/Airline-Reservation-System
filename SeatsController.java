import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SeatsController {

    public static void AddNewSeat(Database database , Scanner s) throws Exception {
        System.out.println("Enter flight id (int): \n(-1 to show all flights)");
        int  flightID = s.nextInt();
        if(flightID == -1){
            FlightsController.ShowAllFlights(database);
            System.out.println("Enter flight id (int): ");
            flightID = s.nextInt();
        }

        System.out.println("Enter seat number: ");
        String seatnumber = s.next();

        System.out.println("Enter class type: ");
        String classtype = s.next();
        
        System.out.println("Enter availability: ");
        String availability = s.next();

        Seat seat = new Seat();
        seat.setFlightid(flightID);
        seat.setSeatnumber(seatnumber);
        seat.setClasstype(classtype);
        seat.setAvailability(availability);

        ArrayList<Seat> seats = getAllSeats(database);
       int id;
       if (seats.size() != 0) {
           id = seats.get(seats.size() - 1).getId() + 1;
       } else {
           id = 0;
       }
       seat.setId(id);

       String insert = "INSERT INTO `airlinereservation`.`seats`(`Id`, `Flight Id`, `Seat Number`, `Class_Type`,`Availability`) VALUES ('0', '"+seat.getFlightid()+"', '"+seat.getSeatnumber()+"', '"+seat.getClasstype()+"','"+seat.getAvailability()+"')";
        database.getStatement().executeUpdate(insert); 

        System.out.println("Flight added sucessfully!");
    }

    public static ArrayList<Seat> getAllSeats(Database database) throws SQLException {
         ArrayList<Seat> seats = new ArrayList<>();
         String select = "SELECT * FROM seats;";
         ResultSet rs = database.getStatement().executeQuery(select);

         ArrayList<Integer> IDs = new ArrayList<>();
         ArrayList<Integer> flightIDs = new ArrayList<>();
         ArrayList<String> seatNos = new ArrayList<>();
         ArrayList<String> classtypes = new ArrayList<>();
         ArrayList<String> available = new ArrayList<>();

         while(rs.next()){

            IDs.add(rs.getInt("Id"));
            flightIDs.add(rs.getInt("Flight Id"));
            seatNos.add(rs.getString("Seat Number"));
            classtypes.add(rs.getString("Class_Type"));
            available.add(rs.getString("Availability"));

         }

             for(int i=0;i<IDs.size();i++){
                Seat t = new Seat();
             t.setId(IDs.get(i)); 

            int flightID = flightIDs.get(i);
            String seatNo= seatNos.get(i);
            String classType= classtypes.get(i);
            String av = available.get(i);

            t.setFlightid(flightID);
            t.setSeatnumber(seatNo);
            t.setClasstype(classType);
            t.setAvailability(av);
    
             seats.add(t);
             }
         return seats;
        }

        public static void ShowAllSeats(Database database) throws SQLException {
            ArrayList<Seat> seats = getAllSeats(database);
            System.out.println("Id\tFlight Id\tSeat Number\tClass_Type\tAvailability");
            for(Seat f : seats){
                f.print();
            }
        }

        public static void CancelSeat(Database database , Scanner s) throws SQLException {
        System.out.println("Enter seat id (int): \n(-1 to show all seats)");
        int id =s.nextInt();
        if(id == -1){
            ShowAllSeats(database);
            System.out.println("Enter seat id (int): ");
            id = s.nextInt();
        }
    
        String delete = "DELETE FROM seats WHERE `Id`="+id+";";
        database.getStatement().execute(delete);
        System.out.println("Seat cancelled sucessfully!");
        }

        public static void getSeatByID(Database database , int id ) throws SQLException {
            //System.out.println("Enter  seat id: ");
            //int seatid = s.nextInt();
            String get = "select * from `seats` where `Id` = "+id+";";
            ResultSet rs = database.getStatement().executeQuery(get);
            Seat p = new Seat();
                while(rs.next()) {
                    p.setId(Integer.parseInt(rs.getString("Id")));
                    p.setFlightid(rs.getInt("Flight Id"));
                    p.setSeatnumber(rs.getString("Seat Number"));
                    p.setClasstype(rs.getString("Class_Type"));
                    p.setAvailability(rs.getString("Availability"));
                }
                p.print1();;
        }
}
