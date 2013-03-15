package dx.mods.amethyst;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBeachGem extends BiomeGenBase
{
    public BiomeGenBeachGem(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)mod_Amethyst.gemSand.blockID;
        this.fillerBlock = (byte)mod_Amethyst.gemSand.blockID;
    }
}
