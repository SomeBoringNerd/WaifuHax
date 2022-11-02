package lol.waifuware.waifuhax.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
abstract class MultiplayerScreenMixin extends Screen
{
    private final Screen _parent;

    protected MultiplayerScreenMixin(Text title, Screen parent)
    {
        super(title);
        this._parent = parent;
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci)
    {
        addDrawableChild(new ButtonWidget(175 + 3, 3, 175, 20, Text.literal("Find IP of a server"), button -> {

        }));
    }
}
