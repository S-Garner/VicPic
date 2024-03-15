package src.Interfaces;

import javax.swing.*;

public interface Instructions<T extends JComponent>{
    void update(T component);
}
