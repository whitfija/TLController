import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// login, link to register, link to credits

public class Home {
    private JPanel mainPanel;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnForgot;
    private JLabel lblTitle;
    private JButton btnWifi;
    private JLabel lblNotif;

    public Home(JFrame frame) {
        //
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Registration.main(null);
            }
        });
        btnWifi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Wifi.main(null);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String username = txtUser.getText();
                char[] pswd = txtPass.getPassword();
                String password = new String(pswd);
                dbLogin("select * from users", username, password, frame);
            }
        });
        btnForgot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                Forgot.main(null);
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Home(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }

    public void dbLogin(String SQLquery, String username, String password, JFrame frame){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://visidel.cau.edu:8080/templightdb","root","admin");
            System.out.println("hi");
            Statement stmt=con.createStatement();
            System.out.println("hi");
            ResultSet rs=stmt.executeQuery(SQLquery);
            System.out.println("hi");
            boolean LoggedIn = false;
            String currUser = "";
            String currPass = "";
            int currID;
            while(rs.next()) {
                //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
                if (LoggedIn) {
                    break;
                }

                currUser = rs.getString(3);
                currPass = rs.getString(4);
                currID = rs.getInt(1);
                if ((currUser.equals(username)) && (currPass.equals(password))) {
                    //System.out.println("LoggedIn");
                    LoggedIn = true;
                    System.out.println("toMain");

                    // save id to file
                    PrintWriter wr = new PrintWriter("src/data/login.txt");
                    wr.println(currID);
                    wr.close();

                    frame.setVisible(false);
                    Main.main(null);
                }
                else {
                    //System.out.println("noLogin");
                }
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        lblNotif.setText("Invalid Username/Password. Try Again");
    }
}
