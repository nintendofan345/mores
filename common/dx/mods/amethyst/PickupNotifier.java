package dx.mods.amethyst;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IPickupNotifier;

public class PickupNotifier implements IPickupNotifier{
	public ItemStack item;
	
	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player) {
		this.item = item.getEntityItem();
		
		if (this.item.itemID == mod_Amethyst.amethyst.itemID) {
			player.addStat(mod_Amethyst.amethystAchieve, 1);
		}
		if (this.item.itemID == mod_Amethyst.peridot.itemID) {
			player.addStat(mod_Amethyst.peridotAchieve, 1);
		}
		if (this.item.itemID == mod_Amethyst.garnet.itemID) {
			player.addStat(mod_Amethyst.garnetAchieve, 1);
		}
	}
	
}
