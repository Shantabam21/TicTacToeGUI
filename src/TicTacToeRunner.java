import javax.swing.*;
import java.awt.*;

public class TicTacToeRunner {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = (int) dimension.getWidth() * 3/4;
        int height = (int) dimension.getHeight() * 3/4;
        JFrame frame = new TicTacToeFrame();
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
