package src.Main.UI.Panels;

import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserStats extends RoundedPanel {
    public UserStats(CurrentUITheme theme) {
        super(theme);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        CurrentUITheme themeInvert = new CurrentUITheme(theme.getForegroundString(), theme.getBackgroundString());

        Font labelFont = new Font("SansSerif", Font.BOLD, 18);

        // Create labels and text fields for the first column
        String[] labelsTextLeft = {"Name", "Points", "Answered", "Passed"};
        for (int i = 0; i < labelsTextLeft.length; i++) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            JLabel labelLeft = new JLabel(labelsTextLeft[i]);
            labelLeft.setForeground(theme.getCurrentForegroundColor().main());
            labelLeft.setFont(labelFont); // Set the font here
            add(labelLeft, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.weightx = 1.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            TextCanvas rightTextField = new TextCanvas(themeInvert, 18, false);
            rightTextField.setColumns(10);
            rightTextField.setBorder(new LineBorder(themeInvert.getCurrentBackgroundColor().main()));
            add(rightTextField, gbc);
        }

        // Create labels and text fields for the second column
        String[] labelsTextRight = {"Nickname", "Absents", "Times Picked", "Influence"};
        for (int i = 0; i < labelsTextRight.length; i++) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 2;
            gbc.gridy = i;
            gbc.weightx = 0.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            JLabel labelLeft = new JLabel(labelsTextRight[i]);
            labelLeft.setForeground(theme.getCurrentForegroundColor().main());
            labelLeft.setFont(labelFont); // Set the font here
            add(labelLeft, gbc);

            gbc.gridx = 3;
            gbc.gridy = i;
            gbc.weightx = 1.5;
            gbc.insets = new Insets(2, 2, 2, 2);
            add(new JTextField(10), gbc);
        }
    }
}
