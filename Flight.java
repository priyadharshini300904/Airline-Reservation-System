import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Flight {
    private int id;
    private int flightnumber;
    private Airport origin;
    private Airport destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
	private int economyseats;
    private int businessseats;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	public Flight() {}

	public int  getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlightnumber() {
		return flightnumber;
	}
	public void setFlightnumber(int flightnumber) {
		this.flightnumber = flightnumber;
	}
	public Airport getOriginAirport() {
		return origin;
	}
	public void setOriginAirport(Airport origin) {
		this.origin = origin;
	}
	public Airport getDestinationAirport() {
		return destination;
	}
	public void setDestinationAirport(Airport destination) {
		this.destination = destination;
	}
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getEconomySeats() {
		return economyseats;
	}
	public void setEconomySeats(int economyseats) {
		this.economyseats = economyseats;
	}
	public int getBusinessSeats() {
		return businessseats;
	}
	public void setBusinessSeats(int businessseats) {
		this.businessseats = businessseats;
	}
	
	public void print() {
		System.out.print(id+"\t");
		System.out.print(flightnumber+"\t\t");
		System.out.print(origin.getCity()+"\t");
		System.out.print(destination.getCity()+"\t\t");
		System.out.print(formatter.format(departureTime)+"\t");
		System.out.print(formatter.format(arrivalTime)+"\t");
		System.out.print(economyseats+"\t\t");
		System.out.print(businessseats+"\t");
		System.out.println();
	}
}
