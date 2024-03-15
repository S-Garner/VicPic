package src;

import src.IOClasses.CompInstrHolder;
import src.IOClasses.SignalAndStart;
import src.Interfaces.Instructions;
import src.Interfaces.Trigger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testPanel extends JPanel {
    public testPanel(){
        JPanel test = this;
        this.setSize(new Dimension(1000,600));
        //JPanel panel = new JPanel();
        JLabel label = new JLabel("This Label");

        Instructions<JLabel> labelUpdate = new Instructions<JLabel>() {
            @Override
            public void update(JLabel component) {
                if (component.getText() == "Updated") {
                    component.setText("AAAGGGAIIIN");
                }
                else {
                    component.setText("Updated");
                }
            }
        };

        CompInstrHolder<JLabel> compInstrHolder = new CompInstrHolder<>(label, labelUpdate);
        Trigger sigAndStart = new SignalAndStart(compInstrHolder);

        JButton button1 = new JButton("This button");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sigAndStart.execute();
            }
        });
        // button1.addListener(e -> sigAndStart.execute());

        test.add(label);
        test.add(button1);
    }
    public testPanel returnThis(){
        return this;
    }
}
