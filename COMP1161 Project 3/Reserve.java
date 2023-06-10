import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Reserve extends JFrame {
    private String fly, maj, grad, cHall, cblock, cRoom, tphone, email, gender, name;
    private int yr, budget, age, ID;
    private String type = "Returning";
    private JCheckBox two, three, four, five, six, seven, eight;
    private JComboBox<String> faculty, hallPref, BlockPref;
    private JTextField txtMaj, txtPhoneNum, txtEmail, txtBudget, txtId, txtName, txtAge;
    private JRadioButton underLevel, postLevel, fbutton, mbutton;
    private JPanel panelCmd, panelDpl;
    private JButton cmdCancel, cmdSubmit;
    // private JScrollBar Scroll;
    private Reserve reserve;
    private ArrayList<Resident> ResidentList = new ArrayList<Resident>();
    private TaylorHall th = new TaylorHall();
    private ElsaLeoRhynieHall eh = new ElsaLeoRhynieHall();
    private RexNettlefordHall rh = new RexNettlefordHall();

    public Reserve(Application app) {
        reserve = this;
        setTitle("Reservation Form for Returning Student ONLY");
        panelCmd = new JPanel();
        panelDpl = new JPanel();

        ResidentList = loadResidents("resident.txt");

        JLabel apHeader = new JLabel("Hall of Residence Reservation Form for Returning Students ONLY",
                SwingConstants.CENTER);
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        panelDpl.add(apHeader);

        panelDpl.add(new JLabel("Name:"));
        txtName = new JTextField(30);
        panelDpl.add(txtName);

        panelDpl.add(new JLabel("ID:"));
        txtId = new JTextField(9);
        panelDpl.add(txtId);

        panelDpl.add(new JLabel("Age:"));
        txtAge = new JTextField(3);
        panelDpl.add(txtAge);

        // to add Buttons to select sex
        panelDpl.add(new JLabel("Sex:"));
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fbutton = new JRadioButton("Female");
        fbutton.setHorizontalAlignment(SwingConstants.LEFT);
        mbutton = new JRadioButton("Male");
        mbutton.setHorizontalAlignment(SwingConstants.LEFT);
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(mbutton);
        bgroup.add(fbutton);
        pnl.add(mbutton);
        pnl.add(fbutton);
        panelDpl.add(pnl);

        panelDpl.add(new JLabel("Telephone: (example: 876-123-4567)"));
        txtPhoneNum = new JTextField(10);
        panelDpl.add(txtPhoneNum);

        panelDpl.add(new JLabel("Email: "));
        txtEmail = new JTextField();
        panelDpl.add(txtEmail);

        // radio button to select graduate level
        panelDpl.add(new JLabel("Are you an undergraduate or postgraduate student?"));
        Panel panel = new Panel(new FlowLayout(FlowLayout.LEFT));
        underLevel = new JRadioButton("Undergraduate");
        underLevel.setHorizontalAlignment(SwingConstants.LEFT);
        postLevel = new JRadioButton("Postgraduate");
        postLevel.setHorizontalAlignment(SwingConstants.LEFT);
        ButtonGroup bg = new ButtonGroup();
        bg.add(underLevel);
        bg.add(postLevel);
        panel.add(underLevel);
        panel.add(postLevel);
        panelDpl.add(panel);

        // dropbox added to display faculties
        panelDpl.add(new JLabel("Choose your Faculty:"));
        String[] faculties = { "Engineering", "Science & Technology", "Medical Sciences", "Law",
                "Social Sciences", "Sport", "Hiumanities & Education" };
        faculty = new JComboBox<>(faculties);
        panelDpl.add(faculty);
        faculty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    fly = faculty.getSelectedItem().toString().replaceAll("\\s", "");
                }
                ;
            }
        });

        // to display and accept inputs for: Major, and Incoming Year
        panelDpl.add(new JLabel("Major?"));
        txtMaj = new JTextField(20);
        panelDpl.add(txtMaj);

        panelDpl.add(new JLabel("Select your upcoming year:"));
        Panel panll = new Panel(new FlowLayout(FlowLayout.LEFT));
        two = new JCheckBox("2");
        three = new JCheckBox("3");
        four = new JCheckBox("4");
        five = new JCheckBox("5");
        six = new JCheckBox("6");
        seven = new JCheckBox("7");
        eight = new JCheckBox("8");
        panll.add(two);
        panll.add(three);
        panll.add(four);
        panll.add(five);
        panll.add(six);
        panll.add(seven);
        panll.add(eight);
        panelDpl.add(panll);

        // dropbox to select preffered hall
        panelDpl.add(new JLabel("Select your Hall of Choice:"));
        String[] halls = { "Elsa Leo Rhynie Hall", "Rex Nettleford Hall", "Taylor Hall" };
        hallPref = new JComboBox<>(halls);
        panelDpl.add(hallPref);
        hallPref.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    cHall = hallPref.getSelectedItem().toString().replaceAll("\\s", "");
                }
                ;
            }
        });

        // dropbox to select preffered block
        panelDpl.add(new JLabel("Please state your Block preference:"));
        String[] blocks = { "A", "B", "C", "D" };
        BlockPref = new JComboBox<>(blocks);
        panelDpl.add(BlockPref);
        BlockPref.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    cblock = BlockPref.getSelectedItem().toString();
                }
                ;
            }
        });

        panelDpl.add(new JLabel("Budget: (Rex: $100000; Taylor: $150000; Elsa: $200000)"));
        txtBudget = new JTextField(20);
        panelDpl.add(txtBudget);

        panelDpl.setLayout(new GridLayout(30, 1));

        // command buttons
        cmdCancel = new JButton("Cancel");
        cmdSubmit = new JButton("Submit");

        // set colour for buttons
        cmdSubmit.setBackground(Color.GREEN);
        cmdCancel.setBackground(Color.RED);
        cmdSubmit.setForeground(Color.WHITE);
        cmdCancel.setForeground(Color.WHITE);

        panelCmd.add(cmdCancel);
        panelCmd.add(cmdSubmit);

        ButtonListener button = new ButtonListener();
        underLevel.addActionListener(button);
        postLevel.addActionListener(button);

        cmdCancel.addActionListener(new CancelButtonListener());
        cmdSubmit.addActionListener(new SubmitButtonListener());

        fbutton.addActionListener(new ButtonListener2());
        mbutton.addActionListener(new ButtonListener2());

        two.addActionListener(new ButtonListener3());
        three.addActionListener(new ButtonListener3());
        four.addActionListener(new ButtonListener3());
        five.addActionListener(new ButtonListener3());
        six.addActionListener(new ButtonListener3());
        seven.addActionListener(new ButtonListener3());
        eight.addActionListener(new ButtonListener3());

        setPreferredSize(new Dimension(1540, 822));

        add(panelDpl, BorderLayout.CENTER);
        add(panelCmd, BorderLayout.SOUTH);
        pack();
        setVisible(true);
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

    public String CheckAvailability(String preference) {
        String available = "You Cannot Be Assigned A Room";
        String check;
        switch (preference) {
            case "TaylorHall":
                check = th.reserveRoom(th.getGender(gender), budget, cblock, "resident.txt");
                if (check.charAt(0) == 'T') {
                    available = check;
                    return available;
                }
            case "ElsaLeoRhynieHall":
                check = eh.reserveRoom(eh.getGender(gender), budget, cblock, "resident.txt");
                if (check.charAt(0) == 'E') {
                    available = check;
                    return available;
                }
            case "RexNettlefordHall":
                check = rh.reserveRoom(rh.getGender(gender), budget, cblock, "resident.txt");
                if (check.charAt(0) == 'R') {
                    available = check;
                    return available;
                }
        }
        return available;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == underLevel) {
                grad = "Undergraduate";
            } else {
                grad = "Postgraduate";
            }
        }
    }

    private class ButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == fbutton) {
                gender = "female";
            } else {
                gender = "male";
            }
        }

    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actev) {
            boolean noerror = false;
            try {
                name = txtName.getText();
                email = txtEmail.getText();
                maj = txtMaj.getText().replaceAll("\\s", "");
                age = Integer.parseInt(txtAge.getText());
                ID = Integer.parseInt(txtId.getText());
                budget = Integer.parseInt(txtBudget.getText());
                tphone = txtPhoneNum.getText();
                String[] isAvailable = CheckAvailability(cHall).split(" ");
                if (isAvailable.length == 2) {
                    cHall = isAvailable[0];
                    cRoom = isAvailable[1];
                    noerror = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            isAvailable[0] + " " + isAvailable[1] + " " + isAvailable[2] + " " + isAvailable[3] + " "
                                    + isAvailable[4] + " " + isAvailable[5],
                            "Error", JOptionPane.DEFAULT_OPTION);
                }
            } catch (NumberFormatException numformerror) {
                System.out.println(numformerror.getMessage());
            } catch (ArrayIndexOutOfBoundsException indexouterror) {
                System.out.println(indexouterror.getMessage());
            } finally {
                if (noerror == true) {
                    Resident resreserve = new Resident(name, ID, age, gender, tphone, email, yr, type, grad, fly, maj,
                            cHall, cRoom);
                    AddResident(resreserve);
                    switch (resreserve.getHall()) {
                        case "TH":
                            JOptionPane
                                    .showMessageDialog(null,
                                            "Your Taylor Hall room reservation was successful. Your room number is "
                                                    + resreserve.getRoom(),
                                            "Congratulations!!!", JOptionPane.DEFAULT_OPTION);
                                            break;
                        case "ELRH":
                            JOptionPane.showMessageDialog(null,
                                    "Your Elsa Leo Rhynie Hall room reservation was successful. Your room number is "
                                            + resreserve.getRoom(),
                                    "Congratulations!!!", JOptionPane.DEFAULT_OPTION);
                                    break;
                        case "RNH":
                            JOptionPane.showMessageDialog(null,
                                    "Your Rex Nettleford Hall room reservation was successful. Your room number is "
                                            + resreserve.getRoom(),
                                    "Congratulations!!!", JOptionPane.DEFAULT_OPTION);
                                    break;
                    }

                }
            }
            reserve.setVisible(false);
        }
    }

    /**
     * The function adds a resident to a list and writes the updated list to a file.
     * 
     * @param resident The parameter "resident" is an object of the class "Resident"
     *                 that is being
     *                 passed as an argument to the method "AddResident". This
     *                 object contains information about a
     *                 resident, such as their name, address, and other details. The
     *                 method adds this resident object
     *                 to a collection called "
     */
    public void AddResident(Resident resident) {
        ResidentList.add(resident);
        try {
            PrintStream out = new PrintStream(new FileOutputStream("resident.txt"));
            for (Resident r : ResidentList) {
                out.println(r);
            }
            out.close();
        } catch (FileNotFoundException fe) {
            System.out.print(fe.getMessage());
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actev) {
            setVisible(false);
        }
    }

    private class ButtonListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == two) {
                yr = 2;
                three.setSelected(false);
                four.setSelected(false);
                five.setSelected(false);
                six.setSelected(false);
                seven.setSelected(false);
                eight.setSelected(false);
            } else if (e.getSource() == three) {
                yr = 3;
                two.setSelected(false);
                four.setSelected(false);
                five.setSelected(false);
                six.setSelected(false);
                seven.setSelected(false);
                eight.setSelected(false);
            } else if (e.getSource() == four) {
                yr = 4;
                two.setSelected(false);
                three.setSelected(false);
                five.setSelected(false);
                six.setSelected(false);
                seven.setSelected(false);
                eight.setSelected(false);
            } else if (e.getSource() == five) {
                yr = 5;
                two.setSelected(false);
                three.setSelected(false);
                four.setSelected(false);
                six.setSelected(false);
                seven.setSelected(false);
                eight.setSelected(false);
            } else if (e.getSource() == six) {
                yr = 6;
                two.setSelected(false);
                three.setSelected(false);
                four.setSelected(false);
                five.setSelected(false);
                seven.setSelected(false);
                eight.setSelected(false);
            } else if (e.getSource() == seven) {
                yr = 7;
                two.setSelected(false);
                three.setSelected(false);
                four.setSelected(false);
                five.setSelected(false);
                six.setSelected(false);
                eight.setSelected(false);
            } else {
                yr = 8;
                two.setSelected(false);
                three.setSelected(false);
                four.setSelected(false);
                five.setSelected(false);
                six.setSelected(false);
                seven.setSelected(false);
            }
        }

    }

}
