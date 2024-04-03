package src.UIElements.Panels;

import src.Main.UI.Format.VicFormatter;
import src.Main.VictimManager;
import src.Questions.Questions;
import src.UIElements.Buttons.HeldButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class QuestionOption extends JPanel {
    private int fontSize = 22;
    private boolean notEdit = false;

    // This will be displayed in the optionCanvas
    // This will be one of the question options
    private String optionText;

    // A held button for the user to select the option displayed
    private HeldButton optionButton;

    // Canvas to display option
    private TextCanvas optionCanvas;

    VicFormatter canvasFormat;
    VicFormatter buttonFormat;

    public QuestionOption(String inOption, CurrentUITheme theme){


        optionText = new String(inOption);
        optionCanvas = new TextCanvas(theme, fontSize, notEdit);
        optionButton = new HeldButton("", theme);

        VicFormatter textFormat = new VicFormatter(optionCanvas, 5);
        textFormat.getPanel().setBackground(null);
        VicFormatter buttonFormat = new VicFormatter(optionButton, 5);
        buttonFormat.getPanel().setBackground(null);

        optionCanvas.setText(inOption);

        this.setLayout(new BorderLayout());
        this.setBackground(null);

        this.add(textFormat.getPanel(), BorderLayout.NORTH);
        this.add(buttonFormat.getPanel(), BorderLayout.CENTER);

        VicFormatter thisHolder = new VicFormatter(this, 5);

        this.setPreferredSize(new Dimension(100, 100));

    }

    public boolean getButtonHeld(){
        return optionButton.isHeld();
    }

    public String getOptionText(){
        return optionText;
    }

    // This will be used after the check button is pressed
    // If this option is not the correct one, change to red, if it is change to green
    // Returns a boolean to determine if correct
    public boolean isCorrect(String inAnswer){
        boolean correct;
        if (optionText == inAnswer){
            optionCanvas.setBackground(Color.GREEN);
            correct = true;
        }
        else{
            optionCanvas.setBackground(Color.RED);
            correct = false;
        }
        optionCanvas.repaint();

        return correct;

    }

}
