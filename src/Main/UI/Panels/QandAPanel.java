package src.Main.UI.Panels;

import src.Main.UI.Format.VicFormatter;
import src.Questions.Questions;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.QuestionOption;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.*;

public class QandAPanel {
    RoundedPanel mainPanel;
    VicFormatter mainFormat;

    CurrentUITheme theme;

    RoundButton next; // Go to next question
    RoundButton previous; // Go to last question
    RoundButton check; // Check if selected is correct

    // This will be used to display the question
    JPanel questionHolderPanel;
    TextCanvas questionDisplayField1;
    TextCanvas questionDisplayField2;
    TextCanvas questionDisplayField3;
    TextCanvas questionDisplayField4;

    RoundedPanel optionHolder; // Where the option buttons will be

    // An array for the options
    // There can be <=4 possible
    QuestionOption[] options;

    // Where all the questions are
    private Queue<Questions> questionsQueue;

    private HashMap<String, JComponent> map;

    JPanel setter = new JPanel();

    private Stack<Questions> displayedQuestions = new Stack<>();

    public QandAPanel(CurrentUITheme inTheme){

        questionsQueue = new LinkedList<>();

        map = new HashMap<>();

        this.theme = inTheme;
        CurrentUITheme themeInvert = new CurrentUITheme(theme.getForegroundString(), theme.getBackgroundString());

        mainPanel = new RoundedPanel(theme);
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BorderLayout());

        RoundedPanel buttonPanel = new RoundedPanel(theme);
        buttonPanel.setLayout(new BorderLayout());
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        VicFormatter buttonPNLFormat = new VicFormatter(buttonPanel, 5);

        Images imageGetter;

        imageGetter = new Images("right", theme, "UIimage");
        next = new RoundButton(imageGetter.getImage(), theme);
        map.put("qaNext", next);
        VicFormatter nextFormat = new VicFormatter(next, 5);

        imageGetter = new Images("left", theme, "UIimage");
        previous = new RoundButton(imageGetter.getImage(), theme);
        map.put("qaPrevious", next);
        VicFormatter previousFormat = new VicFormatter(previous, 5);

        imageGetter = new Images("check", theme, "UIimage");
        check = new RoundButton(imageGetter.getImage(), theme);
        map.put("qaCheck", next);
        VicFormatter checkFormat = new VicFormatter(check, 5);

        next.addActionListener(e -> displayNextQuestion());

        previous.addActionListener(e -> displayPreviousQuestion());

        check.addActionListener(e -> {
            if (!questionsQueue.isEmpty()) {
                checkAnswer(questionsQueue.peek()); // Peek to get the current question without removing it
            }
        });

        //buttonPanel.add(previous);
        //buttonPanel.add(check);
        //buttonPanel.add(next);

        int bufferSet = 25;

        VicFormatter prevFormat = new VicFormatter(previous, bufferSet);
        VicFormatter checkerFormat = new VicFormatter(check, bufferSet);
        VicFormatter nexterFormat = new VicFormatter(next, bufferSet);

        buttonPanel.add(prevFormat.getPanel(), BorderLayout.WEST);
        buttonPanel.add(checkerFormat.getPanel(), BorderLayout.CENTER);
        buttonPanel.add(nexterFormat.getPanel(), BorderLayout.EAST);

        buttonPanel.setPreferredSize(new Dimension(600, 400));

        questionDisplayField1 = new TextCanvas(themeInvert, 25, false);
        questionDisplayField1.setBorder(new BevelBorder(1, theme.getCurrentBackgroundColor().main(), theme.getCurrentBackgroundColor().main()));
        //questionDisplayField1.setText("TestTestTestTestTestTestTestTestTestTestTest");
        questionDisplayField1.setBackground(null);
        questionDisplayField1.setPreferredSize(new Dimension(755, 40));
        questionDisplayField1.setHorizontalAlignment(JTextField.CENTER);

        questionDisplayField2 = new TextCanvas(themeInvert, 25, false);
        questionDisplayField2.setBorder(new BevelBorder(1, theme.getCurrentBackgroundColor().main(), theme.getCurrentBackgroundColor().main()));
        //questionDisplayField2.setText("TestTestTestTestTestTestTestTestTestTestTest");
        questionDisplayField2.setBackground(null);
        questionDisplayField2.setPreferredSize(new Dimension(755, 40));
        questionDisplayField2.setHorizontalAlignment(JTextField.CENTER);

        questionDisplayField3 = new TextCanvas(themeInvert, 25, false);
        questionDisplayField3.setBorder(new BevelBorder(1, theme.getCurrentBackgroundColor().main(), theme.getCurrentBackgroundColor().main()));
        //questionDisplayField3.setText("TestTestTestTestTestTestTestTestTestTestTest");
        questionDisplayField3.setBackground(null);
        questionDisplayField3.setPreferredSize(new Dimension(755, 40));
        questionDisplayField3.setHorizontalAlignment(JTextField.CENTER);

        questionDisplayField4 = new TextCanvas(themeInvert, 25, false);
        questionDisplayField4.setBorder(new BevelBorder(1, theme.getCurrentBackgroundColor().main(), theme.getCurrentBackgroundColor().main()));
        //questionDisplayField4.setText("TestTestTestTestTestTestTestTestTestTestTestTestTest");
        questionDisplayField4.setBackground(null);
        questionDisplayField4.setPreferredSize(new Dimension(755, 40));
        questionDisplayField4.setHorizontalAlignment(JTextField.CENTER);

        questionHolderPanel = new JPanel();
        questionHolderPanel.setLayout(new BoxLayout(questionHolderPanel, BoxLayout.Y_AXIS));
        questionHolderPanel.add(questionDisplayField1);
        questionHolderPanel.add(questionDisplayField2);
        questionHolderPanel.add(questionDisplayField3);
        questionHolderPanel.add(questionDisplayField4);
        questionHolderPanel.setBackground(theme.getCurrentForegroundColor().main());
        questionHolderPanel.setPreferredSize(new Dimension(810, 185));

        VicFormatter questionFormat = new VicFormatter(questionHolderPanel, 5);

        optionHolder = new RoundedPanel(theme);
        VicFormatter optionHolderFormat = new VicFormatter(optionHolder, 5);

        //mainPanel.add(buttonPanelFormat.getPanel());
        mainPanel.add(buttonPNLFormat.getPanel(), BorderLayout.NORTH);
        //mainPanel.add(buttonPNLFormat.getPanel());
        mainPanel.add(questionFormat.getPanel());
        //mainPanel.add(optionHolderFormat.getPanel());

        setter = new JPanel();
        setter.setSize(new Dimension(700, 200));

        //optionHolder.setPreferredSize(new Dimension(700, 200));

        optionHolder.add(setter);

        buttonPanel.setPreferredSize(new Dimension(800, 100));

    }

    public JPanel getPanel(){
        return mainPanel;
    }

    public void setDisplayText(String text) {
        // Split the input text into 52-character chunks
        ArrayList<String> textChunks = splitTextIntoChunks(text, 52);

        // Set the text for each TextCanvas
        questionDisplayField1.setText(textChunks.size() > 0 ? textChunks.get(0) : "");
        questionDisplayField2.setText(textChunks.size() > 1 ? textChunks.get(1) : "");
        questionDisplayField3.setText(textChunks.size() > 2 ? textChunks.get(2) : "");
        questionDisplayField4.setText(textChunks.size() > 3 ? textChunks.get(3) : "");
    }

    // Helper method to split the text into chunks
    private ArrayList<String> splitTextIntoChunks(String text, int chunkSize) {
        ArrayList<String> chunks = new ArrayList<>();
        int start = 0;
        while (start < text.length()) {
            int end = Math.min(start + chunkSize, text.length());
            chunks.add(text.substring(start, end));
            start = end;
        }
        return chunks;
    }

    public void setQuestions(ArrayList<Questions> questions) {
        // Clear the existing queue
        questionsQueue.clear();

        // Shuffle the questions list
        Collections.shuffle(questions);

        // Add all shuffled questions to the queue
        questionsQueue.addAll(questions);
    }

    private void checkAnswer(Questions currentQuestion) {
        for (Component component : optionHolder.getComponents()) {
            if (component instanceof QuestionOption) {
                QuestionOption option = (QuestionOption) component;
                if (option.getButtonHeld()) {
                    boolean isCorrect = option.isCorrect(currentQuestion.getAnswer());
                    // Update UI or logic based on whether the answer is correct
                }
            }
        }
    }

    private void displayNextQuestion() {
        if (questionsQueue.isEmpty()) {
            System.out.println("No more questions available.");
            return;
        }

        Questions currentQuestion = questionsQueue.poll(); // Retrieve and remove the head of the queue
        setDisplayText(currentQuestion.getQuestion());

        // Clear existing options in the optionHolder
        optionHolder.removeAll();
        for (String option : currentQuestion.getOptions()) {
            QuestionOption optionPanel = new QuestionOption(option, theme);
            setter.add(optionPanel);
            // Add listeners or actions to optionPanel here if needed
        }

        optionHolder.revalidate();
        optionHolder.repaint();
    }

    private void displayPreviousQuestion() {
        if (displayedQuestions.size() <= 1) { // Check if there is a previous question to display
            System.out.println("No previous question available.");
            return;
        }

        // Current question needs to be popped as we already are on this question
        displayedQuestions.pop();

        Questions previousQuestion = displayedQuestions.peek(); // Peek the previous question without removing
        setDisplayText(previousQuestion.getQuestion());

        // Clear existing options in the optionHolder
        optionHolder.removeAll();
        for (String option : previousQuestion.getOptions()) {
            QuestionOption optionPanel = new QuestionOption(option, theme);
            setter.add(optionPanel); // Make sure this should be 'setter' or 'optionHolder'
            // Add listeners or actions to optionPanel here if needed
        }

        optionHolder.revalidate();
        optionHolder.repaint();
    }

}
