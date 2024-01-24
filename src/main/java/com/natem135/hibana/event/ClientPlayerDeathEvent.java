package com.natem135.hibana.event;

import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;

public class ClientPlayerDeathEvent extends Event {
    private final DeathMessageS2CPacket deathPacket;

    public ClientPlayerDeathEvent(DeathMessageS2CPacket packet) {
        super();
        this.deathPacket = packet;
    }

    public DeathMessageS2CPacket getPacket() {
        return this.deathPacket;
    }


}
