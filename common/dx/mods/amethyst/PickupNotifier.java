package dx.mods.amethyst;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IPickupNotifier;

public class PickupNotifier implements IPickupNotifier{
	public ItemStack item;
	
	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player) {
		this.item = item.func_92014_d();
		
		if (this.item.itemID == mod_Amethyst.amethyst.shiftedIndex) {
			player.addStat(mod_Amethyst.amethystAchieve, 1);
		}
		if (this.item.itemID == mod_Amethyst.peridot.shiftedIndex) {
			player.addStat(mod_Amethyst.peridotAchieve, 1);
		}
		if (this.item.itemID == mod_Amethyst.garnet.shiftedIndex) {
			player.addStat(mod_Amethyst.garnetAchieve, 1);
		}
	}
	
}
