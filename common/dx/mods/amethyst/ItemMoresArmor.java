package dx.mods.amethyst;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemMoresArmor extends ItemArmor implements IArmorTextureProvider
{
    public ItemMoresArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par4, par4);
    }
    
    public String getArmorTextureFile(ItemStack par1)
    {
    	if (par1.itemID == mod_Amethyst.alloyHelmet.shiftedIndex || par1.itemID == mod_Amethyst.alloyPlate.shiftedIndex || par1.itemID == mod_Amethyst.alloyBoots.shiftedIndex)
    	{
    		return "/armor/alloy_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.alloyLeggings.shiftedIndex)
    	{
    		return "/armor/alloy_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.amethystHelmet.shiftedIndex || par1.itemID == mod_Amethyst.amethystPlate.shiftedIndex || par1.itemID == mod_Amethyst.amethystBoots.shiftedIndex)
    	{
    		return "/armor/amethyst_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.amethystLeggings.shiftedIndex)
    	{
    		return "/armor/amethyst_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.peridotHelmet.shiftedIndex || par1.itemID == mod_Amethyst.peridotPlate.shiftedIndex || par1.itemID == mod_Amethyst.peridotBoots.shiftedIndex)
    	{
    		return "/armor/peridot_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.peridotLeggings.shiftedIndex)
    	{
    		return "/armor/peridot_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.garnetHelmet.shiftedIndex || par1.itemID == mod_Amethyst.garnetPlate.shiftedIndex || par1.itemID == mod_Amethyst.garnetBoots.shiftedIndex)
    	{
    		return "/armor/garnet_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.garnetLeggings.shiftedIndex)
    	{
    		return "/armor/garnet_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.osmiumHelmet.shiftedIndex || par1.itemID == mod_Amethyst.osmiumPlate.shiftedIndex || par1.itemID == mod_Amethyst.osmiumBoots.shiftedIndex)
    	{
    		return "/armor/osmium_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.osmiumLeggings.shiftedIndex)
    	{
    		return "/armor/osmium_2.png";
    	}
    	else
    	{
    		return "/armor/diamond_1.png";
    	}
    }
    
    public String getTextureFile()
    {
            return "/tex/dxmods/amethyst/items0.png";
    }
}