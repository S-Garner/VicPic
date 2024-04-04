package src.Main;

import src.IOClasses.SimpleInstrHolder;
import src.Interfaces.SimpleInstructions;
import src.Main.UI.Format.VicFormatter;
import src.Main.UI.Panels.*;
import src.Questions.Questions;
import src.Students.Victim;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Colors.*;
import src.UIElements.Panels.RoundedPanel;
import src.WriterReader.Input;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static src.Main.Assets.filePaths.*;

public class VicPic {
    public static void main(String[] args) throws FileNotFoundException {
        Holder mainHolder = new Holder();
        Frontend mainFront = new Frontend(mainHolder);
    }
}