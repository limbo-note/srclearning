package selfmade.my.springv1.annotation;

public @interface MyRequestMapping {

	public String path() default "";
}
