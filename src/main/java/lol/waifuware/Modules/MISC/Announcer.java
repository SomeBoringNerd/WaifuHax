package lol.waifuware.Modules.MISC;

import lol.waifuware.Events.OnPlayerConnect;
import lol.waifuware.Events.OnPlayerDisconnect;
import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Util.ChatUtil;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;
import java.util.stream.Collectors;

@Module(name = "Announcer", key = 0, cat = CATEGORY.MISC)
public class Announcer extends AbstractModule
{


    public BooleanSetting RenderDistance = new BooleanSetting("Player render", true, "Send you a message when a player enter your render distance", "-pr");
    public BooleanSetting Welcomer = new BooleanSetting("Welcomer", true, "send a message in chat when a player join or leave", "-w");
    String username;
    public Announcer()
    {
        super();
        addSettings(RenderDistance, Welcomer);
        Create();
        isWorkInProgress = true;
    }


    @EventHandler
    private void OnPlayerConnect(OnPlayerConnect e)
    {
        if(!Welcomer.getEnabled()) return;
        if(MinecraftClient.getInstance().getNetworkHandler() == null) return;
        MinecraftClient.getInstance().getNetworkHandler().sendChatMessage("Welcome, " + e.getPlayer());
    }

    @EventHandler
    private void OnPlayerLeave(OnPlayerDisconnect e)
    {
        if(!Welcomer.getEnabled()) return;
        if(MinecraftClient.getInstance().getNetworkHandler() == null) return;
        MinecraftClient.getInstance().getNetworkHandler().sendChatMessage("Goodbye, " + e.getPlayer());
    }

    private final Set<PlayerEntity> entityList = new HashSet<>();
    private final Set<PlayerEntity> oldPlayerList = new HashSet<>();
    private final Map<String, PlayerEntity> playerMap = new HashMap<>();

    @EventHandler
    private void onTick(OnTickEvent e)
    {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null || client.player == null)
                return;

            if (username == null) {
                username = client.player.getEntityName();
            }

            if (!RenderDistance.getEnabled())
                return;

            entityList.clear();

            for (Entity entity : client.world.getEntities()) {
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    entityList.add(player);
                }
            }

            for (PlayerEntity player : entityList)
            {
                if (!oldPlayerList.contains(player) && !Objects.equals(username, player.getEntityName()))
                {
                    ChatUtil.SendMessage("§8" + player.getEntityName() + " §7entered visual range");
                }
            }

            for (PlayerEntity player : oldPlayerList) {
                if (!entityList.contains(player)) {
                    boolean found = false;
                    for (PlayerListEntry player2 : client.getNetworkHandler().getPlayerList())
                    {
                        if (Objects.equals(player2.getProfile().getName(), player.getEntityName()) && !Objects.equals(username, player2.getProfile().getName()))
                        {
                            ChatUtil.SendMessage("§8" + player.getEntityName() + " §7exited visual range");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        Vec3d pos = player.getPos();
                        assert MinecraftClient.getInstance().player != null;
                        if(player.getEntityName() != MinecraftClient.getInstance().player.getEntityName()) {
                            ChatUtil.SendMessage("§8" + player.getEntityName() + " §7disconnected at §2X:" + (int) pos.x + " Y:" + (int) pos.y + " Z:" + (int) pos.z);
                        }
                    }
                }
            }

            oldPlayerList.clear();
            oldPlayerList.addAll(entityList);
        }catch (Exception ignored){}
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
