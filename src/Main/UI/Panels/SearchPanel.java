package src.Main.UI.Panels;

import src.Main.UI.Buttons.VicButtons;
import src.Main.UI.Buttons.VicTextPanel;
import src.Main.UI.Format.VicFormatter;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Buttons.RoundedButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.ImagePanel;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel{
    JPanel searchPanel;
    CurrentUITheme theme;
    Images logoGetter;
    Image logo;
    VicFormatter searchButton;
    VicFormatter textPanel;
    private VicFormatter topPanel;

    public SearchPanel(CurrentUITheme theme){
        this.theme = theme;
        int buffDistance = 5;
        searchPanel = new JPanel();
        searchPanel.setBackground(this.theme.getCurrentBackgroundColor().main());
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        logoGetter = new Images("logo", this.theme, true);
        logo = logoGetter.getImage();
        ImagePanel imgPanel = new ImagePanel(logo, theme);
        imgPanel.setPreferredSize(new Dimension(logo.getWidth(null), logo.getHeight(null)));


        logoGetter = new Images("magnifyGlass", theme, true);
        RoundedButton magButton = new RoundButton(logoGetter.getImage(), theme);
        searchButton = new VicFormatter(magButton, buffDistance);

        //textPanel = new VicTextPanel(theme, 30, true, 5, 200, 120);
        TextCanvas textPNL = new TextCanvas(theme, 22, true);
        VicFormatter textFormat = new VicFormatter(textPNL, 5);
        textPNL.setColumnWidths(10);
        //textPNL.setPreferredSize(new Dimension(1, 30));
        RoundedPanel round = new RoundedPanel(theme);
        round.setPreferredSize(new Dimension(1, 1));
        round.add(textFormat.getPanel());
        SwingUtilities.invokeLater(() -> textPNL.setCaretPosition(0));

        searchPanel.add(imgPanel); // Adding the image panel to the search panel
        searchPanel.add(Box.createRigidArea(new Dimension(100, 1)));
        searchPanel.add(searchButton.getPanel());
        searchPanel.add(round);

        topPanel = new VicFormatter(searchPanel, buffDistance);

    }

    public JPanel getFormat(){
        return topPanel.getPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call superclass method first to paint the background
        this.repaint();
        topPanel.getPanel().repaint();
    }

}
