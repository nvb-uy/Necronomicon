package elocindev.necronomicon.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/**
 * This annotation is used to mark fields in the config class that should be
 * registered as the instance.
 * 
 * @see NecConfigAPI
 * 
 * @since 1.0.5
 * @author ElocinDev
 */
public @interface NecConfig {}