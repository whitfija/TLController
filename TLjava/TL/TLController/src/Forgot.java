import javax.swing.*;

public class Forgot {

    private JPanel mainPanel;
    private JPanel panel1;

    public Forgot(JFrame frame) {

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Forgot(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
}