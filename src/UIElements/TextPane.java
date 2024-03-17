package src.UIElements;

import src.UIElements.Colors.CurrentUITheme;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

// Moving away from this, need to redesign

public class TextPane extends JTextPane {
    private CurrentUITheme current;
    private Font font;
    private SimpleAttributeSet attributes;
    private StyledDocument doc;

    public TextPane(CurrentUITheme current, int fontSize, boolean editable){
        this.current = current;
        this.setEditable(editable);
        font = new Font("SansSerif", Font.BOLD, fontSize);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setForeground(current.getCurrentForegroundColor().main());
        this.setFont(font);
    }

}