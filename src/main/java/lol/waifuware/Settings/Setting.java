package lol.waifuware.Settings;

import lol.waifuware.Modules.Interfaces.Module;

public class Setting
{
    public String name;
    private boolean visible;

    public String Description;

    public Setting()
    {

        SettingDescription description = this.getClass().getAnnotation(SettingDescription.class);
        visible = true;
        if(description != null) {
            Description = description.name();
        }else{
            Description = "No description provided";
        }
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
