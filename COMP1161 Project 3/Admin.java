public class Admin {
    private String fname, lname, password;
    private int ID;

    public Admin(String fname, String lname, int ID, String password) {
        this.fname = fname;
        this.lname = lname;
        this.ID = ID;
        this.password = password;
    };

    public String getFName() {

        return fname;
    }

    public String getLName() {
        return lname;
    }

    public int getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }
}
