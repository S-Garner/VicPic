package src.UIElements.Colors;

import src.UIElements.Panels.BufferedPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {
    private BufferedImage image;
    private String imageSelect;
    private String imagePath;
    private String assetPath = "C:/Github/VicPic/src/Main/Assets/";
    private String extension = ".png";

    public Images(String imageSelect, CurrentUITheme theme, boolean Alter){
        this.imageSelect = imageSelect;
        this.imagePath = this.assetPath + this.imageSelect + this.extension;

        try{
            if (Alter == true) {
                image = ImageIO.read(new File(this.imagePath));
                processImage(theme.getCurrentForegroundColor().main());
            }else if(Alter == false){
                image = ImageIO.read(new File(this.imagePath));
            }
            //image = cropImageToSize(image, 150, 150);
        }catch (IOException e){
            e.printStackTrace();
            image = null;
        }
    }

    private void processImage(Color color){
        double blendFactor = 0.75;

        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                int rgba = image.getRGB(x, y);
                int alpha = (rgba >> 24) & 0xFF;
                int red = (rgba >> 16) & 0xFF;
                int green = (rgba >> 8) & 0xFF;
                int blue = rgba & 0xFF;

                red = (int)((red * (1 - blendFactor)) + (color.getRed() * blendFactor));
                green = (int)((green * (1 - blendFactor)) + (color.getGreen() * blendFactor));
                blue = (int)((blue * (1 - blendFactor)) + (color.getBlue() * blendFactor));

                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, newRGB);

            }
        }
    }


    public BufferedImage getImage() {
        return image;
    }

    /*
    private BufferedImage cropImageToSize(BufferedImage sourceImage, int maxWidth, int maxHeight) {
        if (sourceImage.getWidth() > maxWidth || sourceImage.getHeight() > maxHeight) {
            // Calculate the top-left corner of the crop area to center the crop
            int x = (sourceImage.getWidth() - maxWidth) / 2;
            int y = (sourceImage.getHeight() - maxHeight) / 2;
            return sourceImage.getSubimage(x, y, maxWidth, maxHeight);
        }
        return sourceImage;
    }

     */

}
