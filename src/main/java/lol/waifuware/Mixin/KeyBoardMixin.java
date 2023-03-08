package lol.waifuware.Mixin;

import lol.waifuware.Modules.GUI.ClickGUI;
import lol.waifuware.Modules.ModuleManager;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyBoardMixin
{

    int prevKey;
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci)
    {
        if (key != GLFW.GLFW_KEY_UNKNOWN)
        {
            assert ClickGUI.getInstance() != null;
            if(MinecraftClient.getInstance().currentScreen == null && action == GLFW.GLFW_PRESS && key == ClickGUI.getInstance().key)
            {
                MinecraftClient.getInstance().setScreen(lol.waifuware.ClickGUI.ClickGUI.instance);
            }
            else if (MinecraftClient.getInstance().currentScreen == null && action == GLFW.GLFW_PRESS)
            {
                prevKey = key;
                ModuleManager.onKeyPressed(key);
            }
        }
    }
}
