package src.UIElements;

import src.UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius = 15;
    private CurrentUITheme current;

    public RoundedPanel(CurrentUITheme currentUITheme) {
        this.current = currentUITheme;

        // Use theme colors
        setBackground(currentUITheme.getCurrentBackgroundColor().main());
        setForeground(currentUITheme.getCurrentForegroundColor().main());

        setOpaque(false);

        Border roundedBorder = BorderFactory.createLineBorder(currentUITheme.getCurrentForegroundColor().main(), 3, true);
        setBorder(roundedBorder);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();
    }
}