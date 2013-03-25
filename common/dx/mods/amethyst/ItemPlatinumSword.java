package dx.mods.amethyst;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemPlatinumSword extends ItemMoresSword {

	public ItemPlatinumSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}
	
	public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return par1ItemStack.itemID == mod_Amethyst.platinumLapisSword.itemID ? EnumRarity.epic : EnumRarity.epic;
    }
}
