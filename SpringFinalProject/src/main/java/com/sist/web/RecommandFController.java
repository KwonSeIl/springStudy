package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //화면 이동
public class RecommandFController {	
	@GetMapping("recommand/recommand.do")
	public String food_recommand()
	{
		return "recommand/recommand";
	}
}
