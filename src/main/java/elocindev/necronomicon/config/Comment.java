package elocindev.necronomicon.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(Comments.class)
/**
 * Adds a comment above a field. Requires getFile() to return a json5 file for readability purposes.
 * The file will still get parsed the same way no matter the extension, but most text editors / IDEs won't support comments with json, so use json5 or jsonc.
 * 
 * You can repeat this annotation to add multiple comments to a field.
 *
 * @since 1.4.0
 * @author ElocinDev
 */
public @interface Comment {
    String value();
}
