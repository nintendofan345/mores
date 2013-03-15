package dx.mods.amethyst;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockGemDirt extends Block
{
    public BlockGemDirt(int par1, int par2)
    {
        super(par1, par2, Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public String getTextureFile()
    {
            return "/tex/dxmods/amethyst/blocks0.png";
    }
}
