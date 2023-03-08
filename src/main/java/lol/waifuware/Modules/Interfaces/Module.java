package lol.waifuware.Modules.Interfaces;

import lol.waifuware.Modules.CATEGORY;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Module
{
    String name();
    int key();
    CATEGORY cat();
}
