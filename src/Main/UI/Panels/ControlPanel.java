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

        //Image image = null;

        imageGetter = new Images("plus", theme, "UIimage");
        RoundButton addVic = new RoundButton(imageGetter.getImage(), theme);
        addVic.setToolTipText("Add Victim");
        map.put("csAddVic", addVic);
        addVictim = new VicFormatter(addVic, buffDistance);

        imageGetter = new Images("floppy", theme, "UIimage");
        RoundButton saveV = new RoundButton(imageGetter.getImage(), theme);
        saveV.setToolTipText("Save");
        map.put("csSaveV", saveV);
        save = new VicFormatter(saveV, buffDistance);

        imageGetter = new Images("X", theme, "UIimage");
        RoundButton delVic = new RoundButton(imageGetter.getImage(), theme);
        delVic.setToolTipText("Delete Victim");
        map.put("csDelVic", delVic);
        deleteVictim = new VicFormatter(delVic, buffDistance);

        imageGetter = new Images("person", theme, "UIimage");
        RoundButton edtVic = new RoundButton(imageGetter.getImage(), theme);
        edtVic.setToolTipText("Edit Victim");
        map.put("edtVic", edtVic);
        editVictim = new VicFormatter(edtVic, buffDistance);

        imageGetter = new Images("people", theme, "UIimage");
        RoundButton edtPpl = new RoundButton(imageGetter.getImage(), theme);
        edtPpl.setToolTipText("Edit Class");
        map.put("csEdtPpl", edtPpl);
        editClass = new VicFormatter(edtPpl, buffDistance);

        imageGetter = new Images("gear", theme, "UIimage");
        RoundButton settingsBt = new RoundButton(imageGetter.getImage(), theme);
        settingsBt.setToolTipText("Settings");
        map.put("csSettings", settingsBt);
        settings = new VicFormatter(settingsBt, buffDistance);

        imageGetter = new Images("door", theme, "UIimage");
        RoundButton ext = new RoundButton(imageGetter.getImage(), theme);
        ext.setToolTipText("Exit");
        map.put("csExit", ext);
        exit = new VicFormatter(ext, buffDistance);

        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(0, 500));
        //test.setMinimumSize(new Dimension(1, 200));
        //test.setMaximumSize(new Dimension(1, 800));
        VicFormatter testBox = new VicFormatter(test, buffDistance);

        controlPanel.add(addVictim.getPanel());
        controlPanel.add(deleteVictim.getPanel());
        controlPanel.add(editVictim.getPanel());
        controlPanel.add(editClass.getPanel());
        controlPanel.add(settings.getPanel());
        controlPanel.add(test);
        controlPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        //controlPanel.add(testBox.getPanel());
        //controlPanel.add(test);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        savePanel.add(save.getPanel());
        savePanel.add(exit.getPanel());
        controlPanel.add(save.getPanel());
        controlPanel.add(exit.getPanel());

        topPanel = new VicFormatter(controlPanel, buffDistance);
        //topPanel.getPanel().setMinimumSize(new Dimension(300, 300));
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
