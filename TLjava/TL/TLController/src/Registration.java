import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Integer.parseInt;


public class Registration {
    private JPanel mainPanel;
    private JLabel lblTitle;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JLabel lblNotif;
    private JButton btnRegister;
    private JTextField txtID;
    private JPasswordField txtConfirm;
    private JTextField txtEmail;
    private JTextField txtPhone1;
    private JTextField txtPhone2;
    private JTextField txtPhone3;
    private JTextField txtHome;

    public Registration(JFrame frame) {

        txtPhone1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
                if (txtPhone1.getText().length() >= 3 ) // limit textfield to 3 characters
                    keyEvent.consume();
            }
        });

        txtPhone2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
                if (txtPhone2.getText().length() >= 3 ) // limit textfield to 3 characters
                    keyEvent.consume();
            }
        });

        txtPhone3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
                if (txtPhone3.getText().length() >= 4 ) // limit textfield to 3 characters
                    keyEvent.consume();
            }
        });

        txtID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
                if (txtID.getText().length() >= 6 ) { // limit textfield to 3 characters
                    keyEvent.consume();
                }
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int deviceID = parseInt(txtID.getText());
                    String username = txtUser.getText();

                    char[] pswd = txtPass.getPassword();
                    String password = new String(pswd);
                    char[] pswd2 = txtConfirm.getPassword();
                    String pwCheck = new String(pswd2);

                    String home = txtHome.getText();
                    String email = txtEmail.getText();
                    int phone1 = parseInt(txtPhone1.getText());
                    int phone2 = parseInt(txtPhone2.getText());
                    int phone3 = parseInt(txtPhone3.getText());
                    if(!password.equals(pwCheck)){
                        lblNotif.setText("Passwords Do Not Match.");
                    }
                    else {
                        dbRegister(deviceID, username, password, home, email, phone1, phone2, phone3, frame);
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
                Home.main(null);
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Registration(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void dbRegister(int ID, String username, String password, String home, String email, int phone1, int phone2, int phone3, JFrame frame){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3308/templightdb","root","admin");
            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO users (deviceid, username, password, name, email, phone1, phone2, `phone3`) VALUES ('"+ ID +"', '"+ username +"', '"+ password +"', '"+ home +"', '"+ email +"', '"+ phone1 +"', '"+ phone2 +"', '"+ phone3 +"');");

            con.close();

            System.out.println("toSetup");

            // save id to file
            PrintWriter wr = new PrintWriter("data/login.txt");
            wr.print(ID);

            frame.setVisible(false);
            Main.main(null);
        }catch(Exception e){
            System.out.println(e);
            lblNotif.setText("Invalid Information. Try Again");
        }
        lblNotif.setText("Invalid Information. Try Again");
    }
}
