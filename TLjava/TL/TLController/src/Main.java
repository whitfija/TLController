import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private JPanel JPanel;
    private JPanel mainPanel;
    private JLabel lblHome;
    private JButton btnLight;
    private JButton btnTemp;
    private JButton btnSettings;
    private JButton DEFAULTButton;
    private JButton GREENButton;
    private JButton BLUEButton;
    private JButton REDButton;
    private JButton button5;
    private JButton button6;
    private JLabel lblTemp;
    private JLabel lblRoom;
    private JLabel lblLight;
    private JLabel lblStatus;
    private JLabel lblColor;

    public Main(JFrame frame) {
        try{
            // read ID

            File log = new File("src/data/login.txt");
            Scanner scan = new Scanner(log);
            int homeID = scan.nextInt();


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb","root","admin");
            Statement stmt=con.createStatement();

            ResultSet roomsSet = stmt.executeQuery("SELECT * FROM rooms WHERE homeid='" + homeID + "';");
            roomsSet.next();
            lblRoom.setText(roomsSet.getString(3));
            lblTemp.setText(roomsSet.getString(4));
            int roomID = roomsSet.getInt(1);

            ResultSet userinfo = stmt.executeQuery("SELECT * FROM users WHERE id='" + homeID + "';");
            userinfo.next();
            lblHome.setText(userinfo.getString(5));

            ResultSet lightsSet = stmt.executeQuery("SELECT * FROM lights WHERE roomid='" + roomID + "';");
            lightsSet.next();
            lblLight.setText(lightsSet.getString(6));
            lblColor.setText(lightsSet.getString(4).toUpperCase());
            int lightStatus = lightsSet.getInt(3);
            if(lightStatus == 0){
                lblStatus.setText("OFF");
            }
            else if(lightStatus == 1){
                lblStatus.setText("ON");
            }
            else{
                lblStatus.setText("Unavailable");
            }
            con.close();
            //rs.next()

        }catch (FileNotFoundException ex){
            System.out.println(ex);
        }catch(Exception e){
            System.out.println(e);
            //lblHome.setText("Error loading info.");
        }
        btnTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Temp.main(null);
            }
        });
        btnLight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Light.main(null);
            }
        });
        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Settings.main(null);
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Main(frame).JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

}
