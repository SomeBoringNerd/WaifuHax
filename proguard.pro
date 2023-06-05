# Add your custom ProGuard configuration rules here

# Keep the entry point class
-keep public class lol.waifuware.waifuhax.Waifuhax {
    public void onInitialize();
}

# Obfuscate class names (optional)
-obfuscate

# Keep the names of all fields that are used by the entry point class
-keepclassmembers class lol.waifuware.waifuhax.Waifuhax {
    public *;
}