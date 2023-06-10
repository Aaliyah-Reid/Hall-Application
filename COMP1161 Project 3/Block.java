import java.util.ArrayList;

public class Block {
    // number of rooms on a block
    private int nrooms = 50;
    private String room;
    private ArrayList<String> mrooms = new ArrayList<String>();
    private ArrayList<String> frooms = new ArrayList<String>();

    public Block(String hallName, String blockName) {
        // to fill the first 20 rooms with males
        for (int i = 0; i < 20; i++) {
            room = hallName + " " + blockName + String.valueOf(i + 1);
            mrooms.add(room);
        }
        // the rest with females
        for (int i = 20; i < 50; i++) {
            room = hallName + " " + blockName + String.valueOf(i + 1);
            frooms.add(room);
        }
    }

    public int getNumOfRooms() {
        return nrooms;
    }

    public ArrayList<String> getMRooms() {
        return mrooms;
    }

    public ArrayList<String> getFRooms() {
        return frooms;
    }
}
