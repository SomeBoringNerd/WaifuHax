package lol.waifuware.Settings;

public class IntSetting extends Setting
{
    private double min, max, increment, defaultValue, currentValue;
    public IntSetting(String name, double min, double max, double defaultValue, double increment, String description)
    {
        super(description);
        this.name = name;
        this.min = min;
        this.max = max;

        this.defaultValue = defaultValue;
        this.currentValue = defaultValue;
        this.increment = increment;
    }

    public static double clamp(double value, double min, double max)
    {
        value = Math.max(min, value);
        value = Math.min(max, value);
        return value;
    }

    public double getValue(){
        return currentValue;
    }

    public int getValueInt(){
        return (int)currentValue;
    }

    public float getValueFloat(){
        return (float)currentValue;
    }

    public double getIncrement(){
        return increment;
    }

    public double getMin(){
        return min;
    }

    public double getMax(){
        return max;
    }

    public void setValue(double value){
        value = clamp(value, this.min, this.max);
        this.currentValue = value;
    }

    public void increment(boolean positive)
    {
        setValue(getValue() + (positive ? getIncrement() : - getIncrement()));
    }
}
