package elocindev.necronomicon.example;

//#if FABRIC==1
import elocindev.necronomicon.api.text.AnimatedText;
import elocindev.necronomicon.item.FancyItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AnimItemExample {
    public static final FancyItem EXAMPLE_RAINBOW = new ExampleFancyItem(new FabricItemSettings(), AnimatedText.RAINBOW);
    public static final FancyItem EXAMPLE_ETYR = new ExampleFancyItem(new FabricItemSettings(), AnimatedText.ETYR);
    public static final FancyItem EXAMPLE_ETYR_BREATHING = new ExampleFancyItem(new FabricItemSettings(), AnimatedText.BREATHING_ETYR);
    
    public static void init() {
        Registry.register(Registries.ITEM, new Identifier("necronomicon", "fancyitem_rainbow"), EXAMPLE_RAINBOW);
        Registry.register(Registries.ITEM, new Identifier("necronomicon", "fancyitem_etyr"), EXAMPLE_ETYR);
        Registry.register(Registries.ITEM, new Identifier("necronomicon", "fancyitem_etyr_breathing"), EXAMPLE_ETYR_BREATHING);
    }
}
//#endif
