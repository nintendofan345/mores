package dx.mods.amethyst;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class MoresEventHooks {
	public EntityPlayer player;
	public ItemStack item;
	
	@ForgeSubscribe
	public void onItemPickup (EntityItemPickupEvent par1) {
		this.item = par1.item.func_92014_d();
		
		if (this.item.itemID == mod_Amethyst.amethyst.shiftedIndex) {
			player.triggerAchievement(mod_Amethyst.amethystAchieve);
		}
		if (this.item.itemID == mod_Amethyst.peridot.shiftedIndex) {
			player.triggerAchievement(mod_Amethyst.peridotAchieve);
		}
		if (this.item.itemID == mod_Amethyst.garnet.shiftedIndex) {
			player.triggerAchievement(mod_Amethyst.garnetAchieve);
		}
	}
}
