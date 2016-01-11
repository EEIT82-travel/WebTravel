package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.SceneService;
import other.bean.FavoriteBean;

/**
 * Servlet implementation class SelectSceneContextServlet
 */
@WebServlet("/SelectSceneContextServlet")
public class SelectSceneContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//接收資料
		String sname = request.getParameter("sceneName");
		//驗證資料
		
		//轉換資料
		
		//model
		FavoriteBean nbean = new FavoriteBean();
		SceneService sceneservice = new SceneService();
		nbean = sceneservice.getName(sname);
		//view
		HttpSession session = request.getSession();
		session.setAttribute("namebean", nbean);
		response.sendRedirect(request.getContextPath()+"/scene/scene_content.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
