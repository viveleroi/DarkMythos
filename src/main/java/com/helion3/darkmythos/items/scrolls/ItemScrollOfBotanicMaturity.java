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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemScrollOfBotanicMaturity extends Scroll {
    public ItemScrollOfBotanicMaturity() {
        super("scrollofbotanicmaturity");

        this.setMaxDamage(8);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Copy the item so we give garbage to applyBonemeal - it does
        // things to the item damage we don't like
        ItemStack phantomBonemeal = player.getHeldItem(hand).copy();

        if (ItemDye.applyBonemeal(phantomBonemeal, worldIn, pos, player, hand)) {
            player.getHeldItem(hand).damageItem(1, player);
        } else {
            // Run only on logical server
            if (worldIn.isRemote) {
                player.sendMessage(new TextComponentTranslation("text.scroll.incorrect_poison"));

                // Curse them
                Curses.applyMinorPoisonCurse(player);
            }
        }

        return EnumActionResult.SUCCESS;
    }
}
