package me.wurgo.advancementroll;

import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
