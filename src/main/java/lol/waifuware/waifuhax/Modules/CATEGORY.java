package lol.waifuware.waifuhax.Modules;

public enum CATEGORY
{
    BOT("『Automation』"),
    CHAT("『Chat』"),
    COMBAT("『Combat』"),
    EXPLOIT("『Exploit』"),

    GUI("『HUD』"),
    MOVEMENT("『Movement』"),

    RENDER("『Render』");

    public String name;

    CATEGORY(String name)
    {
        this.name = name;
    }
}
