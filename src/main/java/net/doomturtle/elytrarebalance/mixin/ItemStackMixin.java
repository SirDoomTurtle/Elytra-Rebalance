package net.doomturtle.elytrarebalance.mixin;

import net.doomturtle.elytrarebalance.DoomsElytraMod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ItemStack.class)
public class ItemStackMixin {

    /*
        @Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
    private void getMaxDamage(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.getItem() == Items.ELYTRA)
        {
            cir.setReturnValue(DoomsElytraMod.elytra_durability_config);
        }
    }
     */

}
