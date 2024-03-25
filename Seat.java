public class Seat {

        private int id;
        private int flightId;
        private String seatNumber;
        private String classtype;
        private String availability;
    
        public Seat() {}
    
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public int getFlightid() {
            return flightId;
        }
    
        public void setFlightid(int flightID) {
            this.flightId = flightID;
        }
    
        public String getSeatnumber() {
            return seatNumber;
        }
    
        public void setSeatnumber(String seatNumber) {
            this.seatNumber = seatNumber;
        }
    
        public String getClasstype() {
            return classtype;
        }
        public void setClasstype(String classtype) {
            this.classtype = classtype;
        }

        public String getAvailability() {
            return availability;
        }
    
        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public void print() {
            System.out.print(id+"\t");
            System.out.print(flightId+"\t\t");
            System.out.print(seatNumber+"\t\t");
            System.out.print(classtype+"\t\t");
            System.out.print(availability+"\t");
            System.out.println();
        }

        public void print1(){    
            System.out.println("id: "+getId());
            System.out.println("flight id: "+getFlightid());
            System.out.println("seat number: "+getSeatnumber());
            System.out.println("class type: "+getClasstype());
            System.out.println("availabilty: "+getAvailability());
			System.out.println();
        }
}
