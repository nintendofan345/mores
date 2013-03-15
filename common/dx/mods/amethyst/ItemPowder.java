package dx.mods.amethyst;

import net.minecraft.item.Item;
import net.minecraft.potion.PotionHelper;

public class ItemPowder extends Item
{
        public ItemPowder(int i, int j)
        {
                super(i);
                if (j == 0) {
                	setPotionEffect(PotionHelper.speckledMelonEffect);
                }
                if (j == 1) {
                	setPotionEffect(PotionHelper.ghastTearEffect);
                }
                if (j == 2) {
                	setPotionEffect(PotionHelper.blazePowderEffect);
                }
                if (j == 3) {
                	setPotionEffect(PotionHelper.magmaCreamEffect);
                }
                if (j == 4) {
                	setPotionEffect(PotionHelper.field_82818_l);
                }
                if (j == 5) {
                	setPotionEffect(PotionHelper.field_77924_a);
                }
        }
        
        public String getTextureFile()
        {
                return "/tex/dxmods/amethyst/items0.png";
        }
}