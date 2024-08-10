package com.n4t3m.hibana.mixin;

import com.n4t3m.hibana.Hibana;
import com.n4t3m.hibana.event.ReceivePacketEvent;
import com.n4t3m.hibana.event.SendPacketEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.listener.PacketListener;
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

    @Inject(method = "handlePacket", at = @At("HEAD"))
    private static void onReceivePacket(Packet<?> packet, PacketListener listener, CallbackInfo ci) {
        ReceivePacketEvent event = new ReceivePacketEvent(packet);
        Hibana.getEventManager().notifyListeners(event);
    }


}