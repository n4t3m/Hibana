package com.n4t3m.hibana.mixin;

import com.n4t3m.hibana.Hibana;
import com.n4t3m.hibana.event.ClientPlayerDeathEvent;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onDeathMessage", at = @At("TAIL"))
    private void onDeathMessageInject(DeathMessageS2CPacket packet, CallbackInfo ci) {
        ClientPlayerDeathEvent event = new ClientPlayerDeathEvent(packet);
        Hibana.getEventManager().notifyListeners(event);
    }
}