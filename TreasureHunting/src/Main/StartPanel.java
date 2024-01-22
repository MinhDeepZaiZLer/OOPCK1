package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartPanel extends JPanel {

    private static final long serialVersionUID = 1L; // Added for serialization warning

    private ImageIcon bg;

    public StartPanel() {
        this.setPreferredSize(new Dimension(768, 576));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        // Adjust the path based on your project structure
        bg = new ImageIcon(getClass().getResource("/background/start.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw the background image
        g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
