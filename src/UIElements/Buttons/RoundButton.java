package src.UIElements.Buttons;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import src.UIElements.Buttons.*;
import src.UIElements.Colors.CurrentUITheme;

public class RoundButton extends RoundedButton {
    private int cornerRadius;
    private int diameter;
    CurrentUITheme current;

    public RoundButton(Image image, CurrentUITheme currentUITheme) {

        super(image, currentUITheme);
        current = currentUITheme;


        this.cornerRadius = Math.min(image.getWidth(null), image.getHeight(null)) / 2;


        setPreferredSize(new Dimension(cornerRadius * 2, cornerRadius * 2));
    }

    public RoundButton(String text, CurrentUITheme currentUITheme) {
        super(text, currentUITheme);
        current = currentUITheme;


        this.diameter = 100;

        // Set the preferred size to the diameter
        setPreferredSize(new Dimension(diameter, diameter));

    }

    @Override
    protected void initButton(CurrentUITheme currentUITheme) {
        super.initButton(currentUITheme);
        // Specific initialization for RoundButton if necessary
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Paint the button as a circle
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the background color based on the button state
        if (getModel().isPressed()) {
            g2d.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g2d.setColor(hoverBackgroundColor);
        } else {
            g2d.setColor(getBackground());
        }

        // Draw the circle
        g2d.fillOval(0, 0, diameter, diameter);

        g2d.setColor(current.getCurrentForegroundColor().main()); // Replace with the actual border color
        g2d.setStroke(new BasicStroke(3)); // Set the border thickness
        g2d.drawOval(1, 1, diameter - 3, diameter - 3); // Draw the border slightly inside the edges

        // Draw the text
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle stringBounds = fm.getStringBounds(this.getText(), g2d).getBounds();

        int textX = (diameter - stringBounds.width) / 2;
        int textY = (diameter - stringBounds.height) / 2 + fm.getAscent();

        // Set the text color
        g2d.setColor(getForeground());
        g2d.drawString(getText(), textX, textY); // Draw the string

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(diameter, diameter);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    // The setBackground method will come from RoundedButton, which sets the hover and pressed colors.
}
