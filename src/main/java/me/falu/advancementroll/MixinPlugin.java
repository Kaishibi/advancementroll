package me.falu.advancementroll;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
    public static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("advancementroll.json").toFile();
    public static JsonObject CONFIG;

    private static void getConfig() {
        if (CONFIG != null) { return; }

        try {
            FileReader reader = new FileReader(CONFIG_FILE);
            JsonParser parser = new JsonParser();

            Object obj = parser.parse(reader);
            CONFIG = obj.equals(JsonNull.INSTANCE) ? new JsonObject() : (JsonObject) obj;

            reader.close();
        } catch (IOException ignored) {
            CONFIG = new JsonObject();
        }
    }

    private static boolean getConfigValue(String feature) {
        if (CONFIG.has(feature)) {
            return CONFIG.get(feature).getAsBoolean();
        }

        CONFIG.addProperty(feature, true);
        return true;
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        getConfig();

        if (mixinClassName.startsWith("me.falu.advancementroll.mixin.features")) {
            String[] packageNames = mixinClassName.split("\\.");
            if (packageNames.length > 5) {
                String featureName = packageNames[5];
                return getConfigValue(featureName);
            }
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
