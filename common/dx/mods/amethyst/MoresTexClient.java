package dx.mods.amethyst;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.client.MinecraftForgeClient;

public class MoresTexClient extends MoresTexProxy
{
        public void registerRenderInformation()
        {
                MinecraftForgeClient.preloadTexture("/tex/dxmods/amethyst/blocks0.png");
                MinecraftForgeClient.preloadTexture("/tex/dxmods/amethyst/items0.png");
                RenderingRegistry.registerEntityRenderingHandler(EntityBeryllope.class, new RenderBeryllope(new ModelBeryllope(), 2.5F));
                GameRegistry.addBiome(mod_Amethyst.extremeHillsGem);
                GameRegistry.addBiome(mod_Amethyst.desertGem);
        }
}