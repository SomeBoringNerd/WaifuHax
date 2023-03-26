package lol.waifuware.Settings;

import lol.waifuware.Util.ChatUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModeSetting extends Setting
{
    private String mode;
    private List<String> modes;

    private int index;

    public ModeSetting(String name, String defaultValue, String... modes)
    {
        this.name = name;

        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultValue);
    }

    public String getMode()
    {
        return mode;
    }

    public List<String> getModes(){
        return modes;
    }

    public int getIndex()
    {
        return index;
    }

    public void setMode(String mode){
        if(modes.contains(mode))
        {
            this.mode = mode;
            this.index = modes.indexOf(mode);
        }else{
            ChatUtil.SendMessage("This mode do not exist for this setting");
        }
    }

    public void setIndex(int index){
        if(modes.size() - 1 > index)
        {
            this.index = index;
            this.mode = modes.get(index);
        }else{
            ChatUtil.SendMessage("This mode do not exist for this setting");
        }
    }

    public void cycle(){
        if(index < modes.size())
        {
            index++;
            mode = modes.get(index);
        }else{
            index = 0;
        }
    }

    public boolean isMode(String mode)
    {
        return Objects.equals(this.mode, mode);
    }
}
