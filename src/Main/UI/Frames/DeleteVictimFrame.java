<<<<<<< Updated upstream
package src.Main.UI.Frames;
=======
package Main.UI.Frames;

import Main.Holder;
import Main.UI.Format.VicFormatter;
import Students.Victim;
import UIElements.Buttons.RoundButton;
import UIElements.Panels.RoundedPanel;
>>>>>>> Stashed changes

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DeleteVictimFrame extends JFrame{
    src.Main.Holder holder;
    JFrame self = this;
    public DeleteVictimFrame(src.Main.Holder h) {
        holder = h;
<<<<<<< Updated upstream
        //create the options panel
        JPanel optionMenu = new JPanel();
        optionMenu.setPreferredSize(new Dimension(200, holder.getVictims().size() * 31));
        for (src.Students.Victim v : holder.getVictims()) {
=======
        RoundedPanel optionMenu = new RoundedPanel(h.getTheme());
        optionMenu.setLayout(new BoxLayout(optionMenu, BoxLayout.Y_AXIS));
        optionMenu.setPreferredSize(new Dimension(200, holder.getVictims().size() * 110));
        for (Victim v : holder.getVictims()) {
>>>>>>> Stashed changes
            String name = v.getName().getFirstName() + " " + v.getName().getLastName();
            RoundButton newButton = new RoundButton(name, h.getTheme());
            newButton.setSize(new Dimension(300, 50)); // Set look
            VicFormatter buttonForm = new VicFormatter(newButton, 5);
            optionMenu.add(buttonForm.getPanel());
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //call delete for the button
                    holder.deleteVictim(v);

                    //Kill the frame
                    self.dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
                }
            });

        }
        JScrollPane scrollPane = new JScrollPane(optionMenu);
        scrollPane.setBackground(h.getTheme().getCurrentBackgroundColor().main());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 400, 700);
        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(h.getTheme().getCurrentBackgroundColor().main());
        contentPane.setPreferredSize(new Dimension(500, 600));
        contentPane.add(scrollPane);
        self.setContentPane(contentPane);
        self.pack();
        self.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        self.setVisible(true);
    }
}