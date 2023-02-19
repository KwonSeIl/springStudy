package com.sist.myapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.config.GoodsConfig;
import com.sist.service.GoodsService;
import com.sist.vo.CategoryVO;
import com.sist.vo.GoodsVO;
@Component
public class MainClass {
	@Autowired
	private GoodsService gs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] table= {"","goods_all","goods_best","goods_special","goods_new"};
		AnnotationConfigApplicationContext app=
				new AnnotationConfigApplicationContext(GoodsConfig.class);
		MainClass mc=(MainClass)app.getBean("mainClass");
		List<CategoryVO> cList=mc.gs.categoryListData();
		for(CategoryVO vo:cList) {
			System.out.println(vo.getCno()+"."+vo.getCate_name());
		}
		System.out.println("===========================================================");
		Scanner sc=new Scanner(System.in);
		System.out.print("카테고리 번호 선택 (1~4) : ");
		int cno=sc.nextInt();
		Map map=new HashMap();
		map.put("goods_tbl", table[cno]);
		List<GoodsVO> gList=mc.gs.goodsListData(map);
		for(GoodsVO vo:gList) {
			System.out.println(vo.getNo()+"."+vo.getGoods_name()+" "+vo.getGoods_price());
		}
	}

}
