package com.natem135.hibana.modules;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.event.Listener;
import com.natem135.hibana.event.ReceivePacketEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class AutoFishSoundModule extends ToggleableModule {

    Consumer<ReceivePacketEvent> onRecv = AutoFishSoundModule::onReceivePacket;
    Listener recvPacketListener = new Listener(onRecv);

    public AutoFishSoundModule() {
        super("AutoFish (Sound-Based)", GLFW.GLFW_KEY_B);
    }

    @Override
    public void onEnable() {
        Hibana.getEventManager().addListener(ReceivePacketEvent.class, recvPacketListener);
    }

    @Override
    public void onDisable() {
        Hibana.getEventManager().removeListener(ReceivePacketEvent.class, recvPacketListener);
    }

    public void onTick() {
    }

    public static void onReceivePacket(ReceivePacketEvent event) {
        Packet<?>packet = event.getPacket();
        if(packet instanceof PlaySoundS2CPacket p) {
            RegistryKey<SoundEvent> entry = p.getSound().getKey().orElse(null);
            if(entry!=null && entry.getValue().getPath().equals("entity.fishing_bobber.splash")){
                castRod();
            }
        }

    }

    private static void castRod() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerInteractionManager interactionManager = client.interactionManager;
        if(interactionManager != null) {
            interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            interactionManager.interactItem(client.player, Hand.MAIN_HAND);
        }
        System.out.println("Caught Fish!!");
    }

}
