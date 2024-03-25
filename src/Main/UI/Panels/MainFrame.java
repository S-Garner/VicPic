package src.Main.UI.Panels;

import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.RoundedPanel;
import src.WriterReader.Input;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MainFrame {
    JFrame frame;
    CurrentUITheme theme;

    public MainFrame(){
        JFrame frame = new JFrame("Custom UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            theme = Input.readUIThemeFile("C:/Github/VicPic/src/UIElements/UITheme.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Colors not found");
            throw new RuntimeException(e);
        }

        frame.getContentPane().setBackground(theme.getCurrentForegroundColor().main());
        frame.setResizable(true);

        // Create a panel to apply BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        SearchPanel searchPanel = new SearchPanel(theme);
        ControlPanel controlPanel = new ControlPanel(theme);

        RoundedPanel tester = new RoundedPanel(theme);
        tester.setPreferredSize(new Dimension(900, 500));

        JPanel second = new JPanel();
        second.setBackground(theme.getCurrentBackgroundColor().main());
        second.setLayout(new BoxLayout(second, BoxLayout.X_AXIS));
        //second.add(controlPanel.getFormat());
        second.add(tester);

        mainPanel.add(searchPanel.getFormat());
        mainPanel.add(second);
        mainPanel.add(controlPanel.getFormat());
        mainPanel.setBackground(theme.getCurrentBackgroundColor().main());

        // Add the main panel to the frame
        frame.getContentPane().add(mainPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
