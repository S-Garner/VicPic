package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
    private HashMap<String, JComponent> map;

    public ControlPanel(CurrentUITheme theme) {
        map = new HashMap<>();

        controlPanel = new RoundedPanel(theme);
        map.put("csControlPanel", controlPanel);

        //controlPanel.setMaximumSize(new Dimension(30, 175));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        int buffDistance = 5;
        Images imageGetter;

        Image image = null;

        imageGetter = new Images("plus", theme, true);
        RoundButton addVic = new RoundButton(imageGetter.getImage(), theme);
        map.put("csAddVic", addVic);
        addVictim = new VicFormatter(addVic, buffDistance);

        imageGetter = new Images("floppy", theme, true);
        RoundButton saveV = new RoundButton(imageGetter.getImage(), theme);
        map.put("csSaveV", saveV);
        save = new VicFormatter(saveV, buffDistance);

        imageGetter = new Images("X", theme, true);
        RoundButton delVic = new RoundButton(imageGetter.getImage(), theme);
        map.put("csDelVic", delVic);
        deleteVictim = new VicFormatter(delVic, buffDistance);

        imageGetter = new Images("person", theme, true);
        RoundButton edtVic = new RoundButton(imageGetter.getImage(), theme);
        map.put("edtVic", edtVic);
        editVictim = new VicFormatter(edtVic, buffDistance);

        imageGetter = new Images("people", theme, true);
        RoundButton edtPpl = new RoundButton(imageGetter.getImage(), theme);
        map.put("csEdtPpl", edtPpl);
        editClass = new VicFormatter(edtPpl, buffDistance);

        imageGetter = new Images("gear", theme, true);
        RoundButton settingsBt = new RoundButton(imageGetter.getImage(), theme);
        map.put("csSettings", settingsBt);
        settings = new VicFormatter(settingsBt, buffDistance);

        imageGetter = new Images("door", theme, true);
        RoundButton ext = new RoundButton(imageGetter.getImage(), theme);
        map.put("csExit", ext);
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
        map.put("csTopPanel", topPanel.getPanel());
    }

    public JPanel getFormat() {
        return topPanel.getPanel();
    }

    public HashMap<String, JComponent> getMap(){
        return map;
    }


    public void updateColors(CurrentUITheme currentTheme) {
        // Update the main control panel
        controlPanel.setBackground(currentTheme.getCurrentBackgroundColor().main());
        controlPanel.setForeground(currentTheme.getCurrentForegroundColor().main());

        // Iterate through all components in the panel and update their colors
        for (Map.Entry<String, JComponent> entry : map.entrySet()) {
            JComponent component = entry.getValue();
            if (component instanceof RoundButton) { // Check if it's a RoundButton to update
                component.setBackground(currentTheme.getCurrentBackgroundColor().main());
                component.repaint();
                component.setForeground(currentTheme.getCurrentForegroundColor().main());
                component.repaint();
                // if RoundButton has specific methods to update its appearance, call them here
            }
            // Add more conditions if there are different types of components with distinct update methods
        }

        controlPanel.repaint(); // Repaint the control panel to reflect the theme changes
    }

}
