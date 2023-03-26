package lol.waifuware.Modules;

public enum CATEGORY
{
    BOT("『Automation』"),
    CHAT("『Chat』"),
    COMBAT("『Combat』"),
    EXPLOIT("『Exploit』"),
    GUI("『GUI』"),
    MOVEMENT("『Movement』"),
    MISC("『Miscellaneous』"),
    RENDER("『Render』");

    public String name;

    CATEGORY(String name)
    {
        this.name = name;
    }
}
