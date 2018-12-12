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
import com.helion3.darkmythos.DarkMythos;
import com.helion3.darkmythos.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemScrollOfIronTouch extends Item {
    public ItemScrollOfIronTouch() {
        this.setCreativeTab(ModItems.tabDarkMythos);
        this.setMaxDamage(8);
        this.setRegistryName("scrollofirontouch");
        this.setUnlocalizedName(DarkMythos.MODID + ".scrollofirontouch");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.AQUA + "Turns coal into iron.");

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Run only on logical server
        if (worldIn.isRemote) {
            return EnumActionResult.PASS;
        }

        // Calculate the remaining durability
        int durabilityLeft = player.getHeldItemMainhand().getMaxDamage() - player.getHeldItemMainhand().getItemDamage();
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
            player.getHeldItemMainhand().damageItem(newDamage, player);

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }
}
