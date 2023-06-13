package lol.waifuware.Modules.AUTOMATION;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.IntSetting;
import lol.waifuware.Settings.NumberType;
import lol.waifuware.Settings.SettingDescription;
import lol.waifuware.Util.ChatUtil;
import lol.waifuware.Util.InventoryUtil;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

@Module(name = "AutoFrameDupe", key = 0, cat = CATEGORY.BOT)
public class AutoFrameDupe extends AbstractModule
{
    int state = 0;

    public IntSetting MaxFrame = new IntSetting("MaxFrame", 1, 8, 4, 1, "Maximum of frames that will be targeted", "mf", NumberType.INT);

    public IntSetting Hit = new IntSetting("Hit", 1, 20, 5, 1, "Number of hits per second", "h", NumberType.INT);

    public BooleanSetting AutoPlace = new BooleanSetting("AutoPlace", false, "Maximum of frames that can be hit per tick", "ap");

    ItemFrameEntity[] frames = new ItemFrameEntity[8];

    public AutoFrameDupe()
    {
        super();
        addSettings(MaxFrame, Hit, AutoPlace);
        Create();

        desc[0] = "Automatically perform the frame";
        desc[1] = "dupe for you if the server have";
        desc[2] = "the plugin installed.";

        isEnabled = false;
    }

    int tick = 0, current = 0;
    @EventHandler
    public void onTick(OnTickEvent e)
    {
        if(!isEnabled) return;

        if(frames[0] == null || !frames[0].isAlive())
        {
            getNearbyItemFrames();
        }
        assert MinecraftClient.getInstance().player != null;
        if(tick >= (int)(20 / Hit.getValueInt()))
        {
            if (frames[current] == null) current = 0;
            else if(frames[current].isAlive())
            {
                if (frames[current].getHeldItemStack().getItem() != Items.AIR)
                {
                    MinecraftClient.getInstance().player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(frames[current], false));
                } else {
                    MinecraftClient.getInstance().player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.interact(frames[current], false, Hand.MAIN_HAND));
                }
                current++;
                tick = 0;
            }else{
                Toggle();
                Toggle();
            }
        }

        tick++;
    }
    @Override
    public String getDisplayName() {
        return name + " §c[§r§4" + MaxFrame.getValueInt() + ", " + Hit.getValueInt() + ", " + (AutoPlace.getEnabled() ? "§2A" : "§4A") + "§c]";
    }


    private void getNearbyItemFrames()
    {
        frames = new ItemFrameEntity[8];
        if(AutoPlace.getEnabled())
        {
            PlayerEntity player = MinecraftClient.getInstance().player;
            if(player.getInventory().getMainHandStack() != Items.ITEM_FRAME.getDefaultStack())
            {
                int slot = InventoryUtil.getItem(Items.ITEM_FRAME.getDefaultStack());
                if (slot < 0) {
                    ChatUtil.SendMessage("There is no item frame in your inventory");
                    AutoPlace.Toggle();
                    return;
                }
            }
        }
        else
        {
            int i = 0;
            for (Entity e : MinecraftClient.getInstance().world.getEntities())
            {
                if(e instanceof ItemFrameEntity && e.distanceTo(MinecraftClient.getInstance().player) <= 4)
                {
                    ItemFrameEntity entity = (ItemFrameEntity) e;

                    if(entity.getHeldItemStack().getItem() == Items.AIR && i <= MaxFrame.getValueInt())
                    {
                        frames[i] = entity;
                        i++;
                    }
                }

            }

            ChatUtil.SendMessage(("Found " + i + " item frames"));
        }
    }

    @Override
    public void onDisable()
    {
        frames = new ItemFrameEntity[8];
    }
}
