package src.Main.UI.Buttons;

import org.w3c.dom.Text;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Panels.BufferedPanel;
import src.UIElements.Panels.RoundedPanel;
import src.UIElements.TextCanvas;

import java.awt.*;

public class VicTextPanel {
    private TextCanvas textCanvas;
    private RoundedPanel shell;
    private BufferedPanel MagPanel;

    public VicTextPanel(CurrentUITheme current, int fontSize, boolean editable, int buffDistance, int width, int height) {
        shell = new RoundedPanel(current);
        shell.setMaximumSize(new Dimension(500, 50));
        textCanvas = new TextCanvas(current, fontSize, true);
        textCanvas.setSize(new Dimension(width, height));
        textCanvas.setMaximumSize(new Dimension(200, 45));

        shell.add(textCanvas);
        MagPanel = new BufferedPanel<>(textCanvas, buffDistance);
    }

    public BufferedPanel getPanel(){
        return MagPanel;
    }

    public TextCanvas getTextCanvas(){
        return textCanvas;
    }
}
