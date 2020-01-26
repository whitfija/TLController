import javax.swing.*;

public class Registration {
    private JPanel mainPanel;
    private JButton button1;

    public Registration(JFrame frame) {

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Registration(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
}
