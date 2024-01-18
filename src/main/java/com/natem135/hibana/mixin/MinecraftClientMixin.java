package com.natem135.hibana.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci){
//        ClientPlayerEntity player = MinecraftClient.getInstance().player;
//        try{
//            if(player != null && player.hasVehicle()){
//                Entity vehicle = player.getVehicle();
//                Vec3d velocity = vehicle.getVelocity();
//                double Yaxis = player.input.jumping ? 0.5 : 0;
//                vehicle.setVelocity(new Vec3d(velocity.x, Yaxis, velocity.z));
//            }
//        }
//        catch (Exception e) {
//            assert true;
//        }
    }
}