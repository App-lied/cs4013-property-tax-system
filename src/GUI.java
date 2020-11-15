package src;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {

    public GUI() {

        ImageIcon picture = new ImageIcon("picture.jpg");
        JLabel label = new JLabel();
        label.setText("this is the text i want");
        label.setIcon(picture);

        this.setTitle("This is the title");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(420, 420);// sets the size of the frame

        this.setVisible(true);
        ImageIcon image = new ImageIcon("logo.png");// creates an image icon
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(123, 50, 250));// the values after color can be hex any prime or
                                                                     // secondary colors and decimal
        this.add(label);
    }
}
