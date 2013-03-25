package dx.mods.amethyst;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler 
{
        //This is how you check if a player has crafted something that returns the block/item you want them to gain an achievement for
        @Override
        public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) 
        {
                if (item.itemID == mod_Amethyst.gemAlloyIngot.itemID)
                {
                        player.addStat(mod_Amethyst.gemStackAch, 1);
                }
        }

        
        //This is how you check if a player has smelted something that returns the block/item you want them to gain an achievement for
        @Override
        public void onSmelting(EntityPlayer player, ItemStack item) 
        {
        	if (item.itemID == mod_Amethyst.amethyst.itemID)
        	{
                    player.addStat(mod_Amethyst.amethystAchieve, 1);
            }
        	if (item.itemID == mod_Amethyst.peridot.itemID)
        	{
                    player.addStat(mod_Amethyst.peridotAchieve, 1);
            }
        	if (item.itemID == mod_Amethyst.garnet.itemID)
        	{
                    player.addStat(mod_Amethyst.garnetAchieve, 1);
            }
        	
        	if (item.itemID == mod_Amethyst.amethystIngot.itemID)
            {
                    player.addStat(mod_Amethyst.temperAmethystAch, 1);
            }
        	if (item.itemID == mod_Amethyst.peridotIngot.itemID)
            {
                    player.addStat(mod_Amethyst.temperPeridotAch, 1);
            }
            if (item.itemID == mod_Amethyst.garnetIngot.itemID)
            {
                    player.addStat(mod_Amethyst.temperGarnetAch, 1);
            }
        }
}