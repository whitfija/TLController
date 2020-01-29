import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private JPanel JPanel;
    private JPanel mainPanel;
    private JLabel lblHome;
    private JButton btnLight;
    private JButton btnRoom;
    private JButton btnSettings;
    private JButton btnDefault;
    private JButton btnGreen;
    private JButton btnBlue;
    private JButton btnRed;
    private JButton btnTempDown;
    private JButton btnTempUp;
    private JLabel lblTemp;
    private JLabel lblRoom;
    private JLabel lblLight;
    private JLabel lblStatus;
    private JLabel lblColor;
    private JButton roomPrev;
    private JButton roomNext;
    private JButton lightPrev;
    private JButton lightNext;
    private JButton btnOn;
    private JButton btnOff;

    private int roomID;
    private int homeID;
    private int lightID;
    private int roomPos;
    private int lightPos;
    ArrayList<Integer> rooms = new ArrayList<Integer>();
    ArrayList<Integer> lights = new ArrayList<Integer>();

    public Main(JFrame frame) {
        try{
            // read ID

            File log = new File("src/data/login.txt");
            Scanner scan = new Scanner(log);
            homeID = scan.nextInt();


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb","root","admin");
            Statement stmt=con.createStatement();

            ResultSet userinfo = stmt.executeQuery("SELECT * FROM users WHERE id='" + homeID + "';");
            userinfo.next();
            lblHome.setText(userinfo.getString(5));

            ResultSet roomsSet = stmt.executeQuery("SELECT * FROM rooms WHERE homeid='" + homeID + "';");

            roomsSet.next();
            lblRoom.setText(roomsSet.getString(3));
            lblTemp.setText(roomsSet.getString(4)+ "°F");
            roomID = roomsSet.getInt(1);
            rooms.add(roomID);
            roomPos = 0;

            while(roomsSet.next())
                rooms.add(roomsSet.getInt(1));


            ResultSet lightsSet = stmt.executeQuery("SELECT * FROM lights WHERE roomid='" + roomID + "';");
            lightsSet.next();

            lblLight.setText(lightsSet.getString(6));
            lblColor.setText(lightsSet.getString(4).toUpperCase());
            lightID = lightsSet.getInt(1);
            lights.add(lightID);
            lightPos = 0;

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

            while(lightsSet.next())
                lights.add(lightsSet.getInt(1));

            con.close();
            //rs.next()

        }catch (FileNotFoundException ex){
            System.out.println(ex);
        }catch(Exception e){
            System.out.println(e);
            //lblHome.setText("Error loading info.");
        }
        btnRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Room.main(null);
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
        btnTempDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int oldTemp = getTemp();
                int newTemp = oldTemp - 1;
                updateTemp(newTemp);
                lblTemp.setText(Integer.toString(newTemp) + "°F");
            }
        });
        btnTempUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int oldTemp = getTemp();
                int newTemp = oldTemp + 1;
                updateTemp(newTemp);
                lblTemp.setText(Integer.toString(newTemp) + "°F");
            }
        });
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
        lightNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")){
                    if (lightPos < lights.size() - 1) {
                        lightPos += 1;
                    } else {
                        lightPos = 0;
                    }
                    lightID = lights.get(lightPos);
                    changeLight(lightID);
                }
            }
        });
        lightPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")) {
                    if (lightPos > 0) {
                        lightPos -= 1;
                    } else {
                        lightPos = lights.size() - 1;
                    }
                    lightID = lights.get(lightPos);
                    changeLight(lightID);
                }
            }
        });
        btnDefault.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")){
                    updateColor("default");
                    lblColor.setText("DEFAULT");
                }
            }
        });
        btnRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")){
                    updateColor("red");
                    lblColor.setText("RED");
                }
            }
        });
        btnBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")){
                    updateColor("blue");
                    lblColor.setText("BLUE");
                }
            }
        });
        btnGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")){
                    updateColor("green");
                    lblColor.setText("GREEN");
                }
            }
        });
        btnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")){
                    updateState(0);
                    lblStatus.setText("OFF");
                }
            }
        });
        btnOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!lblLight.getText().equals("No Light Available")){
                    updateState(1);
                    lblStatus.setText("ON");
                }
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
            lblTemp.setText(roomsSet.getString(4) + "°F");

            ResultSet lightsSet = stmt.executeQuery("SELECT * FROM lights WHERE roomid='" + roomID + "';");
            lightsSet.next();
            lblLight.setText(lightsSet.getString(6));
            lblColor.setText(lightsSet.getString(4).toUpperCase());
            lightID = lightsSet.getInt(1);

            lights = new ArrayList<Integer>();
            lights.add(lightID);
            lightPos = 0;

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

            while(lightsSet.next())
                lights.add(lightsSet.getInt(1));


        } catch(Exception e){
            System.out.println(e);
            lblLight.setText("No Light Available");
            lblStatus.setText("--");
            lblColor.setText("--");
        }
    }

    public void changeLight(int lightID){
        if(lights.size() > 0) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb", "root", "admin");
                Statement stmt = con.createStatement();

                ResultSet lightsSet = stmt.executeQuery("SELECT * FROM lights WHERE lightid='" + lightID + "';");
                lightsSet.next();
                lblLight.setText(lightsSet.getString(6));
                lblColor.setText(lightsSet.getString(4).toUpperCase());

                int lightStatus = lightsSet.getInt(3);
                if (lightStatus == 0) {
                    lblStatus.setText("OFF");
                } else if (lightStatus == 1) {
                    lblStatus.setText("ON");
                } else {
                    lblStatus.setText("Unavailable");
                }

            } catch (Exception e) {
                System.out.println(e);
                lblLight.setText("No Light Available");
                lblStatus.setText("--");
                lblColor.setText("--");
            }
        }
    }

    public int getTemp(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb", "root", "admin");
            Statement stmt = con.createStatement();

            ResultSet roomsSet = stmt.executeQuery("SELECT * FROM rooms WHERE roomid='" + roomID + "';");
            roomsSet.next();
            int temp = Integer.parseInt(roomsSet.getString(4));
            return temp;
        } catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    public void updateTemp(int newTemp){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb", "root", "admin");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE  rooms SET temp = '" + newTemp + "' WHERE roomid='" + roomID + "';");
            con.close();
        }catch(Exception e){System.out.println(e);}
    }

    public void updateColor(String color){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb", "root", "admin");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE  lights SET color = '" + color + "' WHERE lightid='" + lightID + "';");
            con.close();
        }catch(Exception e){System.out.println(e);}
    }

    public void updateState(int status){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb", "root", "admin");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE  lights SET toggle = '" + status + "' WHERE lightid='" + lightID + "';");
            con.close();
        }catch(Exception e){System.out.println(e);}
    }
}
