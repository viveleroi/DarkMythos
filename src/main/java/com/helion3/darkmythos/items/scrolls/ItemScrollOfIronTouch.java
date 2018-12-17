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
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemScrollOfIronTouch extends Scroll {
    public ItemScrollOfIronTouch() {
        super("scrollofirontouch");

        this.curseChance = 0.3;
        this.setMaxDamage(8);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Run only on logical server
        if (worldIn.isRemote) {
            return EnumActionResult.PASS;
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (this.tryForCurse(itemStack)) {
            // Inform them
            player.sendMessage(new TextComponentTranslation("text.scroll.cursed"));

            // Destroy item
            itemStack.damageItem(100, player);

            // Curse them
            Curses.applyMinorPoisonCurse(player);

            return EnumActionResult.FAIL;
        }

        // Calculate the remaining durability
        int durabilityLeft = itemStack.getMaxDamage() - itemStack.getItemDamage();
        int newDamage = 0;

        Block worldBlock = worldIn.getBlockState(pos).getBlock();
        if (worldBlock == Blocks.COAL_ORE) {
            // Transmute block
            worldIn.setBlockState(pos, Blocks.IRON_ORE.getDefaultState());

            newDamage = 1;
        }
        else if (worldBlock == Blocks.COAL_BLOCK) {
            if (durabilityLeft >= 8) {
                // Transmute block
                worldIn.setBlockState(pos, Blocks.IRON_BLOCK.getDefaultState());
            } else {
                player.sendMessage(new TextComponentTranslation("text.scroll.backfire_poison"));

                // Curse them
                Curses.applyMinorPoisonCurse(player);
            }

            newDamage = 9;
        }

        // If used, apply damage and return success
        if (newDamage > 0) {
            itemStack.damageItem(newDamage, player);

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }
}
