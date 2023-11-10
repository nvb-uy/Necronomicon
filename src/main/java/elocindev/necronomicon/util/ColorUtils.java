package elocindev.necronomicon.util;

import java.awt.Color;

public class ColorUtils {
    
    public static int interpolate(int color1, int color2, double ratio) {
        int r = (int) (ColorUtils.getRed(color1) * (1 - ratio) + ColorUtils.getRed(color2) * ratio);
        int g = (int) (ColorUtils.getGreen(color1) * (1 - ratio) + ColorUtils.getGreen(color2) * ratio);
        int b = (int) (ColorUtils.getBlue(color1) * (1 - ratio) + ColorUtils.getBlue(color2) * ratio);

        return (r << 16) | (g << 8) | b;
    }


    public static int interpolateAnimation(int color1, int color2, double ratio, double animationFactor) {
        int r = (int) (ColorUtils.getRed(color1) * (1 - ratio) + ColorUtils.getRed(color2) * ratio);
        int g = (int) (ColorUtils.getGreen(color1) * (1 - ratio) + ColorUtils.getGreen(color2) * ratio);
        int b = (int) (ColorUtils.getBlue(color1) * (1 - ratio) + ColorUtils.getBlue(color2) * ratio);

        r = (int) (r + (255 - r) * animationFactor);
        g = (int) (g + (255 - g) * animationFactor);
        b = (int) (b + (255 - b) * animationFactor);

        return (r << 16) | (g << 8) | b;
    }

    public static int getRed(int color) {
        return (color >> 16) & 0xFF;
    }

    public static int getGreen(int color) {
        return (color >> 8) & 0xFF;
    }

    public static int getBlue(int color) {
        return color & 0xFF;
    }
    
    // Credits RXJpaw
    private static double bounceBack(double input, double range) {
        double doubleRange = range * 2;
        input = Math.abs(input);

        double position = input % doubleRange;
        return position >= range ? doubleRange - position : position;
    }

    // Credits RXJpaw
    public static Color gradientSlide(float progression, Color start, Color end) {
        progression = (float) bounceBack(progression, 1);
        
        int red = (int) (start.getRed() + progression * (end.getRed() - start.getRed()));
        int green = (int) (start.getGreen() + progression * (end.getGreen() - start.getGreen()));
        int blue = (int) (start.getBlue() + progression * (end.getBlue() - start.getBlue()));
        
        return new Color(red, green, blue);
    }
}
