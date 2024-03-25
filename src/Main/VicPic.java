package src.Main;

import src.Main.UI.Format.VicFormatter;
import src.Main.UI.Panels.ControlPanel;
import src.Main.UI.Panels.MainFrame;
import src.Main.UI.Panels.SearchPanel;
import src.UIElements.Colors.*;
import src.UIElements.Panels.RoundedPanel;
import src.WriterReader.Input;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class VicPic {
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel upPanel = new JPanel();
        //upPanel.setLayout(new FlowLayout());
        upPanel.setLayout(new BorderLayout());
        //upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.X_AXIS));

        CurrentUITheme theme;
        try {
            theme = Input.readUIThemeFile("C:/Github/VicPic/src/UIElements/UITheme.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Colors not found");
            throw new RuntimeException(e);
        }

        frame.setBackground(theme.getCurrentBackgroundColor().main());

        JPanel controlAndSecond = new JPanel();
        RoundedPanel round = new RoundedPanel(theme);
        VicFormatter roundForm = new VicFormatter(round, 5);
        round.setPreferredSize(new Dimension(800, 470));

        controlAndSecond.setLayout(new BorderLayout());


        ControlPanel cntrlPanel = new ControlPanel(theme);
        SearchPanel srchPanel = new SearchPanel(theme);

        controlAndSecond.add(cntrlPanel.getFormat(), BorderLayout.WEST);
        controlAndSecond.add(roundForm.getPanel(), BorderLayout.CENTER);
        controlAndSecond.setBackground(theme.getCurrentBackgroundColor().main());

        upPanel.add(controlAndSecond, BorderLayout.CENTER);
        upPanel.add(srchPanel.getFormat(), BorderLayout.NORTH);
        upPanel.setBackground(theme.getCurrentBackgroundColor().main());

        frame.getContentPane().add(upPanel);

        frame.pack();
        frame.setVisible(true);

    }
}