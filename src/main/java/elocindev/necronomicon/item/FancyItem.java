package elocindev.necronomicon.item;

//? if fabric {
import elocindev.necronomicon.api.text.AnimatedText;
import elocindev.necronomicon.api.text.IAnimatedText;
//? }
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FancyItem extends Item 

//? if fabric {
    implements IAnimatedText
//? }

{
    //? if fabric {
    public AnimatedText nameAnimation;
    //? }

    public FancyItem(Properties settings 
    //? if fabric {
    , AnimatedText animatedTextType
    //? }
    ) {
        super(settings);
        //? if fabric {
        this.nameAnimation = animatedTextType;
        //? }
    }

    //? if fabric {
    @Override
    public AnimatedText getAnimatedName(ItemStack stack) {
       return this.nameAnimation;
    }
    //? }
}
