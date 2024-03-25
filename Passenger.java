public class Passenger{
    
        private int id;
	    private String name;
	    private String tel;
	    private String email;

		public Passenger() {}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

		public void print() {
			System.out.println("id: "+getId());
            System.out.println("name: "+getName());
            System.out.println("Tel: "+getTel());
            System.out.println("email: "+getEmail());
			System.out.println();
		}
}