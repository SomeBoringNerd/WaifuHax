package lol.waifuware.Commands.MISC;

import lol.waifuware.Commands.Interfaces.AbstractCommand;
import lol.waifuware.Commands.Interfaces.BadCommandException;
import lol.waifuware.Commands.Interfaces.Command;
import lol.waifuware.Util.ChatUtil;
import net.minecraft.block.AirBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import java.util.Map;

@Command(name = "player", usage = "-player <username>", description = "give some infos about a player")
public class Player extends AbstractCommand
{
    @Override
    public void Execute(String[] command) throws BadCommandException
    {
        if(command.length == 0)
        {
            throw new BadCommandException(getUsage());
        }else
        {
            for(PlayerListEntry player : MinecraftClient.getInstance().getNetworkHandler().getPlayerList())
            {
                if(player.getProfile().getName().toString().toLowerCase().equals(command[1].toLowerCase().trim()))
                {
                    ChatUtil.SendMessage("Infos about " + command[1] + " :");
                    ChatUtil.ClearLine();
                    ChatUtil.SendMessage("UUID : " + player.getProfile().getId());
                    ChatUtil.SendMessage("Ping : " + player.getLatency());
                    ChatUtil.SendMessage("Gamemode : " + player.getGameMode().toString());
                    ChatUtil.SendMessage("has cape : " + player.hasCape());
                    ChatUtil.SendMessage("has skin : " + player.hasSkinTexture());
                    ChatUtil.SendMessage("Skin URL : " + player.getSkinTexture().getPath());
                    for(PlayerEntity players : MinecraftClient.getInstance().world.getPlayers())
                    {
                        if(players.getEntityName().equals(player.getProfile().getName()))
                        {
                            ChatUtil.SendMessage("is OP : " + players.isCreativeLevelTwoOp());
                            ChatUtil.SendMessage("HP : " + players.getHealth());
                            ChatUtil.SendMessage("Coordinates : §2X:" + (int)players.getPos().x + " Y:" + (int)players.getPos().y + " Z:" + (int)players.getPos().z);

                            players.getStackInHand(Hand.MAIN_HAND).getName().getString();
                            ChatUtil.SendMessage("Held item info");
                            if(players.getActiveItem().hasCustomName()) {
                                ChatUtil.SendMessage(Text.translatable(players.getActiveItem().getTranslationKey()).getString() + " named " + players.getActiveItem().getName().getString());
                            }else{
                                ChatUtil.SendMessage(players.getActiveItem().getName().getString());
                            }
                            ChatUtil.SendMessage("Durability : " + players.getActiveItem().getDamage() + " / " + players.getActiveItem().getMaxDamage());
                            if(!players.getActiveItem().getEnchantments().isEmpty())
                            {
                                ChatUtil.SendMessage("with enchants : ");
                                for(Map.Entry<Enchantment, Integer> m : EnchantmentHelper.get(players.getActiveItem()).entrySet())
                                {
                                    ChatUtil.SendMessage(m.getKey().getName(m.getValue()).getString());
                                }
                            }
                            ChatUtil.ClearLine();

                            ChatUtil.SendMessage("Equipped items : ");
                            for(ItemStack i : players.getArmorItems())
                            {
                                if(i.hasCustomName()) {
                                    ChatUtil.SendMessage(Text.translatable(i.getTranslationKey()).getString() + " named " + i.getName().getString());
                                }else{
                                    ChatUtil.SendMessage(i.getName().getString());
                                }
                                ChatUtil.SendMessage("Durability : " + (i.getMaxDamage() - i.getDamage()) + " / " + i.getMaxDamage());
                                if(!i.getEnchantments().isEmpty())
                                {
                                    ChatUtil.SendMessage("with enchants : ");
                                    for(Map.Entry<Enchantment, Integer> m : EnchantmentHelper.get(i).entrySet())
                                    {
                                        ChatUtil.SendMessage(m.getKey().getName(m.getValue()).getString());
                                    }
                                }
                                ChatUtil.ClearLine();
                            }
                        }
                    }
                }
            }
        }
    }
}
