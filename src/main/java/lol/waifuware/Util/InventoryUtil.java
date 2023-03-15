package lol.waifuware.Util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil
{
    public static int getItem(ItemStack target)
    {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if(player.getInventory().contains(target))
        {
            return player.getInventory().indexOf(target);
        }
        else
        {
            return -1;
        }
    }
}
