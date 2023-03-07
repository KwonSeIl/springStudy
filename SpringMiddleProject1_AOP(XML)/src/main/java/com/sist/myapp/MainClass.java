package com.sist.myapp;

import com.sist.dao.MovieDAO;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovieDAO dao=new MovieDAO();
		dao.select();
		System.out.println("===========================");
		dao.insert();
		System.out.println("===========================");
		dao.update();
		System.out.println("===========================");
		dao.delete();
	}

}
