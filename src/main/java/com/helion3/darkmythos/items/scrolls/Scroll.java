/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 Mike Botsko (aka viveleroi)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.helion3.darkmythos.items.scrolls;

import com.helion3.darkmythos.DarkMythos;
import com.helion3.darkmythos.ModBlocks;
import com.helion3.darkmythos.ModItems;
import com.helion3.darkmythos.items.ModItem;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class Scroll extends ModItem {
    protected double curseChance = 0.1;

    protected Random rng;

    public Scroll(String name) {
        super(name);

        this.rng = new Random();
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.AQUA + I18n.format("tooltip." + this.getRegistryName().toString().replace("darkmythos:", "") + ".description"));

        if (this.isPure(stack)) {
            tooltip.add(TextFormatting.GREEN + I18n.format("tooltip.pure"));
        } else {
            tooltip.add(TextFormatting.GRAY + I18n.format("tooltip.cursed"));
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public boolean tryForCurse(ItemStack itemStack) {
        return !this.isPure(itemStack) && rng.nextDouble() < this.curseChance;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack) {
        return this.isPure(itemStack);
    }

    public boolean isPure(ItemStack itemStack) {
        NBTTagCompound nbt = getOrCreateNBTTagCompound(itemStack);

        return nbt.hasKey("IsPure") && nbt.getBoolean("IsPure");
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        // Only check if not yet purified
        if (!this.isPure(entityItem.getItem())) {
            Block worldBlock = entityItem.world.getBlockState(entityItem.getPosition()).getBlock();

            if (worldBlock == ModBlocks.divineWater) {
                setPurified(entityItem.getItem(), true);
            }
        }

        return super.onEntityItemUpdate(entityItem);
    }

    public void setPurified(ItemStack itemStack, boolean purified) {
        NBTTagCompound nbt = getOrCreateNBTTagCompound(itemStack);
        nbt.setBoolean("IsPure", purified);

        itemStack.setTagCompound(nbt);
    }
}
