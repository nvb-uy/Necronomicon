package elocindev.necronomicon.api.text;

import java.awt.Color;

//#if FABRIC==1
import elocindev.necronomicon.util.ColorUtils;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
//#endif

// TODO: FORGE PORT, REFACTOR
public class TextAPI {
    //#if FABRIC==1
    public static void setStaticGradient(MutableText text, int color1, int color2) {
        text.setStyle(Styles.getStaticGradient(text, color1, color2).getStyle());
    }

    public static void setSlideGradient(MutableText text, int color1, int color2) {
        text.setStyle(Styles.getStaticGradient(text, color1, color2).getStyle());
    }

    public static void setBreathingGradient(MutableText text, int offset, int color1, int color2, float tickrate) {
        text.setStyle(Styles.getBreathingGradient(text, offset, color1, color2, tickrate).getStyle());
    }

    public static void setRainbowGradient(MutableText text, int offset, float tickrate) {
        text.setStyle(Styles.getRainbowGradient(text, offset, tickrate).getStyle());
    }

    public class Styles {
        public static MutableText getStaticGradient(Text text, int color1, int color2) {
            MutableText gradientColor = Text.empty();

            String string = text.getString();

            for (int i = 0; i < string.length(); i++) {
                int color = ColorUtils.interpolate(color1, color2, (double) i / string.length());
                Style style = Style.EMPTY.withColor(color);

                gradientColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return gradientColor;
        }

        // Credits RXJpaw
        public static MutableText getGradient(Text text, int offset, int color1, int color2, float tickrate) {
            long time = System.currentTimeMillis() / ((long) tickrate * 50L);
            MutableText gradientColor = Text.empty();
            String string = text.getString();
            
            for (int i = 0; i < string.length(); i++) {
                double hue = (time - i - offset) % 45.0;
                Style style = Style.EMPTY.withColor(ColorUtils.gradientSlide(((float) hue / 22.5F), new Color(color1), new Color(color2)).getRGB());

                gradientColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return gradientColor;
        }

        public static MutableText getBreathingGradient(Text text, int offset, int color1, int color2, float tickrate) {
            long time = System.currentTimeMillis() / ((long) tickrate * 50L);
            MutableText gradientColor = Text.empty();

            String string = text.getString();

            for (int i = 0; i < string.length(); i++) {
                double animationFactor = (Math.sin((time - i - offset) * 0.05) + 1) / 2.0;
                int color = ColorUtils.interpolateAnimation(color1, color2, (double) i / string.length(), animationFactor);

                Style style = Style.EMPTY.withColor(color);

                gradientColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return gradientColor;
        }

        public static MutableText getRainbowGradient(Text text, int offset, float tickrate) {
            long time = System.currentTimeMillis() / ((long) tickrate * 50L);
            String string = text.getString();

            MutableText rainbowColor = Text.empty();

            for (int i = 0; i < string.length(); i++) {
                double hue = 1.0/90.0 * (time - i - offset);
                Style style = Style.EMPTY.withColor(Color.HSBtoRGB((float) (hue % 360), 0.5F, 1.0F));

                rainbowColor.append(Text.literal(String.valueOf(string.charAt(i))).setStyle(style));
            }

            return rainbowColor;
        }
    }
    //#endif
}
