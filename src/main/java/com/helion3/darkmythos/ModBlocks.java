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

import com.helion3.darkmythos.blocks.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
    @GameRegistry.ObjectHolder("darkmythos:darkcrystalore")
    public static BlockDarkCrystalOre darkCrystalOre;

    @GameRegistry.ObjectHolder("darkmythos:darkstone")
    public static ModBlock darkStone;

    @GameRegistry.ObjectHolder("darkmythos:divinewater")
    public static BlockDivineWater divineWater;

    @GameRegistry.ObjectHolder("darkmythos:lightcrystalore")
    public static BlockLightCrystalOre lightCrystalOre;

    @GameRegistry.ObjectHolder("darkmythos:lightstone")
    public static ModTranslucentBlock lightStone;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        darkCrystalOre.initModel();
        darkStone.initModel();
        lightCrystalOre.initModel();
        lightStone.initModel();

        divineWater.render();
    }
}
