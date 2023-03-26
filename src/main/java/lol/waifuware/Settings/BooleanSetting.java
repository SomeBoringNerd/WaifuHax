package lol.waifuware.Settings;

public class BooleanSetting extends Setting
{
    private boolean enabled;
    public BooleanSetting(String name, boolean defaultValue)
    {
        this.name = name;
        this.enabled = defaultValue;
    }

    public void Toggle(){
        this.enabled = !this.enabled;
    }

    public void setEnabled(boolean newEnabled)
    {
        enabled = newEnabled;
    }
    public boolean getEnabled()
    {
        return enabled;
    }
}
