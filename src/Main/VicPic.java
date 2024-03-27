package src.Main;

import src.IOClasses.CompInstrHolder;
import src.IOClasses.SimpleInstrHolder;
import src.Interfaces.Instructions;
import src.Interfaces.SimpleInstructions;
import src.Main.UI.Format.VicFormatter;
import src.Main.UI.Panels.*;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Buttons.RoundedButton;
import src.UIElements.Colors.*;
import src.UIElements.Panels.RoundedPanel;
import src.WriterReader.Input;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class VicPic {
    public static void main(String[] args) {
        HashMap<String, JComponent> map;
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

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
        HighScorePanel highScores = new HighScorePanel(theme);
        highScores.getFormat().setPreferredSize(new Dimension(200, 300));
        round.add(highScores.getFormat());
        VicFormatter roundForm = new VicFormatter(round, 5);
        round.setPreferredSize(new Dimension(680, 480));

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

        map = cntrlPanel.getMap();

        RoundButton button = (RoundButton) map.get("csSettings");

        SimpleInstructions openFrame = () ->{
            JFrame newFrame = new JFrame("Wheel");
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SpinningWheel wheel = new SpinningWheel(theme);
            newFrame.add(wheel);
            newFrame.pack();
            newFrame.setVisible(true);
        };

        SimpleInstrHolder holder = new SimpleInstrHolder(openFrame);
        button.addActionListener(e -> holder.execute());

        frame.pack();
        frame.setVisible(true);

    }
}