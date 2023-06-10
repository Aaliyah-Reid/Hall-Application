import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.awt.BorderLayout;
import java.awt.Color;

public class ResidentListing extends JPanel {
    private JButton cmdEdit;
    private JButton cmdDelete;
    private JButton cmdSortHall;
    private JButton cmdSortFirstName;
    private JButton cmdSortFaculty;
    private JButton cmdClose;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private ArrayList<Resident> rslist;
    private ResidentListing thisForm;
    private JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public ResidentListing() {
        super(new GridLayout(2, 1));
        thisForm = this;

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        JLabel apHeader = new JLabel("List of Students Applying for/ Reserving a Hall");
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));

        rslist = loadResidents("resident.txt");

        String[] columns = { "First Name", "Last Name", "ID", "Age", "Sex", "Telephone",
                "Email", "Year", "Type", "Level", "Faculty", "Major", "Hall", "Room" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        TableColumn column7 = table.getColumnModel().getColumn(13);
        column7.setPreferredWidth(40);

        showTable(rslist);

        table.setPreferredScrollableViewportSize(new Dimension(700, rslist.size() * 15 + 50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        cmdDelete = new JButton("Delete Info");
        cmdEdit = new JButton("Edit Info");
        cmdSortFirstName = new JButton("Sort by First Name");
        cmdSortHall = new JButton("Sort by Hall");
        cmdSortFaculty = new JButton("Sort by Faculty");
        cmdClose = new JButton("Close");

        cmdDelete.addActionListener(new DeleteInfoListener());
        cmdEdit.addActionListener(new EditInfoListener());
        cmdSortFirstName.addActionListener(new SortNameListener());
        cmdSortHall.addActionListener(new SortHallListener());
        cmdSortFaculty.addActionListener(new SortFacultyListener());
        cmdClose.addActionListener(new CloseButtonListener());

        cmdEdit.setBackground(Color.GREEN);
        cmdDelete.setBackground(Color.YELLOW);
        cmdSortFirstName.setBackground(Color.ORANGE);
        cmdSortHall.setBackground(Color.MAGENTA);
        cmdSortFaculty.setBackground(Color.BLUE);
        cmdClose.setBackground(Color.RED);
        cmdEdit.setForeground(Color.WHITE);
        cmdDelete.setForeground(Color.WHITE);
        cmdSortFirstName.setForeground(Color.WHITE);
        cmdSortHall.setForeground(Color.WHITE);
        cmdSortFaculty.setForeground(Color.WHITE);
        cmdClose.setForeground(Color.WHITE);

        pnlCommand.add(cmdEdit);
        pnlCommand.add(cmdSortFirstName);
        pnlCommand.add(cmdSortHall);
        pnlCommand.add(cmdSortFaculty);
        pnlCommand.add(cmdDelete);
        pnlCommand.add(cmdClose);

        setPreferredSize(new Dimension(1920, 1080));
        add(pnlCommand);
        setVisible(true);

    }

    private class EditInfoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            EditInfo edit = new EditInfo(thisForm, rslist);
        }
    }

    private class DeleteInfoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            EditInfo delete = new EditInfo(thisForm, rslist);
        }
    }

    public static Comparator<Resident> nameComparator = new Comparator<Resident>() {
        public int compare(Resident rs1, Resident rs2) {
            return rs1.getName().compareTo(rs2.getName());
        }
    };

    private class SortNameListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            model.setRowCount(0);
            Collections.sort(rslist, nameComparator);
            showTable(rslist);

        }
    }

    public static Comparator<Resident> hallComparator = new Comparator<Resident>() {
        public int compare(Resident rs1, Resident rs2) {
            return rs1.getHall().compareToIgnoreCase(rs2.getHall());
        }
    };

    private class SortHallListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            model.setRowCount(0);
            Collections.sort(rslist, hallComparator);
            showTable(rslist);
        }
    }

    public static Comparator<Resident> facultyComparator = new Comparator<Resident>() {
        public int compare(Resident rs1, Resident rs2) {
            return rs1.getFaculty().compareToIgnoreCase(rs2.getFaculty());
        }
    };

    private class SortFacultyListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            model.setRowCount(0);
            Collections.sort(rslist, facultyComparator);
            showTable(rslist);

        }

    }

    private class CloseButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.exit(0);

        }

    }

    private ArrayList<Resident> loadResidents(String rsfile) {
        Scanner scanner = null;
        ArrayList<Resident> rslist = new ArrayList<Resident>();
        try {
            scanner = new Scanner(new File(rsfile));
            while (scanner.hasNext()) {
                String[] nextLine = scanner.nextLine().split(" ");
                String name = nextLine[0] + " " + nextLine[1];
                int id = Integer.parseInt(nextLine[2]);
                int age = Integer.parseInt(nextLine[3]);
                String gender = nextLine[4];
                String tphone = nextLine[5];
                String email = nextLine[6];
                int year = Integer.parseInt(nextLine[7]);
                String type = nextLine[8];
                String gradlevel = nextLine[9];
                String faculty = nextLine[10];
                String major = nextLine[11];
                String hall = nextLine[12];
                String room = nextLine[13];

                Resident rs = new Resident(name, id, age, gender, tphone, email, year, type, gradlevel, faculty, major,
                        hall, room);
                rslist.add(rs);
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("no");
        }
        return rslist;
    }

    private void showTable(ArrayList<Resident> rsList) {
        if (rsList.size() > 0) {
            for (Resident rs : rsList) {
                addToTable(rs);
            }
        }
    }

    private void addToTable(Resident rs) {
        String[] name = rs.getName().split(" ");
        String[] item = { name[0], name[1], "" + rs.getID(), "" + rs.getAge(), "" + rs.getGender(), "" +
                rs.getTphone(), "" + rs.getEmail(), "" + rs.getYear(), "" + rs.getType(), "" + rs.getLevel(),
                "" +
                        rs.getFaculty(),
                "" + rs.getMajor(), "" + rs.getHall(), "" + rs.getRoom() };

        model.addRow(item);

    }
    // String name, int ID, int age, String gender, String tphone, String email, int
    // yr, String type, String grad, String faculty, String major, String hall,
    // String room){

    public void showList() {
        JFrame frame = new JFrame("List of Students Applying for/ Reserving a Hall");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        ResidentListing newContentPane = new ResidentListing();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);

    }

}
