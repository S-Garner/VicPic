package src.IOClasses;

import src.Interfaces.Instructions;

import javax.swing.*;

public class CompInstrHolder<T> {
    private T component;
    private Instructions<T> instructions;

    public CompInstrHolder(T component, Instructions<T> instructions){
        this.component = component;
        this.instructions = instructions;
    }

    public void update(){
        instructions.update(component);
    }

}
