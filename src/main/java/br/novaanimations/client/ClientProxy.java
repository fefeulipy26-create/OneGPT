
package br.novaanimations.client;

import br.novaanimations.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    private KeyBinding menuKey;

    @Override
    public void init() {

        menuKey = new KeyBinding(
            "NovaAnimations Menu",
            Keyboard.KEY_RBRACKET,
            "NovaAnimations"
        );

        ClientRegistry.registerKeyBinding(menuKey);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {

        if (menuKey != null && menuKey.isPressed()) {

            Minecraft.getMinecraft().displayGuiScreen(
                new NovaAnimationsGui()
            );
        }
    }
}
