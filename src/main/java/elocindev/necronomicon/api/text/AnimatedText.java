package elocindev.necronomicon.api.text;

//#if FABRIC==1
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
//#else
//$$ import net.minecraft.network.chat.Component;
//$$ import net.minecraft.network.chat.MutableComponent;
//#endif

public enum AnimatedText {
    EMPTY(0, "empty"),
    RAINBOW(1, "rainbow"),
    ETYR(2, "etyr"),
    BREATHING_ETYR(3, "breathing_etyr");

    private final int id;
    private final String name;

    private AnimatedText(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }


    public 
        //#if FABRIC==1
        MutableText 
        //#else
        //$$ MutableComponent
        //#endif
    getText(Text text) {
        return this.getText(text, 0);
    }

    public 
        //#if FABRIC==1
        MutableText 
        //#else
        //$$ MutableComponent
        //#endif

    getText(
        //#if FABRIC==1
        Text text, 
        //#else
        //$$ Component text, 
        //#endif       
    int offset) {
        switch (this) {
            case EMPTY -> {
                return text.copy();
            }

            case RAINBOW -> {
                return TextAPI.Styles.getRainbowGradient(text, offset, 1.0F);
            }

            case ETYR -> {
                return TextAPI.Styles.getStaticGradient(text, 0x67467d, 0x467d49);
            }

            case BREATHING_ETYR -> {
                return TextAPI.Styles.getBreathingGradient(text, offset, 0x67467d, 0x467d49, 1.0F);
            }
        }

        return text.copy();
    }
}