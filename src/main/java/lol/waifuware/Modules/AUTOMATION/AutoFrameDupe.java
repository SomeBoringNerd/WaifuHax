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
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.PickFromInventoryC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import java.util.Objects;

@Module(name = "AutoFrameDupe", key = 0, cat = CATEGORY.BOT)
public class AutoFrameDupe extends AbstractModule
{
    int state = 0;

    public IntSetting MaxFrame = new IntSetting("MaxFrame", 1, 24, 4, 1, "Maximum of frames that will be targeted", "mf", NumberType.INT);

    public IntSetting Hit = new IntSetting("Hit", 1, 20, 5, 1, "Number of hits per second", "h", NumberType.INT);

    public BooleanSetting NoDelay = new BooleanSetting("NoDelay", false, "Interact with every frame instead of only one", "nd");
    public BooleanSetting AutoRefill = new BooleanSetting("AutoRefill", false, "Weird shit that somehow work", "nd");

    ItemFrameEntity[] frames = new ItemFrameEntity[(int) MaxFrame.getMax()];
    boolean[] marked = new boolean[(int) MaxFrame.getMax()];

    public AutoFrameDupe()
    {
        super();
        addSettings(MaxFrame, Hit, NoDelay, AutoRefill);
        Create();

        desc[0] = "Automatically perform the frame";
        desc[1] = "dupe for you if the server have";
        desc[2] = "the plugin installed.";

        isEnabled = false;
    }
    String hold;
    int tick = 0, current = 0, ActualTick = 0;
    @EventHandler
    public void onTick(OnTickEvent e)
    {
        if(!isEnabled) return;

        if(frames[0] == null || !frames[0].isAlive())
        {
            getNearbyItemFrames();
        }
        assert MinecraftClient.getInstance().player != null;
        if(NoDelay.getEnabled())
        {
            int i = 0;
            if(tick >= (int)(20 / Hit.getValueInt()))
            {
                for (ItemFrameEntity entity : frames)
                {
                    if (entity == null) return;
                    else if (entity.isAlive())
                    {
                        if (entity.getHeldItemStack().getItem() != Items.AIR && !marked[i])
                        {
                            MinecraftClient.getInstance().player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(entity, false));

                            marked[i] = true;
                        }
                        if (entity.getHeldItemStack().getItem() == Items.AIR)
                        {
                            MinecraftClient.getInstance().player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.interact(entity, false, Hand.MAIN_HAND));
                            marked[i] = false;
                        }
                        tick = 0;
                        i++;
                    }
                }
            }
        }
        else
        {
            if(tick >= (int)(20 / Hit.getValueInt()))
            {
                if (frames[current] == null) current = 0;
                else if(frames[current].isAlive())
                {
                    if(!marked[current] && frames[current].getHeldItemStack().getItem() != Items.AIR)
                    {
                        MinecraftClient.getInstance().player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(frames[current], false));

                        marked[current] = true;
                    }

                    if (frames[current].getHeldItemStack().getItem() == Items.AIR)
                    {
                        MinecraftClient.getInstance().player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.interact(frames[current], false, Hand.MAIN_HAND));
                        marked[current] = false;
                    }

                    current++;
                    tick = 0;
                }else{
                    Toggle();
                    Toggle();
                }
            }
        }
        if(ActualTick >= 5 ) {
            Refill();
            ActualTick = 0;
        }
        ActualTick++;
        tick++;
    }
    @Override
    public String getDisplayName() {
        return name + " §c[§r§4" + MaxFrame.getValueInt() + ", " + Hit.getValueInt() + ", " + (NoDelay.getEnabled() ? "§2ND" : "§4ND") + "§c]";
    }
    int hotbar = -1;
    void Refill()
    {
        if(!AutoRefill.getEnabled()) return;
        if(hotbar == -1)
        {
            hotbar = MinecraftClient.getInstance().player.getInventory().selectedSlot;
        }else{
            MinecraftClient.getInstance().getNetworkHandler().sendPacket(new UpdateSelectedSlotC2SPacket(hotbar));
        }
        int e = 0;
        for(int j = 0; j < MinecraftClient.getInstance().player.getInventory().size(); j++)
        {
            MinecraftClient.getInstance().player.getInventory();
            if(PlayerInventory.isValidHotbarIndex(j))
            {
                ChatUtil.Log(PlayerInventory.isValidHotbarIndex(j) + " " + j);
                if (MinecraftClient.getInstance().player.getInventory().getStack(j).getItem() != Items.AIR) {
                    //ChatUtil.SendMessage(MinecraftClient.getInstance().player.getInventory().getStack(j).getItem().getDefaultStack().getName().getString() + " at " + j);
                    //ChatUtil.SendMessage(Objects.equals(MinecraftClient.getInstance().player.getInventory().getStack(j).getItem().getDefaultStack().getName().getString(), hold) + "");

                    if (Objects.equals(MinecraftClient.getInstance().player.getInventory().getStack(j).getItem().getDefaultStack().getName().getString(), hold)) {
                        if (MinecraftClient.getInstance().player.getInventory().selectedSlot != j) {
                            //ChatUtil.SendMessage("found item " + MinecraftClient.getInstance().player.getInventory().getStack(j).getItem().getDefaultStack().getName().getString() + " at hotbar slot " + j);
                            MinecraftClient.getInstance().player.getInventory().swapSlotWithHotbar(j);
                            e = j;
                        }
                    }
                }
                if(MinecraftClient.getInstance().player.getInventory().getStack(j).getItem() == Items.AIR || MinecraftClient.getInstance().player.getInventory().getStack(j).getItem().getDefaultStack().getName().getString() == hold) {
                    MinecraftClient.getInstance().getNetworkHandler().sendPacket(new PickFromInventoryC2SPacket(j));
                }
            }
        }
    }


    private void getNearbyItemFrames()
    {
        frames = new ItemFrameEntity[(int) MaxFrame.getMax()];
        int i = 0;
        for (Entity e : MinecraftClient.getInstance().world.getEntities())
        {
            if(e instanceof ItemFrameEntity && e.distanceTo(MinecraftClient.getInstance().player) <= 4)
            {
                ItemFrameEntity entity = (ItemFrameEntity) e;

                if(i < MaxFrame.getValueInt())
                {
                    frames[i] = entity;
                    marked[i] = false;
                    i++;
                }
            }

        }
        if(i == 0)
        {
            ChatUtil.SendMessage("Couldn't find item frames in range");
            Toggle(false);
        }
        else
        {
            ChatUtil.SendMessage(("Found " + i + " item frames"));
        }
    }

    @Override
    public void onDisable()
    {
        frames = new ItemFrameEntity[(int) MaxFrame.getMax()];
        hotbar = -1;
    }

    @Override
    public void onEnable()
    {
        if(MinecraftClient.getInstance().player == null) return;
        hold = MinecraftClient.getInstance().player.getMainHandStack().getItem().getDefaultStack().getName().getString();
        ChatUtil.SendMessage("Duping " + hold);
    }
}
