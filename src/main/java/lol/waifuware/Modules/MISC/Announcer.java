package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnPlayerConnect;
import lol.waifuware.Events.OnPlayerDisconnect;
import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Util.ChatUtil;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Module(name = "Announcer", key = 0, cat = CATEGORY.MISC)
public class Announcer extends AbstractModule
{
    private final List<PlayerEntity> entityList = new ArrayList<>();
    private final List<PlayerEntity> oldPlayerList = new ArrayList<>();

    public Announcer() {
        super();
        Create();
        isWorkInProgress = true;
    }


    @EventHandler
    private void onTick(OnTickEvent e) {
        if (MinecraftClient.getInstance().world == null || MinecraftClient.getInstance().player == null)
            return;

        entityList.clear();

        for (Entity entity : MinecraftClient.getInstance().world.getEntities())
        {
            if(entity != null) {
                EntityType<?> type = entity.getType();
                if (type == EntityType.PLAYER) {
                    PlayerEntity player = (PlayerEntity) entity;
                    if (!Objects.equals(player.getEntityName(), MinecraftClient.getInstance().player.getEntityName())) {
                        entityList.add(player);
                    }
                }
            }
        }

        for (PlayerEntity player : entityList) {
            if (!oldPlayerList.contains(player)) {
                ChatUtil.SendMessage("§8" + player.getEntityName() + " §7entered visual range");
            }
        }

        for (PlayerEntity player : oldPlayerList)
        {
            if (!entityList.contains(player))
            {
                boolean found = false;
                for(PlayerListEntry player2 : MinecraftClient.getInstance().getNetworkHandler().getPlayerList())
                {
                    if(player2.getProfile().getName().equals(player.getEntityName()))
                    {
                        ChatUtil.SendMessage("§8" + player.getEntityName() + " §7exited visual range");
                        found = true;
                    }
                }

                if(!found)
                {
                    ChatUtil.SendMessage("§8" + player.getEntityName() + " §7disconnected at §2X:" + (int)player.getPos().x + " Y:" + (int)player.getPos().y + " Z:" + (int)player.getPos().z);
                }
            }
        }

        oldPlayerList.clear();
        oldPlayerList.addAll(entityList);
    }



    /*@EventHandler
    private void onPlayerDisconnect(OnPlayerDisconnect event)
    {
        boolean found = false;
        for (PlayerEntity player : oldPlayerList)
        {
            if(player.getEntityName().equals(event.getPlayer()))
            {
                found = true;
            }
        }
        if(!found){
            ChatUtil.SendMessage("§7" + event.getPlayer() + " §7has disconnected");
        }
    }

    @EventHandler
    private void onPlayerConnect(OnPlayerConnect event)
    {
        ChatUtil.SendMessage("§7" + event.getPlayer() + " §7logged in");
    }*/

}