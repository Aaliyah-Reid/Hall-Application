public interface HallOfResidence {
    enum Gender{male,female};
    public int numRooms();

    public String bookRoom(Gender gender, int budget, String rfile);

    public String reserveRoom(Gender gender, int budget, String blockName, String rfile);

}
