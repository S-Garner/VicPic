package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.Students.Victim;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.BufferedPanel;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.HashMap;

public class CurrentUserStats extends JPanel{
    private HashMap<String, JComponent> map;
    private CurrentUITheme theme;
    private CurrentUITheme inverse;

    private Victim student;

    private VicFormatter displayFormat;
    private VicFormatter mainPanelFormat;

    private RoundedPanel mainPanel;

    private JPanel contentFrame;

    private String firstName = "";
    private String lastName = "";
    private String nickName = "";
    private String points = "";
    private String absents = "";
    private String answered = "";
    private String timesPicked = "";
    private String passed = "";
    private String influence = "";

    private TextCanvas fullNameLbl;
    private TextCanvas nickNameLbl;
    private TextCanvas pointsLbl;
    private TextCanvas absentLbl;
    private TextCanvas answeredLbl;
    private TextCanvas timesPickLbl;
    private TextCanvas passedLbl;
    private TextCanvas inflLbl;

    private TextCanvas fullName;
    private TextCanvas nickNameMain;
    private TextCanvas pointsMain;
    private TextCanvas absentMain;
    private TextCanvas answeredMain;
    private TextCanvas timesPickedMain;
    private TextCanvas passedMain;
    private TextCanvas influenceMain;

    public CurrentUserStats(CurrentUITheme inTheme){

        map = new HashMap<>();

        int buffDistance = 3;
        int fontSize = 22;
        boolean unEditable = false;

        theme = inTheme;
        inverse = new CurrentUITheme(theme.getForegroundString(), theme.getBackgroundString());

        mainPanel = new RoundedPanel(theme);
        mainPanel.setMinimumSize(new Dimension(900, 1));
        //mainPanel.setPreferredSize(new Dimension(900, 75));
        mainPanelFormat = new VicFormatter<>(mainPanel, buffDistance);

        contentFrame = new JPanel();
        contentFrame.setBackground(inverse.getCurrentBackgroundColor().main());
        contentFrame.setLayout(new BoxLayout(contentFrame, BoxLayout.Y_AXIS));
        VicFormatter contentFrameFormat = new VicFormatter(contentFrame, buffDistance);

        JPanel fullNickPanel = new JPanel();
        fullNickPanel.setLayout(new BoxLayout(fullNickPanel, BoxLayout.X_AXIS));

        JPanel fullNamePnl = new JPanel();
        fullNamePnl.setLayout(new BoxLayout(fullNamePnl, BoxLayout.X_AXIS));
        fullNamePnl.setBackground(null);
        fullNameLbl = new TextCanvas(inverse, fontSize, unEditable);
        fullNameLbl.setText("Full Name: ");
        fullName = new TextCanvas(inverse, fontSize, unEditable); // Where first and last name displayed
        fullName.setPreferredSize(new Dimension(160, 1));
        fullName.setText(firstName + " " + lastName);
        fullNamePnl.add(fullNameLbl);
        fullNamePnl.add(fullName);

        JPanel nickNamePnl = new JPanel();
        nickNamePnl.setLayout(new BoxLayout(nickNamePnl, BoxLayout.X_AXIS));
        nickNamePnl.setBackground(null);
        nickNameLbl = new TextCanvas(inverse, fontSize, unEditable);
        nickNameLbl.setText("Nickname: ");
        nickNameMain = new TextCanvas(inverse, fontSize, unEditable);
        nickNameMain.setPreferredSize(new Dimension(200, 1));
        nickNameMain.setText(nickName);
        nickNamePnl.add(nickNameLbl);
        nickNamePnl.add(nickNameMain);

        fullNickPanel.add(fullNamePnl);
        fullNickPanel.add(nickNamePnl);


        JPanel pointsAndAbsents = new JPanel();
        pointsAndAbsents.setLayout(new BoxLayout(pointsAndAbsents, BoxLayout.X_AXIS));

        JPanel pointsPnl = new JPanel();
        pointsPnl.setLayout(new BoxLayout(pointsPnl, BoxLayout.X_AXIS));
        pointsPnl.setBackground(null);
        pointsLbl = new TextCanvas(inverse, fontSize, unEditable);
        pointsLbl.setBorder(new BevelBorder(1,Color.BLACK, Color.BLACK));
        pointsLbl.setOpaque(true);
        pointsLbl.setText("Points: ");
        pointsLbl.setPreferredSize(new Dimension(1, 1));
        pointsMain = new TextCanvas(inverse, fontSize, unEditable); // Where first and last name displayed
        pointsMain.setPreferredSize(new Dimension(55, 1));
        pointsMain.setText("55");
        pointsPnl.add(pointsLbl);
        pointsPnl.add(pointsMain);

        JPanel absentPnl = new JPanel();
        absentPnl.setLayout(new BoxLayout(absentPnl, BoxLayout.X_AXIS));
        absentPnl.setBackground(null);
        absentLbl = new TextCanvas(inverse, fontSize, unEditable);
        absentPnl.setOpaque(true);
        absentLbl.setText("Absents: ");
        absentMain = new TextCanvas(inverse, fontSize, unEditable);
        absentMain.setSize(new Dimension(200, 10));
        absentMain.setText(absents);
        absentPnl.add(absentLbl);
        nickNamePnl.add(absentMain);

        pointsAndAbsents.add(pointsPnl);
        pointsAndAbsents.add(absentPnl);

        contentFrame.add(fullNickPanel);
        contentFrame.add(pointsAndAbsents);

        mainPanel.add(contentFrame);


    }

    public void setStudent(Victim inStudent){
        student = new Victim(inStudent);
    }

    public Victim getStudent(){
        return student;
    }

    public void updateStatText(Victim inStudent){
        setStudent(inStudent);

        firstName = student.getName().getFirstName();
        lastName = student.getName().getLastName();
        points = Integer.toString(student.getPoints());
        absents = Integer.toString(student.getAbsences());
        answered = Integer.toString(student.getAnswered());
        timesPicked = Integer.toString(student.getNumPicked());
        passed = Integer.toString(student.getPassed());
        influence = Double.toString(student.getInfluenceScore());

        writeStats();

    }

    public void writeStats(){
        fullName.setText(firstName + " " + lastName);
        pointsMain.setText(points);
        absentMain.setText(absents);
        answeredMain.setText(answered);
        timesPickedMain.setText(timesPicked);
        passedMain.setText(passed);
        influenceMain.setText(influence);
    }

    public BufferedPanel getMainPanel(){
        return mainPanelFormat.getPanel();
    }

}
