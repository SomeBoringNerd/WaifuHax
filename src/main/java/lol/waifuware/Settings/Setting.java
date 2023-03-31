package lol.waifuware.Settings;

import lol.waifuware.Modules.Interfaces.Module;

public class Setting
{
    public String name;
    private boolean visible;

    public String Description;

    public Setting(String description)
    {
        visible = true;

        Description = description;
    }

    public String getName()
    {
        return name;
    }

    public boolean getVisible()
    {
        return visible;
    }

    public void setVisible(boolean vis)
    {
        visible = vis;
    }
}
