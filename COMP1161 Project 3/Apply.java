import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Apply extends JFrame {
    private String name, gender, tphone, email, type, grad, facul, major, option1, option2, option3, hall, room;
    private int bdgt, yr, age, ID;
    private JTextField txtMajor, txtID, txtName, txtAge, txtPhoneNum, txtEmail, txtBudget;
    private JCheckBox rexBox, rexBox2, rexBox3, taylorBox, taylorBox2, taylorBox3, elsaBox, elsaBox2, elsaBox3;
    private JCheckBox one, two, three, four, five, six, seven, eight;
    private JComboBox<String> faculty;
    private JRadioButton underLevel, postLevel, newbtn, rtnbtn, fbutton, mbutton;
    private JButton cmdCancel, cmdSubmit;
    private JPanel pCommand, pDisplay;
    private JScrollPane scrollPane;
    private Apply apply;
    private ArrayList<String> PrefHalls;
    private ArrayList<Resident> ResidentList = new ArrayList<Resident>();
    private TaylorHall th = new TaylorHall();
    private ElsaLeoRhynieHall eh = new ElsaLeoRhynieHall();
    private RexNettlefordHall rh = new RexNettlefordHall();

    public Apply(Application app) {
        apply = this;
        PrefHalls = new ArrayList<String>();

        ResidentList = loadResidents("resident.txt");

        setTitle("Application Form For New And Returning Students");
        pCommand = new JPanel();
        pDisplay = new JPanel();

        // to display Application Header
        JLabel apHeader = new JLabel("Hall of Residence Application Form for New and Returning Students",
                SwingConstants.CENTER);
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        pDisplay.add(apHeader);

        pDisplay.add(new JLabel("Name:"));
        txtName = new JTextField(30);
        pDisplay.add(txtName);

        pDisplay.add(new JLabel("ID:"));
        txtID = new JTextField(9);
        pDisplay.add(txtID);

        pDisplay.add(new JLabel("Age:"));
        txtAge = new JTextField(3);
        pDisplay.add(txtAge);

        // to add Buttons to select sex
        pDisplay.add(new JLabel("Sex:"));
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
        pDisplay.add(pnl);

        pDisplay.add(new JLabel("Telephone: (example: 876-123-4567)"));
        txtPhoneNum = new JTextField(10);
        pDisplay.add(txtPhoneNum);

        pDisplay.add(new JLabel("Email: "));
        txtEmail = new JTextField();
        pDisplay.add(txtEmail);

        pDisplay.add(new JLabel("Are you a new or returning student?"));
        Panel pnll = new Panel(new FlowLayout(FlowLayout.LEFT));
        newbtn = new JRadioButton("New");
        newbtn.setHorizontalAlignment(SwingConstants.LEFT);
        rtnbtn = new JRadioButton("Returning");
        rtnbtn.setHorizontalAlignment(SwingConstants.LEFT);
        ButtonGroup btg = new ButtonGroup();
        btg.add(newbtn);
        btg.add(rtnbtn);
        pnll.add(newbtn);
        pnll.add(rtnbtn);
        pDisplay.add(pnll);

        // radio button to select graduate level
        pDisplay.add(new JLabel("Are you an undergraduate or postgraduate student?"));
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
        pDisplay.add(panel);

        // add dropbox here to display faculties

        pDisplay.add(new JLabel("Choose your Faculty:"));
        String[] faculties = { "Engineering", "Science & Technology", "Medical Sciences", "Law",
                "Social Sciences", "Sport", "Hiumanities & Education" };
        faculty = new JComboBox<>(faculties);
        pDisplay.add(faculty);
        faculty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    facul = faculty.getSelectedItem().toString().replaceAll("\\s", "");
                }
                ;
            }
        });

        // to display the labels for Major,Telphone, Email,Budget and take input
        pDisplay.add(new JLabel("Major: "));
        txtMajor = new JTextField(20);
        pDisplay.add(txtMajor);

        pDisplay.add(new JLabel("Select your year:"));
        Panel panll = new Panel(new FlowLayout(FlowLayout.LEFT));
        one = new JCheckBox("1");
        two = new JCheckBox("2");
        three = new JCheckBox("3");
        four = new JCheckBox("4");
        five = new JCheckBox("5");
        six = new JCheckBox("6");
        seven = new JCheckBox("7");
        eight = new JCheckBox("8");
        panll.add(one);
        panll.add(two);
        panll.add(three);
        panll.add(four);
        panll.add(five);
        panll.add(six);
        panll.add(seven);
        panll.add(eight);
        pDisplay.add(panll);

        pDisplay.add(new JLabel("Choose your Hall Preference (select in order of 1st, 2nd and 3rd):"));
        Panel p = new Panel(new FlowLayout(FlowLayout.LEFT));
        Panel pl = new Panel(new FlowLayout(FlowLayout.LEFT));
        Panel p2 = new Panel(new FlowLayout(FlowLayout.LEFT));
        rexBox = new JCheckBox("Rex Nettleford Hall");
        rexBox2 = new JCheckBox("Rex Nettleford Hall");
        rexBox3 = new JCheckBox("Rex Nettleford Hall");
        taylorBox = new JCheckBox("Taylor Hall");
        taylorBox2 = new JCheckBox("Taylor Hall");
        taylorBox3 = new JCheckBox("Taylor Hall");
        elsaBox = new JCheckBox("Elsa Leo Rhynie Hall");
        elsaBox2 = new JCheckBox("Elsa Leo Rhynie Hall");
        elsaBox3 = new JCheckBox("Elsa Leo Rhynie Hall");
        p.add(rexBox);
        p.add(taylorBox);
        p.add(elsaBox);
        pl.add(rexBox2);
        pl.add(taylorBox2);
        pl.add(elsaBox2);
        p2.add(rexBox3);
        p2.add(taylorBox3);
        p2.add(elsaBox3);
        pDisplay.add(new JLabel("Option 1:"));
        pDisplay.add(p);
        pDisplay.add(new JLabel("Option 2:"));
        pDisplay.add(pl);
        pDisplay.add(new JLabel("Option 3:"));
        pDisplay.add(p2);

        pDisplay.add(new JLabel("Budget (Rex: $100000; Taylor: $150000; Elsa: $200000):"));
        txtBudget = new JTextField(20);
        pDisplay.add(txtBudget);

        GridLayout layout = new GridLayout(32, 2);
        pDisplay.setLayout(layout);
        cmdCancel = new JButton("Cancel");
        cmdSubmit = new JButton("Submit");

        // set colour for buttons
        cmdSubmit.setBackground(Color.GREEN);
        cmdCancel.setBackground(Color.RED);
        cmdSubmit.setForeground(Color.WHITE);
        cmdCancel.setForeground(Color.WHITE);

        pCommand.add(cmdCancel);
        pCommand.add(cmdSubmit);

        setPreferredSize(new Dimension(1540, 825));

        add(pDisplay, BorderLayout.CENTER);
        add(pCommand, BorderLayout.SOUTH);
        scrollPane = new JScrollPane(pDisplay);
        getContentPane().add(scrollPane);

        pack();
        setVisible(true);

        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());

        ButtonListener btnListen = new ButtonListener();
        rexBox.addActionListener(btnListen);
        taylorBox.addActionListener(btnListen);
        elsaBox.addActionListener(btnListen);

        rexBox2.addActionListener(new ButtonListener2());
        taylorBox2.addActionListener(new ButtonListener2());
        elsaBox2.addActionListener(new ButtonListener2());

        rexBox3.addActionListener(new ButtonListener3());
        taylorBox3.addActionListener(new ButtonListener3());
        elsaBox3.addActionListener(new ButtonListener3());

        underLevel.addActionListener(new ButtonListener4());
        postLevel.addActionListener(new ButtonListener4());

        newbtn.addActionListener(new ButtonListener5());
        rtnbtn.addActionListener(new ButtonListener5());

        fbutton.addActionListener(new ButtonListener6());
        mbutton.addActionListener(new ButtonListener6());

        one.addActionListener(new ButtonListener7());
        two.addActionListener(new ButtonListener7());
        three.addActionListener(new ButtonListener7());
        four.addActionListener(new ButtonListener7());
        five.addActionListener(new ButtonListener7());
        six.addActionListener(new ButtonListener7());
        seven.addActionListener(new ButtonListener7());
        eight.addActionListener(new ButtonListener7());

    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actev) {

            boolean noerror = false;
            try {
                name = txtName.getText();
                email = txtEmail.getText();
                major = txtMajor.getText().replaceAll("\\s", "");
                age = Integer.parseInt(txtAge.getText());
                ID = Integer.parseInt(txtID.getText());
                bdgt = Integer.parseInt(txtBudget.getText());
                tphone = txtPhoneNum.getText();
                PrefHalls.add(option1);
                PrefHalls.add(option2);
                PrefHalls.add(option3);
                String[] isAvailable = CheckAvailability(PrefHalls).split(" ");
                if (isAvailable.length == 2) {
                    hall = isAvailable[0];
                    room = isAvailable[1];
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
                    Resident resapply = new Resident(name, ID, age, gender, tphone, email, yr, type, grad, facul, major,
                            hall, room);
                    AddResident(resapply);
                    switch (resapply.getHall()) {
                        case "TH":
                            JOptionPane
                                    .showMessageDialog(null,
                                            "Your application to Taylor Hall was successful. Your room number is "
                                                    + resapply.getRoom(),
                                            "Congratulations!!!", JOptionPane.DEFAULT_OPTION);
                                            break;
                        case "ELRH":
                            JOptionPane.showMessageDialog(null,
                                    "Your application to Elsa Leo Rhynie Hall was successful. Your room number is "
                                            + resapply.getRoom(),
                                    "Congratulations!!!", JOptionPane.DEFAULT_OPTION);
                                    break;
                        case "RNH":
                            JOptionPane.showMessageDialog(null,
                                    "Your application to Rex Nettleford Hall was successful. Your room number is "
                                            + resapply.getRoom(),
                                    "Congratulations!!!", JOptionPane.DEFAULT_OPTION);
                                    break;
                    }

                }
            }
            apply.setVisible(false);
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

    public String CheckAvailability(ArrayList<String> lst) {
        String available = "You Cannot Be Assigned A Room";
        String check;
        for (int i = 0; i < lst.size(); i++) {
            String hall = lst.get(i);
            switch (hall) {
                case "Taylor Hall":
                    check = th.bookRoom(th.getGender(gender), bdgt, "resident.txt");
                    if (check.charAt(0) == 'T') {
                        available = check;
                        return available;
                    } else {
                        continue;
                    }
                case "Elsa Leo Rhynie Hall":
                    check = eh.bookRoom(eh.getGender(gender), bdgt, "resident.txt");
                    if (check.charAt(0) == 'E') {
                        available = check;
                        return available;
                    } else {
                        continue;
                    }
                case "Rex Nettleford Hall":
                    check = rh.bookRoom(rh.getGender(gender), bdgt, "resident.txt");
                    if (check.charAt(0) == 'R') {
                        available = check;
                        return available;
                    } else {
                        continue;
                    }
            }
        }
        return available;
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

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent u) {
            if (u.getSource() == rexBox) {
                option1 = "Rex Nettleford Hall";
                taylorBox.setSelected(false);
                elsaBox.setSelected(false);
            } else if (u.getSource() == taylorBox) {
                option1 = "Taylor Hall";
                rexBox.setSelected(false);
                elsaBox.setSelected(false);
            } else {
                option1 = "Elsa Leo Rhynie Hall";
                taylorBox.setSelected(false);
                rexBox.setSelected(false);
            }
        }
    }

    private class ButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent t) {
            if (t.getSource() == rexBox2) {
                option2 = "Rex Nettleford Hall";
                taylorBox2.setSelected(false);
                elsaBox2.setSelected(false);
            } else if (t.getSource() == taylorBox2) {
                option2 = "Taylor Hall";
                rexBox2.setSelected(false);
                elsaBox2.setSelected(false);
            } else {
                option2 = "Elsa Leo Rhynie Hall";
                taylorBox2.setSelected(false);
                rexBox2.setSelected(false);
            }

        }
    }

    private class ButtonListener3 implements ActionListener {
        public void actionPerformed(ActionEvent t) {
            if (t.getSource() == rexBox3) {
                option3 = "Rex Nettleford Hall";
                taylorBox3.setSelected(false);
                elsaBox3.setSelected(false);
            } else if (t.getSource() == taylorBox3) {
                option3 = "Taylor Hall";
                rexBox3.setSelected(false);
                elsaBox3.setSelected(false);
            } else {
                option3 = "Elsa Leo Rhynie Hall";
                taylorBox3.setSelected(false);
                rexBox3.setSelected(false);
            }

        }
    }

    private class ButtonListener4 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == underLevel) {
                grad = "Undergraduate";
            } else {
                grad = "Postgraduate";
            }
        }

    }

    private class ButtonListener5 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newbtn) {
                one.setEnabled(false);
                two.setEnabled(false);
                three.setEnabled(false);
                four.setEnabled(false);
                five.setEnabled(false);
                six.setEnabled(false);
                seven.setEnabled(false);
                eight.setEnabled(false);
                one.setSelected(true);
                two.setSelected(false);
                three.setSelected(false);
                four.setSelected(false);
                five.setSelected(false);
                six.setSelected(false);
                seven.setSelected(false);
                eight.setSelected(false);
                yr = 1;
                type = "New";
            } else {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(true);
                five.setEnabled(true);
                six.setEnabled(true);
                seven.setEnabled(true);
                eight.setEnabled(true);
                one.setSelected(false);
                type = "Returning";
            }
        }

    }

    private class ButtonListener6 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == fbutton) {
                gender = "female";
            } else {
                gender = "male";
            }
        }

    }

    private class ButtonListener7 implements ActionListener {
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
