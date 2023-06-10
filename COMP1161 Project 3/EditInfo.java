import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class EditInfo extends JFrame {
    private int ID;
    private JButton cmdCancel;
    private JTextField txtID;
    private JButton cmdEdit, cmdDelete;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private ArrayList<Resident> rslist;
    private EditInfo thisForm;
    private PrintStream out;
    private ResidentListing residentListing;

    public EditInfo(ResidentListing residentListing, ArrayList<Resident> rslist) {
        thisForm = this;
        this.residentListing = residentListing;
        this.rslist = rslist;
        setTitle("Edit/Delete Info");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        JLabel apHeader = new JLabel("Edit/Delete Student Information", SwingConstants.CENTER);
        apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
        apHeader.setAlignmentX(CENTER_ALIGNMENT);
        apHeader.setPreferredSize(new Dimension(720, 20));
        pnlDisplay.add(apHeader);

        pnlDisplay.add(new JLabel("Please enter the ID number of the student:"));
        txtID = new JTextField(12);
        pnlDisplay.add(txtID);

        cmdCancel = new JButton("Cancel");
        cmdEdit = new JButton("Edit Info");
        cmdDelete = new JButton("Delete Info");

        // set colour for buttons
        cmdEdit.setBackground(Color.GREEN);
        cmdDelete.setBackground(Color.YELLOW);
        cmdCancel.setBackground(Color.RED);
        cmdEdit.setForeground(Color.WHITE);
        cmdDelete.setForeground(Color.WHITE);
        cmdCancel.setForeground(Color.WHITE);

        pnlCommand.add(cmdEdit);
        pnlCommand.add(cmdDelete);
        pnlCommand.add(cmdCancel);

        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 600;
        int height = 200;
        setBounds(center.x - width / 2, center.y - height / 2, width, height);
        setPreferredSize(new Dimension(600, 200));
        pnlDisplay.setLayout(new GridLayout(4, 1));

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        cmdEdit.addActionListener(new EditButtonListener());
        cmdDelete.addActionListener(new DeleteButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());
    }

    private class EditButtonListener implements ActionListener {
        private Resident res;

        public void actionPerformed(ActionEvent actev) {
            boolean noerror = false;
            try {
                ID = Integer.parseInt(txtID.getText());
                int studdx = findRes(rslist, ID);
                res = rslist.get(studdx);
                if (studdx >= 0) {
                    noerror = true;
                }
            } catch (NumberFormatException numformerror) {
                System.out.println(numformerror.getMessage());
            } catch (ArrayIndexOutOfBoundsException indexouterror) {
                System.out.println(indexouterror.getMessage());
            } finally {
                if (noerror == true) {
                    EditLocalInfo edit = new EditLocalInfo(residentListing, res, rslist);
                } else {
                    JOptionPane.showMessageDialog(null,
                            ID + " is not a valid ID",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            setVisible(false);
        }
    }

    private class DeleteButtonListener implements ActionListener {
        private int studdx;

        public void actionPerformed(ActionEvent actev) {
            boolean noerror = false;
            try {
                ID = Integer.parseInt(txtID.getText());
                studdx = findRes(rslist, ID);
                if (studdx >= 0) {
                    noerror = true;
                    out = new PrintStream(new FileOutputStream("resident.txt"));
                }
            } catch (NumberFormatException numformerror) {
                System.out.println(numformerror.getMessage());
            } catch (ArrayIndexOutOfBoundsException indexouterror) {
                System.out.println(indexouterror.getMessage());
            } catch (FileNotFoundException filenotfound) {
                System.out.println(filenotfound.getMessage());
            } finally {
                if (noerror == true) {
                    rslist.remove(studdx);
                    for (Resident r : rslist) {
                        out.println(r);
                    }
                    residentListing.setVisible(false);
                    ResidentListing rs = new ResidentListing();
                    rs.showList();
                } else {
                    JOptionPane.showMessageDialog(null,
                            ID + " is not a valid ID",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            setVisible(false);
        }
    }

    public int findRes(ArrayList<Resident> res, int ID) {
        int pdx = -1;
        int currdx = 0;
        while ((currdx < res.size()) && (pdx == -1)) {
            if (res.get(currdx).getID() == ID)
                pdx = currdx;
            currdx++;
        }
        return pdx;
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}