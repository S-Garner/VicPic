package src.Main;

import src.Main.UI.Format.VicFormatter;
import src.Main.UI.Panels.*;
import src.Students.Victim;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Frontend {
    private JFrame frame;
    private JPanel mainPanel;
    private CurrentUITheme theme;
    private ArrayList<Victim> victims;
    private Holder mainHolder;
    private VictimManager manager;
    private HashMap<String, JComponent> map;

    public Frontend(Holder inHolder){
        mainHolder = inHolder;
        frame = new JFrame("VicPic");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        initializeUIComponents();

        frame.pack();
        frame.setVisible(true);

    }

    private void initializeUIComponents(){
        // Panel holds controls and content sections
        JPanel controlAndContentPanel = new JPanel(new BorderLayout());

        // Initialize map
        map = new HashMap<>();

        // Main content panel with rounded borders
        RoundedPanel contentPanel = new RoundedPanel(mainHolder.getTheme());

        // Panel for displaying high scores
        HighScorePanel highScorePanel = new HighScorePanel(mainHolder.getTheme());

        // Get HighScorePanels buttons
        map.putAll(highScorePanel.getMap());

        // Holder for player options and display sections
        JPanel playerOptionsHolder = new JPanel();
        playerOptionsHolder.setLayout(new BoxLayout(playerOptionsHolder, BoxLayout.Y_AXIS));
        playerOptionsHolder.setBackground(null);
        playerOptionsHolder.setOpaque(false);

        // Panel for player options
        PlayerOptions playerOptions = new PlayerOptions(mainHolder.getTheme());
        JPanel playerOptionsPanel = new JPanel();
        playerOptionsPanel.setBackground(null);
        playerOptionsPanel.add(playerOptions.getFormat());  // Add formatted player options

        // Get map from PlayerOptions
        map.putAll(playerOptions.getMap());

        // Panel for displaying players
        PlayerDisplayPanel playerDisplayPanel = new PlayerDisplayPanel(mainHolder.getTheme(), mainHolder.getVictims());
        JPanel playerDisplayTopPanel = new JPanel();
        playerDisplayTopPanel.setBackground(null);
        playerDisplayTopPanel.add(playerDisplayPanel.getTopPanel());  // Add top panel of player display

        // This will be used to send changes to the students selected
        manager = new VictimManager(playerDisplayPanel);
        mainHolder.setManagaer(manager);

        // Panel for current user stats
        CurrentUserStats currentUserStats = new CurrentUserStats(mainHolder.getTheme());
        JPanel userStatsPanel = new JPanel();
        userStatsPanel.setBackground(null);
        userStatsPanel.add(currentUserStats.getMainPanel());  // Add main panel of user stats

        // Get map from stats
        map.putAll(currentUserStats.getMap());

        // Assemble player options and stats into the holder
        playerOptionsHolder.add(playerDisplayTopPanel);
        playerOptionsHolder.add(playerOptionsPanel);
        playerOptionsHolder.add(userStatsPanel);

        contentPanel.add(playerOptionsHolder);  // Add player options holder to content panel
        contentPanel.setBackground(null);

        // UNDER CONSTRUCTION
        // QA panel and its holder
        QandAPanel qaPanel = new QandAPanel(mainHolder.getTheme());
        VicFormatter qaPanelHolder = new VicFormatter(qaPanel.getPanel(), 5);
        //qaPanel.setQuestions(qaPanel.setQuestions());

        // Panel to hold high scores and questions
        RoundedPanel highScoreAndQuestionPanel = new RoundedPanel(mainHolder.getTheme());
        highScoreAndQuestionPanel.setLayout(new BorderLayout());
        highScoreAndQuestionPanel.setPreferredSize(new Dimension(800, 900));
        highScoreAndQuestionPanel.add(qaPanelHolder.getPanel(), BorderLayout.NORTH);  // Add QA panel at the top
        highScoreAndQuestionPanel.add(highScorePanel.getFormat(), BorderLayout.CENTER);  // Add high score panel in the center
        highScorePanel.getFormat().setPreferredSize(new Dimension(800, 100));
        highScoreAndQuestionPanel.setBackground(null);

        contentPanel.add(highScoreAndQuestionPanel);  // Add high score and question panel to the content panel
        VicFormatter contentFormatter = new VicFormatter(contentPanel, 5);

        // Control panel on the left
        ControlPanel controlPanel = new ControlPanel(mainHolder.getTheme());
        // Search panel at the top
        SearchPanel searchPanel = new SearchPanel(mainHolder.getTheme());

        // Put all components into map
        map.putAll(searchPanel.getMap());

        // Assemble control and content panels
        controlAndContentPanel.add(controlPanel.getFormat(), BorderLayout.WEST);
        controlAndContentPanel.add(contentFormatter.getPanel(), BorderLayout.CENTER);
        controlAndContentPanel.setBackground(mainHolder.getTheme().getCurrentBackgroundColor().main());

        mainPanel.add(controlAndContentPanel, BorderLayout.CENTER);  // Add control and content panel to main panel
        mainPanel.add(searchPanel.getFormat(), BorderLayout.NORTH);  // Add search panel to the top of main panel

        //mainHolder. = controlPanel.getMap();
    }

}
