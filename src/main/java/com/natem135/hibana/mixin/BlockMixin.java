package com.natem135.hibana.mixin;

import com.natem135.hibana.modules.ModuleManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.natem135.hibana.modules.render.XrayModule;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(at = { @At("RETURN") }, method = "shouldDrawSide", cancellable = true)
    private static void shouldDrawSide(BlockState state, BlockView world, BlockPos pos, Direction direction, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if(ModuleManager.xrayModule.module_enabled) {
            cir.setReturnValue(XrayModule.shouldRender(state.getBlock()));
        }
    }
}
