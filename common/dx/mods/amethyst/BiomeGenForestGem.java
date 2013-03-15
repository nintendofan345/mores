package dx.mods.amethyst;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenForestGem extends BiomeGenBase
{
    public BiomeGenForestGem(int par1)
    {
        super(par1);
        this.topBlock = (byte)mod_Amethyst.grassGemID;
        this.fillerBlock = (byte)mod_Amethyst.dirtGemID;
    }
}