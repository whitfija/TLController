import javax.swing.*;

public class Wifi {

    private JPanel mainPanel;

    public Wifi(JFrame frame) {

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Wifi(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
}
