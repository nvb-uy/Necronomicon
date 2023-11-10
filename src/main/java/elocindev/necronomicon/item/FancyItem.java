package elocindev.necronomicon.item;

//#if FABRIC==1
import elocindev.necronomicon.api.text.AnimatedText;
import elocindev.necronomicon.api.text.IAnimatedText;
//#endif

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FancyItem extends Item 

//#if FABRIC==1
    implements IAnimatedText
//#endif

{
    //#if FABRIC==1
    public AnimatedText nameAnimation;
    //#endif

    public FancyItem(Settings settings 
    //#if FABRIC==1
    , AnimatedText animatedTextType
    //#endif
    ) {
        super(settings);
        //#if FABRIC==1
        this.nameAnimation = animatedTextType;
        //#endif
    }

    //#if FABRIC==1
    @Override
    public AnimatedText getAnimatedName(ItemStack stack) {
       return this.nameAnimation;
    }
    //#endif
}
