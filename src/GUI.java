import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class GUI extends JFrame {

    public GUI() {

        ImageIcon picture = new ImageIcon("src/image.png");
        Border border = BorderFactory.createLineBorder(Color.green, 10);
        JLabel label = new JLabel();
        label.setText("this is the text i want");
        label.setIcon(picture);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setForeground(new Color(0, 0, 150));
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label.setBackground(Color.black);// sets background color
        label.setOpaque(true);// displays the background color
        label.setBorder(border);

        this.setTitle("This is the title");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(420, 420);// sets the size of the frame

        this.setVisible(true);
        ImageIcon image = new ImageIcon("src/image.png");// creates an image icon
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(123, 50, 250));// the values after color can be hex any prime or
                                                                     // secondary colors and decimal
        this.add(label);
    }
}
