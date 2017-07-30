package qhd.zy.shop.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShopDi {
	String abd() default "";
	String value() default"";
}
