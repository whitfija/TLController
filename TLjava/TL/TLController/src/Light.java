import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Light {

    private JPanel mainPanel;
    private JLabel lblTitle;
    private JButton btnLogin;
    private JLabel lblNotif;
    private JButton btnRegister;
    private JTextField txtBrand;
    private JLabel lblRoom;
    private JButton roomPrev;
    private JButton roomNext;
    private JTextField txtLight;

    private int homeID;
    private int roomID;
    ArrayList<Integer> rooms = new ArrayList<Integer>();
    private int roomPos;

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Light(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
    public Light(JFrame frame) {
        try {
            File log = new File("src/data/login.txt");
            Scanner scan = new Scanner(log);
            homeID = scan.nextInt();


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb", "root", "admin");
            Statement stmt = con.createStatement();

            ResultSet roomsSet = stmt.executeQuery("SELECT * FROM rooms WHERE homeid='" + homeID + "';");

            roomsSet.next();
            lblRoom.setText(roomsSet.getString(3));
            roomID = roomsSet.getInt(1);
            rooms.add(roomID);
            roomPos = 0;

            while (roomsSet.next())
                rooms.add(roomsSet.getInt(1));

        }catch(Exception e){
            System.out.println();
        }

        roomPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (rooms.size() > 0) {
                    if (roomPos > 0) {
                        roomPos -= 1;
                    } else {
                        roomPos = rooms.size() - 1;
                    }
                    roomID = rooms.get(roomPos);
                    changeRoom(roomID);
                }
            }
        });
        roomNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (rooms.size() > 0) {
                    if (roomPos < rooms.size() - 1) {
                        roomPos += 1;
                    } else {
                        roomPos = 0;
                    }
                    roomID = rooms.get(roomPos);
                    changeRoom(roomID);
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Main.main(null);
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    String name = txtLight.getText();
                    String brand = txtBrand.getText();

                    if(!name.equals("") && !brand.equals("")) {
                        dbRegister(brand, name);
                        txtBrand.setText("");
                        txtLight.setText("");
                    }
                    else{
                        lblNotif.setText("Invalid Information. Try Again");
                    }

                }catch(Exception e){
                    System.out.println(e);
                    lblNotif.setText("Invalid Information. Try Again");
                }
            }
        });
    }

    public void dbRegister(String brand, String name){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb","root","admin");
            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO lights (roomid, toggle, color, brand, name) VALUES ('"+ roomID +"', '0', 'default', '"+ brand +"', '"+ name +"');");

            con.close();

            lblNotif.setText("Room has been added");
        }catch(Exception e){
            System.out.println(e);
            lblNotif.setText("Invalid Information. Try Again");
        }
    }

    public void changeRoom(int roomID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb", "root", "admin");
            Statement stmt = con.createStatement();

            ResultSet roomsSet = stmt.executeQuery("SELECT * FROM rooms WHERE roomid='" + roomID + "';");
            roomsSet.next();

            try {
                lblRoom.setText(roomsSet.getString(3));
            }catch(Exception e){
                lblRoom.setText("No Room Available");
                System.out.println(e);
            }

        } catch(Exception e){
            System.out.println(e);
            lblRoom.setText("No Room Available");
        }
    }
}
