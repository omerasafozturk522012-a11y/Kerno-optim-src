package com.kerno;

import com.kerno.gui.KernoGui;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KernoClient implements ClientModInitializer {
    public static KeyBinding openGuiKey;
    
    // Varsayilan Optimizasyon Durumlari
    public static boolean fullbright = true;
    public static boolean noFog = true;
    public static boolean noParticles = false;
    public static boolean noClouds = true;
    public static boolean fastRender = false;

    @Override
    public void onInitializeClient() {
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.kerno.gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "category.kerno"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                client.setScreen(new KernoGui());
            }
            
            if (client.player == null || client.world == null || client.options == null) return;

            // Fullbright
            if (fullbright) {
                client.options.getGamma().setValue(10.0D);
            } else {
                client.options.getGamma().setValue(1.0D);
            }

            // No Particles
            if (noParticles) {
                client.options.getParticles().setValue(ParticlesMode.MINIMAL);
            } else {
                client.options.getParticles().setValue(ParticlesMode.ALL);
            }

            // No Clouds
            if (noClouds) {
                client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
            } else {
                client.options.getCloudRenderMode().setValue(CloudRenderMode.FANCY);
            }
            
            // Fast Render (Render mesafesini dusurur)
            if (fastRender) {
                client.options.getViewDistance().setValue(4);
            } else {
                client.options.getViewDistance().setValue(8);
            }
        });
    }
}
