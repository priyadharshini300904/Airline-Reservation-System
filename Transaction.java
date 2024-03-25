public class Transaction {
    
    private int id;
    private int bookingid;
    private int seatid;
    private String classtype;
    private int amount;

    public Transaction(){}

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getbookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public int getSeatid() {
		return seatid;
	}
	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

    public void print(){
        System.out.print(getId()+"\t\t");
        System.out.print(getbookingid()+"\t\t");
        System.out.print(getSeatid()+"\t\t");
        System.out.print(getClasstype()+"\t\t");
        System.out.print(getAmount());
        System.out.println();
    }   
}
