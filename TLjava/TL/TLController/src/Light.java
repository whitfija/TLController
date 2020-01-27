import javax.swing.*;

public class Light {

    private JPanel mainPanel;

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Light(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
    public Light(JFrame frame) {

    }
}
