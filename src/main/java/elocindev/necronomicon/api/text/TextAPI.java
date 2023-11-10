package elocindev.necronomicon.api.text;

import java.awt.Color;

import elocindev.necronomicon.util.ColorUtils;

//#if FABRIC==1
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
//#else
//$$ import net.minecraft.network.chat.Component;
//$$ import net.minecraft.network.chat.MutableComponent;
//$$ import net.minecraft.network.chat.Style;
//#endif

/**
 * An API containing methods to set the style of a text.
 * 
 * @minecraft   1.19 and above
 * @loader      Fabric, Forge
 * 
 * @since       1.3.0
 * @author      ElocinDev
 */
public class TextAPI {
    /**
     * Sets the text's style to a 2 color gradient, static.
     * 
     * @param text      The text to set the style of.
     * @param color1    The first color of the gradient.
     * @param color2    The second color of the gradient.
     * 
     * @since 1.3.0
     * @author ElocinDev
     */
    public static void setStaticGradient(
        //#if FABRIC==1
        MutableText text,
        //#else
        //$$ MutableComponent text,
        //#endif        
    int color1, int color2) {
        text.setStyle(Styles.getStaticGradient(text, color1, color2).getStyle());
    }

    /**
     * Sets the text's style to a 2 color gradient, animated.
     * 
     * @param text      The text to set the style of.
     * @param color1    The first color of the gradient.
     * @param color2    The second color of the gradient.
     * 
     * @since 1.3.0
     * @author ElocinDev
     */
    public static void setSlideGradient(
        //#if FABRIC==1
        MutableText text,
        //#else
        //$$ MutableComponent text,
        //#endif       
    int offset, int color1, int color2, float tickrate) {
        text.setStyle(Styles.getGradient(text, offset, color1, color2, tickrate).getStyle());
    }

    /**
     * Sets the text's style to a breathing gradient, animated.
     * 
     * @param text      The text to set the style of.
     * @param offset    The offset of the breathing gradient.
     * @param color1    The first color of the breathing gradient.
     * @param color2    The second color of the breathing gradient.
     * @param tickrate  The update tickrate. Lower is faster.
     * 
     * @since 1.3.0
     * @author ElocinDev
     */
    public static void setBreathingGradient(
        //#if FABRIC==1
        MutableText text,
        //#else
        //$$ MutableComponent text,
        //#endif 
    int offset, int color1, int color2, float tickrate) {
        text.setStyle(Styles.getBreathingGradient(text, offset, color1, color2, tickrate).getStyle());
    }

    /**
     * Sets the text's style to a rainbow gradient, animated.
     * 
     * @param text      The text to set the style of.
     * @param offset    The offset of the rainbow gradient.
     * @param tickrate  The update tickrate. Lower is faster.
     * 
     * @since 1.3.0
     * @author ElocinDev
     */
    public static void setRainbowGradient(
        //#if FABRIC==1
        MutableText text,
        //#else
        //$$ MutableComponent text,
        //#endif 
    int offset, float tickrate) {
        text.setStyle(Styles.getRainbowGradient(text, offset, tickrate).getStyle());
    }



    public class Styles {
        public static MutableText getStaticGradient(
            //#if FABRIC==1
            Text text,
            //#else
            //$$ Component text,
            //#endif
        int color1, int color2) {
            var gradientColor = getEmptyText();

            String string = text.getString();

            for (int i = 0; i < string.length(); i++) {
                int color = ColorUtils.interpolate(color1, color2, (double) i / string.length());
                Style style = Style.EMPTY.withColor(color);

                gradientColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return gradientColor;
        }

        // Credits RXJpaw
        public static MutableText getGradient(
            //#if FABRIC==1
            Text text,
            //#else
            //$$ Component text,
            //#endif
        int offset, int color1, int color2, float tickrate) {
            long time = System.currentTimeMillis() / ((long) tickrate * 50L);

            var gradientColor = getEmptyText();
            String string = text.getString();
            
            for (int i = 0; i < string.length(); i++) {
                double hue = (time - i - offset) % 45.0;
                Style style = Style.EMPTY.withColor(ColorUtils.gradientSlide(((float) hue / 22.5F), new Color(color1), new Color(color2)).getRGB());

                gradientColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return gradientColor;
        }

        public static MutableText getBreathingGradient(
            //#if FABRIC==1
            Text text,
            //#else
            //$$ Component text,
            //#endif
        int offset, int color1, int color2, float tickrate) {
            long time = System.currentTimeMillis() / ((long) tickrate * 50L);

            var gradientColor = getEmptyText();

            String string = text.getString();

            for (int i = 0; i < string.length(); i++) {
                double animationFactor = (Math.sin((time - i - offset) * 0.05) + 1) / 2.0;
                int color = ColorUtils.interpolateAnimation(color1, color2, (double) i / string.length(), animationFactor);

                Style style = Style.EMPTY.withColor(color);

                gradientColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return gradientColor;
        }

        public static MutableText getRainbowGradient(
            //#if FABRIC==1
            Text text,
            //#else
            //$$ Component text,
            //#endif
        int offset, float tickrate) {
            long time = System.currentTimeMillis() / ((long) tickrate * 50L);
            String string = text.getString();

            var rainbowColor = getEmptyText();

            for (int i = 0; i < string.length(); i++) {
                double hue = 1.0/90.0 * (time - i - offset);
                Style style = Style.EMPTY.withColor(Color.HSBtoRGB((float) (hue % 360), 0.5F, 1.0F));

                rainbowColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return rainbowColor;
        }
    }

    private static 
        //#if FABRIC==1
        MutableText
        //#else
        //$$ MutableComponent
        //#endif
    getEmptyText() {
        //#if FABRIC==1
        return Text.empty();
        //#else
        //$$ return Component.empty();
        //#endif
    }
}
