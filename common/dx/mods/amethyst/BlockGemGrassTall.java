package dx.mods.amethyst;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockGemGrassTall extends BlockFlower implements IShearable {

	public BlockGemGrassTall(int par1, int par2) {
		super(par1, par2);
		float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }
	
	@Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z) 
    {
        return true;
    }
	
	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        if (par1 == mod_Amethyst.gemGrass.blockID || par1 == mod_Amethyst.gemDirt.blockID)
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) 
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }
    
    public String getTextureFile()
    {
            return "/tex/dxmods/amethyst/blocks0.png";
    }
}
