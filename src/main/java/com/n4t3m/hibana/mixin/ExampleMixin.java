package com.n4t3m.hibana.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}


//	@Inject(at = @At("HEAD"), method = "tick")
//	private void tick(CallbackInfo ci){
//		ClientPlayerEntity player = MinecraftClient.getInstance().player;
//		try{
//			if(player != null && player.hasVehicle()){
//				Entity vehicle = player.getVehicle();
//				Vec3d velocity = vehicle.getVelocity();
//				double Yaxis = player.input.jumping ? 0.5 : 0;
//				vehicle.setVelocity(new Vec3d(velocity.x, Yaxis, velocity.z));
//			}
//		}
//		catch (Exception e) {
//			assert true;
//		}
//	}
}