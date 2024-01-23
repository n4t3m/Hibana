package com.natem135.hibana.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onDeathMessage", at = @At("TAIL"))
    private void onDeathMessageInject(DeathMessageS2CPacket deathMessageS2CPacket, CallbackInfo ci) {
        System.out.println("ON DEATH CALLBACK");
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if(player!=null) {
            player.requestRespawn();
        }}
}