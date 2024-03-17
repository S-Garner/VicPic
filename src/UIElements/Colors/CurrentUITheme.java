package src.UIElements.Colors;

public class CurrentUITheme {
    private ColorScheme currentBackgroundColor;
    public ColorScheme getCurrentBackgroundColor() {
        return currentBackgroundColor;
    }
    public void setCurrentBackgroundColor(String select) {
        this.currentBackgroundColor = UIColors.getColorScheme(select);
    }


    private ColorScheme currentForegroundColor;
    public ColorScheme getCurrentForegroundColor() {
        return currentForegroundColor;
    }
    public void setCurrentForegroundColor(String select) {
        this.currentForegroundColor = UIColors.getColorScheme(select);
        //this.currentForegroundColor = currentForegroundColor;
    }

    public CurrentUITheme(){
        currentBackgroundColor = UIColors.getColorScheme("cream");
        currentForegroundColor = UIColors.getColorScheme("charcoal");
    }

    public CurrentUITheme(String backSelect, String foreSelect){
        currentBackgroundColor = UIColors.getColorScheme(backSelect);
        currentForegroundColor = UIColors.getColorScheme(foreSelect);
    }

}
