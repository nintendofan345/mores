package dx.mods.amethyst;

import java.util.Random;
import java.util.Calendar;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenMoresOres implements IWorldGenerator {
	public Calendar rightNow = Calendar.getInstance();
	public int month = rightNow.get(rightNow.MONTH);
	
        @Override
        public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider

chunkGenerator, IChunkProvider chunkProvider) {
                switch(world.provider.dimensionId) {
                case -1:
                        generateNether(world, random, chunkX*16, chunkZ*16);
                        break;
                case 0:
                        generateSurface(world, random, chunkX*16, chunkZ*16);
                        break;
                case 1:
                        generateEnd();
                        break;
                }
        }
        
        public void generateNether(World world, Random rand, int x, int z) {
        	for(int a = 0; a < 8; a++){
    			int i = x + rand.nextInt(16);
    			int j = rand.nextInt(world.getHeight());
    			int k = z + rand.nextInt(16);
    			(new WorldGenOreNether(mod_Amethyst.platinumOre.blockID, 6)).generate(world, rand, i, j, k);
    		}
        }
        
        public void generateSurface(World world, Random rand, int x, int z){
    		for(int a = 0; a < 12; a++){
    			int i = x + rand.nextInt(16);
    			int j = rand.nextInt(128);
    			int k = z + rand.nextInt(16);
    			(new WorldGenMinable(mod_Amethyst.amethystOre.blockID, 10)).generate(world, rand, i, j, k);
    		}
    		
    		for(int a = 0; a < 12; a++){
    			int i = x + rand.nextInt(16);
    			int j = rand.nextInt(128);
    			int k = z + rand.nextInt(16);
    			(new WorldGenMinable(mod_Amethyst.peridotOre.blockID, 10)).generate(world, rand, i, j, k);
    		}
    		
    		for(int a = 0; a < 12; a++){
    			int i = x + rand.nextInt(16);
    			int j = rand.nextInt(128);
    			int k = z + rand.nextInt(16);
    			(new WorldGenMinable(mod_Amethyst.garnetOre.blockID, 10)).generate(world, rand, i, j, k);
    		}
    		
    		for(int a = 0; a < 10; a++){
    			int i = x + rand.nextInt(16);
    			int j = rand.nextInt(32);
    			int k = z + rand.nextInt(16);
    			(new WorldGenMinable(mod_Amethyst.osmiumOre.blockID, 9)).generate(world, rand, i, j, k);
    		}
    		
    		for(int a = 0; a < 12; a++){
    			int i = x + rand.nextInt(16);
    			int j = rand.nextInt(32);
    			int k = z + rand.nextInt(16);
    			(new WorldGenMinable(mod_Amethyst.glowstoneOre.blockID, 11)).generate(world, rand, i, j, k);
    		}
    		
    		if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 9 || this.month == 11) {
    			for(int a = 0; a < 9; a++) {
    				int i = x + rand.nextInt(16);
    				int j = rand.nextInt(64);
    				int k = z + rand.nextInt(16);
    				(new WorldGenMinable(mod_Amethyst.beryllaniumOre.blockID, 12)).generate(world, rand, i, j, k);
    			}
    		}
    	}
        
        public void generateEnd() {
                //we're not going to generate in the end either
        }
}