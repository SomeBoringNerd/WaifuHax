package lol.waifuware.Modules.COMBAT;

import lol.waifuware.Events.OnTickEvent;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import meteordevelopment.orbit.EventHandler;

@Module(name = "AutoTotem", key = 0, cat = CATEGORY.COMBAT)
public class AutoTotem extends AbstractModule
{

    public AutoTotem()
    {
        super();
        Create();

        desc[0] = "Put a totem in your offhand if";
        desc[1] = "you have some in your inventory";
        isWorkInProgress = true;
    }

    @EventHandler
    public void OnTick(OnTickEvent e)
    {

    }
}
