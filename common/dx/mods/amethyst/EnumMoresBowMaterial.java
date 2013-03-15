package dx.mods.amethyst;

public enum EnumMoresBowMaterial {
	BASE(1800, 2, 5),
	ALLOY(2200, 3, 6),
	OSMIUM(3000, 4, 7);

	private final int maxUses;
	
	private final int damageVsEntity;
	
	private final int enchantability;
	
	private EnumMoresBowMaterial(int par3, int par4, int par5)
    {
        this.maxUses = par3;
        this.damageVsEntity = par4;
        this.enchantability = par5;
    }
	
	public int getMaxUses()
    {
        return this.maxUses;
    }
	
	public int getDamageVsEntity()
    {
        return this.damageVsEntity;
    }
	
	public int getEnchantability()
    {
        return this.enchantability;
    }
}
