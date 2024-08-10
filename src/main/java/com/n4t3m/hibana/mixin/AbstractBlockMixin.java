package com.n4t3m.hibana.mixin;

import com.n4t3m.hibana.modules.ModuleManager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("HEAD"), cancellable = true)
    public void getAmbientOcclusionLightLevel(BlockState state, net.minecraft.world.BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir)
    {
        if(ModuleManager.xrayModule.module_enabled || ModuleManager.fullBrightModule.module_enabled) {
            cir.setReturnValue(1f);
        }
    }
}
