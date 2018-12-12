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
package com.helion3.darkmythos;

import com.helion3.darkmythos.items.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
    @GameRegistry.ObjectHolder("darkmythos:darkstone")
    public static ItemBlock darkStone;

    @GameRegistry.ObjectHolder("darkmythos:darkcrystal")
    public static ItemDarkCrystal darkCrystal;

    @GameRegistry.ObjectHolder("darkmythos:legendarydarkcrystal")
    public static ItemLegendaryDarkCrystal legendayDarkCrystal;

    @GameRegistry.ObjectHolder("darkmythos:mythicaldarkcrystal")
    public static ItemMythicalDarkCrystal mythicalDarkCrystal;

    @GameRegistry.ObjectHolder("darkmythos:superiordarkcrystal")
    public static ItemSuperiorDarkCrystal superiorDarkCrystal;

    @GameRegistry.ObjectHolder("darkmythos:darkcrystalaxe")
    public static ItemDarkCrystalAxe darkCrystalAxe;

    @GameRegistry.ObjectHolder("darkmythos:legendarydarkcrystalaxe")
    public static ItemDarkCrystalAxe legendaryDarkCrystalAxe;

    @GameRegistry.ObjectHolder("darkmythos:mythicaldarkcrystalaxe")
    public static ItemDarkCrystalAxe mythicalDarkCrystalAxe;

    @GameRegistry.ObjectHolder("darkmythos:superiordarkcrystalaxe")
    public static ItemDarkCrystalAxe superiorDarkCrystalAxe;

    @GameRegistry.ObjectHolder("darkmythos:scrollofirontouch")
    public static ItemScrollOfIronTouch scrollOfIronTouch;

    public static final CreativeTabs tabDarkMythos = (new CreativeTabs("tabDarkMythos") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(darkStone);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    });

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        // Crystals
        darkCrystal.initModel();
        legendayDarkCrystal.initModel();
        mythicalDarkCrystal.initModel();
        superiorDarkCrystal.initModel();

        // Axes
        darkCrystalAxe.initModel();
        legendaryDarkCrystalAxe.initModel();
        mythicalDarkCrystalAxe.initModel();
        superiorDarkCrystalAxe.initModel();

        // Scrolls
        scrollOfIronTouch.initModel();
    }
}
