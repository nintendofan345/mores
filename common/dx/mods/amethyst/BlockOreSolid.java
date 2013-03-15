package dx.mods.amethyst;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOreSolid extends Block {

	public BlockOreSolid(int par1, int par2)
    {
        super(par1, par2, Material.iron);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
	
	public String getTextureFile()
    {
            return "/tex/dxmods/amethyst/blocks0.png";
    }
}