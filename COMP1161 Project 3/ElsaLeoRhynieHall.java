import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ElsaLeoRhynieHall implements HallOfResidence {
    private int numRoom;
    private int budget = 200000;
    private String aRoom;
    private int index, count;
    private Block blockA = new Block("ELRH", "A");
    private Block blockB = new Block("ELRH", "B");
    private Block blockC = new Block("ELRH", "C");
    private Block blockD = new Block("ELRH", "D");
    private ArrayList<Block> numBlocks = new ArrayList<Block>();
    // private ArrayList<String> bookedRooms = new ArrayList<String>();
    // `private Random random = new Random();` is creating a new instance of the
    // `Random` class, which is used to generate random numbers. This is used in the
    // `bookRoom` method to
    // randomly select a block and room to book.
    private Random random = new Random();
    private Gender gnd;

    public ElsaLeoRhynieHall() {
        numRoom = blockA.getNumOfRooms() + blockB.getNumOfRooms() + blockC.getNumOfRooms() + blockD.getNumOfRooms();
        numBlocks.add(blockA);
        numBlocks.add(blockB);
        numBlocks.add(blockC);
        numBlocks.add(blockD);
    }

    public int numRooms() {
        return numRoom;
    }

    public int getBudget() {
        return budget;
    }

    // The `bookRoom` method is used to book a room in the TaylorHall hall of
    // residence. It takes two
    // parameters: `gender`, which is the gender of the person booking the room, and
    // `budget`, which is the
    // budget of the person booking the room.
    public String bookRoom(Gender gender, int budget, String rfile) {
        Scanner scan;
        try {
            scan = new Scanner(new File(rfile));
            ArrayList<String> rooms = new ArrayList<String>();
            while (scan.hasNext()) {
                String[] lines = scan.nextLine().split(" ");
                String room = lines[13];
                rooms.add(room);
            }
            if (budget >= this.budget) {
                switch (gender) {
                    case male:
                        // randomizes the block number and assigns the value to index (eg. the first
                        // block in the list is given index 1)
                        index = random.nextInt(numBlocks.size());
                        /*
                         * assigns the variable 'block' to the index generated(eg. if there are 3 blocks
                         * in the list and the index is 2,
                         * 'block' would be assigned to the 2nd block in the list)
                         */
                        Block block = numBlocks.get(index);
                        /*
                         * assigns a random number based on the amount available in the arrayList (eg.
                         * if the size of the arrayList is 10, 'count'
                         * can be assigned any number from 1 to 10)
                         */
                        count = random.nextInt(block.getMRooms().size());
                        /* assigns the room number to 'aRoom' based on the value of count */
                        aRoom = block.getMRooms().get(count);
                        for (String rm : rooms) {
                            if (rm.equals(aRoom)) {
                                count = random.nextInt(block.getMRooms().size());
                                aRoom = block.getMRooms().get(count);
                            }
                        }
                        /*
                         * checks through the arrayList to find the room that matches to the value of
                         * 'aRoom' and add it to the arrayList 'bookedRooms'
                         * then removes it from the existing arrayList
                         */
                        /*
                         * for (int i = 0; i < numBlocks.size(); i++) {
                         * if (block == numBlocks.get(i)) {
                         * bookedRooms.add(numBlocks.get(i).getMRooms().get(count));
                         * numBlocks.get(i).getMRooms().remove(count);
                         * if (block.getMRooms().size()== 0){
                         * numBlocks.remove(index);}
                         * }
                         * }
                         */
                        break;
                    case female:
                        index = random.nextInt(numBlocks.size());
                        block = numBlocks.get(index);
                        count = random.nextInt(block.getFRooms().size());
                        aRoom = block.getFRooms().get(count);
                        for (String rm : rooms) {
                            if (rm.equals(aRoom)) {
                                count = random.nextInt(block.getMRooms().size());
                                aRoom = block.getMRooms().get(count);
                            }
                        }
                        /*
                         * for (int i = 0; i < numBlocks.size(); i++) {
                         * if (block == numBlocks.get(i)) {
                         * bookedRooms.add(numBlocks.get(i).getFRooms().get(count));
                         * numBlocks.get(i).getFRooms().remove(count);
                         * if (block.getFRooms().size()== 0){
                         * numBlocks.remove(index);}
                         * }
                         * }
                         */
                        break;
                }
            } else {
                //"You do not possess enough funds to book a room on this hall"
                aRoom = "";
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (ArrayIndexOutOfBoundsException ae) {
            //"No available rooms"
            aRoom = "";
        }
        return aRoom;

    }

    public String reserveRoom(Gender gender, int budget, String blockName, String rfile) {
        Scanner scan;
        try {
            scan = new Scanner(new File(rfile));
            ArrayList<String> rooms = new ArrayList<String>();
            while (scan.hasNext()) {
                String[] lines = scan.nextLine().split(" ");
                String room = lines[13];
                rooms.add(room);
            }
            if (budget >= this.budget) {
                switch (gender) {
                    case male:

                        Block block = getBlock(blockName);
                        /*
                         * assigns a random number based on the amount available in the arrayList (eg.
                         * if the size of the arrayList is 10, 'count'
                         * can be assigned any number from 1 to 10)
                         */
                        count = random.nextInt(block.getMRooms().size());
                        /* assigns the room number to 'aRoom' based on the value of count */
                        aRoom = block.getMRooms().get(count);
                        for (String rm : rooms) {
                            if (rm.equals(aRoom)) {
                                count = random.nextInt(block.getMRooms().size());
                                aRoom = block.getMRooms().get(count);
                            }
                        }
                        /*
                         * checks through the arrayList to find the room that matches to the value of
                         * 'aRoom' and add it to the arrayList 'bookedRooms'
                         * then removes it from the existing arrayList
                         */
                        /*
                         * for (int i = 0; i < numBlocks.size(); i++) {
                         * if (block == numBlocks.get(i)) {
                         * bookedRooms.add(numBlocks.get(i).getMRooms().get(count));
                         * numBlocks.get(i).getMRooms().remove(count);
                         * if (block.getMRooms().size()== 0){
                         * numBlocks.remove(index);}
                         * }
                         * }
                         */
                        break;
                    case female:
                        block = getBlock(blockName);
                        count = random.nextInt(block.getFRooms().size());
                        aRoom = block.getFRooms().get(count);
                        for (String rm : rooms) {
                            if (rm.equals(aRoom)) {
                                count = random.nextInt(block.getMRooms().size());
                                aRoom = block.getMRooms().get(count);
                            }
                        }
                        /*
                         * for (int i = 0; i < numBlocks.size(); i++) {
                         * if (block == numBlocks.get(i)) {
                         * bookedRooms.add(numBlocks.get(i).getFRooms().get(count));
                         * numBlocks.get(i).getFRooms().remove(count);
                         * if (block.getFRooms().size()== 0){
                         * numBlocks.remove(index);}
                         * }
                         * }
                         */
                        break;
                }
            } else {
                //"You do not possess enough funds to book a room on this hall"
                aRoom = "";
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (ArrayIndexOutOfBoundsException ae) {
            //"No available rooms"
            aRoom = "";
        }
        return aRoom;

    }

    // The `getGender` method takes a `String` parameter `gndd` and returns a
    // `Gender` enum value. It
    // checks if the `gndd` parameter is equal to `"male"`, and if so, it returns
    // the `Gender` enum value
    // `male`. Otherwise, it returns the `Gender` enum value `female`. This method
    // is used to convert a
    // `String` representation of a gender to the corresponding `Gender` enum value.
    public Gender getGender(String gndd) {
        if (gndd.equals("male")) {
            gnd = Gender.male;
        } else {
            gnd = Gender.female;
        }
        return gnd;
    }

    public Block getBlock(String blockName) {
        Block bl;
        if (blockName.equals("A")) {
            bl = blockA;
        } else if (blockName.equals("B")) {
            bl = blockB;
        } else if (blockName.equals("C")) {
            bl = blockC;
        } else {
            bl = blockD;
        }
        return bl;
    }
}
