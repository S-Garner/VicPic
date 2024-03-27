package src.UIElements.Buttons;

import src.UIElements.Colors.CurrentUITheme;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeldButton extends RoundButton {
    private boolean isHeld = false;

    public HeldButton(String text, CurrentUITheme currentUITheme) {
        super(text, currentUITheme);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isHeld = true;
                setBackground(pressedBackgroundColor);  // Use the pressed background color
                // You might want to set a different foreground or border here as well
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isHeld = false;
                setBackground(currentUITheme.getCurrentBackgroundColor().main()); // Restore the original background
                // Restore other styles if needed
                updateButtonColors(); // This method will reset the colors based on the current theme
            }
        });
    }

    public boolean isHeld() {
        return isHeld;
    }

    // Optional: Override paintComponent if you want the HeldButton to have a different appearance
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // You can add custom painting code here if you want to change how the button looks when held
    }
}
