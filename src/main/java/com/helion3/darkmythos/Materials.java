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

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class Materials {
    // name, harvest level, durability, mining speed, damage, enchantability
    public static final Item.ToolMaterial DARK_CRYSTAL = EnumHelper.addToolMaterial("DARK_CRYSTAL", 3, 256, 13, 4, 20);
    public static final Item.ToolMaterial SUPERIOR_DARK_CRYSTAL = EnumHelper.addToolMaterial("SUPERIOR_DARK_CRYSTAL", 3, 2048, 9, 10, 30);
    public static final Item.ToolMaterial LEGENDARY_DARK_CRYSTAL = EnumHelper.addToolMaterial("LEGENDARY_DARK_CRYSTAL", 3, 16384, 40, 20, 40);
    public static final Item.ToolMaterial MYTHICAL_DARK_CRYSTAL = EnumHelper.addToolMaterial("MYTHICAL_DARK_CRYSTAL", 3, 65000, 80, 40, 100);
}
