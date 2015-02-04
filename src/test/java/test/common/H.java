package test.common;

import org.apache.commons.lang.builder.ToStringBuilder;

public class H {
	
	public static void p(Object obj){
		System.out.println(ToStringBuilder.reflectionToString(obj));
	}
	
	public static void ptl(Object obj){
		System.out.println(obj);
	}

}
