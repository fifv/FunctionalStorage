package com.buuz135.functionalstorage.recipe;


import com.buuz135.functionalstorage.block.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

public class FramedDrawerRecipe extends CustomRecipe {

    public static RecipeSerializer<FramedDrawerRecipe> SERIALIZER = new SimpleRecipeSerializer<>(FramedDrawerRecipe::new);

    public FramedDrawerRecipe(ResourceLocation idIn) {
        super(idIn);
    }


    public static boolean matches(ItemStack first, ItemStack second, ItemStack drawer) {
        //System.out.println(((BlockItem) drawer.getItem()).getBlock().getClass());
        return !first.isEmpty() && first.getItem() instanceof BlockItem && !second.isEmpty() && second.getItem() instanceof BlockItem && !drawer.isEmpty() && drawer.getItem() instanceof BlockItem && ((BlockItem) drawer.getItem()).getBlock() instanceof FramedDrawerBlock;
    }

    public static boolean matchesCompacting(ItemStack first, ItemStack second, ItemStack drawer) {
        //System.out.println(((BlockItem) drawer.getItem()).getBlock().getClass());
        return !first.isEmpty() && first.getItem() instanceof BlockItem && !second.isEmpty() && second.getItem() instanceof BlockItem && !drawer.isEmpty() && drawer.getItem() instanceof BlockItem && ((BlockItem) drawer.getItem()).getBlock() instanceof CompactingFramedDrawerBlock;
    }

    public static boolean matchesController(ItemStack first, ItemStack second, ItemStack drawer) {
        return !first.isEmpty() && first.getItem() instanceof BlockItem && !second.isEmpty() && second.getItem() instanceof BlockItem && !drawer.isEmpty() && drawer.getItem() instanceof BlockItem && ((BlockItem) drawer.getItem()).getBlock() instanceof FramedDrawerControllerBlock;
    }

    public static boolean matchesControllerExtension(ItemStack first, ItemStack second, ItemStack drawer) {
        return !first.isEmpty() && first.getItem() instanceof BlockItem && !second.isEmpty() && second.getItem() instanceof BlockItem && !drawer.isEmpty() && drawer.getItem() instanceof BlockItem && ((BlockItem) drawer.getItem()).getBlock() instanceof FramedControllerExtensionBlock;
    }

    public static boolean matchesSimpleCompacting(ItemStack first, ItemStack second, ItemStack drawer) {
        //System.out.println(((BlockItem) drawer.getItem()).getBlock().getClass());
        return !first.isEmpty() && first.getItem() instanceof BlockItem && !second.isEmpty() && second.getItem() instanceof BlockItem && !drawer.isEmpty() && drawer.getItem() instanceof BlockItem && ((BlockItem) drawer.getItem()).getBlock() instanceof FramedSimpleCompactingDrawerBlock;
    }

    @Override
    public boolean matches(CraftingContainer inv, Level worldIn) {
        return matches(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesCompacting(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesSimpleCompacting(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesController(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesControllerExtension(inv.getItem(0), inv.getItem(1), inv.getItem(2));
    }

    @Override
    public ItemStack assemble(CraftingContainer inv) {
        if (matches(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesCompacting(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesSimpleCompacting(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesController(inv.getItem(0), inv.getItem(1), inv.getItem(2)) ||
                matchesControllerExtension(inv.getItem(0), inv.getItem(1), inv.getItem(2)))
        {
            return FramedDrawerBlock.fill(inv.getItem(0), inv.getItem(1), inv.getItem(2).copy());
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
