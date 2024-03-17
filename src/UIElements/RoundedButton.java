package src.UIElements;

import src.UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    private int cornerRadius = 50;
    private int borderThickness = 2;
    private int fontSize = 16;

    private CurrentUITheme current;

    public RoundedButton(String text, CurrentUITheme currentUITheme) {
        super(text);
        this.current = currentUITheme;
        this.setSize(new Dimension(350, 100));
        this.setPreferredSize(new Dimension(350, 100));

        // Use theme colors
        setBackground(currentUITheme.getCurrentBackgroundColor().main());
        hoverBackgroundColor = currentUITheme.getCurrentBackgroundColor().select();
        pressedBackgroundColor = currentUITheme.getCurrentBackgroundColor().action();
        setForeground(currentUITheme.getCurrentForegroundColor().main());

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        Border whiteline = BorderFactory.createLineBorder(currentUITheme.getCurrentForegroundColor().main(), 3);
        setBorder(whiteline);

        setFont(new Font(getFont().getName(), Font.BOLD, fontSize));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateButtonColors();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int red = 0, green = 0, blue = 0;
                red = 255 - currentUITheme.getCurrentForegroundColor().main().getRed();
                green = 255 - currentUITheme.getCurrentForegroundColor().main().getGreen();
                blue = 255 - currentUITheme.getCurrentForegroundColor().main().getBlue();
                Color tempForeground = new Color(red, green, blue);
                setBackground(pressedBackgroundColor);
                setForeground(tempForeground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(currentUITheme.getCurrentBackgroundColor().main());
                setForeground(currentUITheme.getCurrentForegroundColor().main());
            }
        });
    }

    private void updateButtonColors() {
        setBackground(current.getCurrentBackgroundColor().main());
        hoverBackgroundColor = current.getCurrentBackgroundColor().select();
        pressedBackgroundColor = current.getCurrentBackgroundColor().action();
        setForeground(current.getCurrentForegroundColor().main());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color thisColor = new Color(23,45,21);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw mock border
        g2.setColor(current.getCurrentForegroundColor().main());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius + 5, cornerRadius + 5);

        // Draw inner button
        int inset = borderThickness;
        if (getModel().isPressed()) {
            g2.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverBackgroundColor);
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(inset, inset, getWidth() - inset * 2, getHeight() - inset * 2, cornerRadius, cornerRadius);

        super.paintComponent(g2);
        g2.dispose();
    }

}
