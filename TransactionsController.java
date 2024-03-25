import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionsController {

    public static void AddNewTransaction(Database database, Scanner s) throws Exception {
        System.out.println("Enter booking id (int): \n(-1 to show all bookings)");
        int bookingId = s.nextInt();
        int seatId = 0;

        if (bookingId == -1) {
            BookingsController.ShowAllBookings(database);
            System.out.println("Enter booking id (int): ");
            bookingId = s.nextInt();
            System.out.println("Enter seat id (int): ");
            seatId = s.nextInt();
        } 
        else {
            seatId = getSeatIdByBookingId(database, bookingId);
            if (seatId == -1) {
                System.out.println("Booking with ID " + bookingId + " not found.");
                return;
            }
        }

        String classType = getClassTypeBySeatId(database, seatId);
        if (classType == null) {
            System.out.println("Seat with ID " + seatId + " not found.");
            return;
        }

        int a = calculateAmount(classType);

        Transaction t = new Transaction();
        t.setBookingid(bookingId);
        t.setSeatid(seatId);
        t.setClasstype(classType);
        t.setAmount(a);

        String insert = "INSERT INTO `airlinereservation`.`transactions`(`Id`, `Booking Id`, `Seat Id`, `Class_Type`, `Amount`) " +
                "VALUES ('0', '" + t.getbookingid() + "', '" + t.getSeatid() + "', '" + t.getClasstype() + "', '" + t.getAmount() + "')";
        database.getStatement().executeUpdate(insert);

        System.out.println("Your transaction was successful.");
    }

    public static int getSeatIdByBookingId(Database database, int bookingId) throws SQLException {
        String query = "SELECT `Seat Id` FROM `bookings` WHERE `Id` = " + bookingId;
        ResultSet resultSet = database.getStatement().executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt("Seat Id");
        }
        return -1; 
    }

    public static String getClassTypeBySeatId(Database database, int seatId) throws SQLException {
        String query = "SELECT `Class_Type` FROM `seats` WHERE `Id` = " + seatId;
        ResultSet resultSet = database.getStatement().executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getString("Class_Type");
        }
        return null; 
    }

    public static int calculateAmount(String classType) {
        int amount = 0;
        if (classType.equalsIgnoreCase("busines")) {
            amount = 2000; 
        } else if (classType.equalsIgnoreCase("economy")) {
            amount = 1000; 
        } else {
            System.out.println("Unknown class type: " + classType);
        }
        return amount;
    }

    public static void ShowAllTransactions(Database database) throws SQLException {
        ArrayList<Transaction> transactions = getAllTransactions(database);
        System.out.println("Transaction ID\tBooking ID\tSeat ID\tClass Type\tAmount");
        for(Transaction t : transactions){
            t.print();
        }
    }

    public static ArrayList<Transaction> getAllTransactions(Database database) throws SQLException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String select = "SELECT * FROM transactions";
        ResultSet rs = database.getStatement().executeQuery(select);

        while (rs.next()) {
            Transaction t = new Transaction();
            t.setId(rs.getInt("Id"));
            t.setBookingid(rs.getInt("Booking Id"));
            t.setSeatid(rs.getInt("Seat Id"));
            t.setClasstype(rs.getString("Class_Type"));
            t.setAmount(rs.getInt("Amount"));
            transactions.add(t);
        }
        return transactions;
    }

    public static void CancelTransaction(Database database, Scanner s) throws SQLException {
        System.out.println("Enter transaction id (int) \n(-1 to show all transactions)");
        int t_id = s.nextInt();
        if(t_id == -1){
            ShowAllTransactions(database);
            System.out.println("Enter seat id (int): ");
            t_id = s.nextInt();

        }
        String deleteQuery = "DELETE FROM transactions WHERE Id = "+t_id+";";
        database.getStatement().executeUpdate(deleteQuery);

        System.out.println("Transaction canceled successfully."); 
    }

}