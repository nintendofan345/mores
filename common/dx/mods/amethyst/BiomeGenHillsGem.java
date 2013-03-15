package dx.mods.amethyst;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHillsGem extends BiomeGenBase
{
	private WorldGenerator theWorldGenerator;
	
    public BiomeGenHillsGem(int par1)
    {
        super(par1);
        this.topBlock = (byte)mod_Amethyst.gemGrass.blockID;
        this.fillerBlock = (byte)mod_Amethyst.gemDirt.blockID;
    }
}
