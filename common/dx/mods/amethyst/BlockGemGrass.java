package dx.mods.amethyst;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockGemGrass extends Block
{
    public BlockGemGrass(int par1, int par2)
    {
        super(par1, par2, Material.ground);
        setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.isRemote)
        {
            return;
        }

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && Block.lightOpacity[par1World.getBlockId(par2, par3 + 1, par4)] > 2)
        {
            par1World.setBlockWithNotify(par2, par3, par4, mod_Amethyst.gemGrass.blockID);
        }
        else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            for (int i = 0; i < 45; i++)
            {
                int j = (par2 + par5Random.nextInt(3)) - 1;
                int k = (par3 + par5Random.nextInt(5)) - 3;
                int l = (par4 + par5Random.nextInt(3)) - 1;
                int i1 = par1World.getBlockId(j, k + 1, l);

                if (par1World.getBlockId(j, k, l) == mod_Amethyst.gemDirt.blockID && par1World.getBlockLightValue(j, k + 1, l) >= 4 && Block.lightOpacity[i1] <= 2)
                {
                    par1World.setBlockWithNotify(j, k, l, mod_Amethyst.gemGrass.blockID);
                }
            }
        }
    }

    public int getBlockTextureFromSideAndMetadata (int i, int j)
    {
    	
    	if (i == 0)
    		
    		return mod_Amethyst.grassBottom;
    	if (i == 1)
    		
    		return mod_Amethyst.grassTop;
    	
    	if (i == 2)
    		
    		return mod_Amethyst.grassSide;
    	if (i == 3)
    		
    		return mod_Amethyst.grassSide;
    	if (i == 4)
	
    		return mod_Amethyst.grassSide;
    	if (i == 5)
	
    		return mod_Amethyst.grassSide;
   
    	if (j ==1)
    	{
    		return 166;
    	}
    
    		return j != 2 ? 20 : 177;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_Amethyst.gemDirt.idDropped(0, par2Random, par3);
    }
    
    public String getTextureFile()
    {
            return "/tex/dxmods/amethyst/blocks0.png";
    }
}
