import javax.swing.*;

public class Main {

    private JPanel mainPanel;

    public Main(JFrame frame) {

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Main(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
}
