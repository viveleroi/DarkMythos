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
package com.helion3.darkmythos.proxy;

import com.helion3.darkmythos.*;
import com.helion3.darkmythos.blocks.*;
import com.helion3.darkmythos.generation.BlockDarkCrystalOreGen;
import com.helion3.darkmythos.generation.BlockDarkStoneGen;
import com.helion3.darkmythos.generation.BlockLightCrystalOreGen;
import com.helion3.darkmythos.generation.BlockLightStoneGen;
import com.helion3.darkmythos.items.*;
import com.helion3.darkmythos.items.scrolls.ItemScrollOfBotanicMaturity;
import com.helion3.darkmythos.items.scrolls.ItemScrollOfIronTouch;
import com.helion3.darkmythos.items.scrolls.ItemScrollOfTransmootation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        // Register generators
        GameRegistry.registerWorldGenerator(new BlockDarkCrystalOreGen(), 3);
        GameRegistry.registerWorldGenerator(new BlockDarkStoneGen(), 3);
        GameRegistry.registerWorldGenerator(new BlockLightCrystalOreGen(), 3);
        GameRegistry.registerWorldGenerator(new BlockLightStoneGen(), 3);

        // Register fluids
        FluidRegistry.registerFluid(ModFluids.DIVINE_WATER);
        FluidRegistry.addBucketForFluid(ModFluids.DIVINE_WATER);
    }

    public void init(FMLInitializationEvent e) {}

    public void postInit(FMLPostInitializationEvent e) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockDarkCrystalOre());
        event.getRegistry().register(new BlockDivineWater());
        event.getRegistry().register(new BlockLightCrystalOre());

        Block darkStone = new ModBlock("darkstone", Material.ROCK);
        darkStone.setHardness(2);
        event.getRegistry().register(darkStone);

        Block lightStone = new ModTranslucentBlock("lightstone", Material.ROCK);
        lightStone.setHardness(1);
        lightStone.setLightLevel(0.2f);
        lightStone.setResistance(200);
        event.getRegistry().register(lightStone);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        // Alpha pearl
        ModItem alphaPearl = new ModItem("alphapearl");
        alphaPearl.setContainerItem(alphaPearl);
        event.getRegistry().register(alphaPearl);

        // Blocks
        event.getRegistry().register(new ItemBlock(ModBlocks.darkCrystalOre).setRegistryName(ModBlocks.darkCrystalOre.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.darkStone).setRegistryName(ModBlocks.darkStone.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.lightCrystalOre).setRegistryName(ModBlocks.lightCrystalOre.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.lightStone).setRegistryName(ModBlocks.lightStone.getRegistryName()));

        // Crystals
        event.getRegistry().register(new ModItem("darkcrystal"));
        event.getRegistry().register(new ModItem("legendarydarkcrystal"));
        event.getRegistry().register(new ModItem("lightcrystal"));
        event.getRegistry().register(new ModItem("mythicaldarkcrystal"));
        event.getRegistry().register(new ModItem("superiordarkcrystal"));

        // Misc
        event.getRegistry().register(new ModItem("darkstonerod"));
        event.getRegistry().register(new ItemDivineStone());
        event.getRegistry().register(new ModItem("reinforceddarkstonerod"));

        // Scrolls
        event.getRegistry().register(new ItemScrollOfBotanicMaturity());
        event.getRegistry().register(new ItemScrollOfIronTouch());
        event.getRegistry().register(new ItemScrollOfTransmootation());

        // Tools
//        event.getRegistry().register(new ItemDarkCrystalAxe("legendarydarkcrystalaxe", Materials.LEGENDARY_DARK_CRYSTAL));
//        event.getRegistry().register(new ItemDarkCrystalAxe("mythicaldarkcrystalaxe", Materials.MYTHICAL_DARK_CRYSTAL));
        event.getRegistry().register(new ItemDarkCrystalAxe("superiordarkcrystalaxe", Materials.SUPERIOR_DARK_CRYSTAL, 9, -2.8f));
        event.getRegistry().register(new ItemDarkCrystalPickaxe("superiordarkcrystalpickaxe", Materials.SUPERIOR_DARK_CRYSTAL));
        event.getRegistry().register(new ItemDarkCrystalShovel("superiordarkcrystalshovel", Materials.SUPERIOR_DARK_CRYSTAL));
        event.getRegistry().register(new ItemDarkCrystalSword("superiordarkcrystalsword", Materials.SUPERIOR_DARK_CRYSTAL));
        event.getRegistry().register(new ItemDarkCrystalSword("legendarydarkcrystalsword", Materials.LEGENDARY_DARK_CRYSTAL));
    }
}