import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Dashboard {
    private JPanel panelMain;
    private JLabel labelTeqIcon;
    private JLabel labelAdminDashboard;

    public static void main(String [] args){
        Dashboard db = new Dashboard();
        JFrame frame = new JFrame("TEQ Dashboard");
        JPanel panelMain = db.panelMain;
        frame.setContentPane(panelMain);
        panelMain.setBorder(new EmptyBorder(10,10,10,10));
        JLabel labelAdminDashboard = db.labelAdminDashboard;


        frame.pack();
        frame.setVisible(true);



    }
}
