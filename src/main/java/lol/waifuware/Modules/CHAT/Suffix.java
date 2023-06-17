package lol.waifuware.Modules.CHAT;

import it.unimi.dsi.fastutil.chars.Char2CharMap;
import it.unimi.dsi.fastutil.chars.Char2CharOpenHashMap;
import lol.waifuware.Commands.MISC.Pronoun;
import lol.waifuware.Events.OnMessageSend;
import lol.waifuware.Modules.CATEGORY;
import lol.waifuware.Modules.AbstractModule;
import lol.waifuware.Modules.Interfaces.Module;
import lol.waifuware.Settings.BooleanSetting;
import lol.waifuware.Settings.ModeSetting;
import meteordevelopment.orbit.EventHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Module(name = "Suffix", key = 0, cat = CATEGORY.CHAT)
public class Suffix extends AbstractModule
{
    String[] normal = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".split("");
    String[] full_width = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ１２３４５６７８９".split("");

    String[] small = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ123456789".split("");

    String[] brackets = "⒜⒝⒞⒟⒠⒡⒢⒣⒤⒥⒦⒧⒨⒩⒪⒫⒬⒭⒮⒯⒰⒱⒲⒳⒴⒵⒜⒝⒞⒟⒠⒡⒢⒣⒤⒥⒦⒧⒨⒩⒪⒫⒬⒭⒮⒯⒰⒱⒲⒳⒴⒵⑴⑵⑶⑷⑸⑹⑺⑻⑼".split("");

    String[] emoji = "☯❤❣⚜☣⁈⁉✪✰✯✭∞♾⌀♪♩♫♬⏻⚝⚧♂♀㊗㊙☮❀✿❁✵❃✾✼❉✷❋✺✹✸✴✳✶☆⯪⯫☽☀⭐★☘❄⚔⛏⚗✂⚓✎✏✒☂☔⌛⏳⌚⚐☕☎⌨✉⌂⚒⚙⚖⚰⚱✈✁✃✄♚♔".split("");
    public ModeSetting message = new ModeSetting("Message", "client_name", "stuff that get added at the end of the message", "m", "client_name", "pronouns", "love");
    public ModeSetting type = new ModeSetting("Font", "small", "look of the suffix", "m", "normal", "full width", "small", "brackets");

    public BooleanSetting AntiAntiSpam = new BooleanSetting("Anti Anti-Spam", true, "a nice and cozy anti anti spam", "aas");

    public Suffix()
    {
        super();
        Toggle(true);
        addSettings(message, type, AntiAntiSpam);

        Create();

        desc[0] = "Add some fancy text after your messages";
    }

    @EventHandler
    public void OnSendMessage(OnMessageSend event)
    {
        int rng = new Random().nextInt(emoji.length);
        String prefix = "";
        if (message.getIndex() == 0) {
            prefix = event.getMessage() + fancy(" ➣ WaifuHax V2");
        } else if (message.getIndex() == 1) {
            prefix = event.getMessage() + " ➣ " + fancy(Pronoun.self_pronoun);
        }else{
            prefix = event.getMessage();
        }

        event.setMessage(AntiAntiSpam.getEnabled() ? prefix + " " + emoji[rng] : prefix);
    }

    @Override
    public String getDisplayName()
    {
        return name + " §c[§r§4" + message.getModeAtIndex() + "§c]";
    }

    private String fancy(String input)
    {
        Char2CharMap fancy_char_array = new Char2CharOpenHashMap();
        switch (type.getIndex()) {
            default -> {
                return input;
            }
            case 1 -> input = replaceChars(input, normal, full_width);
            case 2 -> input = replaceChars(input, normal, small);
            case 3 -> input = replaceChars(input, normal, brackets);
        }

        return input;
    }



    private String replaceChars(String input, String[] normal, String[] replacement) {
        Map<Character, Character> fancy_char_array = new HashMap<>();
        for (int i = 0; i < normal.length; i++) {
            fancy_char_array.put(normal[i].charAt(0), replacement[i].charAt(0));
        }

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
