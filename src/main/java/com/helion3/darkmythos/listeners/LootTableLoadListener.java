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
package com.helion3.darkmythos.listeners;

import com.helion3.darkmythos.ModItems;
import com.helion3.darkmythos.util.LootUtil;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class LootTableLoadListener {
    @SubscribeEvent
    public static void lootTableLoad(LootTableLoadEvent event) {
        if (event.getName().getResourcePath().equals("entities/cow")) {
            LootUtil.addItemToTable(event.getTable(), ModItems.scrollOfTransmootation, 1, 1, 0.02f, "scroll");
        }
        else if (event.getName().getResourcePath().equals("entities/ender_dragon")) {
            LootUtil.addItemToTable(event.getTable(), ModItems.alphaPearl, 1, 1, 1, "alphapearl");
        }
        else if (event.getName().getResourcePath().equals("entities/mooshroom")) {
            LootUtil.addItemToTable(event.getTable(), ModItems.scrollOfTransmootation, 1, 1, 0.1f, "scroll");
        }
        else if (event.getName().getResourcePath().equals("entities/skeleton")) {
            LootUtil.addItemToTable(event.getTable(), ModItems.scrollOfBotanicMaturity, 1, 1, 0.3f, "scroll");
        }
        else if (event.getName().getResourcePath().equals("entities/zombie")) {
            LootUtil.addItemToTable(event.getTable(), ModItems.scrollOfIronTouch, 1, 1, 0.2f, "scroll");
        }
    }
}
