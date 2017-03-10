package eyeq.painfulcactus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import eyeq.painfulcactus.event.PainfulCactusEventHandler;

import static eyeq.painfulcactus.PainfulCactus.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class PainfulCactus {
    public static final String MOD_ID = "eyeq_painfulcactus";

    @Mod.Instance(MOD_ID)
    protected static PainfulCactus instance;

    public static float prob;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PainfulCactusEventHandler());
        load(new Configuration(event.getSuggestedConfigurationFile()));
    }

    public static void load(Configuration config) {
        config.load();

        String category = "Float";
        prob = (float) config.get(category, "prob", 0.2).getDouble();

        if(config.hasChanged()) {
            config.save();
        }
    }
}
