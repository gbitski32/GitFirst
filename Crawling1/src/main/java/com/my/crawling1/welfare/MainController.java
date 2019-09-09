package com.my.crawling1.welfare;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	
	@RequestMapping(value="/welfare/main")
	public String goMain() {
		
		return "welfare/main";
	}
}
