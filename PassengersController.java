
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PassengersController {
    public static void AddNewPassenger(Database database,Scanner s) throws SQLException{
        System.out.println("Enter name: ");
        String name=s.next();
        System.out.println("Enter tel: ");
        String tel=s.next();
        System.out.println("Enter email: ");
        String email=s.next();

        Passenger p = new Passenger();
        p.setName(name);
        p.setTel(tel);
        p.setEmail(email);

        ArrayList<Passenger>passengers =  getAllPassengers(database);
        int id;
        if(passengers.size()!=0){
            id = passengers.get(passengers.size()-1).getId()+1;
        }
        else{
            id = 0;
        }
        p.setId(id);

        String insert = "INSERT INTO `airlinereservation`.`passengers` (`Id`, `Name`, `Tel`, `Email`) VALUES ('"+p.getId()+"', '"+p.getName()+"', '"+p.getTel()+"', '"+p.getEmail()+"')";
        database.getStatement().execute(insert);

        System.out.println("Passenger added successfully!");
    }

    public static void EditPassenger(Database database , Scanner s) throws SQLException {
        System.out.println("Enter passenger id (int): \n(-1 to get passenger by name)");
        int id=s.nextInt();
        Passenger passenger;
        if(id == -1){
           passenger = getPassengerByName(database , s);
        }
        else{
            String query = "select `id`,`name`,`Tel`,`email` from `passengers` where `id` = "+id+";";
        ResultSet rs = database.getStatement().executeQuery(query);
        Passenger p = new Passenger();
        rs.next();
                 p.setId((Integer.parseInt(rs.getString("id"))));
                 p.setName(rs.getString("name"));
                 p.setTel(rs.getString("Tel"));
                 p.setEmail(rs.getString("email"));
                 passenger = p;
        }
        System.out.println("Enter name: \n(-1 to keep old value)");
        String name = s.next();
        if(name.equals("-1")) name = passenger.getName();

        System.out.println("Enter Tel: \n(-1 to keep old value)");
        String Tel = s.next();
        if(Tel.equals("-1")) Tel = passenger.getTel();

        System.out.println("Enter email: \n(-1 to keep old value)");
        String email = s.next();
        if(email.equals("-1")) email = passenger.getEmail();

        passenger.setId(id);
        passenger.setName(name);
        passenger.setTel(Tel);
        passenger.setEmail(email);
        String update = "UPDATE `airlinereservation`.`passengers` SET `id`='"+passenger.getId()+"',`name`='"+passenger.getName()+"',`Tel`='"+passenger.getTel()+"',`email`='"+passenger.getEmail()+"' where `id`='"+passenger.getId()+"'";
       database.getStatement().execute(update);
        System.out.println("Passenger edited successfully!");
    }

    public static void FindPassengerByName(Database database , Scanner s) throws SQLException {
        System.out.println("Enter name: ");
        String name = s.next();
        String get = "select * from `passengers` where `name` = \""+name+"\";";
        ResultSet rs = database.getStatement().executeQuery(get);
        Passenger passenger = new Passenger();
        while(rs.next()){
            //rs.next();
            Passenger p = new Passenger();
                 p.setId(Integer.parseInt(rs.getString("id")));
                 p.setName(rs.getString("name"));
                 p.setTel(rs.getString("Tel"));
                 p.setEmail(rs.getString("email"));

                 if(p.getName().equals(name)) passenger = p ;break;
        }
        passenger.print();
    }

    public static Passenger getPassengerByName(Database database , Scanner s) throws SQLException {
        System.out.println("Enter name: ");
        String name = s.next();
        String get = "select * from `passengers` where `name` = \""+name+"\";";
        ResultSet rs = database.getStatement().executeQuery(get);
        Passenger passenger = new Passenger();
        while(rs.next()){
            rs.next();
            Passenger p = new Passenger();
                 p.setId((Integer.parseInt(rs.getString("id"))));
                 p.setName(rs.getString("name"));
                 p.setTel(rs.getString("Tel"));
                 p.setEmail(rs.getString("email"));

                 if(p.getName().equals(name)) passenger = p ;break;
        }
        passenger.print();
        return passenger;
    }

    public static void PrintAllPassengers(Database database) throws SQLException {
        ArrayList <Passenger> passengers = getAllPassengers(database);
        System.out.println("\n-----------------------------");
        for(Passenger p:passengers){
            p.print();
        }
        System.out.println("-----------------------------\n");
    }

    public static void DeletePassenger(Database database , Scanner s) throws SQLException{
        System.out.println("Enter id(int): \n(-1 to get passenger by name)");
        int id = s.nextInt();
        Passenger passenger;
        if(id==-1){
            passenger = getPassengerByName(database , s);
        }
        else{
            String query = "select `id`,`name`,`Tel`,`email` from `passengers` where `id` = "+id+";";
            ResultSet rs = database.getStatement().executeQuery(query);
            Passenger p = new Passenger();
            rs.next();
                     p.setId((Integer.parseInt(rs.getString("id"))));
                     p.setName(rs.getString("name"));
                     p.setTel(rs.getString("Tel"));
                     p.setEmail(rs.getString("email"));
                     passenger = p;
        }
        String delete = "DELETE FROM `passengers` WHERE `id` = "+passenger.getId()+";";
        database.getStatement().execute(delete);
        System.out.println("Passenger delete successfully!");
    }
    public static ArrayList<Passenger> getAllPassengers(Database database) throws SQLException{

        String query = "select * from passengers";
        ResultSet rs = database.getStatement().executeQuery(query);
        ArrayList<Passenger> passengers = new ArrayList<>();

        while(rs.next()){
             Passenger p = new Passenger();
             p.setId(Integer.parseInt(rs.getString("id")));
             p.setName(rs.getString("name"));
             p.setTel(rs.getString("Tel"));
             p.setEmail(rs.getString("email"));

             passengers.add(p);

            }
            return passengers;
    }

    public static Passenger getPassengerByID(Database database , int id) throws SQLException {
        String get = "select * from `passengers` where `id` = "+id+";";
        ResultSet rs = database.getStatement().executeQuery(get);
        Passenger p = new Passenger();
            while(rs.next()) {
                p.setId(Integer.parseInt(rs.getString("id")));
                p.setName(rs.getString("name"));
                p.setTel(rs.getString("Tel"));
                p.setEmail(rs.getString("email"));
            }
            return p;
    }

}
