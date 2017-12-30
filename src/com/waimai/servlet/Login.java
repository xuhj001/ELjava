package com.waimai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.waimai.daoimp.ShopImpl;
import com.waimai.daoimp.UserImpl;
import com.waimai.model.Shop;
import com.waimai.model.User;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");//ָ�����صĸ�ʽΪJSON��ʽ  
		response.setCharacterEncoding("UTF-8");//setContentType��setCharacterEncoding��˳���ܵ������������޷�����������������  
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		String result = "0";
		
		UserImpl userimp = new UserImpl();
		//�жϴ��û��Ƿ����
		boolean flag = userimp.isExist(userName);
		if(!flag) {
			result = "0";
		}
		else {
			//�жϴ��û������Ƿ���ȷ
			User user = userimp.findByUserName(userName);
			int userid = user.getUserid();
			if(userid == 0) {
				result = "3"; //�̼��û���app�û�����ͨ��
			}
			
			String passWord1 = user.getPassword();
			if(passWord1.equals(passWord)) {
				result = "1";
			}
			else result = "2";
		}
		
		
		
		PrintWriter out =null;  
		out =response.getWriter();  
		out.write(result);  
		out.close();  
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}