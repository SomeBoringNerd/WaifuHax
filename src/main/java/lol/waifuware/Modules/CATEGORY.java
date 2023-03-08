package lol.waifuware.Modules;

public enum CATEGORY
{
    BOT("『Automation』"),
    CHAT("『Chat』"),
    COMBAT("『Combat』"),
    EXPLOIT("『Exploit』"),
    GUI("『GUI』"),
    MOVEMENT("『Movement』"),
    RENDER("『Render』");

    public String name;

    CATEGORY(String name)
    {
        this.name = name;
    }
}
