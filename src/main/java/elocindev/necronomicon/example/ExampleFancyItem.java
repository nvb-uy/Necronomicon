package elocindev.necronomicon.example;
//#if FABRIC==1

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.necronomicon.api.text.AnimatedText;
import elocindev.necronomicon.api.text.TextAPI;
import elocindev.necronomicon.item.FancyItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ExampleFancyItem extends FancyItem {
    public ExampleFancyItem(Settings settings, AnimatedText animatedTextType) {
        super(settings, animatedTextType);
    }

    
    @Override
    public void appendTooltip(ItemStack itemStack, @Nullable World world, List<Text> list, TooltipContext tooltipContext) {
        super.appendTooltip(itemStack, world, list, tooltipContext);
        
        list.add(TextAPI.Styles.getRainbowGradient(Text.of("Gay people are based"), 0, 1.0F));
        list.add(TextAPI.Styles.getRainbowGradient(Text.of("I love you "), 0, 1.0F).append(TextAPI.Styles.getStaticGradient(Text.of("chorb :3"), 0xfcc203, 0xfce303)));
    }
    
}
//#endif
