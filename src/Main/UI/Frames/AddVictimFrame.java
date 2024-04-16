<<<<<<< Updated upstream
package src.Main.UI.Frames;
=======
package Main.UI.Frames;

import Main.Holder;
import UIElements.Buttons.RoundButton;
import UIElements.Panels.RoundedPanel;
import UIElements.TextCanvas;
>>>>>>> Stashed changes

import javax.swing.*;

public class AddVictimFrame extends JFrame {
<<<<<<< Updated upstream
    public AddVictimFrame(src.Main.Holder holder){
        String VictimName = JOptionPane.showInputDialog(this,
                "Add the Victims Name", null);
=======
    public AddVictimFrame(Holder holder){
        String VictimName = "Seth";
        RoundedPanel roundAdd = new RoundedPanel(holder.getTheme());
        roundAdd.setLayout(new BoxLayout(roundAdd, BoxLayout.X_AXIS));
        RoundButton addButton = new RoundButton("ADD", holder.getTheme());
        TextCanvas canvas = new TextCanvas(holder.getTheme(), 20, true);
        roundAdd.add(canvas);
        roundAdd.add(addButton);
        this.add(roundAdd);
>>>>>>> Stashed changes
        if(VictimName != null) {
            holder.addVictim(VictimName);
        }
    }
}