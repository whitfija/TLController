import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// login, link to register, link to credits

public class Home {
    private JPanel mainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnForgot;
    private JLabel lblTitle;
    private JButton btnWifi;

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
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Home(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
}