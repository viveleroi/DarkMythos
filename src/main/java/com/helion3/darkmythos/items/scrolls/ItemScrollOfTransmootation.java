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

import com.helion3.darkmythos.Curses;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemScrollOfTransmootation extends Scroll {
    public ItemScrollOfTransmootation() {
        super("scrolloftransmootation");

        this.curseChance = 0.5;
        this.setMaxDamage(1);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        // Run only on logical server
        if (target.world.isRemote) {
            return true;
        }

        ItemStack itemStack = playerIn.getHeldItem(hand);

        if (this.tryForCurse(itemStack)) {
            // Inform them
            playerIn.sendMessage(new TextComponentTranslation("text.scroll.cursed"));

            // Destroy item
            itemStack.damageItem(100, playerIn);

            // Curse them
            Curses.applyMinorHungerCurse(playerIn);

            return false;
        }

        if (target instanceof EntityMooshroom) {
            // Spawn a cow
            EntityCow cow = new EntityCow(target.world);
            cow.setPositionAndRotation(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
            target.world.spawnEntity(cow);

            // Remove the mooshroom
            target.world.removeEntity(target);
        }
        else if (target instanceof EntityCow) {
            // Spawn a mooshroom
            EntityMooshroom mooshroom = new EntityMooshroom(target.world);
            mooshroom.setPositionAndRotation(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
            target.world.spawnEntity(mooshroom);

            // Remove the cow
            target.world.removeEntity(target);
        }
        else {
            playerIn.sendMessage(new TextComponentTranslation("text.scroll.incorrect_poison"));

            // Curse them
            Curses.applyMinorHungerCurse(playerIn);
        }

        itemStack.damageItem(2, playerIn);

        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}
