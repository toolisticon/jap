package io.toolisticon.jigsawbyap.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.PACKAGE})
public @interface JigsawModule {

    String value();

}
