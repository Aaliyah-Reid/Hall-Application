public class Resident implements Comparable<Resident> {
    public String name, gender, tphone, email, type, grad, faculty, major, hall, room;
    private int ID, age, yr;

    public Resident(String name, int ID, int age, String gender, String tphone, String email, int yr, String type,
            String grad, String faculty, String major, String hall, String room) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        this.tphone = tphone;
        this.email = email;
        this.yr = yr;
        this.type = type;
        this.grad = grad;
        this.faculty = faculty;
        this.major = major;
        this.hall = hall;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    protected void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    protected void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    protected void setGender(String gender) {
        this.gender = gender;
    }

    public String getTphone() {
        return tphone;
    }

    protected void setPhoneNum(String tphone) {
        this.tphone = tphone;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public int getYear() {
        return yr;
    }

    protected void setYear(int yr) {
        this.yr = yr;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return grad;
    }

    protected void setLevel(String grad) {
        this.grad = grad;
    }

    public String getFaculty() {
        return faculty;
    }

    protected void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    protected void setMajor(String major) {
        this.major = major;
    }

    public String getHall() {
        return hall;
    }

    protected void setHall(String hall) {
        this.hall = hall;
    }

    public String getRoom() {
        return room;
    }

    protected void setRoom(String room) {
        this.room = room;
    }

    public String toString() {
        return getName() + " " + getID() + " " + getAge() + " " + getGender() + " " + getTphone() + " " + getEmail()
                + " " + getYear() + " " + getType() + " " + getLevel() + " " + getFaculty() + " " + getMajor() + " "
                + getHall() + " " + getRoom();
    }

    @Override
    public int compareTo(Resident other) {
        return other.getID() - this.getID();
    }

}
