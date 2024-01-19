package com.natem135.hibana.util;

import com.natem135.hibana.interfaces.ISimpleOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import org.jetbrains.annotations.NotNull;

import static com.natem135.hibana.Hibana.LOGGER;

@SuppressWarnings({"unchecked", "ConstantConditions"})
public class GammaUtils {

    public static void setMaxGamma(@NotNull MinecraftClient client) {
        ISimpleOption<Double> gamma = (ISimpleOption<Double>)(Object)client.options.getGamma();
        gamma.forceSetValue(1000000000.0);
        LOGGER.info(String.format("Set Gamma To %.2f", client.options.getGamma().getValue()));
    }

    @SuppressWarnings("unused")
    public static void setGamma(@NotNull MinecraftClient client, Double newGamma) {
        ISimpleOption<Double> gamma = (ISimpleOption<Double>)(Object)client.options.getGamma();
        gamma.forceSetValue(newGamma);
        LOGGER.info(String.format("Set Gamma To %.2f", client.options.getGamma().getValue()));
    }

    public static void resetToDefaultGamma(@NotNull MinecraftClient client) {
        // This function uses the normal Minecraft Interface to reset the gamma value to normal.
        SimpleOption<Double> gamma = client.options.getGamma();
        gamma.setValue(0.5);
        LOGGER.info(String.format("Set Gamma To %.2f", client.options.getGamma().getValue()));
    }


}
