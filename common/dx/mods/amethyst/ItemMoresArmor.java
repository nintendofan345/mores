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
    	if (par1.itemID == mod_Amethyst.alloyHelmet.itemID || par1.itemID == mod_Amethyst.alloyPlate.itemID || par1.itemID == mod_Amethyst.alloyBoots.itemID)
    	{
    		return "/armor/alloy_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.alloyLeggings.itemID)
    	{
    		return "/armor/alloy_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.amethystHelmet.itemID || par1.itemID == mod_Amethyst.amethystPlate.itemID || par1.itemID == mod_Amethyst.amethystBoots.itemID)
    	{
    		return "/armor/amethyst_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.amethystLeggings.itemID)
    	{
    		return "/armor/amethyst_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.peridotHelmet.itemID || par1.itemID == mod_Amethyst.peridotPlate.itemID || par1.itemID == mod_Amethyst.peridotBoots.itemID)
    	{
    		return "/armor/peridot_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.peridotLeggings.itemID)
    	{
    		return "/armor/peridot_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.garnetHelmet.itemID || par1.itemID == mod_Amethyst.garnetPlate.itemID || par1.itemID == mod_Amethyst.garnetBoots.itemID)
    	{
    		return "/armor/garnet_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.garnetLeggings.itemID)
    	{
    		return "/armor/garnet_2.png";
    	}
    	if (par1.itemID == mod_Amethyst.osmiumHelmet.itemID || par1.itemID == mod_Amethyst.osmiumPlate.itemID || par1.itemID == mod_Amethyst.osmiumBoots.itemID)
    	{
    		return "/armor/osmium_1.png";
    	}
    	if (par1.itemID == mod_Amethyst.osmiumLeggings.itemID)
    	{
    		return "/armor/osmium_2.png";
    	}
    	else
    	{
    		return "/armor/diamond_1.png";
    	}
    }
}