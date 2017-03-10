package eyeq.painfulcactus.event;

import eyeq.painfulcactus.PainfulCactus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class PainfulCactusEventHandler {
    @SubscribeEvent
    public void onHarvest(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        if(player.getEntityWorld().isRaining()) {
            return;
        }
        if(event.getState().getBlock() != Blocks.CACTUS) {
            return;
        }
        if(player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty()) {
            player.attackEntityFrom(DamageSource.CACTUS, 1);
        }
    }

    @SubscribeEvent
    public void onEntityItemPickup(EntityItemPickupEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if(player.getEntityWorld().isRemote) {
            return;
        }
        ItemStack itemStack = event.getItem().getEntityItem();
        if(itemStack.getItem() != Item.getItemFromBlock(Blocks.CACTUS)) {
            return;
        }
        Random rand = player.getRNG();
        for(int i = 0; i < itemStack.getCount(); i++) {
            if(rand.nextFloat() < PainfulCactus.prob) {
                player.attackEntityFrom(DamageSource.CACTUS, 1);
            }
        }
    }
}
