package lol.waifuware.Modules.CHAT;

import it.unimi.dsi.fastutil.chars.Char2CharMap;
import it.unimi.dsi.fastutil.chars.Char2CharOpenHashMap;
import lol.waifuware.Commands.MISC.Pronoun;
import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.ModeSetting;
import meteordevelopment.orbit.EventHandler;

@Module(name = "Suffix", key = 0, cat = CATEGORY.CHAT)
public class Suffix extends AbstractModule
{
    private final Char2CharMap fancy_char_array = new Char2CharOpenHashMap();
    public ModeSetting message = new ModeSetting("Message", "client_name", "stuff that get added at the end of the message", "m", "client_name", "pronouns", "love");

    public Suffix()
    {
        super();

        addSetting(message);

        Create();

        String[] a = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".split("");
        String[] b = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ１２３４５６７８９".split("");
        for (int i = 0; i < a.length; i++) fancy_char_array.put(a[i].charAt(0), b[i].charAt(0));

        desc[0] = "Add some fancy text after your messages";
    }

    @EventHandler
    public void OnSendMessage(OnMessageSend event)
    {
        switch(message.getIndex())
        {
            case 0:
                event.setMessage((event.getMessage() + " | " + fancy("WaifuHax V2")));
                break;
            case 1:
                event.setMessage((event.getMessage() + " | (" + fancy(Pronoun.self_pronoun)) + ")");
                break;
        }
    }

    @Override
    public String getDisplayName()
    {
        return name + " §c[§r§4" + message.getModeAtIndex() + "§c]";
    }

    private String fancy(String input)
    {
        for (char ch : input.toCharArray())
        {
            if(fancy_char_array.containsKey(ch))
            {
                input = input.replace(ch, fancy_char_array.get(ch));
            }
        }

        return input;
    }
}
