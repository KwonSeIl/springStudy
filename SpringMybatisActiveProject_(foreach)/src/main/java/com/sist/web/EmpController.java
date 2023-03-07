package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.*;
// Model => 반드시 (@Controller, @RestController) 사용해야 함. repository는 component 써도 무관
@Controller
public class EmpController {
	@Autowired
	private EmpDAO dao;
	// => _ok.do : post.<form method=post>, ajax=type:post / 나머지는 getMapping이라고 생각.
	@GetMapping("emp/list.do")
	public String emp_list(Model model)
	{
		List<String> list=dao.empNameListData();
		model.addAttribute("list", list);
		return "emp/list";
	}
	@PostMapping("emp/info.do")
	public String emp_info(Model model,String[] names)
	{
		Map map=new HashMap();
		map.put("names", names);
		List<EmpVO> list=dao.empInfoData(map);
		model.addAttribute("list", list);
		return "emp/info";
	}
}
