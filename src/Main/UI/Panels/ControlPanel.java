package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class ControlPanel {
    private RoundedPanel controlPanel;
    private VicFormatter addVictim;
    private VicFormatter deleteVictim;
    private VicFormatter editVictim;
    private VicFormatter editClass;
    private VicFormatter settings;
    private VicFormatter save;
    private VicFormatter exit;
    private VicFormatter topPanel;

    public ControlPanel(CurrentUITheme theme) {
        controlPanel = new RoundedPanel(theme);
        //controlPanel.setMaximumSize(new Dimension(30, 175));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        int buffDistance = 5;
        Images imageGetter;

        Image image = null;

        imageGetter = new Images("plus", theme);
        RoundButton addVic = new RoundButton(imageGetter.getImage(), theme);
        addVictim = new VicFormatter(addVic, buffDistance);

        imageGetter = new Images("floppy", theme);
        RoundButton saveV = new RoundButton(imageGetter.getImage(), theme);
        save = new VicFormatter(saveV, buffDistance);

        imageGetter = new Images("X", theme);
        RoundButton delVic = new RoundButton(imageGetter.getImage(), theme);
        deleteVictim = new VicFormatter(delVic, buffDistance);

        imageGetter = new Images("person", theme);
        RoundButton edtVic = new RoundButton(imageGetter.getImage(), theme);
        editVictim = new VicFormatter(edtVic, buffDistance);

        imageGetter = new Images("people", theme);
        RoundButton edtPpl = new RoundButton(imageGetter.getImage(), theme);
        editClass = new VicFormatter(edtPpl, buffDistance);

        imageGetter = new Images("gear", theme);
        RoundButton settns = new RoundButton(imageGetter.getImage(), theme);
        settings = new VicFormatter(settns, buffDistance);

        imageGetter = new Images("door", theme);
        RoundButton ext = new RoundButton(imageGetter.getImage(), theme);
        exit = new VicFormatter(ext, buffDistance);

        controlPanel.add(addVictim.getPanel());
        controlPanel.add(deleteVictim.getPanel());
        controlPanel.add(editVictim.getPanel());
        controlPanel.add(editClass.getPanel());
        controlPanel.add(settings.getPanel());
        controlPanel.add(Box.createRigidArea(new Dimension(10, 90)));
        controlPanel.add(save.getPanel());
        controlPanel.add(exit.getPanel());

        topPanel = new VicFormatter(controlPanel, buffDistance);
        topPanel.getPanel().setMinimumSize(new Dimension(300, 300));
    }

    public JPanel getFormat() {
        return topPanel.getPanel();
    }

    public void updateColors(CurrentUITheme currentTheme) {
        controlPanel.setBackground(currentTheme.getCurrentBackgroundColor().main());
        controlPanel.setForeground(currentTheme.getCurrentForegroundColor().main());
        // If there are other color attributes to update, do it here
        controlPanel.repaint(); // Refresh the component to apply new colors
    }

}
