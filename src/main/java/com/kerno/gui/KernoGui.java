package com.kerno.gui;

import com.kerno.KernoClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class KernoGui extends Screen {
    public KernoGui() { super(Text.literal("Kerno Optimizer (1.21.11)")); }

    @Override
    protected void init() {
        super.init();
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 60;

        this.addDrawableChild(ButtonWidget.builder(getText("Fullbright", KernoClient.fullbright), b -> {
            KernoClient.fullbright = !KernoClient.fullbright;
            b.setMessage(getText("Fullbright", KernoClient.fullbright));
        }).dimensions(x, y, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(getText("No Fog", KernoClient.noFog), b -> {
            KernoClient.noFog = !KernoClient.noFog;
            b.setMessage(getText("No Fog", KernoClient.noFog));
        }).dimensions(x, y + 24, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(getText("No Particles", KernoClient.noParticles), b -> {
            KernoClient.noParticles = !KernoClient.noParticles;
            b.setMessage(getText("No Particles", KernoClient.noParticles));
        }).dimensions(x, y + 48, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(getText("No Clouds", KernoClient.noClouds), b -> {
            KernoClient.noClouds = !KernoClient.noClouds;
            b.setMessage(getText("No Clouds", KernoClient.noClouds));
        }).dimensions(x, y + 72, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(getText("Fast Render (4 Chunk)", KernoClient.fastRender), b -> {
            KernoClient.fastRender = !KernoClient.fastRender;
            b.setMessage(getText("Fast Render (4 Chunk)", KernoClient.fastRender));
        }).dimensions(x, y + 96, 200, 20).build());
    }

    private Text getText(String name, boolean state) {
        return Text.literal(name + ": " + (state ? "ON" : "OFF"))
                .formatted(state ? Formatting.GREEN : Formatting.RED);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0x80000000); // Yari saydam arka plan
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2 - 80, 0xFF55FFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
