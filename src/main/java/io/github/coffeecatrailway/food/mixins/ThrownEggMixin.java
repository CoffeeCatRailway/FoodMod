package io.github.coffeecatrailway.food.mixins;

import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.config.FoodConfigs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
@Mixin(ThrownEgg.class)
public abstract class ThrownEggMixin extends ThrowableItemProjectile
{
	public ThrownEggMixin(EntityType<? extends ThrowableItemProjectile> entityType, Level level)
	{
		super(entityType, level);
	}

	@Inject(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V"))
	protected void onHit(HitResult result, CallbackInfo info)
	{
		if (!this.level().isClientSide())
			if (this.level().random.nextDouble() <= FoodConfigs.SERVER.crackedEggSpawnChance.getAsDouble())
				this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.CRACKED_EGG.get())));
	}
}
