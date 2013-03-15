package dx.mods.amethyst;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenPlainsGem extends BiomeGenBase
{
    public BiomeGenPlainsGem(int par1)
    {
        super(par1);
        this.topBlock = (byte)mod_Amethyst.grassGemID;
        this.fillerBlock = (byte)mod_Amethyst.dirtGemID;
    }
}
