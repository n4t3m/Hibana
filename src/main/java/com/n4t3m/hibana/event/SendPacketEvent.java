package com.n4t3m.hibana.event;

import com.n4t3m.hibana.Hibana;
import net.minecraft.network.packet.Packet;

public class SendPacketEvent extends Event {
    private final Packet<?> packet;

    public SendPacketEvent(Packet<?> packet) {
        super();
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public void printPacketType() {
        Hibana.LOGGER.info(packet.getClass().getSimpleName());
    }

}
