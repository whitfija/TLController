import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Room {

    private JPanel mainPanel;
    private JLabel lblTitle;
    private JButton btnLogin;
    private JLabel lblNotif;
    private JButton btnRegister;
    private JTextField txtRoom;
    private JTextField txtTemp;
    private int homeID;

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Room(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
    public Room(JFrame frame) {
        txtTemp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
                if (txtTemp.getText().length() >= 2 ) // limit textfield to 3 characters
                    keyEvent.consume();
            }
        });

        try {
            File log = new File("src/data/login.txt");
            Scanner scan = new Scanner(log);
            homeID = scan.nextInt();
        } catch(Exception e){
            System.out.println(e);
        }

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int temp = parseInt(txtTemp.getText());
                    String name = txtRoom.getText();

                    if(!name.equals("")) {
                        dbRegister(name, temp);
                        txtTemp.setText("");
                        txtRoom.setText("");
                    }
                    else{
                        lblNotif.setText("Invalid Information. Try Again");
                        System.out.println(name);
                    }

                }catch(Exception e){
                    System.out.println(e);
                    lblNotif.setText("Invalid Information. Try Again");
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
    }

    public void dbRegister(String name, int temp){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb","root","admin");
            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO rooms (homeid, name, temp) VALUES ('"+ homeID +"', '"+ name +"', '"+ temp +"');");

            con.close();

            lblNotif.setText("Room has been added");
        }catch(Exception e){
            System.out.println(e);
            lblNotif.setText("Invalid Information. Try Again");
        }
    }
}
