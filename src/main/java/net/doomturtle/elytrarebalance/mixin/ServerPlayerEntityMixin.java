package net.doomturtle.elytrarebalance.mixin;

import net.doomturtle.elytrarebalance.DoomsElytraMod;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    @Inject(method = "playerTick", at = @At("HEAD"))
    private void onPlayerTick(CallbackInfo ci)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        ItemStack chestStack = player.getInventory().armor.get(2);
        if (chestStack.getItem() == Items.ELYTRA)
        {
            /*
                // Create a new ItemStack for Elytra with custom durability
            ItemStack newElytraStack = new ItemStack(Items.ELYTRA);
            newElytraStack.setDamage(-DoomsElytraMod.elytra_durability_config);

            player.getInventory().armor.set(2, newElytraStack);
             */

        }
    }

}
