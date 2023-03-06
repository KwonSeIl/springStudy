package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("main/main.do")
	public String main_main()
	{
		return "main"; //Tiles에 등록되어 있음
	}
	@GetMapping("chat/chat.do")
	public String chat_chat()
	{
		return "site/chat/chat"; //ViewResolver로 인식
	}
}
