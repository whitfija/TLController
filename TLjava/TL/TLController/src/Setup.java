import javax.swing.*;

public class Setup {

    private JPanel mainPanel;

    public Setup(JFrame frame) {

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Setup(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }
}
