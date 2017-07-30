package qhd.zy.shop.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
//必须要啊
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateForm {
	public ValidateType type();
	public String errorMsg();
	public String value() default  "";
}
