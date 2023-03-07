package com.sist.di4;
import java.util.*;
import java.sql.*;
public class EmpDAO {
   private Connection conn;
   private PreparedStatement ps;
   private String url,user,pwd;
   /*
    *    p:url="jdbc:oracle:thin:@localhost:1521:XE"
         p:user="hr"
         p:pwd="happy"
    */
    public String getUrl() {
	  return url;
	}
	// p:url="jdbc:oracle:thin:@localhost:1521:XE"
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUser() {
		return user;
	}
	// p:user="hr"
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPwd() {
		return pwd;
	}
    // p:pwd="happy"
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	// c:driver="oracle.jdbc.driver.OracleDriver"
	public EmpDAO(String driver)
	{
		   try
		   {
			   Class.forName(driver);
		   }catch(Exception ex){}
	}
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(url,user,pwd);
		}catch(Exception ex) {}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	public List<EmpVO> empListData()
	{
		List<EmpVO> list=new ArrayList<EmpVO>();
		try
		{
			getConnection();
			String sql="SELECT empno,ename,job,hiredate,sal FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	public void init()
	{
		System.out.println("===================== 사원 목록 ===================");
	}
	public void destory()
	{
		System.out.println("================================================");
	}
}
