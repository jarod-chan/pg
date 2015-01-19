package cn.fyg.pg.interfaces;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloCtl {

	private static final String PATH = "";
	private interface Page {
		String HELLO = PATH + "hello";
	}
	

	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String toHello(Map<String,Object> map){
		return Page.HELLO;
	}

}
