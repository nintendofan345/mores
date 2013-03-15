package dx.mods.amethyst;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockItemOre extends Block {
	public int i;
	
	public BlockItemOre(int par1, int par2, int par3)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        i = par3;
    }
	
	public int idDropped(int par1, Random par2Random, int par3) {
		if (i == 0) {
			return mod_Amethyst.amethyst.shiftedIndex;
		}
		if (i == 1) {
			return mod_Amethyst.peridot.shiftedIndex;
		}
		if (i == 2) {
			return mod_Amethyst.garnet.shiftedIndex;
		}
		if (i == 3) {
			return Item.lightStoneDust.shiftedIndex;
		}
		return 0;
	}
	
	public int quantityDropped(Random par1Random)
    {
		if (i == 3) {
			return 1 + par1Random.nextInt(2);
		}
        return 1;
    }
	
	public String getTextureFile()
    {
            return "/tex/dxmods/amethyst/blocks0.png";
    }
}
