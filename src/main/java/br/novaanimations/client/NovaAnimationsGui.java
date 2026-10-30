
package br.novaanimations.client;

import br.novaanimations.config.ModConfig;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class NovaAnimationsGui extends GuiScreen {

    private static final String[][] OPTIONS = {

        {"old_swing", "Old Swing"},
        {"old_sneak", "Old Sneak"},
        {"old_eating", "Old Eating"},
        {"old_rod", "Old Rod"},
        {"old_bow", "Old Bow"},
        {"old_blockhit", "Old Blockhit"},
        {"old_item_switch", "Old Item Switch"},
        {"old_item_position", "Old Item Position"},
        {"items_2d", "2D Dropped Items"},
        {"old_projectiles", "Old Projectiles"},
        {"old_enchant_glint", "Old Enchant Glint"},
        {"armor_damage_tint", "Armor Damage Tint"},
        {"no_hurt_camera", "No Hurt Camera"},
        {"old_blocking", "Old Blocking Pose"},
        {"old_item_pickup", "Old Item Pickup"},
        {"walking_1_11", "Modern Backwards Walk"}
    };

    @Override
    public void initGui() {

        buttonList.clear();

        int buttonWidth = Math.min(150, (width - 20) / 2);

        int left = width / 2 - buttonWidth - 4;
        int right = width / 2 + 4;

        int top = 42;

        for (int i = 0; i < OPTIONS.length; i++) {

            int column = i % 2;
            int row = i / 2;

            String key = OPTIONS[i][0];
            String label = OPTIONS[i][1];

            boolean enabled = ModConfig.get(key);

            buttonList.add(new GuiButton(
                i,
                column == 0 ? left : right,
                top + row * 23,
                buttonWidth,
                20,
                label + ": " + (enabled ? "ON" : "OFF")
            ));
        }

        buttonList.add(new GuiButton(
            100,
            width / 2 - 75,
            height - 28,
            150,
            20,
            "Done"
        ));
    }

    @Override
    protected void actionPerformed(GuiButton button) {

        if (button.id == 100) {
            mc.displayGuiScreen(null);
            return;
        }

        if (button.id >= 0 && button.id < OPTIONS.length) {

            String key = OPTIONS[button.id][0];

            ModConfig.set(
                key,
                !ModConfig.get(key)
            );

            initGui();
        }
    }

    @Override
    public void drawScreen(
        int mouseX,
        int mouseY,
        float partialTicks
    ) {

        drawDefaultBackground();

        drawCenteredString(
            fontRendererObj,
            "NovaAnimations",
            width / 2,
            12,
            0xFFFFFF
        );

        drawCenteredString(
            fontRendererObj,
            "Forge 1.8.9",
            width / 2,
            26,
            0xAAAAAA
        );

        super.drawScreen(
            mouseX,
            mouseY,
            partialTicks
        );
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
