package model.dao.jndi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.SceneBean;
import model.dao.SceneDAO;

public class SceneDAOjndi implements SceneDAO {
	
	//select
	private static final String SELECT_ALL = "select * from scene";
	private static final String SELECT_BY_LOCATION = "select * from scene where location = ?";	
	//insert
	private static final String INSERT = 
			"insert into scene"
		  + " (location,city,sceneName,sceneContent,timeStart,timeEnd,MemberId) "
		  + "values(?, ?, ?, ?, ?, ?, ?)";
	//update
	private static final String UPDATE = "update scene set location=?, city=?,"
		  + "sceneName=?, sceneContent=?, timeStart=?, timeEnd=?, MemberId=?";	
	//delete
	private static final String DELETE = "delete from scene where sceneName=?";
	private Connection conn = null;
	
	DataSource ds =null;
	public SceneDAOjndi(){
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public  List<SceneBean> select() {
		List<SceneBean> list = null;
		SceneBean sbean =null;
		try (//AutoCloseable
			 Connection conn = ds.getConnection(); 
			 ){
			
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<SceneBean>();
			while(rs.next()){
				sbean = new SceneBean();
				sbean.setSceneId(rs.getInt(1));
				sbean.setLocation(rs.getString(2));
				sbean.setCity(rs.getString(3));
				sbean.setSceneName(rs.getString(4));
				sbean.setSceneContent(rs.getString(5));
				sbean.setTimeStart(rs.getString(6));
				sbean.setTimeEnd(rs.getString(7));
				sbean.setMemberId(rs.getInt(8));
				list.add(sbean);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	//查詢SELECT_BY_LOCATION
	/* (non-Javadoc)
	 * @see model.dao.jdbc.SceneDAO#select(java.lang.String)
	 */
	@Override
	public  SceneBean select(String location) {
		SceneBean sbean =null;
		try (
				Connection conn = ds.getConnection(); 
			 ){
			PreparedStatement ps = conn.prepareStatement(SELECT_BY_LOCATION);
			ps.setString(1, location);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				sbean = new SceneBean();				
				sbean.setSceneId(rs.getInt(1));
				sbean.setLocation(rs.getString(2));
				sbean.setCity(rs.getString(3));
				sbean.setSceneName(rs.getString(4));
				sbean.setSceneContent(rs.getString(5));
				sbean.setTimeStart(rs.getString(6));
				sbean.setTimeEnd(rs.getString(7));
				sbean.setMemberId(rs.getInt(8));	
			}		
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	
		return sbean;
	}
	
	//新增INSERT
	/* (non-Javadoc)
	 * @see model.dao.jdbc.SceneDAO#insert(model.SceneBean)
	 */
	@Override
	public SceneBean insert(SceneBean bean) {
		SceneBean result = null;
		try (
				Connection conn = ds.getConnection(); 
				){
			PreparedStatement ps = conn.prepareStatement(INSERT);
			if(bean != null){
			ps.setString(1, bean.getLocation());
			ps.setString(2, bean.getCity());
			ps.setString(3, bean.getSceneName());
			ps.setString(4, bean.getSceneContent());
			ps.setString(5, bean.getTimeStart());
			ps.setString(6, bean.getTimeEnd());
			ps.setInt(7, bean.getMemberId());
					
			int rs = ps.executeUpdate();
			if (rs == 1){
				result = bean;
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//修改UPDATE
	/* (non-Javadoc)
	 * @see model.dao.jdbc.SceneDAO#update(model.SceneBean)
	 */
	@Override
	public SceneBean update(SceneBean bean){
		SceneBean result = null;
		try (
				Connection conn = ds.getConnection(); 	
				){
			PreparedStatement ps = conn.prepareStatement(UPDATE);
			if(bean != null){
			ps.setString(1, bean.getLocation());
			ps.setString(2, bean.getCity());
			ps.setString(3, bean.getSceneName());
			ps.setString(4, bean.getSceneContent());
			ps.setString(5, bean.getTimeStart());
			ps.setString(6, bean.getTimeEnd());
			ps.setInt(7, bean.getMemberId());
					
			int rs = ps.executeUpdate();
			if (rs == 1){
				result = bean;
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//刪除DELETE
	/* (non-Javadoc)
	 * @see model.dao.jdbc.SceneDAO#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String sceneName) {
		try (
				Connection conn = ds.getConnection(); 	
					){
				PreparedStatement ps = conn.prepareStatement(DELETE);
				
				ps.setString(1, sceneName);
									
				int rs = ps.executeUpdate();
				if (rs == 1){
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	
	public static void main(String[] args){
		SceneDAO test = new SceneDAOjndi();
//----------------------------------------------------------
//		List<SceneBean> li= test.select();  //全部select
//		for(SceneBean e:li){
//			System.out.println(e);
//		}
//----------------------------------------------------------
//		System.out.println(test.select("北區"));  //單筆select
//----------------------------------------------------------
		SceneBean sbean = new SceneBean();
		sbean.setLocation("南區");
		sbean.setCity("台南市xxx");
		sbean.setSceneName("安平古堡");
		sbean.setSceneContent("安平古堡XXXXXXX");
		sbean.setTimeStart("09:00");
		sbean.setTimeEnd("21:00");
    	sbean.setMemberId(1);
//		
//		System.out.println(test.insert(sbean)); // 新增資料
//----------------------------------------------------------
//		System.out.println(test.select("text123")); //單筆select （帳號）
//----------------------------------------------------------
//		System.out.println(test.update(sbean)); //修改
//----------------------------------------------------------
		System.out.println(test.delete("安平古堡"));//刪除
//----------------------------------------------------------
//		FileOutputStream fo = new FileOutputStream("/Users/mouse/Desktop/4.jpg");   //把圖片取出來放桌面 
//		fo.write(m.select(2).getPhoto(), 0, t);
//		fo.close();
//----------------------------------------------------------
		
	}
}//class
