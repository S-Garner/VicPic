package src.UIElements.Colors;

import java.awt.*;

public class UIColors {

    final static ColorScheme CHARCOAL = new ColorScheme(Color.decode("#171717"), Color.decode("#444444"), Color.decode("#6F6F6F"));
    final static ColorScheme CREAM = new ColorScheme(Color.decode("#FEFCE3"), Color.decode("#DFD9B3"), Color.decode("#BFB582"));
    final static ColorScheme MOSS = new ColorScheme(Color.decode("#55AA6F"), Color.decode("#499460"), Color.decode("#367D50"));
    final static ColorScheme NAVY = new ColorScheme(Color.decode("#10101F"), Color.decode("#252A42"), Color.decode("#393F5C"));
    final static ColorScheme MARIGOLD = new ColorScheme(Color.decode("#EDE682"), Color.decode("#D8CC6E"), Color.decode("#C2B259"));
    final static ColorScheme CRIMSON = new ColorScheme(Color.decode("#ED4434"), Color.decode("#DA2714"), Color.decode("#9C1C0E"));
    final static ColorScheme EGG = new ColorScheme(Color.decode("#F7F7F7"), Color.decode("#DCDBDF"), Color.decode("#BDBCC2"));

    public static ColorScheme getColorScheme(String name) {
        switch (name.toLowerCase()) {
            case "charcoal":
                return CHARCOAL;
            case "cream":
                return CREAM;
            case "moss":
                return MOSS;
            case "navy":
                return NAVY;
            case "marigold":
                return MARIGOLD;
            case "crimson":
                return CRIMSON;
            case "egg":
                return EGG;
            default:
                throw new IllegalArgumentException("Unknown color scheme");
        }
    }

}
