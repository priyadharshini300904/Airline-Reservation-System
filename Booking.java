public class Booking {
    
    private int id;
    private int flightid;
    private int passengerid;
    private int seatid;
    private String status;

    public Booking(){}

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlightid() {
		return flightid;
	}
	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}
	public int getPassengerid() {
		return passengerid;
	}
	public void setPassengerid(int passengerid) {
		this.passengerid = passengerid;
	}
	public int getSeatid() {
		return seatid;
	}
	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void print() {
		System.out.print(id+"\t");
		System.out.print(flightid+"\t\t");
		System.out.print(passengerid+"\t\t");
		System.out.print(seatid+"\t\t");
		System.out.print(status+"\t");
		System.out.println();
	}
}
