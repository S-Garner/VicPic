package src.Main.UI.Buttons;

import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.BufferedPanel;

import java.awt.*;


public class VicButtons {
    private RoundButton button;
    private BufferedPanel MagPanel;

    public VicButtons(Image image, CurrentUITheme theme, int buffDistance) {
        button = new RoundButton(image, theme);
        MagPanel = new BufferedPanel<>(button, buffDistance);
    }

    public BufferedPanel getPanel(){
        return MagPanel;
    }

    public RoundButton getButton(){
        return button;
    }

}
