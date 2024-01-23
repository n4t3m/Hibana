package com.natem135.hibana.mixin;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.event.SendPacketEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;Z)V", at = @At("HEAD"))
    private void onSendPacket(Packet<?> p, PacketCallbacks callback, boolean flush, CallbackInfo ci) {
        SendPacketEvent event = new SendPacketEvent(p);
        Hibana.getEventManager().notifyListeners(event);
    }


}