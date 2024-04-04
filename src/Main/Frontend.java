package src.Main;

import java.io.FileNotFoundException;

public class Frontend {
    private Holder holder;
    private VicMainUI vicUI;

    public Frontend() throws FileNotFoundException {
        Holder mainHolder = new Holder();
        VicMainUI mainFront = new VicMainUI(mainHolder);
    }

    public Holder getHolder(){
        return holder;
    }

    public VicMainUI getVicUI(){
        return vicUI;
    }

}
