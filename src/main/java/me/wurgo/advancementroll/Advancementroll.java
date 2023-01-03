package me.wurgo.advancementroll;

import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;

import java.io.FileWriter;
import java.io.IOException;

public class Advancementroll implements ClientModInitializer {
    public static int screenSeed = 0;

    @Override
    public void onInitializeClient() {
        try {
            FileWriter writer = new FileWriter(MixinPlugin.configFile);

            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(MixinPlugin.config));
            writer.flush();
            writer.close();

            MixinPlugin.config = null;
        } catch (IOException ignored) {}
    }
}
