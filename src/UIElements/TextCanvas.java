package src.UIElements;

// Currently under construction

import src.UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import java.awt.*;

public class TextCanvas extends JTextPane {
    private final CurrentUITheme current;
    private final Font font;

    public TextCanvas(CurrentUITheme current, int fontSize, boolean editable){
        this.current = current;
        font = new Font("SansSerif", Font.BOLD, fontSize);
        this.setBackground(this.current.getCurrentBackgroundColor().main());
        this.setForeground(this.current.getCurrentForegroundColor().main());
        this.setFont(font);
        this.setEditable(editable);
    }

    public void updateColors(CurrentUITheme currentTheme) {
        setBackground(currentTheme.getCurrentBackgroundColor().main());
        setForeground(currentTheme.getCurrentForegroundColor().main());
        // If there are other color attributes to update, do it here
        repaint(); // Refresh the component to apply new colors
    }

}
