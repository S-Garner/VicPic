package src.Main.UI.Panels;

import src.Interfaces.Trigger;
import src.Main.UI.Buttons.VicButtons;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.RoundedPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ControlPanel {
    private RoundedPanel controlPanel;
    private VicButtons addVictim;
    private VicButtons deleteVictim;
    private VicButtons editVictim;
    private VicButtons editClass;
    private VicButtons settings;
    private VicButtons save;
    private VicButtons exit;

    public ControlPanel(CurrentUITheme theme) {
        controlPanel = new RoundedPanel(theme);
        //controlPanel.setMaximumSize(new Dimension(30, 175));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        Images imageGetter;

        Image image = null;

        imageGetter = new Images("plus", theme);
        addVictim = new VicButtons(imageGetter.getImage(), theme, 5);

        imageGetter = new Images("floppy", theme);
        save = new VicButtons(imageGetter.getImage(), theme, 5);

        imageGetter = new Images("X", theme);
        deleteVictim = new VicButtons(imageGetter.getImage(), theme, 5);

        imageGetter = new Images("person", theme);
        editVictim = new VicButtons(imageGetter.getImage(), theme, 5);

        imageGetter = new Images("people", theme);
        editClass = new VicButtons(imageGetter.getImage(), theme, 5);

        imageGetter = new Images("gear", theme);
        settings = new VicButtons(imageGetter.getImage(), theme, 5);

        imageGetter = new Images("door", theme);
        exit = new VicButtons(imageGetter.getImage(), theme, 5);

        controlPanel.add(addVictim.getPanel());
        controlPanel.add(deleteVictim.getPanel());
        controlPanel.add(editVictim.getPanel());
        controlPanel.add(editClass.getPanel());
        controlPanel.add(settings.getPanel());
        controlPanel.add(Box.createRigidArea(new Dimension(10, 90)));
        controlPanel.add(save.getPanel());
        controlPanel.add(exit.getPanel());
    }

    public RoundedPanel getPanel() {
        return controlPanel;
    }

    public void updateColors(CurrentUITheme currentTheme) {
        controlPanel.setBackground(currentTheme.getCurrentBackgroundColor().main());
        controlPanel.setForeground(currentTheme.getCurrentForegroundColor().main());
        // If there are other color attributes to update, do it here
        controlPanel.repaint(); // Refresh the component to apply new colors
    }

}
