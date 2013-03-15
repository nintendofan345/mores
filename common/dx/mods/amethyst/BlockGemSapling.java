package dx.mods.amethyst;
import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockGemSapling extends BlockFlower
{

        public BlockGemSapling(int i, int j)
        {
                super(i, j);
                float f = 0.4F;
                //the blockbounds set the hight and x/w of a block.
                setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
                this.setCreativeTab(CreativeTabs.tabBlock);
        }

        public void updateTick(World world, int i, int j, int k, Random random)
        {
                if(world.isRemote)
                {
                        return;
                }
                super.updateTick(world, i, j, k, random);

                if(world.getBlockLightValue(i, j + 1, k) >= 9 && random.nextInt(7) == 0)
                {
                        int l = world.getBlockMetadata(i, j, k);
                        if((l & 8) == 0)
                        {
                                world.setBlockMetadataWithNotify(i, j, k, l | 8);
                        } else
                        {
                                growTree(world, i, j, k, random);
                        }
                }
        }
        
        @Override
        protected boolean canThisPlantGrowOnThisBlockID(int par1)
        {
            return par1 == mod_Amethyst.gemGrass.blockID || par1 == mod_Amethyst.gemDirt.blockID;
        }

        //this code just adds a way for your generation code to be applied. remember to replace WorldGenYourTree with your 
        //world gen code. 
        public void growTree(World world, int i, int j, int k, Random random)
        {
                int l = world.getBlockMetadata(i, j, k) & 3;
                world.setBlock(i, j, k, 0);
                Object obj = null;
                obj = new WorldGenBerylTree(false);
                if(!((WorldGenerator) (obj)).generate(world, random, i, j, k))
                {
                        world.setBlockAndMetadata(i, j, k, blockID, l);
                }
        }

        //this code applies a texture path
        public String getTextureFile()
        {
         return "/tex/dxmods/amethyst/blocks0.png";
        }
    @SideOnly(Side.CLIENT)

    //just ignore this code ^_^ it is likely to deal with making the tree spawn
public void fertilize(World world, int x, int y, int z) 
{

}
}