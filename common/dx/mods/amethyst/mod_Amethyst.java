package dx.mods.amethyst;

import java.util.Calendar;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "M'ores", name = "M'ores", version = "1.4.7-V2.0", useMetadata = true)
public class mod_Amethyst {
	@SidedProxy(clientSide = "dx.mods.amethyst.MoresTexClient", serverSide= "dx.mods.amethyst.MoresTexProxy")
    public static MoresTexProxy proxy;
	
	//Variable Declarations
	public static int grassTop;
	public static int grassSide;
	public static int grassBottom;
	public Calendar rightNow = Calendar.getInstance();
	public int month = rightNow.get(rightNow.MONTH);
	
	//Gem Ores and Solids Definitions
	public static Block amethystOre;
	public static Block peridotOre;
	public static Block garnetOre;
	public static Block amethystBlock;
	public static Block peridotBlock;
	public static Block garnetBlock;
	
	//Gem Biome Block Definitions
	public static Block gemSand;
	public static Block gemDirt;
	public static Block gemGrass;
	public static Block gemLog;
	public static Block gemLeaves;
	public static Block gemSapling;
	public static Block gemTallGrass;
	
	//Metal Ores and Solids Definitions
	public static Block osmiumOre;
	public static Block osmiumBlock;
	public static Block beryllaniumBlock;
	public static Block beryllaniumOre;
	public static Block platinumOre;
	
	//Miscellaneous Ore Definitions
	public static Block glowstoneOre;
	
	//Material Registrations
	static EnumToolMaterial EnumBaseMoresToolMat = EnumHelper.addToolMaterial("MORESBASE", 2, 800, 12.0F, 2, 22);
	static EnumToolMaterial EnumAlloyToolMat = EnumHelper.addToolMaterial("ALLOY", 3, 2500, 16.0F, 4, 45);
	static EnumToolMaterial EnumOsmiumToolMat = EnumHelper.addToolMaterial("OSMIUM", 3, 4000, 20.0F, 6, 60);
	static EnumToolMaterial EnumPlatinumToolMat = EnumHelper.addToolMaterial("PLATINUM", 3, 6000, 20.0F, 12, 120);
	
	static EnumArmorMaterial EnumBaseMoresArmorMat = EnumHelper.addArmorMaterial("MORESBASE", 21, new int[]{3, 7, 4, 2}, 25);
	static EnumArmorMaterial EnumAlloyArmorMat = EnumHelper.addArmorMaterial("ALLOY", 45, new int[]{4, 8, 6, 4}, 35);
	static EnumArmorMaterial EnumOsmiumArmorMat = EnumHelper.addArmorMaterial("OSMIUM", 65, new int[]{5, 10, 8, 5}, 50);
	
	//Init Codes
	@PreInit
    public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		amethystOreID = config.getBlock("amethystOreID", 2280).getInt();
		peridotOreID = config.getBlock("peridotOreID", 2281).getInt();
		garnetOreID = config.getBlock("garnetOreID", 2282).getInt();
		amethystBlockID = config.getBlock("amethystBlockID", 2283).getInt();
		peridotBlockID = config.getBlock("peridotBlockID", 2284).getInt();
		garnetBlockID = config.getBlock("garnetBlockID", 2285).getInt();
		sandGemID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "gemSandID", 234, null).getInt();
		dirtGemID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "gemDirtID", 235, null).getInt();
		grassGemID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "gemGrassID", 236, null).getInt();
		logGemID = config.getBlock("gemLogID", 2286).getInt();
		leafGemID = config.getBlock("gemLeavesID", 2287).getInt();
		saplingGemID = config.getBlock("gemSaplingID", 2288).getInt();
		tallgrassGemID = config.getBlock("gemTallGrassID", 2289).getInt();
		osmiumOreID = config.getBlock("osmiumOreID", 2290).getInt();
		osmiumBlockID = config.getBlock("osmiumBlockID", 2291).getInt();
		beryllaniumOreID = config.getBlock("beryllaniumOreID", 2292).getInt();
		beryllaniumBlockID = config.getBlock("beryllaniumBlockID", 2293).getInt();
		platinumOreID = config.getBlock("platinumOreID", 2294).getInt();
		glowstoneOreID = config.getBlock("glowstoneOreID", 2295).getInt();
		
		GDTID = config.get(Configuration.CATEGORY_GENERAL, "gemDesertID", 32).getInt();
		GHLID = config.get(Configuration.CATEGORY_GENERAL, "gemHillsID", 33).getInt();
		
		config.save();
    }
	
	@Init
	public void init(FMLInitializationEvent event) {
		amethystOre = (new BlockItemOre(amethystOreID, 1, 0)).setHardness(3.0F).setResistance(5.0F).setUnlocalizedName("oreAmethyst").setStepSound(Block.soundStoneFootstep);
		peridotOre = (new BlockItemOre(peridotOreID, 3, 1)).setHardness(3.0F).setResistance(5.0F).setUnlocalizedName("orePeridot").setStepSound(Block.soundStoneFootstep);
		garnetOre = (new BlockItemOre(garnetOreID, 5, 2)).setHardness(3.0F).setResistance(5.0F).setUnlocalizedName("oreGarnet").setStepSound(Block.soundStoneFootstep);
		amethystBlock = (new BlockOreSolid(amethystBlockID, 0)).setHardness(3.0F).setResistance(10.0F).setUnlocalizedName("blockAmethyst").setStepSound(Block.soundMetalFootstep);
		peridotBlock = (new BlockOreSolid(peridotBlockID, 2)).setHardness(3.0F).setResistance(10.0F).setUnlocalizedName("blockPeridot").setStepSound(Block.soundMetalFootstep);
		garnetBlock = (new BlockOreSolid(garnetBlockID, 4)).setHardness(3.0F).setResistance(10.0F).setUnlocalizedName("blockGarnet").setStepSound(Block.soundMetalFootstep);
		
		gemSand = (new BlockGemSand(sandGemID, 12)).setHardness(0.5F).setUnlocalizedName("sandGem").setStepSound(Block.soundSandFootstep);
		gemDirt = (new BlockGemDirt(dirtGemID, 6)).setHardness(0.5F).setUnlocalizedName("dirtGem").setStepSound(Block.soundGravelFootstep);
		gemGrass = (new BlockGemGrass(grassGemID, 8)).setHardness(0.5F).setUnlocalizedName("grassGem").setStepSound(Block.soundGrassFootstep);
		gemLog = (new BlockGemLog(logGemID)).setHardness(1.0F).setUnlocalizedName("logGem").setCreativeTab(CreativeTabs.tabBlock);
		gemLeaves = (new BlockGemLeaves(leafGemID, 9)).setHardness(1.0F).setStepSound(Block.soundGrassFootstep).setResistance(1.0F).setUnlocalizedName("leavesGem").setCreativeTab(CreativeTabs.tabDecorations);
		gemSapling = (new BlockGemSapling(saplingGemID, 13)).setStepSound(Block.soundGrassFootstep).setHardness(0.0F).setUnlocalizedName("saplingGem").setCreativeTab(CreativeTabs.tabDecorations);
		gemTallGrass = (new BlockGemGrassTall(tallgrassGemID, 16)).setStepSound(Block.soundGrassFootstep).setHardness(0.0F).setUnlocalizedName("tallGrassGem").setCreativeTab(CreativeTabs.tabDecorations);
		
		osmiumOre = (new BlockBlockOre(osmiumOreID, 14).setHardness(6.0F).setResistance(14.0F).setUnlocalizedName("oreOsmium").setStepSound(Block.soundStoneFootstep));
		osmiumBlock = (new BlockOreSolid(osmiumBlockID, 18).setHardness(6.0F).setResistance(20.0F).setUnlocalizedName("blockOsmium").setStepSound(Block.soundMetalFootstep));
		beryllaniumBlock = (new BlockOreSolid(beryllaniumBlockID, 17).setHardness(5.0F).setResistance(12.0F).setUnlocalizedName("blockBeryllanium").setStepSound(Block.soundMetalFootstep));
		beryllaniumOre = (new BlockBlockOre(beryllaniumOreID, 19).setHardness(5.0F).setResistance(7.0F).setUnlocalizedName("oreBeryllanium").setStepSound(Block.soundStoneFootstep));
		platinumOre = (new BlockBlockOre(platinumOreID, 20).setHardness(6.0F).setResistance(14.0F).setUnlocalizedName("orePlatinum").setStepSound(Block.soundStoneFootstep));
		
		glowstoneOre = (new BlockItemOre(glowstoneOreID, 21, 3).setHardness(3.0F).setResistance(5.0F).setUnlocalizedName("oreGlowstone").setLightValue(1.0F).setStepSound(Block.soundStoneFootstep));
		
		/*
		//Gem and Ingot Definitions
		amethyst = new ItemGem(16384, 1).setIconIndex(0).setUnlocalizedName("amethyst").setCreativeTab(CreativeTabs.tabMaterials);
		peridot = new ItemGem(16385, 2).setIconIndex(1).setUnlocalizedName("peridot").setCreativeTab(CreativeTabs.tabMaterials);
		garnet = new ItemGem(16386, 3).setIconIndex(2).setUnlocalizedName("garnet").setCreativeTab(CreativeTabs.tabMaterials);
		amethystIngot = new ItemGem(16387).setIconIndex(16).setUnlocalizedName("amethystIngot").setCreativeTab(CreativeTabs.tabMaterials);
		peridotIngot = new ItemGem(16388).setIconIndex(17).setUnlocalizedName("peridotIngot").setCreativeTab(CreativeTabs.tabMaterials);
		garnetIngot = new ItemGem(16389).setIconIndex(18).setUnlocalizedName("garnetIngot").setCreativeTab(CreativeTabs.tabMaterials);
		gemAlloyIngot = new ItemGem(16390).setIconIndex(3).setUnlocalizedName("gemAlloyIngot").setCreativeTab(CreativeTabs.tabMaterials);
		berylliteIngot = new ItemGem(16391).setIconIndex(19).setUnlocalizedName("berylliteIngot").setCreativeTab(CreativeTabs.tabMaterials);
		
		//Metal Definitions
		osmiumNugget = new ItemGem(16539).setIconIndex(112).setUnlocalizedName("osmiumNugget").setCreativeTab(CreativeTabs.tabMaterials);
		osmiumStone = new ItemOsmiumStone(16540).setIconIndex(113).setUnlocalizedName("osmiumStone").setCreativeTab(CreativeTabs.tabMaterials);
		osmiumUntemperedIngot = new ItemGem(16541).setIconIndex(114).setUnlocalizedName("osmiumUntemperedIngot").setCreativeTab(CreativeTabs.tabMaterials);
		osmiumIngot = new ItemGem(16542).setIconIndex(115).setUnlocalizedName("osmiumIngot").setCreativeTab(CreativeTabs.tabMaterials);
		
		//Tool Definitions
		amethystSword = new ItemMoresSword(16515, EnumBaseMoresToolMat).setIconCoord(0, 6).setUnlocalizedName("amethystSword").setCreativeTab(CreativeTabs.tabCombat);
		peridotSword = new ItemMoresSword(16520, EnumBaseMoresToolMat).setIconCoord(1, 6).setUnlocalizedName("peridotSword").setCreativeTab(CreativeTabs.tabCombat);
		garnetSword = new ItemMoresSword(16525, EnumBaseMoresToolMat).setIconCoord(2, 6).setUnlocalizedName("garnetSword").setCreativeTab(CreativeTabs.tabCombat);
		amethystShovel = new ItemMoresSpade(16500, EnumBaseMoresToolMat).setIconCoord(0, 5).setUnlocalizedName("amethystShovel").setCreativeTab(CreativeTabs.tabTools);
		peridotShovel = new ItemMoresSpade(16504, EnumBaseMoresToolMat).setIconCoord(1, 5).setUnlocalizedName("peridotShovel").setCreativeTab(CreativeTabs.tabTools);
		garnetShovel = new ItemMoresSpade(16508, EnumBaseMoresToolMat).setIconCoord(2, 5).setUnlocalizedName("garnetShovel").setCreativeTab(CreativeTabs.tabTools);
		amethystPickaxe = new ItemMoresPickaxe(16501, EnumBaseMoresToolMat).setIconCoord(0, 4).setUnlocalizedName("amethystPickaxe").setCreativeTab(CreativeTabs.tabTools);
		peridotPickaxe = new ItemMoresPickaxe(16505, EnumBaseMoresToolMat).setIconCoord(1, 4).setUnlocalizedName("peridotPickaxe").setCreativeTab(CreativeTabs.tabTools);
		garnetPickaxe = new ItemMoresPickaxe(16509, EnumBaseMoresToolMat).setIconCoord(2, 4).setUnlocalizedName("garnetPickaxe").setCreativeTab(CreativeTabs.tabTools);
		amethystAxe = new ItemMoresAxe(16502, EnumBaseMoresToolMat).setIconCoord(0, 2).setUnlocalizedName("amethystAxe").setCreativeTab(CreativeTabs.tabTools);
		peridotAxe = new ItemMoresAxe(16506, EnumBaseMoresToolMat).setIconCoord(1, 2).setUnlocalizedName("peridotAxe").setCreativeTab(CreativeTabs.tabTools);
		garnetAxe = new ItemMoresAxe(16510, EnumBaseMoresToolMat).setIconCoord(2, 2).setUnlocalizedName("garnetAxe").setCreativeTab(CreativeTabs.tabTools);
		amethystHoe = new ItemMoresHoe(16503, EnumBaseMoresToolMat).setIconCoord(0, 3).setUnlocalizedName("amethystHoe").setCreativeTab(CreativeTabs.tabTools);
		peridotHoe = new ItemMoresHoe(16507, EnumBaseMoresToolMat).setIconCoord(1, 3).setUnlocalizedName("peridotHoe").setCreativeTab(CreativeTabs.tabTools);
		garnetHoe = new ItemMoresHoe(16511, EnumBaseMoresToolMat).setIconCoord(2, 3).setUnlocalizedName("garnetHoe").setCreativeTab(CreativeTabs.tabTools);
		alloySword = new ItemMoresSword(16530, EnumAlloyToolMat).setIconCoord(3, 6).setUnlocalizedName("alloySword").setCreativeTab(CreativeTabs.tabCombat);
		alloyShovel = new ItemMoresSpade(16531, EnumAlloyToolMat).setIconCoord(3, 5).setUnlocalizedName("alloyShovel").setCreativeTab(CreativeTabs.tabTools);
		alloyPickaxe = new ItemMoresPickaxe(16532, EnumAlloyToolMat).setIconCoord(3, 4).setUnlocalizedName("alloyPickaxe").setCreativeTab(CreativeTabs.tabTools);
		alloyAxe = new ItemMoresAxe(16533, EnumAlloyToolMat).setIconCoord(3, 2).setUnlocalizedName("alloyAxe").setCreativeTab(CreativeTabs.tabTools);
		alloyHoe = new ItemMoresHoe(16534, EnumAlloyToolMat).setIconCoord(3, 3).setUnlocalizedName("alloyHoe").setCreativeTab(CreativeTabs.tabTools);
		osmiumSword = new ItemMoresSword(17000, EnumOsmiumToolMat).setIconCoord(0, 8).setUnlocalizedName("osmiumSword").setCreativeTab(CreativeTabs.tabCombat);
		osmiumShovel = new ItemMoresSpade(17001, EnumOsmiumToolMat).setIconCoord(0, 9).setUnlocalizedName("osmiumShovel").setCreativeTab(CreativeTabs.tabTools);
		osmiumPickaxe = new ItemMoresPickaxe(17002, EnumOsmiumToolMat).setIconCoord(0, 10).setUnlocalizedName("osmiumPickaxe").setCreativeTab(CreativeTabs.tabTools);
		osmiumAxe = new ItemMoresAxe(17003, EnumOsmiumToolMat).setIconCoord(0, 11).setUnlocalizedName("osmiumAxe").setCreativeTab(CreativeTabs.tabTools);
		osmiumHoe = new ItemMoresHoe(17004, EnumOsmiumToolMat).setIconCoord(0, 12).setUnlocalizedName("osmiumHoe").setCreativeTab(CreativeTabs.tabTools);
		
		//Armor Definitions
		amethystHelmet = new ItemMoresArmor(16516, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 0).setIconCoord(4, 0).setUnlocalizedName("amethystHelmet").setCreativeTab(CreativeTabs.tabCombat);
		amethystPlate = new ItemMoresArmor(16517, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 1).setIconCoord(4, 1).setUnlocalizedName("amethystPlate").setCreativeTab(CreativeTabs.tabCombat);
		amethystLeggings = new ItemMoresArmor(16518, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 2).setIconCoord(4, 2).setUnlocalizedName("amethystLeggings").setCreativeTab(CreativeTabs.tabCombat);
		amethystBoots = new ItemMoresArmor(16519, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 3).setIconCoord(4, 3).setUnlocalizedName("amethystBoots").setCreativeTab(CreativeTabs.tabCombat);
		peridotHelmet = new ItemMoresArmor(16521, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 0).setIconCoord(5, 0).setUnlocalizedName("peridotHelmet").setCreativeTab(CreativeTabs.tabCombat);
		peridotPlate = new ItemMoresArmor(16522, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 1).setIconCoord(5, 1).setUnlocalizedName("peridotPlate").setCreativeTab(CreativeTabs.tabCombat);
		peridotLeggings = new ItemMoresArmor(16523, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 2).setIconCoord(5, 2).setUnlocalizedName("peridotLeggings").setCreativeTab(CreativeTabs.tabCombat);
		peridotBoots = new ItemMoresArmor(16524, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 3).setIconCoord(5, 3).setUnlocalizedName("peridotBoots").setCreativeTab(CreativeTabs.tabCombat);
		garnetHelmet = new ItemMoresArmor(16526, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 0).setIconCoord(6, 0).setUnlocalizedName("garnetHelmet").setCreativeTab(CreativeTabs.tabCombat);
		garnetPlate = new ItemMoresArmor(16527, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 1).setIconCoord(6, 1).setUnlocalizedName("garnetPlate").setCreativeTab(CreativeTabs.tabCombat);
		garnetLeggings = new ItemMoresArmor(16528, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 2).setIconCoord(6, 2).setUnlocalizedName("garnetLeggings").setCreativeTab(CreativeTabs.tabCombat);
		garnetBoots = new ItemMoresArmor(16529, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 3).setIconCoord(6, 3).setUnlocalizedName("garnetBoots").setCreativeTab(CreativeTabs.tabCombat);
		alloyHelmet = new ItemMoresArmor(16535, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 0).setIconCoord(7, 0).setUnlocalizedName("alloyHelmet").setCreativeTab(CreativeTabs.tabCombat);
		alloyPlate = new ItemMoresArmor(16536, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 1).setIconCoord(7, 1).setUnlocalizedName("alloyPlate").setCreativeTab(CreativeTabs.tabCombat);
		alloyLeggings = new ItemMoresArmor(16537, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 2).setIconCoord(7, 2).setUnlocalizedName("alloyLeggings").setCreativeTab(CreativeTabs.tabCombat);
		alloyBoots = new ItemMoresArmor(16538, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 3).setIconCoord(7, 3).setUnlocalizedName("alloyBoots").setCreativeTab(CreativeTabs.tabCombat);
		osmiumHelmet = new ItemMoresArmor(17005, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 0).setIconCoord(8, 0).setUnlocalizedName("osmiumHelmet").setCreativeTab(CreativeTabs.tabCombat);
		osmiumPlate = new ItemMoresArmor(17006, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 1).setIconCoord(8, 1).setUnlocalizedName("osmiumPlate").setCreativeTab(CreativeTabs.tabCombat);
		osmiumLeggings = new ItemMoresArmor(17007, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 2).setIconCoord(8, 2).setUnlocalizedName("osmiumLeggings").setCreativeTab(CreativeTabs.tabCombat);
		osmiumBoots = new ItemMoresArmor(17008, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 3).setIconCoord(8, 3).setUnlocalizedName("osmiumBoots").setCreativeTab(CreativeTabs.tabCombat);
		
		//Powder Definitions
		amethystPowder = new ItemPowder(17016, 0).setIconCoord(13, 0).setUnlocalizedName("amethystPowder").setCreativeTab(CreativeTabs.tabMaterials);
		peridotPowder = new ItemPowder(17017, 1).setIconCoord(14, 0).setUnlocalizedName("peridotPowder").setCreativeTab(CreativeTabs.tabMaterials);
		garnetPowder = new ItemPowder(17018, 2).setIconCoord(15, 0).setUnlocalizedName("garnetPowder").setCreativeTab(CreativeTabs.tabMaterials);
		
		//Food Definitions
		amethystRockCandy = new ItemRockCandy(17100, 3, -0.2F, false, 0).setUnlocalizedName("amethystRockCandy").setIconCoord(13, 15);
		peridotRockCandy = new ItemRockCandy(17101, 3, -0.2F, false, 1).setUnlocalizedName("peridotRockCandy").setIconCoord(14, 15);
		garnetRockCandy = new ItemRockCandy(17102, 3, -0.2F, false, 2).setUnlocalizedName("garnetRockCandy").setIconCoord(15, 15);
		rawGemMeat = new ItemMoresMeat(17110, 4, 0.8F, true, 1).setUnlocalizedName("rawGemMeat").setIconCoord(14, 14);
		cookedGemMeat = new ItemMoresMeat(17111, 6, 1.1F, true).setUnlocalizedName("cookedGemMeat").setIconCoord(15, 14);
		*/
		
		desertGem = (new BiomeGenBeachGem(GDTID)).setBiomeName("Gem Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
	    extremeHillsGem = (new BiomeGenHillsGem(GHLID)).setBiomeName("Extreme Gem Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.2F, 0.3F);
		
		this.moddedBlocks();
		this.moddedMisc();
		this.moddedLocalizations();
		this.moddedRecipes();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}
	
	//Gem and Ingot Definitions
	public static Item amethyst = new ItemGem(16384, 1).setIconIndex(0).setUnlocalizedName("amethyst").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item peridot = new ItemGem(16385, 2).setIconIndex(1).setUnlocalizedName("peridot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item garnet = new ItemGem(16386, 3).setIconIndex(2).setUnlocalizedName("garnet").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item amethystIngot = new ItemGem(16387).setIconIndex(16).setUnlocalizedName("amethystIngot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item peridotIngot = new ItemGem(16388).setIconIndex(17).setUnlocalizedName("peridotIngot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item garnetIngot = new ItemGem(16389).setIconIndex(18).setUnlocalizedName("garnetIngot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item gemAlloyIngot = new ItemGem(16390).setIconIndex(3).setUnlocalizedName("gemAlloyIngot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item berylliteIngot = new ItemGem(16391).setIconIndex(19).setUnlocalizedName("berylliteIngot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item lapisIngot = new ItemGem(16544).setIconIndex(116).setUnlocalizedName("lapisIngot").setCreativeTab(CreativeTabs.tabMaterials);
	
	//Metal Definitions
	@Deprecated
	public static Item osmiumNugget = new ItemGem(16539).setIconIndex(112).setUnlocalizedName("osmiumNugget").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item osmiumStone = new ItemOsmiumStone(16540).setIconIndex(113).setUnlocalizedName("osmiumStone").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item osmiumUntemperedIngot = new ItemGem(16541).setIconIndex(114).setUnlocalizedName("osmiumUntemperedIngot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item osmiumIngot = new ItemGem(16542).setIconIndex(115).setUnlocalizedName("osmiumIngot").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item platinumIngot = new ItemGem(16543).setIconIndex(112).setUnlocalizedName("platinumIngot").setCreativeTab(CreativeTabs.tabMaterials);
	
	//Tool Definitions
	public static Item amethystSword = new ItemMoresSword(16515, EnumBaseMoresToolMat).setIconCoord(0, 6).setUnlocalizedName("amethystSword").setCreativeTab(CreativeTabs.tabCombat);
	public static Item peridotSword = new ItemMoresSword(16520, EnumBaseMoresToolMat).setIconCoord(1, 6).setUnlocalizedName("peridotSword").setCreativeTab(CreativeTabs.tabCombat);
	public static Item garnetSword = new ItemMoresSword(16525, EnumBaseMoresToolMat).setIconCoord(2, 6).setUnlocalizedName("garnetSword").setCreativeTab(CreativeTabs.tabCombat);
	public static Item amethystShovel = new ItemMoresSpade(16500, EnumBaseMoresToolMat).setIconCoord(0, 5).setUnlocalizedName("amethystShovel").setCreativeTab(CreativeTabs.tabTools);
	public static Item peridotShovel = new ItemMoresSpade(16504, EnumBaseMoresToolMat).setIconCoord(1, 5).setUnlocalizedName("peridotShovel").setCreativeTab(CreativeTabs.tabTools);
	public static Item garnetShovel = new ItemMoresSpade(16508, EnumBaseMoresToolMat).setIconCoord(2, 5).setUnlocalizedName("garnetShovel").setCreativeTab(CreativeTabs.tabTools);
	public static Item amethystPickaxe = new ItemMoresPickaxe(16501, EnumBaseMoresToolMat).setIconCoord(0, 4).setUnlocalizedName("amethystPickaxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item peridotPickaxe = new ItemMoresPickaxe(16505, EnumBaseMoresToolMat).setIconCoord(1, 4).setUnlocalizedName("peridotPickaxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item garnetPickaxe = new ItemMoresPickaxe(16509, EnumBaseMoresToolMat).setIconCoord(2, 4).setUnlocalizedName("garnetPickaxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item amethystAxe = new ItemMoresAxe(16502, EnumBaseMoresToolMat).setIconCoord(0, 2).setUnlocalizedName("amethystAxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item peridotAxe = new ItemMoresAxe(16506, EnumBaseMoresToolMat).setIconCoord(1, 2).setUnlocalizedName("peridotAxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item garnetAxe = new ItemMoresAxe(16510, EnumBaseMoresToolMat).setIconCoord(2, 2).setUnlocalizedName("garnetAxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item amethystHoe = new ItemMoresHoe(16503, EnumBaseMoresToolMat).setIconCoord(0, 3).setUnlocalizedName("amethystHoe").setCreativeTab(CreativeTabs.tabTools);
	public static Item peridotHoe = new ItemMoresHoe(16507, EnumBaseMoresToolMat).setIconCoord(1, 3).setUnlocalizedName("peridotHoe").setCreativeTab(CreativeTabs.tabTools);
	public static Item garnetHoe = new ItemMoresHoe(16511, EnumBaseMoresToolMat).setIconCoord(2, 3).setUnlocalizedName("garnetHoe").setCreativeTab(CreativeTabs.tabTools);
	public static Item alloySword = new ItemMoresSword(16530, EnumAlloyToolMat).setIconCoord(3, 6).setUnlocalizedName("alloySword").setCreativeTab(CreativeTabs.tabCombat);
	public static Item alloyShovel = new ItemMoresSpade(16531, EnumAlloyToolMat).setIconCoord(3, 5).setUnlocalizedName("alloyShovel").setCreativeTab(CreativeTabs.tabTools);
	public static Item alloyPickaxe = new ItemMoresPickaxe(16532, EnumAlloyToolMat).setIconCoord(3, 4).setUnlocalizedName("alloyPickaxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item alloyAxe = new ItemMoresAxe(16533, EnumAlloyToolMat).setIconCoord(3, 2).setUnlocalizedName("alloyAxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item alloyHoe = new ItemMoresHoe(16534, EnumAlloyToolMat).setIconCoord(3, 3).setUnlocalizedName("alloyHoe").setCreativeTab(CreativeTabs.tabTools);
	public static Item osmiumSword = new ItemMoresSword(17000, EnumOsmiumToolMat).setIconCoord(0, 8).setUnlocalizedName("osmiumSword").setCreativeTab(CreativeTabs.tabCombat);
	public static Item osmiumShovel = new ItemMoresSpade(17001, EnumOsmiumToolMat).setIconCoord(0, 9).setUnlocalizedName("osmiumShovel").setCreativeTab(CreativeTabs.tabTools);
	public static Item osmiumPickaxe = new ItemMoresPickaxe(17002, EnumOsmiumToolMat).setIconCoord(0, 10).setUnlocalizedName("osmiumPickaxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item osmiumAxe = new ItemMoresAxe(17003, EnumOsmiumToolMat).setIconCoord(0, 11).setUnlocalizedName("osmiumAxe").setCreativeTab(CreativeTabs.tabTools);
	public static Item osmiumHoe = new ItemMoresHoe(17004, EnumOsmiumToolMat).setIconCoord(0, 12).setUnlocalizedName("osmiumHoe").setCreativeTab(CreativeTabs.tabTools);
	public static Item platinumLapisSword = new ItemPlatinumSword(17023, EnumPlatinumToolMat).setIconCoord(1, 8).setUnlocalizedName("platinumLapisSword").setCreativeTab(CreativeTabs.tabCombat);
	
	//Armor Definitions
	public static Item amethystHelmet = new ItemMoresArmor(16516, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 0).setIconCoord(4, 0).setUnlocalizedName("amethystHelmet").setCreativeTab(CreativeTabs.tabCombat);
	public static Item amethystPlate = new ItemMoresArmor(16517, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 1).setIconCoord(4, 1).setUnlocalizedName("amethystPlate").setCreativeTab(CreativeTabs.tabCombat);
	public static Item amethystLeggings = new ItemMoresArmor(16518, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 2).setIconCoord(4, 2).setUnlocalizedName("amethystLeggings").setCreativeTab(CreativeTabs.tabCombat);
	public static Item amethystBoots = new ItemMoresArmor(16519, EnumBaseMoresArmorMat, ModLoader.addArmor("amethyst"), 3).setIconCoord(4, 3).setUnlocalizedName("amethystBoots").setCreativeTab(CreativeTabs.tabCombat);
	public static Item peridotHelmet = new ItemMoresArmor(16521, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 0).setIconCoord(5, 0).setUnlocalizedName("peridotHelmet").setCreativeTab(CreativeTabs.tabCombat);
	public static Item peridotPlate = new ItemMoresArmor(16522, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 1).setIconCoord(5, 1).setUnlocalizedName("peridotPlate").setCreativeTab(CreativeTabs.tabCombat);
	public static Item peridotLeggings = new ItemMoresArmor(16523, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 2).setIconCoord(5, 2).setUnlocalizedName("peridotLeggings").setCreativeTab(CreativeTabs.tabCombat);
	public static Item peridotBoots = new ItemMoresArmor(16524, EnumBaseMoresArmorMat, ModLoader.addArmor("peridot"), 3).setIconCoord(5, 3).setUnlocalizedName("peridotBoots").setCreativeTab(CreativeTabs.tabCombat);
	public static Item garnetHelmet = new ItemMoresArmor(16526, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 0).setIconCoord(6, 0).setUnlocalizedName("garnetHelmet").setCreativeTab(CreativeTabs.tabCombat);
	public static Item garnetPlate = new ItemMoresArmor(16527, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 1).setIconCoord(6, 1).setUnlocalizedName("garnetPlate").setCreativeTab(CreativeTabs.tabCombat);
	public static Item garnetLeggings = new ItemMoresArmor(16528, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 2).setIconCoord(6, 2).setUnlocalizedName("garnetLeggings").setCreativeTab(CreativeTabs.tabCombat);
	public static Item garnetBoots = new ItemMoresArmor(16529, EnumBaseMoresArmorMat, ModLoader.addArmor("garnet"), 3).setIconCoord(6, 3).setUnlocalizedName("garnetBoots").setCreativeTab(CreativeTabs.tabCombat);
	public static Item alloyHelmet = new ItemMoresArmor(16535, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 0).setIconCoord(7, 0).setUnlocalizedName("alloyHelmet").setCreativeTab(CreativeTabs.tabCombat);
	public static Item alloyPlate = new ItemMoresArmor(16536, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 1).setIconCoord(7, 1).setUnlocalizedName("alloyPlate").setCreativeTab(CreativeTabs.tabCombat);
	public static Item alloyLeggings = new ItemMoresArmor(16537, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 2).setIconCoord(7, 2).setUnlocalizedName("alloyLeggings").setCreativeTab(CreativeTabs.tabCombat);
	public static Item alloyBoots = new ItemMoresArmor(16538, EnumAlloyArmorMat, ModLoader.addArmor("alloy"), 3).setIconCoord(7, 3).setUnlocalizedName("alloyBoots").setCreativeTab(CreativeTabs.tabCombat);
	public static Item osmiumHelmet = new ItemMoresArmor(17005, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 0).setIconCoord(8, 0).setUnlocalizedName("osmiumHelmet").setCreativeTab(CreativeTabs.tabCombat);
	public static Item osmiumPlate = new ItemMoresArmor(17006, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 1).setIconCoord(8, 1).setUnlocalizedName("osmiumPlate").setCreativeTab(CreativeTabs.tabCombat);
	public static Item osmiumLeggings = new ItemMoresArmor(17007, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 2).setIconCoord(8, 2).setUnlocalizedName("osmiumLeggings").setCreativeTab(CreativeTabs.tabCombat);
	public static Item osmiumBoots = new ItemMoresArmor(17008, EnumAlloyArmorMat, ModLoader.addArmor("osmium"), 3).setIconCoord(8, 3).setUnlocalizedName("osmiumBoots").setCreativeTab(CreativeTabs.tabCombat);
	
	//Powder Definitions
	public static Item amethystPowder = new ItemPowder(17016, 0).setIconCoord(13, 0).setUnlocalizedName("amethystPowder").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item peridotPowder = new ItemPowder(17017, 1).setIconCoord(14, 0).setUnlocalizedName("peridotPowder").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item garnetPowder = new ItemPowder(17018, 2).setIconCoord(15, 0).setUnlocalizedName("garnetPowder").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item beryllaniumPowder = new ItemPowder(17019, 3).setIconCoord(13, 1).setUnlocalizedName("beryllaniumPowder").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item osmiumPowder = new ItemPowder(17020, 4).setIconCoord(14, 1).setUnlocalizedName("osmiumPowder").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item goldPowder = new ItemPowder(17021, 5).setIconCoord(13, 1).setUnlocalizedName("goldPowder").setCreativeTab(CreativeTabs.tabMaterials);
	public static Item diamondPowder = new ItemPowder(17022, 5).setIconCoord(13, 1).setUnlocalizedName("diamondPowder").setCreativeTab(CreativeTabs.tabMaterials);
	
	//Food Definitions
	public static Item amethystRockCandy = new ItemRockCandy(17100, 3, -0.2F, false, 0).setUnlocalizedName("amethystRockCandy").setIconCoord(13, 15);
	public static Item peridotRockCandy = new ItemRockCandy(17101, 3, -0.2F, false, 1).setUnlocalizedName("peridotRockCandy").setIconCoord(14, 15);
	public static Item garnetRockCandy = new ItemRockCandy(17102, 3, -0.2F, false, 2).setUnlocalizedName("garnetRockCandy").setIconCoord(15, 15);
	public static Item rawGemMeat = new ItemMoresMeat(17110, 4, 0.8F, true, 1).setUnlocalizedName("rawGemMeat").setIconCoord(14, 14);
	public static Item cookedGemMeat = new ItemMoresMeat(17111, 6, 1.1F, true).setUnlocalizedName("cookedGemMeat").setIconCoord(15, 14);
	
	public static Item osmiumOrePic = new ItemIcon(21095).setIconIndex(14);
	public static Item beryllaniumOrePic = new ItemIcon(21096).setIconIndex(19);
	public static Item platinumOrePic = new ItemIcon(21097).setIconIndex(20);
	
	//Biome Definitions
	//public static BiomeGenBase forestGem = (new BiomeGenForestGem(30)).setBiomeName("Gem Forest").setTemperatureRainfall(0.7F, 0.8F);
    //public static BiomeGenBase plainsGem = (new BiomeGenPlainsGem(31)).setBiomeName("Gem Plains").setTemperatureRainfall(0.8F, 0.4F);
    public static BiomeGenBase desertGem;
    public static BiomeGenBase extremeHillsGem;
    
    //Achievement Registrations
  	public static Achievement amethystAchieve = (new Achievement(2100, "amethystAchieve", 0, -1, mod_Amethyst.amethyst, AchievementList.acquireIron)).registerAchievement();
  	public static Achievement peridotAchieve = (new Achievement(2101, "peridotAchieve", 0, 0, mod_Amethyst.peridot, AchievementList.acquireIron)).registerAchievement();
  	public static Achievement garnetAchieve = (new Achievement(2102, "garnetAchieve", 0, 1, mod_Amethyst.garnet, AchievementList.acquireIron)).registerAchievement();
  	public static Achievement temperAmethystAch = (new Achievement(2103, "temperAmethystAch", 2, -2, mod_Amethyst.amethystIngot, amethystAchieve)).registerAchievement();
  	public static Achievement temperPeridotAch = (new Achievement(2104, "temperPeridotAch", 2, 0, mod_Amethyst.peridotIngot, peridotAchieve)).registerAchievement();
  	public static Achievement temperGarnetAch = (new Achievement(2105, "temperGarnetAch", 2, 2, mod_Amethyst.garnetIngot, garnetAchieve)).registerAchievement();
  	public static Achievement gemStackAch = (new Achievement(2106, "gemStackAch", 4, 0, mod_Amethyst.gemAlloyIngot, temperPeridotAch)).registerAchievement();
  	public static Achievement beryllaniumIngotAch = (new Achievement(2107, "beryllaniumIngotAch", 5, 2, mod_Amethyst.berylliteIngot, gemStackAch)).registerAchievement();
  	public static Achievement osmiumIngotAch = (new Achievement(2108, "osmiumIngotAch", 0, 3, mod_Amethyst.osmiumIngot, AchievementList.diamonds)).registerAchievement();
  	public static Achievement beryllaniumOreAch = (new Achievement(2109, "beryllaniumOreAch", 1, -3, mod_Amethyst.beryllaniumOrePic, AchievementList.diamonds)).registerAchievement();
  	
  	//Achievement Page Registration
  	public static AchievementPage moresAchPage = new AchievementPage("M'ores", amethystAchieve, peridotAchieve, garnetAchieve, temperAmethystAch, temperPeridotAch, temperGarnetAch, gemStackAch, beryllaniumIngotAch, osmiumIngotAch, beryllaniumOreAch);
	
    //Block Registrations
	public void moddedBlocks() {
		//Registration
		GameRegistry.registerBlock(amethystOre);
		GameRegistry.registerBlock(peridotOre);
		GameRegistry.registerBlock(garnetOre);
		GameRegistry.registerBlock(amethystBlock);
		GameRegistry.registerBlock(peridotBlock);
		GameRegistry.registerBlock(garnetBlock);
		GameRegistry.registerBlock(gemSand);
		GameRegistry.registerBlock(gemDirt);
		GameRegistry.registerBlock(gemGrass);
		GameRegistry.registerBlock(gemLog);
		GameRegistry.registerBlock(gemLeaves);
		GameRegistry.registerBlock(gemSapling);
		GameRegistry.registerBlock(osmiumOre);
		GameRegistry.registerBlock(gemTallGrass);
		GameRegistry.registerBlock(osmiumBlock);
		GameRegistry.registerBlock(beryllaniumBlock);
		GameRegistry.registerBlock(beryllaniumOre);
		GameRegistry.registerBlock(platinumOre);
		GameRegistry.registerBlock(glowstoneOre);
		
		//Resistance
		MinecraftForge.setBlockHarvestLevel(amethystOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(peridotOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(garnetOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(amethystBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(peridotBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(garnetBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(gemSand, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(gemDirt, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(gemGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(gemLog, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(gemLeaves, "sword", 0);
		MinecraftForge.setBlockHarvestLevel(gemSapling, "sword", 0);
		MinecraftForge.setBlockHarvestLevel(gemTallGrass, "sword", 0);
		MinecraftForge.setBlockHarvestLevel(osmiumOre, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(osmiumBlock, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(beryllaniumOre, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(beryllaniumBlock, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(platinumOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(glowstoneOre, "pickaxe", 2);
	}
	
	//Localization Registrations
	public void moddedLocalizations() {
		// English (US) - Blocks
		LanguageRegistry.instance().addStringLocalization("tile.oreAmethyst.name", "en_US", "Amethyst Ore");
		LanguageRegistry.instance().addStringLocalization("tile.orePeridot.name", "en_US", "Peridot Ore");
		LanguageRegistry.instance().addStringLocalization("tile.oreGarnet.name", "en_US", "Garnet Ore");
		LanguageRegistry.instance().addStringLocalization("tile.blockAmethyst.name", "en_US", "Amethyst Block");
		LanguageRegistry.instance().addStringLocalization("tile.blockPeridot.name", "en_US", "Peridot Block");
		LanguageRegistry.instance().addStringLocalization("tile.blockGarnet.name", "en_US", "Garnet Block");
		LanguageRegistry.instance().addStringLocalization("tile.sandGem.name", "en_US", "Gem Sand");
		LanguageRegistry.instance().addStringLocalization("tile.dirtGem.name", "en_US", "Gem Dirt");
		LanguageRegistry.instance().addStringLocalization("tile.grassGem.name", "en_US", "Gem Grass");
		LanguageRegistry.instance().addStringLocalization("tile.logGem.name", "en_US", "Berylwood");
		LanguageRegistry.instance().addStringLocalization("tile.leavesGem.name", "en_US", "Berylwood Leaves");
		LanguageRegistry.instance().addStringLocalization("tile.saplingGem.name", "en_US", "Berylwood Sapling");
		LanguageRegistry.instance().addStringLocalization("tile.tallGrassGem.name", "en_US", "Tall Gem Grass");
		LanguageRegistry.instance().addStringLocalization("tile.oreOsmium.name", "en_US", "Osmium Ore");
		LanguageRegistry.instance().addStringLocalization("tile.blockOsmium.name", "en_US", "Osmium Block");
		LanguageRegistry.instance().addStringLocalization("tile.blockBeryllanium.name", "en_US", "Beryllanium Block");
		LanguageRegistry.instance().addStringLocalization("tile.oreBeryllanium.name", "en_US", "Beryllanium Ore");
		LanguageRegistry.instance().addStringLocalization("tile.orePlatinum.name", "en_US", "Platinum Ore");
		
		// English (US) - Items
		LanguageRegistry.instance().addStringLocalization("item.amethyst.name", "en_US", "Amethyst");
		LanguageRegistry.instance().addStringLocalization("item.peridot.name", "en_US", "Peridot");
		LanguageRegistry.instance().addStringLocalization("item.garnet.name", "en_US", "Garnet");
		LanguageRegistry.instance().addStringLocalization("item.amethystIngot.name", "en_US", "Tempered Amethyst");
		LanguageRegistry.instance().addStringLocalization("item.peridotIngot.name", "en_US", "Tempered Peridot");
		LanguageRegistry.instance().addStringLocalization("item.garnetIngot.name", "en_US", "Tempered Garnet");
		LanguageRegistry.instance().addStringLocalization("item.gemAlloyIngot.name", "en_US", "Pile of Tempered Gems");
		LanguageRegistry.instance().addStringLocalization("item.berylliteIngot.name", "en_US", "Beryllanium Ingot");
		LanguageRegistry.instance().addStringLocalization("item.osmiumNugget.name", "en_US", "Osmium Nugget");
		LanguageRegistry.instance().addStringLocalization("item.osmiumUntemperedIngot.name", "en_US", "Untempered Osmium Ingot");
		LanguageRegistry.instance().addStringLocalization("item.osmiumStone.name", "en_US", "Osmium Grinding Stone");
		LanguageRegistry.instance().addStringLocalization("item.osmiumIngot.name", "en_US", "Tempered Osmium Ingot");
		LanguageRegistry.instance().addStringLocalization("item.amethystPowder.name", "en_US", "Amethyst Powder");
		LanguageRegistry.instance().addStringLocalization("item.peridotPowder.name", "en_US", "Peridot Powder");
		LanguageRegistry.instance().addStringLocalization("item.garnetPowder.name", "en_US", "Garnet Powder");
		LanguageRegistry.instance().addStringLocalization("item.amethystRockCandy.name", "en_US", "Amethyst Rock Candy");
		LanguageRegistry.instance().addStringLocalization("item.peridotRockCandy.name", "en_US", "Peridot Rock Candy");
		LanguageRegistry.instance().addStringLocalization("item.garnetRockCandy.name", "en_US", "Garnet Rock Candy");
		LanguageRegistry.instance().addStringLocalization("item.rawGemMeat.name", "en_US", "Raw Unnamed Meat");
		LanguageRegistry.instance().addStringLocalization("item.cookedGemMeat.name", "en_US", "Cooked Unnamed Meat");
		LanguageRegistry.instance().addStringLocalization("item.platinumIngot.name", "en_US", "Platinum Ingot");
		LanguageRegistry.instance().addStringLocalization("item.lapisIngot.name", "en_US", "Lapis Lazuli Ingot");
		
		// English (US) - Tools & Armor
		LanguageRegistry.instance().addStringLocalization("item.amethystSword.name", "en_US", "Amethyst Sword");
		LanguageRegistry.instance().addStringLocalization("item.peridotSword.name", "en_US", "Peridot Sword");
		LanguageRegistry.instance().addStringLocalization("item.garnetSword.name", "en_US", "Garnet Sword");
		LanguageRegistry.instance().addStringLocalization("item.amethystAxe.name", "en_US", "Amethyst Axe");
		LanguageRegistry.instance().addStringLocalization("item.peridotAxe.name", "en_US", "Peridot Axe");
		LanguageRegistry.instance().addStringLocalization("item.garnetAxe.name", "en_US", "Garnet Axe");
		LanguageRegistry.instance().addStringLocalization("item.amethystShovel.name", "en_US", "Amethyst Shovel");
		LanguageRegistry.instance().addStringLocalization("item.peridotShovel.name", "en_US", "Peridot Shovel");
		LanguageRegistry.instance().addStringLocalization("item.garnetShovel.name", "en_US", "Garnet Shovel");
		LanguageRegistry.instance().addStringLocalization("item.amethystPickaxe.name", "en_US", "Amethyst Pickaxe");
		LanguageRegistry.instance().addStringLocalization("item.peridotPickaxe.name", "en_US", "Peridot Pickaxe");
		LanguageRegistry.instance().addStringLocalization("item.garnetPickaxe.name", "en_US", "Garnet Pickaxe");
		LanguageRegistry.instance().addStringLocalization("item.amethystHoe.name", "en_US", "Amethyst Hoe");
		LanguageRegistry.instance().addStringLocalization("item.peridotHoe.name", "en_US", "Peridot Hoe");
		LanguageRegistry.instance().addStringLocalization("item.garnetHoe.name", "en_US", "Garnet Hoe");
		LanguageRegistry.instance().addStringLocalization("item.alloySword.name", "en_US", "Beryllanium Sword");
		LanguageRegistry.instance().addStringLocalization("item.alloyPickaxe.name", "en_US", "Beryllanium Pickaxe");
		LanguageRegistry.instance().addStringLocalization("item.alloyAxe.name", "en_US", "Beryllanium Axe");
		LanguageRegistry.instance().addStringLocalization("item.alloyShovel.name", "en_US", "Beryllanium Shovel");
		LanguageRegistry.instance().addStringLocalization("item.alloyHoe.name", "en_US", "Beryllanium Hoe");
		LanguageRegistry.instance().addStringLocalization("item.osmiumSword.name", "en_US", "Osmium Sword");
		LanguageRegistry.instance().addStringLocalization("item.osmiumPickaxe.name", "en_US", "Osmium Pickaxe");
		LanguageRegistry.instance().addStringLocalization("item.osmiumAxe.name", "en_US", "Osmium Axe");
		LanguageRegistry.instance().addStringLocalization("item.osmiumShovel.name", "en_US", "Osmium Shovel");
		LanguageRegistry.instance().addStringLocalization("item.osmiumHoe.name", "en_US", "Osmium Hoe");
		LanguageRegistry.instance().addStringLocalization("item.amethystHelmet.name", "en_US", "Amethyst Helmet");
		LanguageRegistry.instance().addStringLocalization("item.amethystPlate.name", "en_US", "Amethyst Chestplate");
		LanguageRegistry.instance().addStringLocalization("item.amethystLeggings.name", "en_US", "Amethyst Leggings");
		LanguageRegistry.instance().addStringLocalization("item.amethystBoots.name", "en_US", "Amethyst Boots");
		LanguageRegistry.instance().addStringLocalization("item.peridotHelmet.name", "en_US", "Peridot Helmet");
		LanguageRegistry.instance().addStringLocalization("item.peridotPlate.name", "en_US", "Peridot Chestplate");
		LanguageRegistry.instance().addStringLocalization("item.peridotLeggings.name", "en_US", "Peridot Leggings");
		LanguageRegistry.instance().addStringLocalization("item.peridotBoots.name", "en_US", "Peridot Boots");
		LanguageRegistry.instance().addStringLocalization("item.garnetHelmet.name", "en_US", "Garnet Helmet");
		LanguageRegistry.instance().addStringLocalization("item.garnetPlate.name", "en_US", "Garnet Chestplate");
		LanguageRegistry.instance().addStringLocalization("item.garnetLeggings.name", "en_US", "Garnet Leggings");
		LanguageRegistry.instance().addStringLocalization("item.garnetBoots.name", "en_US", "Garnet Boots");
		LanguageRegistry.instance().addStringLocalization("item.alloyHelmet.name", "en_US", "Beryllanium Helmet");
		LanguageRegistry.instance().addStringLocalization("item.alloyPlate.name", "en_US", "Beryllanium Chestplate");
		LanguageRegistry.instance().addStringLocalization("item.alloyLeggings.name", "en_US", "Beryllanium Leggings");
		LanguageRegistry.instance().addStringLocalization("item.alloyBoots.name", "en_US", "Beryllanium Boots");
		LanguageRegistry.instance().addStringLocalization("item.osmiumHelmet.name", "en_US", "Osmium Helmet");
		LanguageRegistry.instance().addStringLocalization("item.osmiumPlate.name", "en_US", "Osmium Chestplate");
		LanguageRegistry.instance().addStringLocalization("item.osmiumLeggings.name", "en_US", "Osmium Leggings");
		LanguageRegistry.instance().addStringLocalization("item.osmiumBoots.name", "en_US", "Osmium Boots");
		LanguageRegistry.instance().addStringLocalization("item.platinumLapisSword.name", "en_US", "Platinum-Lapis Broadsword");
		
		// English (US) - Achievements
		LanguageRegistry.instance().addStringLocalization("achievement.amethystAchieve", "en_US", "Amethyst!");
		LanguageRegistry.instance().addStringLocalization("achievement.peridotAchieve", "en_US", "Peridot!");
		LanguageRegistry.instance().addStringLocalization("achievement.garnetAchieve", "en_US", "Garnet!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperAmethystAch", "en_US", "Tempered Amethyst!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperPeridotAch", "en_US", "Tempered Peridot!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperGarnetAch", "en_US", "Tempered Garnet!");
		LanguageRegistry.instance().addStringLocalization("achievement.gemStackAch", "en_US", "Temperception!");
		LanguageRegistry.instance().addStringLocalization("achievement.amethystAchieve.desc", "en_US", "Obtain an Amethyst using an Iron Pickaxe");
		LanguageRegistry.instance().addStringLocalization("achievement.peridotAchieve.desc", "en_US", "Obtain a Peridot using an Iron Pickaxe");
		LanguageRegistry.instance().addStringLocalization("achievement.garnetAchieve.desc", "en_US", "Obtain a Garnet using an Iron Pickaxe");
		LanguageRegistry.instance().addStringLocalization("achievement.temperAmethystAch.desc", "en_US", "Smelt an Amethyst until you obtain a Tempered Amethyst");
		LanguageRegistry.instance().addStringLocalization("achievement.temperPeridotAch.desc", "en_US", "Smelt a Peridot until you obtain a Tempered Peridot");
		LanguageRegistry.instance().addStringLocalization("achievement.temperGarnetAch.desc", "en_US", "Smelt a Garnet until you obtain a Tempered Garnet");
		LanguageRegistry.instance().addStringLocalization("achievement.gemStackAch.desc", "en_US", "Craft a Tempered Gem Stack using 3 of each Tempered Gem");
		
		// Svenska (SE) - Blocks
		LanguageRegistry.instance().addStringLocalization("tile.oreAmethyst.name", "sv_SE", "Ametistmalm");
		LanguageRegistry.instance().addStringLocalization("tile.orePeridot.name", "sv_SE", "Olivinmalm");
		LanguageRegistry.instance().addStringLocalization("tile.oreGarnet.name", "sv_SE", "Garnetmalm");
		LanguageRegistry.instance().addStringLocalization("tile.blockAmethyst.name", "sv_SE", "Ametistblock");
		LanguageRegistry.instance().addStringLocalization("tile.blockPeridot.name", "sv_SE", "Olivinblock");
		LanguageRegistry.instance().addStringLocalization("tile.blockGarnet.name", "sv_SE", "Garnetblock");
		LanguageRegistry.instance().addStringLocalization("tile.sandGem.name", "sv_SE", "Pärlasand");
		LanguageRegistry.instance().addStringLocalization("tile.dirtGem.name", "sv_SE", "Pärlajord");
		LanguageRegistry.instance().addStringLocalization("tile.grassGem.name", "sv_SE", "Pärlagräsblock");
		LanguageRegistry.instance().addStringLocalization("tile.logGem.name", "sv_SE", "Berylträ");
		LanguageRegistry.instance().addStringLocalization("tile.leavesGem.name", "sv_SE", "Berylträlöv");
		LanguageRegistry.instance().addStringLocalization("tile.saplingGem.name", "sv_SE", "Berylträplanta");
		LanguageRegistry.instance().addStringLocalization("tile.tallGrassGem.name", "sv_SE", "Pärlagräs");
		LanguageRegistry.instance().addStringLocalization("tile.oreOsmium.name", "sv_SE", "Osmiumalm");
		LanguageRegistry.instance().addStringLocalization("tile.blockOsmium.name", "sv_SE", "Osmiumblock");
		LanguageRegistry.instance().addStringLocalization("tile.blockBeryllanium.name", "sv_SE", "Beryllaniumblock");
		LanguageRegistry.instance().addStringLocalization("tile.oreBeryllanium.name", "sv_SE", "Beryllaniumalm");
		
		// Svenska (SE) - Items
		LanguageRegistry.instance().addStringLocalization("item.amethyst.name", "sv_SE", "Ametist");
		LanguageRegistry.instance().addStringLocalization("item.peridot.name", "sv_SE", "Olivin");
		LanguageRegistry.instance().addStringLocalization("item.garnet.name", "sv_SE", "Garnet");
		LanguageRegistry.instance().addStringLocalization("item.amethystIngot.name", "sv_SE", "Härdat ametist");
		LanguageRegistry.instance().addStringLocalization("item.peridotIngot.name", "sv_SE", "Härdat olivin");
		LanguageRegistry.instance().addStringLocalization("item.garnetIngot.name", "sv_SE", "Härdat garnet");
		LanguageRegistry.instance().addStringLocalization("item.gemAlloyIngot.name", "sv_SE", "Hög av härdat pärlar");
		LanguageRegistry.instance().addStringLocalization("item.berylliteIngot.name", "sv_SE", "Beryllaniumtacka");
		LanguageRegistry.instance().addStringLocalization("item.osmiumNugget.name", "sv_SE", "Osmiumklimp");
		LanguageRegistry.instance().addStringLocalization("item.osmiumUntemperedIngot.name", "sv_SE", "Ohärdat osmiumtacka");
		LanguageRegistry.instance().addStringLocalization("item.osmiumStone.name", "sv_SE", "Osmiumslipsten");
		LanguageRegistry.instance().addStringLocalization("item.osmiumIngot.name", "sv_SE", "Härdat osmiumtacka");
		LanguageRegistry.instance().addStringLocalization("item.amethystPowder.name", "sv_SE", "Ametistspulver");
		LanguageRegistry.instance().addStringLocalization("item.peridotPowder.name", "sv_SE", "Olivinspulver");
		LanguageRegistry.instance().addStringLocalization("item.garnetPowder.name", "sv_SE", "Garnetspulver");
		LanguageRegistry.instance().addStringLocalization("item.amethystRockCandy.name", "sv_SE", "Ametist stengodis");
		LanguageRegistry.instance().addStringLocalization("item.peridotRockCandy.name", "sv_SE", "Olivin stengodis");
		LanguageRegistry.instance().addStringLocalization("item.garnetRockCandy.name", "sv_SE", "Garnet stengodis");
		
		// Svenska (SE) - Tools & Armor
		LanguageRegistry.instance().addStringLocalization("item.amethystSword.name", "sv_SE", "Ametistsvärd");
		LanguageRegistry.instance().addStringLocalization("item.peridotSword.name", "sv_SE", "Olivinsvärd");
		LanguageRegistry.instance().addStringLocalization("item.garnetSword.name", "sv_SE", "Garnetsvärd");
		LanguageRegistry.instance().addStringLocalization("item.amethystAxe.name", "sv_SE", "Ametistyxa");
		LanguageRegistry.instance().addStringLocalization("item.peridotAxe.name", "sv_SE", "Olivinyxa");
		LanguageRegistry.instance().addStringLocalization("item.garnetAxe.name", "sv_SE", "Garnetyxa");
		LanguageRegistry.instance().addStringLocalization("item.amethystShovel.name", "sv_SE", "Ametistspade");
		LanguageRegistry.instance().addStringLocalization("item.peridotShovel.name", "sv_SE", "Olivinspade");
		LanguageRegistry.instance().addStringLocalization("item.garnetShovel.name", "sv_SE", "Garnetspade");
		LanguageRegistry.instance().addStringLocalization("item.amethystPickaxe.name", "sv_SE", "Ametisthacka");
		LanguageRegistry.instance().addStringLocalization("item.peridotPickaxe.name", "sv_SE", "Olivinhacka");
		LanguageRegistry.instance().addStringLocalization("item.garnetPickaxe.name", "sv_SE", "Garnethacka");
		LanguageRegistry.instance().addStringLocalization("item.amethystHoe.name", "sv_SE", "Ametistflohacka");
		LanguageRegistry.instance().addStringLocalization("item.peridotHoe.name", "sv_SE", "Olivinflohacka");
		LanguageRegistry.instance().addStringLocalization("item.garnetHoe.name", "sv_SE", "Garnetflohacka");
		LanguageRegistry.instance().addStringLocalization("item.alloySword.name", "sv_SE", "Beryllaniumsvärd");
		LanguageRegistry.instance().addStringLocalization("item.alloyPickaxe.name", "sv_SE", "Beryllaniumhacka");
		LanguageRegistry.instance().addStringLocalization("item.alloyAxe.name", "sv_SE", "Beryllaniumyxa");
		LanguageRegistry.instance().addStringLocalization("item.alloyShovel.name", "sv_SE", "Beryllaniumspade");
		LanguageRegistry.instance().addStringLocalization("item.alloyHoe.name", "sv_SE", "Beryllaniumflohacka");
		LanguageRegistry.instance().addStringLocalization("item.osmiumSword.name", "sv_SE", "Osmiumsvärd");
		LanguageRegistry.instance().addStringLocalization("item.osmiumPickaxe.name", "sv_SE", "Osmiumhacka");
		LanguageRegistry.instance().addStringLocalization("item.osmiumAxe.name", "sv_SE", "Osmiumyxa");
		LanguageRegistry.instance().addStringLocalization("item.osmiumShovel.name", "sv_SE", "Osmiumspade");
		LanguageRegistry.instance().addStringLocalization("item.osmiumHoe.name", "sv_SE", "Osmiumflohacka");
		LanguageRegistry.instance().addStringLocalization("item.amethystHelmet.name", "sv_SE", "Ametisthjälm");
		LanguageRegistry.instance().addStringLocalization("item.amethystPlate.name", "sv_SE", "Ametistharnesk");
		LanguageRegistry.instance().addStringLocalization("item.amethystLeggings.name", "sv_SE", "Ametistbyxor");
		LanguageRegistry.instance().addStringLocalization("item.amethystBoots.name", "sv_SE", "Ametiststövlar");
		LanguageRegistry.instance().addStringLocalization("item.peridotHelmet.name", "sv_SE", "Olivinhjälm");
		LanguageRegistry.instance().addStringLocalization("item.peridotPlate.name", "sv_SE", "Olivinharnesk");
		LanguageRegistry.instance().addStringLocalization("item.peridotLeggings.name", "sv_SE", "Olivinbyxor");
		LanguageRegistry.instance().addStringLocalization("item.peridotBoots.name", "sv_SE", "Olivinstövlar");
		LanguageRegistry.instance().addStringLocalization("item.garnetHelmet.name", "sv_SE", "Garnethjälm");
		LanguageRegistry.instance().addStringLocalization("item.garnetPlate.name", "sv_SE", "Garnetharnesk");
		LanguageRegistry.instance().addStringLocalization("item.garnetLeggings.name", "sv_SE", "Garnetbyxor");
		LanguageRegistry.instance().addStringLocalization("item.garnetBoots.name", "sv_SE", "Garnetstövlar");
		LanguageRegistry.instance().addStringLocalization("item.alloyHelmet.name", "sv_SE", "Beryllaniumhjälm");
		LanguageRegistry.instance().addStringLocalization("item.alloyPlate.name", "sv_SE", "Beryllaniumharnesk");
		LanguageRegistry.instance().addStringLocalization("item.alloyLeggings.name", "sv_SE", "Beryllaniumbyxor");
		LanguageRegistry.instance().addStringLocalization("item.alloyBoots.name", "sv_SE", "Beryllaniumstövlar");
		LanguageRegistry.instance().addStringLocalization("item.osmiumHelmet.name", "sv_SE", "Osmiumhjälm");
		LanguageRegistry.instance().addStringLocalization("item.osmiumPlate.name", "sv_SE", "Osmiumharnesk");
		LanguageRegistry.instance().addStringLocalization("item.osmiumLeggings.name", "sv_SE", "Osmiumbyxor");
		LanguageRegistry.instance().addStringLocalization("item.osmiumBoots.name", "sv_SE", "Osmiumstövlar");
		
		// Svenska (SE) - Achievements
		LanguageRegistry.instance().addStringLocalization("achievement.amethystAchieve", "sv_SE", "Ametist!");
		LanguageRegistry.instance().addStringLocalization("achievement.peridotAchieve", "sv_SE", "Olivin!");
		LanguageRegistry.instance().addStringLocalization("achievement.garnetAchieve", "sv_SE", "Garnet!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperAmethystAch", "sv_SE", "Härdat ametist!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperPeridotAch", "sv_SE", "Härdat olivin!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperGarnetAch", "sv_SE", "Härdat garnet!");
		LanguageRegistry.instance().addStringLocalization("achievement.amethystAchieve.desc", "sv_SE", "Skaffa en Ametist med hjälp av en Järnhacka");
		LanguageRegistry.instance().addStringLocalization("achievement.peridotAchieve.desc", "sv_SE", "Skaffa en Olivin med hjälp av en Järnhacka");
		LanguageRegistry.instance().addStringLocalization("achievement.garnetAchieve.desc", "sv_SE", "Skaffa en Garnet med hjälp av en Järnhacka");
		LanguageRegistry.instance().addStringLocalization("achievement.temperAmethystAch.desc", "sv_SE", "Smelt en Ametist tills du får en Härdat ametist");
		LanguageRegistry.instance().addStringLocalization("achievement.temperPeridotAch.desc", "sv_SE", "Smelt en Olivin tills du får en Härdat olivin");
		LanguageRegistry.instance().addStringLocalization("achievement.temperGarnetAch.desc", "sv_SE", "Smelt en Garnet tills du får en Härdat garnet");
		
		// Rålmska (XR) - Blocks
		LanguageRegistry.instance().addStringLocalization("tile.oreAmethyst.name", "rl_XR", "Ametists Örë");
		LanguageRegistry.instance().addStringLocalization("tile.orePeridot.name", "rl_XR", "Peridots Örë");
		LanguageRegistry.instance().addStringLocalization("tile.oreGarnet.name", "rl_XR", "Garnets Örë");
		LanguageRegistry.instance().addStringLocalization("tile.blockAmethyst.name", "rl_XR", "Ametists Blok");
		LanguageRegistry.instance().addStringLocalization("tile.blockPeridot.name", "rl_XR", "Peridots Blok");
		LanguageRegistry.instance().addStringLocalization("tile.blockGarnet.name", "rl_XR", "Garnets Blok");
		LanguageRegistry.instance().addStringLocalization("tile.sandGem.name", "rl_XR", "Pärls Sanda");
		LanguageRegistry.instance().addStringLocalization("tile.dirtGem.name", "rl_XR", "Pärls Jord");
		LanguageRegistry.instance().addStringLocalization("tile.grassGem.name", "rl_XR", "Pärls Gras Blok");
		LanguageRegistry.instance().addStringLocalization("tile.logGem.name", "rl_XR", "Bërilwöd");
		LanguageRegistry.instance().addStringLocalization("tile.leavesGem.name", "rl_XR", "Bërilwöds Lēëvam");
		LanguageRegistry.instance().addStringLocalization("tile.saplingGem.name", "rl_XR", "Bërilwöds Säpliŋa");
		LanguageRegistry.instance().addStringLocalization("tile.tallGrassGem.name", "rl_XR", "Gems Gras");
		LanguageRegistry.instance().addStringLocalization("tile.oreOsmium.name", "rl_XR", "Osmiums Örë");
		LanguageRegistry.instance().addStringLocalization("tile.blockOsmium.name", "rl_XR", "Osmiums Blok");
		LanguageRegistry.instance().addStringLocalization("tile.blockBeryllanium.name", "rl_XR", "Bërillaniums Blok");
		LanguageRegistry.instance().addStringLocalization("tile.oreBeryllanium.name", "rl_XR", "Bërillaniums Örë");
		
		// Rålmska (XR) - Items
		LanguageRegistry.instance().addStringLocalization("item.amethyst.name", "rl_XR", "Ametista");
		LanguageRegistry.instance().addStringLocalization("item.peridot.name", "rl_XR", "Peridota");
		LanguageRegistry.instance().addStringLocalization("item.garnet.name", "rl_XR", "Garneta");
		LanguageRegistry.instance().addStringLocalization("item.amethystIngot.name", "rl_XR", "Hardad Ametista");
		LanguageRegistry.instance().addStringLocalization("item.peridotIngot.name", "rl_XR", "Hardad Peridota");
		LanguageRegistry.instance().addStringLocalization("item.garnetIngot.name", "rl_XR", "Hardad Garneta");
		LanguageRegistry.instance().addStringLocalization("item.gemAlloyIngot.name", "rl_XR", "Stakka në Hardad Pärlam");
		LanguageRegistry.instance().addStringLocalization("item.berylliteIngot.name", "rl_XR", "Bërillaniums Iŋota");
		LanguageRegistry.instance().addStringLocalization("item.osmiumNugget.name", "rl_XR", "Osmiums Klimp");
		LanguageRegistry.instance().addStringLocalization("item.osmiumUntemperedIngot.name", "rl_XR", "Hardadi Osmiums Iŋota");
		LanguageRegistry.instance().addStringLocalization("item.osmiumStone.name", "rl_XR", "Osmiums Grindar Stena");
		LanguageRegistry.instance().addStringLocalization("item.osmiumIngot.name", "rl_XR", "Hardad Osmiums Iŋota");
		LanguageRegistry.instance().addStringLocalization("item.amethystPowder.name", "rl_XR", "Ametists Påder");
		LanguageRegistry.instance().addStringLocalization("item.peridotPowder.name", "rl_XR", "Peridots Påder");
		LanguageRegistry.instance().addStringLocalization("item.garnetPowder.name", "rl_XR", "Garnets Påder");
		LanguageRegistry.instance().addStringLocalization("item.amethystRockCandy.name", "rl_XR", "Ametists Stens Konfë");
		LanguageRegistry.instance().addStringLocalization("item.peridotRockCandy.name", "rl_XR", "Peridots Stens Konfë");
		LanguageRegistry.instance().addStringLocalization("item.garnetRockCandy.name", "rl_XR", "Garnets Stens Konfë");
		
		// Rålmska (XR) - Tools & Armor
		LanguageRegistry.instance().addStringLocalization("item.amethystSword.name", "rl_XR", "Ametists Svard");
		LanguageRegistry.instance().addStringLocalization("item.peridotSword.name", "rl_XR", "Peridots Svard");
		LanguageRegistry.instance().addStringLocalization("item.garnetSword.name", "rl_XR", "Garnets Svard");
		LanguageRegistry.instance().addStringLocalization("item.amethystAxe.name", "rl_XR", "Ametists Ax");
		LanguageRegistry.instance().addStringLocalization("item.peridotAxe.name", "rl_XR", "Peridots Ax");
		LanguageRegistry.instance().addStringLocalization("item.garnetAxe.name", "rl_XR", "Garnets Ax");
		LanguageRegistry.instance().addStringLocalization("item.amethystShovel.name", "rl_XR", "Ametists Spada");
		LanguageRegistry.instance().addStringLocalization("item.peridotShovel.name", "rl_XR", "Peridots Spada");
		LanguageRegistry.instance().addStringLocalization("item.garnetShovel.name", "rl_XR", "Garnets Spada");
		LanguageRegistry.instance().addStringLocalization("item.amethystPickaxe.name", "rl_XR", "Ametists Pika");
		LanguageRegistry.instance().addStringLocalization("item.peridotPickaxe.name", "rl_XR", "Peridots Pika");
		LanguageRegistry.instance().addStringLocalization("item.garnetPickaxe.name", "rl_XR", "Garnets Pika");
		LanguageRegistry.instance().addStringLocalization("item.amethystHoe.name", "rl_XR", "Ametists Hå");
		LanguageRegistry.instance().addStringLocalization("item.peridotHoe.name", "rl_XR", "Peridots Hå");
		LanguageRegistry.instance().addStringLocalization("item.garnetHoe.name", "rl_XR", "Garnets Hå");
		LanguageRegistry.instance().addStringLocalization("item.alloySword.name", "rl_XR", "Bërillaniums Svard");
		LanguageRegistry.instance().addStringLocalization("item.alloyPickaxe.name", "rl_XR", "Bërillaniums Pika");
		LanguageRegistry.instance().addStringLocalization("item.alloyAxe.name", "rl_XR", "Bërillaniums Ax");
		LanguageRegistry.instance().addStringLocalization("item.alloyShovel.name", "rl_XR", "Bërillaniums Spada");
		LanguageRegistry.instance().addStringLocalization("item.alloyHoe.name", "rl_XR", "Bërillaniums Hå");
		LanguageRegistry.instance().addStringLocalization("item.osmiumSword.name", "rl_XR", "Osmiums Svard");
		LanguageRegistry.instance().addStringLocalization("item.osmiumPickaxe.name", "rl_XR", "Osmiums Pika");
		LanguageRegistry.instance().addStringLocalization("item.osmiumAxe.name", "rl_XR", "Osmiums Ax");
		LanguageRegistry.instance().addStringLocalization("item.osmiumShovel.name", "rl_XR", "Osmiums Spada");
		LanguageRegistry.instance().addStringLocalization("item.osmiumHoe.name", "rl_XR", "Osmiums Hå");
		LanguageRegistry.instance().addStringLocalization("item.amethystHelmet.name", "rl_XR", "Ametists Hëlm");
		LanguageRegistry.instance().addStringLocalization("item.amethystPlate.name", "rl_XR", "Ametists Torsoplatt");
		LanguageRegistry.instance().addStringLocalization("item.amethystLeggings.name", "rl_XR", "Ametists Lëggiŋam");
		LanguageRegistry.instance().addStringLocalization("item.amethystBoots.name", "rl_XR", "Ametists Butam");
		LanguageRegistry.instance().addStringLocalization("item.peridotHelmet.name", "rl_XR", "Peridots Hëlm");
		LanguageRegistry.instance().addStringLocalization("item.peridotPlate.name", "rl_XR", "Peridots Torsoplatt");
		LanguageRegistry.instance().addStringLocalization("item.peridotLeggings.name", "rl_XR", "Peridots Lëggiŋam");
		LanguageRegistry.instance().addStringLocalization("item.peridotBoots.name", "rl_XR", "Peridots Butam");
		LanguageRegistry.instance().addStringLocalization("item.garnetHelmet.name", "rl_XR", "Garnets Hëlm");
		LanguageRegistry.instance().addStringLocalization("item.garnetPlate.name", "rl_XR", "Garnets Torsoplatt");
		LanguageRegistry.instance().addStringLocalization("item.garnetLeggings.name", "rl_XR", "Garnets Lëggiŋam");
		LanguageRegistry.instance().addStringLocalization("item.garnetBoots.name", "rl_XR", "Garnets Butam");
		LanguageRegistry.instance().addStringLocalization("item.alloyHelmet.name", "rl_XR", "Bërillaniums Hëlm");
		LanguageRegistry.instance().addStringLocalization("item.alloyPlate.name", "rl_XR", "Bërillaniums Torsoplatt");
		LanguageRegistry.instance().addStringLocalization("item.alloyLeggings.name", "rl_XR", "Bërillaniums Lëggiŋam");
		LanguageRegistry.instance().addStringLocalization("item.alloyBoots.name", "rl_XR", "Bërillaniums Butam");
		LanguageRegistry.instance().addStringLocalization("item.osmiumHelmet.name", "rl_XR", "Osmiums Hëlm");
		LanguageRegistry.instance().addStringLocalization("item.osmiumPlate.name", "rl_XR", "Osmiums Torsoplatt");
		LanguageRegistry.instance().addStringLocalization("item.osmiumLeggings.name", "rl_XR", "Osmiums Lëggiŋam");
		LanguageRegistry.instance().addStringLocalization("item.osmiumBoots.name", "rl_XR", "Osmiums Butam");
		
		// Rålmska (XR) - Achievements
		LanguageRegistry.instance().addStringLocalization("achievement.amethystAchieve", "rl_XR", "Ametista!");
		LanguageRegistry.instance().addStringLocalization("achievement.peridotAchieve", "rl_XR", "Peridota!");
		LanguageRegistry.instance().addStringLocalization("achievement.garnetAchieve", "rl_XR", "Garneta!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperAmethystAch", "rl_XR", "Hardad Ametista!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperPeridotAch", "rl_XR", "Hardad Peridota!");
		LanguageRegistry.instance().addStringLocalization("achievement.temperGarnetAch", "rl_XR", "Hardad Garneta!");
		LanguageRegistry.instance().addStringLocalization("achievement.amethystAchieve.desc", "rl_XR", "Gëtta Ametista bē jusar Fërros Pika");
		LanguageRegistry.instance().addStringLocalization("achievement.peridotAchieve.desc", "rl_XR", "Gëtta Peridota bē jusar Fërros Pika");
		LanguageRegistry.instance().addStringLocalization("achievement.garnetAchieve.desc", "rl_XR", "Gëtta Garneta bē jusar Fërros Pika");
		LanguageRegistry.instance().addStringLocalization("achievement.temperAmethystAch.desc", "rl_XR", "Smälta Ametista e gëtta Hardad Ametista");
		LanguageRegistry.instance().addStringLocalization("achievement.temperPeridotAch.desc", "rl_XR", "Smälta Peridota e gëtta Hardad Peridota");
		LanguageRegistry.instance().addStringLocalization("achievement.temperGarnetAch.desc", "rl_XR", "Smälta Garneta e gëtta Hardad Garneta");
	}
	
	//Miscellaneous Registrations
	public void moddedMisc() {
		//Setting Grass Textures
		grassTop = 8;
		grassSide = 7;
		grassBottom = 6;
		
		//Registering World Generators
		GameRegistry.registerWorldGenerator(new WorldGenMoresOres());
		
		//EntityRegistry.registerModEntity(EntityBeryllope.class, "Beryllope", 33, this, 80, 3, true);
		//EntityRegistry.addSpawn(EntityBeryllope.class, 10, 2, 7, EnumCreatureType.creature, mod_Amethyst.extremeHillsGem);
		
		//Registering Biomes and Texture Files
		proxy.registerRenderInformation();
		
		//Adding Achievement Page
		AchievementPage.registerAchievementPage(moresAchPage);
		
		GameRegistry.registerCraftingHandler(new CraftingHandler());
		GameRegistry.registerPickupHandler(new PickupNotifier());
		MinecraftForge.EVENT_BUS.register(new PickupNotifier());
		
		if (month == 0) {
			System.out.println("Month: January");
		}
		if (month == 1) {
			System.out.println("Month: February");
		}
		if (month == 2) {
			System.out.println("Month: March");
		}
		if (month == 3) {
			System.out.println("Month: April");
		}
		if (month == 4) {
			System.out.println("Month: May");
		}
		if (month == 5) {
			System.out.println("Month: June");
		}
		if (month == 6) {
			System.out.println("Month: July");
		}
		if (month == 7) {
			System.out.println("Month: August");
		}
		if (month == 8) {
			System.out.println("Month: September");
		}
		if (month == 9) {
			System.out.println("Month: October");
		}
		if (month == 10) {
			System.out.println("Month: November");
		}
		if (month == 11) {
			System.out.println("Month: December");
		}
		
		System.out.println("Gem Dirt ID: " + this.gemDirt.blockID);
	}
	
	//Adding Recipes
	public void moddedRecipes() {
		//Tools Recipes
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystSword, 1), new Object[] {"X", "X", "#", 'X', mod_Amethyst.amethyst, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotSword, 1), new Object[] {"X", "X", "#", 'X', mod_Amethyst.peridot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetSword, 1), new Object[] {"X", "X", "#", 'X', mod_Amethyst.garnet, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystAxe, 1), new Object[] {"XX", "X#", " #", 'X', mod_Amethyst.amethyst, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotAxe, 1), new Object[] {"XX", "X#", " #", 'X', mod_Amethyst.peridot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetAxe, 1), new Object[] {"XX", "X#", " #", 'X', mod_Amethyst.garnet, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystShovel, 1), new Object[] {"X", "#", "#", 'X', mod_Amethyst.amethyst, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotShovel, 1), new Object[] {"X", "#", "#", 'X', mod_Amethyst.peridot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetShovel, 1), new Object[] {"X", "#", "#", 'X', mod_Amethyst.garnet, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystPickaxe, 1), new Object[] {"XXX", " # ", " # ", 'X', mod_Amethyst.amethyst, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotPickaxe, 1), new Object[] {"XXX", " # ", " # ", 'X', mod_Amethyst.peridot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetPickaxe, 1), new Object[] {"XXX", " # ", " # ", 'X', mod_Amethyst.garnet, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystHoe, 1), new Object[] {"XX", " #", " #", 'X', mod_Amethyst.amethyst, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotHoe, 1), new Object[] {"XX", " #", " #", 'X', mod_Amethyst.peridot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetHoe, 1), new Object[] {"XX", " #", " #", 'X', mod_Amethyst.garnet, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloySword, 1), new Object[] {"X", "X", "#", 'X', mod_Amethyst.berylliteIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyAxe, 1), new Object[] {"XX", "X#", " #", 'X', mod_Amethyst.berylliteIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyShovel, 1), new Object[] {"X", "#", "#", 'X', mod_Amethyst.berylliteIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyPickaxe, 1), new Object[] {"XXX", " # ", " # ", 'X', mod_Amethyst.berylliteIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyHoe, 1), new Object[] {"XX", " #", " #", 'X', mod_Amethyst.berylliteIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumSword, 1), new Object[] {"X", "X", "#", 'X', mod_Amethyst.osmiumIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumAxe, 1), new Object[] {"XX", "X#", " #", 'X', mod_Amethyst.osmiumIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumShovel, 1), new Object[] {"X", "#", "#", 'X', mod_Amethyst.osmiumIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumPickaxe, 1), new Object[] {"XXX", " # ", " # ", 'X', mod_Amethyst.osmiumIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumHoe, 1), new Object[] {"XX", " #", " #", 'X', mod_Amethyst.osmiumIngot, '#', Item.stick});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.platinumLapisSword, 1), new Object[] {" X ", "YXY", " # ", 'X', mod_Amethyst.platinumIngot, '#', Item.stick, 'Y', mod_Amethyst.lapisIngot});
		
		//Solids Recipes
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystBlock, 1), new Object[] {"XXX", "XXX", "XXX", 'X', mod_Amethyst.amethyst});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotBlock, 1), new Object[] {"XXX", "XXX", "XXX", 'X', mod_Amethyst.peridot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetBlock, 1), new Object[] {"XXX", "XXX", "XXX", 'X', mod_Amethyst.garnet});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.beryllaniumBlock, 1), new Object[] {"XXX", "XXX", "XXX", 'X', mod_Amethyst.berylliteIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumBlock, 1), new Object[] {"XXX", "XXX", "XXX", 'X', mod_Amethyst.osmiumIngot});
		//Solids-to-Items Recipes
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.amethyst, 9), new Object[] {mod_Amethyst.amethystBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.peridot, 9), new Object[] {mod_Amethyst.peridotBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.garnet, 9), new Object[] {mod_Amethyst.garnetBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.berylliteIngot, 9), new Object[] {mod_Amethyst.beryllaniumBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.osmiumIngot, 9), new Object[] {mod_Amethyst.osmiumBlock});
		
		//Items-to-Powders Recipes
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.amethystPowder, 3), new Object[] {mod_Amethyst.amethyst, mod_Amethyst.osmiumStone});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.peridotPowder, 3), new Object[] {mod_Amethyst.peridot, mod_Amethyst.osmiumStone});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.garnetPowder, 3), new Object[] {mod_Amethyst.garnet, mod_Amethyst.osmiumStone});
		
		//Rock Candy Recipes
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.amethystRockCandy, 1), new Object[] {mod_Amethyst.amethystPowder, Item.sugar});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.peridotRockCandy, 1), new Object[] {mod_Amethyst.peridotPowder, Item.sugar});
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.garnetRockCandy, 1), new Object[] {mod_Amethyst.garnetPowder, Item.sugar});
		
		//Armor Recipes
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystHelmet, 1), new Object[] {"XXX", "X X", 'X', mod_Amethyst.amethyst});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystPlate, 1), new Object[] {"X X", "XXX", "XXX", 'X', mod_Amethyst.amethyst});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystLeggings, 1), new Object[] {"XXX", "X X", "X X", 'X', mod_Amethyst.amethyst});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.amethystBoots, 1), new Object[] {"X X", "X X", 'X', mod_Amethyst.amethyst});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotHelmet, 1), new Object[] {"XXX", "X X", 'X', mod_Amethyst.peridot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotPlate, 1), new Object[] {"X X", "XXX", "XXX", 'X', mod_Amethyst.peridot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotLeggings, 1), new Object[] {"XXX", "X X", "X X", 'X', mod_Amethyst.peridot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.peridotBoots, 1), new Object[] {"X X", "X X", 'X', mod_Amethyst.peridot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetHelmet, 1), new Object[] {"XXX", "X X", 'X', mod_Amethyst.garnet});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetPlate, 1), new Object[] {"X X", "XXX", "XXX", 'X', mod_Amethyst.garnet});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetLeggings, 1), new Object[] {"XXX", "X X", "X X", 'X', mod_Amethyst.garnet});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.garnetBoots, 1), new Object[] {"X X", "X X", 'X', mod_Amethyst.garnet});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyHelmet, 1), new Object[] {"XXX", "X X", 'X', mod_Amethyst.berylliteIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyPlate, 1), new Object[] {"X X", "XXX", "XXX", 'X', mod_Amethyst.berylliteIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyLeggings, 1), new Object[] {"XXX", "X X", "X X", 'X', mod_Amethyst.berylliteIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.alloyBoots, 1), new Object[] {"X X", "X X", 'X', mod_Amethyst.berylliteIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumHelmet, 1), new Object[] {"XXX", "X X", 'X', mod_Amethyst.osmiumIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumPlate, 1), new Object[] {"X X", "XXX", "XXX", 'X', mod_Amethyst.osmiumIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumLeggings, 1), new Object[] {"XXX", "X X", "X X", 'X', mod_Amethyst.osmiumIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumBoots, 1), new Object[] {"X X", "X X", 'X', mod_Amethyst.osmiumIngot});
		
		//Metals Recipes
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumStone, 1), new Object[] {"XX", "XX", 'X', mod_Amethyst.osmiumUntemperedIngot});
		GameRegistry.addRecipe(new ItemStack(mod_Amethyst.osmiumUntemperedIngot, 2), new Object[] {"XXX", 'X', mod_Amethyst.osmiumNugget});
		
		//Misc Items Recipes
		GameRegistry.addShapelessRecipe(new ItemStack(mod_Amethyst.gemAlloyIngot, 2), new Object[] {mod_Amethyst.amethystIngot, mod_Amethyst.peridotIngot, mod_Amethyst.garnetIngot, mod_Amethyst.amethystIngot, mod_Amethyst.peridotIngot, mod_Amethyst.garnetIngot, mod_Amethyst.amethystIngot, mod_Amethyst.peridotIngot, mod_Amethyst.garnetIngot});
		GameRegistry.addRecipe(new ItemStack(Item.minecartEmpty, 1), new Object[] {"X X", "XXX", 'X', mod_Amethyst.berylliteIngot});
		GameRegistry.addRecipe(new ItemStack(Item.minecartEmpty, 1), new Object[] {"X X", "XXX", 'X', mod_Amethyst.osmiumIngot});
		
		//Smelting Recipes
		GameRegistry.addSmelting(this.amethyst.itemID, new ItemStack(this.amethystIngot), 1.0F);
		GameRegistry.addSmelting(this.peridot.itemID, new ItemStack(this.peridotIngot), 1.0F);
		GameRegistry.addSmelting(this.garnet.itemID, new ItemStack(this.garnetIngot), 1.0F);
		GameRegistry.addSmelting(this.gemAlloyIngot.itemID, new ItemStack(this.berylliteIngot, 2), 1.2F);
		GameRegistry.addSmelting(this.osmiumOre.blockID, new ItemStack(this.osmiumUntemperedIngot, 1), 1.0F);
		GameRegistry.addSmelting(this.osmiumUntemperedIngot.itemID, new ItemStack(this.osmiumIngot), 1.0F);
		GameRegistry.addSmelting(this.amethystOre.blockID, new ItemStack(this.amethyst), 1.0F);
		GameRegistry.addSmelting(this.peridotOre.blockID, new ItemStack(this.peridot), 1.0F);
		GameRegistry.addSmelting(this.garnetOre.blockID, new ItemStack(this.garnet), 1.0F);
		GameRegistry.addSmelting(this.beryllaniumOre.blockID, new ItemStack(this.berylliteIngot), 1.0F);
		GameRegistry.addSmelting(this.platinumOre.blockID, new ItemStack(this.platinumIngot), 1.0F);
		FurnaceRecipes.smelting().addSmelting(Item.dyePowder.itemID, 4, new ItemStack(this.lapisIngot), 0.0F);
	}
	
	public static int amethystOreID;
	public static int peridotOreID;
	public static int garnetOreID;
	public static int amethystBlockID;
	public static int peridotBlockID;
	public static int garnetBlockID;
	public static int sandGemID;
	public static int dirtGemID;
	public static int grassGemID;
	public static int logGemID;
	public static int leafGemID;
	public static int saplingGemID;
	public static int tallgrassGemID;
	public static int osmiumOreID;
	public static int osmiumBlockID;
	public static int beryllaniumOreID;
	public static int beryllaniumBlockID;
	public static int platinumOreID;
	public static int glowstoneOreID;
	
	public static int AID;
	public static int PID;
	public static int GID;
	public static int AIID;
	public static int PIID;
	public static int GIID;
	public static int SID;
	public static int BID;
	public static int ONID;
	public static int OSID;
	public static int OIID;
	public static int OI2ID;
	public static int ASWID;
	public static int PSWID;
	public static int GSWID;
	public static int BSWID;
	public static int OSWID;
	public static int ASHID;
	public static int PSHID;
	public static int GSHID;
	public static int BSHID;
	public static int OSHID;
	public static int APID;
	public static int PPID;
	public static int GPID;
	public static int BPID;
	public static int OPID;
	public static int AAID;
	public static int PAID;
	public static int GAID;
	public static int BAID;
	public static int OAID;
	public static int AHID;
	public static int PHID;
	public static int GHID;
	public static int BHID;
	public static int OHID;
	public static int AHEID;
	public static int PHEID;
	public static int GHEID;
	public static int BHEID;
	public static int OHEID;
	public static int APLID;
	public static int PPLID;
	public static int GPLID;
	public static int BPLID;
	public static int OPLID;
	public static int ALEID;
	public static int PLEID;
	public static int GLEID;
	public static int BLEID;
	public static int OLEID;
	public static int ABOID;
	public static int PBOID;
	public static int GBOID;
	public static int BBOID;
	public static int OBOID;
	public static int APOID;
	public static int PPOID;
	public static int GPOID;
	public static int ARCID;
	public static int PRCID;
	public static int GRCID;
	public static int GUMID;
	public static int GCMID;
	
	public static int GDTID;
	public static int GHLID;
}
