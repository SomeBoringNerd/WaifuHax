package lol.waifuware.Settings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface SettingDescription
{
    String getDescription();
}
