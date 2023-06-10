import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AdminLogin extends JFrame {
    private String fname, lname, password;
    private int ID;
    private JTextField txtPassword, txtID, txtName;
    private JButton cmdCancel;
    private JButton cmdSubmit;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private AdminLogin thisForm;
    private ArrayList<Admin> admins;

    public AdminLogin(Application app) {
        admins = loadPasswords("password.txt");
        thisForm = this;
        setTitle("Admin Log In");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 650;
        int height = 400;
        setBounds(center.x - width / 2, center.y - height / 2, width, height);

        JLabel apHeader = new JLabel("Admin Log In To See List of Students Applying for/ Reserving a Hall",
                SwingConstants.CENTER);
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        apHeader.setAlignmentX(CENTER_ALIGNMENT);
        apHeader.setPreferredSize(new Dimension(720, 20));
        pnlDisplay.add(apHeader);

        pnlDisplay.add(new JLabel("Name:"));
        txtName = new JTextField(36);
        pnlDisplay.add(txtName);

        pnlDisplay.add(new JLabel("ID:"));
        txtID = new JTextField(12);
        pnlDisplay.add(txtID);

        pnlDisplay.add(new JLabel("Password:"));
        txtPassword = new JTextField(10);
        pnlDisplay.add(txtPassword);

        cmdCancel = new JButton("Cancel");
        cmdSubmit = new JButton("Submit");

        // set colour for buttons
        cmdSubmit.setBackground(Color.GREEN);
        cmdCancel.setBackground(Color.RED);
        cmdSubmit.setForeground(Color.WHITE);
        cmdCancel.setForeground(Color.WHITE);

        pnlCommand.add(cmdCancel);
        pnlCommand.add(cmdSubmit);

        setPreferredSize(new Dimension(700, 400));
        pnlDisplay.setLayout(new GridLayout(8, 1));

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());
    }

    private ArrayList<Admin> loadPasswords(String pfile) {
        Scanner scan;
        ArrayList<Admin> admns = new ArrayList<Admin>();
        try {
            scan = new Scanner(new File(pfile));
            while (scan.hasNext()) {
                String[] pw = scan.nextLine().split(" ");
                String fname = pw[0];
                String lname = pw[1];
                int ID = Integer.parseInt(pw[2]);
                String pword = pw[3];
                Admin admin = new Admin(fname, lname, ID, pword);
                admns.add(admin);
            }
        } catch (FileNotFoundException fe) {
            System.out.println("Password file not found");
        }
        return admns;
    }

    private boolean isAdmitted(Admin admin) {
        boolean bln = false;
        for (Admin admn : admins) {
            if ((admn.getFName().equals(admin.getFName())) && (admn.getLName().equals(admin.getLName()))
                    && (admn.getID() == admin.getID()) && (admn.getPassword().equals(admin.getPassword()))) {
                bln = true;
                break;
            }
        }
        return bln;
    }

    private class SubmitButtonListener implements ActionListener {
        private ResidentListing rslist;

        public void actionPerformed(ActionEvent actev) {
            boolean enter = false;
            try {
                fname = txtName.getText().split(" ")[0];
                lname = txtName.getText().split(" ")[1];
                ID = Integer.parseInt(txtID.getText());
                password = txtPassword.getText();
                Admin an = new Admin(fname, lname, ID, password);
                enter = isAdmitted(an);
            } catch (NumberFormatException numformerror) {
                System.out.println(numformerror.getMessage());
            } catch (ArrayIndexOutOfBoundsException indexouterror) {
                System.out.println(indexouterror.getMessage());
            } finally {
                if (enter == true) {
                    rslist = new ResidentListing();
                    rslist.showList();
                } else {
                    JOptionPane.showMessageDialog(null, "Access Denied!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    thisForm.setVisible(true);
                }
            }
            thisForm.setVisible(false);
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);

        }
    }
}