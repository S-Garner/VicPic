package src.IOClasses;

import src.Interfaces.Trigger;

public class SignalAndStart implements Trigger {
    private CompInstrHolder<?> compInstrHolder;

    public SignalAndStart(CompInstrHolder <?> compInstrHolder){
        this.compInstrHolder = compInstrHolder;
    }

    @Override
    public void execute(){
        compInstrHolder.update();
    }

}
