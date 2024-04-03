package src.Main;

import src.IOClasses.SimpleInstrHolder;
import src.Interfaces.SimpleInstructions;
import src.Main.UI.Format.VicFormatter;
import src.Main.UI.Panels.*;
import src.Questions.Questions;
import src.Students.Victim;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.*;
import src.UIElements.Panels.BufferedPanel;
import src.UIElements.Panels.DisplayPlayer;
import src.UIElements.Panels.PlayerPanel;
import src.UIElements.Panels.RoundedPanel;
import src.WriterReader.Input;
import src.WriterReader.randomizeImages;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import static src.Main.Assets.filePaths.*;
import static src.UIElements.Colors.ImageResizer.resize;

public class VicPic {
    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, JComponent> map;
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        JPanel upPanel = new JPanel();
        //upPanel.setLayout(new FlowLayout());
        upPanel.setLayout(new BorderLayout());
        //upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.X_AXIS));


        CurrentUITheme theme;
        try {
            theme = Input.readUIThemeFile("./src/Main/SaveFiles/UITheme.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Colors not found");
            throw new RuntimeException(e);
        }

        ArrayList<Victim> vics = new ArrayList<>();
        String filePath = saveFilePath + vicList;
        vics = Input.readStudentFile(filePath);

        //randomizeImages randomImg = new randomizeImages(vics, photoPath);

        //randomImg.assignPhotosToVictims();

        frame.setBackground(theme.getCurrentBackgroundColor().main());

        JPanel controlAndSecond = new JPanel();
        RoundedPanel round = new RoundedPanel(theme);
        HighScorePanel highScores = new HighScorePanel(theme);

        JPanel psPanelandPlayerDisplayHolder = new JPanel();
        //psPanelandPlayerDisplayHolder.setPreferredSize(new Dimension(800, 1220));
        psPanelandPlayerDisplayHolder.setBackground(null);


        PlayerOptions psPanel = new PlayerOptions(theme);
        JPanel anotherOne = new JPanel();
        anotherOne.setBackground(null);
        anotherOne.add(psPanel.getFormat());
        PlayerDisplayPanel playDisplayPanel = new PlayerDisplayPanel(theme, vics);
        JPanel anotherTwo = new JPanel();
        CurrentUserStats stats = new CurrentUserStats(theme);
        stats.setBackground(null);
        anotherTwo.add(playDisplayPanel.getTopPanel());
        JPanel anotherFour = new JPanel();
        anotherFour.setBackground(null);
        anotherFour.add(stats.getMainPanel());

        JPanel anotherThree = new JPanel();
        anotherThree.setLayout(new BoxLayout(anotherThree, BoxLayout.Y_AXIS));

        anotherThree.add(anotherTwo);
        anotherTwo.setBackground(null);
        anotherTwo.setOpaque(false);
        anotherThree.add(anotherOne);
        anotherThree.add(anotherFour);
        anotherThree.setBackground(null);
        anotherThree.setOpaque(false);

        psPanelandPlayerDisplayHolder.add(anotherThree);
        psPanelandPlayerDisplayHolder.setBackground(null);
        psPanelandPlayerDisplayHolder.setOpaque(false);

        QandAPanel testQA = new QandAPanel(theme);
        VicFormatter testQAHolder = new VicFormatter(testQA.getPanel(), 5);

        ArrayList<Questions> questions;

        try {
            questions = Input.readQuestionsFile("./src/Main/SaveFiles/Questions.txt");
            testQA.setQuestions(questions);
            } catch (Exception e) {
            e.printStackTrace();
        }

        testQA.setDisplayText("This is a very long string to test if seperator is working properly");



        //testQA.getPanel().setPreferredSize(new Dimension(600, 600));


        //highScores.getFormat().setPreferredSize(new Dimension(200, 300));
        RoundedPanel highScoreAndQuestionHolder = new RoundedPanel(theme);

        highScoreAndQuestionHolder.setLayout(new BorderLayout());

        highScoreAndQuestionHolder.setPreferredSize(new Dimension(800, 900));

        //DisplayPlayer player = new DisplayPlayer(images, theme);
        //PlayerPanel firstPL = new PlayerPanel(theme);
        //firstPL.getPlayerDisplay().
        //PlayerPanel secondPL = new PlayerPanel(theme);
        //firstPL.setImage("chad");
        //DisplayPlayer player2 = new DisplayPlayer(ima2, theme);
        //VicFormatter play2 = new VicFormatter(player2, 5);
        //VicFormatter playerhOld = new VicFormatter(player, 5);
        //VicFormatter firstPLFirst = new VicFormatter(firstPL, 5);
        //VicFormatter secondPLFirst = new VicFormatter(secondPL, 5);
        round.add(psPanelandPlayerDisplayHolder);
        round.setBackground(null);
        highScoreAndQuestionHolder.add(testQAHolder.getPanel(), BorderLayout.NORTH);
        highScoreAndQuestionHolder.add(highScores.getFormat(), BorderLayout.CENTER);
        highScores.getFormat().setPreferredSize(new Dimension(800, 100));
        round.add(highScoreAndQuestionHolder);
        highScoreAndQuestionHolder.setBackground(null);
        //round.add(playerhOld.getPanel());
        //round.add(play2.getPanel());
        //round.add(firstPLFirst.getPanel());
        //round.add(secondPLFirst.getPanel());
        VicFormatter roundForm = new VicFormatter(round, 5);
        //round.setPreferredSize(new Dimension(680, 480));

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
            /*
            JFrame newFrame = new JFrame("Wheel");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            SpinningWheel wheel = new SpinningWheel(theme);
            newFrame.add(wheel);
            newFrame.pack();
            newFrame.setVisible(true);
             */

            //resize(player.getImage(), 12, 12);
            //player.repaint();

            //randomImg.resetAll();

        };

        SimpleInstrHolder holder = new SimpleInstrHolder(openFrame);
        //button.addActionListener(e -> holder.execute());

        frame.pack();
        frame.setVisible(true);

    }
}