package dx.mods.amethyst;

import net.minecraft.item.Item;

public class ItemGem extends Item
{
        public ItemGem(int par1, int par2)
        {
            super(par1);
            if (par2 == 1) {
            	// Set off pickup event for ach here
            }
            if (par2 == 2) {
            	// Set off pickup event for ach here
            }
            if (par2 == 3) {
            	// Set off pickup event for ach here
            }
        }
        
        public ItemGem(int par1) {
        	this(par1, 0);
        }
}