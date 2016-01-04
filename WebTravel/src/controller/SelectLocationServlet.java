package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import model.bean.SceneBean;
import model.service.SceneService;
import model.util.TypeConveter;
import other.bean.FavoriteBean;

/**
 * Servlet implementation class SelectLocationServlet
 */
@WebServlet("/SelectLocationServlet")
public class SelectLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		// 接收資料
		SceneService sceneservice = new SceneService();
		List<FavoriteBean> li = new ArrayList<>();
		String lo = request.getParameter("location");
					
		// 驗證資料
		if ( "北區".equals(lo) || "中區".equals(lo) ||"南區".equals(lo) ||"東區".equals(lo)) {
			li = sceneservice.getLocation(lo);
			
		}
		// 轉換資料
		//JSONArray scenelist = TypeConveter.parseJSONArray(li); 
		// model
		HttpSession session = request.getSession();
		session.setAttribute("li", li);
		
//		PrintWriter out = response.getWriter();
//		out.print(scenelist);
		
		// View
		RequestDispatcher rd = request.getRequestDispatcher("/scene/scene_location.jsp");
		rd.forward(request, response);
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
