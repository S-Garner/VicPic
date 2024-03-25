package src.Main.UI.Panels;

import src.Main.UI.Buttons.VicButtons;
import src.Main.UI.Buttons.VicTextPanel;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.ImagePanel;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import javax.swing.*;
import java.awt.*;

public class SearchPanel {
    JPanel searchPanel;
    CurrentUITheme theme;
    Images logoGetter;
    Image logo;
    VicButtons searchButton;
    VicTextPanel textPanel;

    public SearchPanel(CurrentUITheme theme){
        this.theme = theme;
        searchPanel = new JPanel();
        searchPanel.setBackground(this.theme.getCurrentBackgroundColor().main());
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        logoGetter = new Images("logo2", this.theme);
        logo = logoGetter.getImage();
        ImagePanel imgPanel = new ImagePanel(logo, theme);
        imgPanel.setPreferredSize(new Dimension(logo.getWidth(null), logo.getHeight(null)));


        logoGetter = new Images("magnifyGlass", theme);
        searchButton = new VicButtons(logoGetter.getImage(), theme, 5);

        textPanel = new VicTextPanel(theme, 30, true, 5, 200, 120);
        RoundedPanel round = new RoundedPanel(theme);
        round.add(textPanel.getTextCanvas());

        searchPanel.add(imgPanel); // Adding the image panel to the search panel
        searchPanel.add(Box.createRigidArea(new Dimension(200, 1)));
        searchPanel.add(searchButton.getButton());
        searchPanel.add(round);

    }

    public JPanel getPanel(){
        return searchPanel;
    }

}
