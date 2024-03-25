package src.Main.UI.Buttons;

import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.BufferedPanel;
import src.UIElements.Panels.RoundedPanel;

public class VicPanels {
    private RoundedPanel panel;
    private BufferedPanel bfPanel;

    public VicPanels(CurrentUITheme theme, int buffDistance){
        panel = new RoundedPanel(theme);
        bfPanel = new BufferedPanel<>(panel, buffDistance);
    }

    public BufferedPanel getBfPanel(){ return bfPanel; }

    public RoundedPanel getPanel() { return panel; }
}
