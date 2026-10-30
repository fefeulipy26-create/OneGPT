
package br.novaanimations;

import br.novaanimations.config.ModConfig;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
    modid = NovaAnimations.MODID,
    name = NovaAnimations.NAME,
    version = NovaAnimations.VERSION,
    acceptedMinecraftVersions = "[1.8.9]"
)
public class NovaAnimations {

    public static final String MODID = "novaanimations";
    public static final String NAME = "NovaAnimations";
    public static final String VERSION = "1.0.0";

    @SidedProxy(
        clientSide = "br.novaanimations.client.ClientProxy",
        serverSide = "br.novaanimations.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModConfig.init(event.getModConfigurationDirectory());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
