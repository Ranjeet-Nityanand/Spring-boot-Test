package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reg reg= new Reg();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("uid");
		String pass=request.getParameter("pass");
		String name=request.getParameter("name");
		String mob=request.getParameter("mob");
		reg.setUserId(id);
		reg.setPassword(pass);
		reg.setName(name);
		reg.setContact(mob);
		try
		{
		Connection con=Connect.gettingConnection();
		PreparedStatement ps=con.prepareStatement("insert into reg (user_id,password,name,mobileno) values (?,?,?,?)");
		ps.setString(1,reg.getUserId());
		ps.setString(2, reg.getPassword());
		ps.setString(3, reg.getName());
		ps.setString(4, reg.getContact());
		int i=ps.executeUpdate();
		if(i>0)
		{
			out.print("Data Insertion Successfull");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		else
		{
			out.print("Data not Inserted");
		}
	}
		catch(Exception e)
		{
			System.out.println(e);
		}

}
}