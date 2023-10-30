package me.falu.advancementroll;

import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;

import java.io.FileWriter;
import java.io.IOException;

public class Advancementroll implements ModInitializer {
    public static int screenSeed = 0;

    @Override
    public void onInitialize() {
        try {
            FileWriter writer = new FileWriter(MixinPlugin.CONFIG_FILE);

            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(MixinPlugin.CONFIG));
            writer.flush();
            writer.close();

            MixinPlugin.CONFIG = null;
        } catch (IOException ignored) {}
    }
}
