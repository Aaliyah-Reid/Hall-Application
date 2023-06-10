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
//import java.io.IOException;
//import java.util.Scanner;
//import java.io.File;

public class EditLocalInfo extends JFrame {
    private String name, tphone, email, type, grad, facul, major;
    private int yr, age, ID;
    private JTextField txtMajor, txtID, txtName, txtAge, txtPhoneNum, txtEmail;
    private JComboBox<String> faculty;
    private JComboBox<Integer> Year;
    private JRadioButton underLevel, postLevel, newbtn, rtnbtn;
    private JButton cmdCancel, cmdSubmit;
    private JPanel pCommand, pDisplay;
    private Resident res;
    private ArrayList<Resident> rslist;
    private EditLocalInfo thisform;
    private PrintStream out;
    private ResidentListing resList;

    public EditLocalInfo(ResidentListing resList, Resident res, ArrayList<Resident> rslist) {
        thisform = this;
        this.res = res;
        this.rslist = rslist;
        this.resList = resList;

        setTitle("Edit Info");
        pCommand = new JPanel();
        pDisplay = new JPanel();

        // to display Application Header
        JLabel apHeader = new JLabel("Hall of Residence Update Local Info", SwingConstants.CENTER);
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
        Integer[] Years = { 2, 3, 4, 5, 6, 7, 8 };
        Year = new JComboBox<>(Years);
        pDisplay.add(Year);
        Year.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    yr = Integer.parseInt(Year.getSelectedItem().toString().replaceAll("\\s", ""));
                }
                ;
            }
        });

        pDisplay.setLayout(new GridLayout(31, 1));
        cmdCancel = new JButton("Cancel");
        cmdSubmit = new JButton("Submit");

        // set colour for buttons
        cmdSubmit.setBackground(Color.GREEN);
        cmdCancel.setBackground(Color.RED);
        cmdSubmit.setForeground(Color.WHITE);
        cmdCancel.setForeground(Color.WHITE);

        pCommand.add(cmdCancel);
        pCommand.add(cmdSubmit);

        setPreferredSize(new Dimension(1545, 825));

        add(pDisplay, BorderLayout.CENTER);
        add(pCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());

        underLevel.addActionListener(new ButtonListener1());
        postLevel.addActionListener(new ButtonListener1());

        newbtn.addActionListener(new ButtonListener2());
        rtnbtn.addActionListener(new ButtonListener2());

    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actev) {

            boolean noerror = false;
            try {
                name = txtName.getText();
                email = txtEmail.getText();
                major = txtMajor.getText().replaceAll("\\s", "");
                ;
                age = Integer.parseInt(txtAge.getText());
                ID = Integer.parseInt(txtID.getText());
                tphone = txtPhoneNum.getText();
                noerror = true;
                out = new PrintStream(new FileOutputStream("resident.txt"));
            } catch (NumberFormatException numformerror) {
                System.out.println(numformerror.getMessage());
            } catch (ArrayIndexOutOfBoundsException indexouterror) {
                System.out.println(indexouterror.getMessage());
            } catch (FileNotFoundException filenotfound) {
                System.out.println(filenotfound.getMessage());
            } finally {
                if (noerror == true) {
                    res.setName(name);
                    res.setEmail(email);
                    res.setMajor(major);
                    res.setAge(age);
                    res.setID(ID);
                    res.setPhoneNum(tphone);
                    res.setType(type);
                    res.setLevel(grad);
                    res.setFaculty(facul);
                    res.setYear(yr);
                    for (Resident r : rslist) {
                        out.println(r);
                    }
                    thisform.setVisible(false);
                    resList.setVisible(false);
                    ResidentListing rsl = new ResidentListing();
                    rsl.showList();
                }
            }

        }
    }

    private class ButtonListener1 implements ActionListener {
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
            if (e.getSource() == newbtn) {
                Year.setEnabled(false);
                yr = 1;
                type = "New";
            } else {
                Year.setEnabled(true);
                type = "Returning";
            }
        }

    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actev) {
            setVisible(false);
        }
    }

}