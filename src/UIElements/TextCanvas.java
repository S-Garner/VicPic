package src.UIElements;

// Currently under construction

import src.UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import java.awt.*;

public class TextCanvas extends JTextPane {
    private final CurrentUITheme current;
    private final Font font;

    public TextCanvas(CurrentUITheme current){
        this.current = current;
        font = new Font("SansSerif", Font.BOLD, 18);
        this.setBackground(this.current.getCurrentBackgroundColor().main());
        this.setForeground(this.current.getCurrentForegroundColor().main());
        this.setFont(font);
    }

}
