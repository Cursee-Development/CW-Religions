package com.cursee.cw_religions.mixin;

import com.cursee.cw_religions.core.religion.util.IEntityReligionSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityReligionMixin implements IEntityReligionSaver {

    @Unique
    private static final String DATA_ID = "cw_religions.religion_data";

    @Unique
    private CompoundTag religionAsData;

    @Override
    public CompoundTag getReligion() {
        if (religionAsData == null) religionAsData = new CompoundTag();
        return religionAsData;
    }

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    protected void injectWriteMethod(CompoundTag nbt, CallbackInfoReturnable info) {
        if(religionAsData != null) {
            nbt.put(DATA_ID, religionAsData);
        }
    }

    @Inject(method = "load", at = @At("HEAD"))
    protected void injectReadMethod(CompoundTag nbt, CallbackInfo info) {
        if (nbt.contains(DATA_ID, Tag.TAG_COMPOUND)) {
            religionAsData = nbt.getCompound(DATA_ID);
        }
    }
}
