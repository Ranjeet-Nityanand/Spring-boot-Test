package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class FetchServlet
 */
@WebServlet("/FetchServlet")
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//String n=request.getParameter("name");
		List<Reg> data=new ArrayList<>();
		
		try
		{
		Connection con=Connect.gettingConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from reg ");
		
		
		Reg reg = null;
		out.print("<html><body><table align='center' border='1'>");
		ResultSetMetaData rsmd=rs.getMetaData();  
		int total=rsmd.getColumnCount();  
		out.print("<tr>");  
		for(int i=1;i<=total;i++)  
		{  
		out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
		}  
		  
		out.print("</tr>");  
		while(rs.next())
		{
			
			reg=new Reg();
			reg.setId(rs.getInt("id"));
			reg.setUserId(rs.getString("user_id"));
			reg.setPassword(rs.getString("password"));
			reg.setName(rs.getString("name"));
			reg.setContact(rs.getString("mobileno"));
			data.add(reg);
			
			
			
			//out.print("<html><body><table align='center' border='1'>");
			/*data.add(rs.getString(1));
			data.add(rs.getString(2));
			data.add(rs.getString(3));
			data.add(rs.getString(4));
			HttpSession session=request.getSession();
			
			session.setAttribute("record",data);
			*/
		}
			for(int count=0;count<data.size();count++)
			{
				out.print("<tr><td>"+data.get(count).getId()+"</td><td> "+data.get(count).getUserId()+"</td><td> "+data.get(count).getPassword()+"</td><td>"
				+data.get(count).getName()+"</td><td>"+data.get(count).getContact()+"</td></tr>");
			}
			request.getRequestDispatcher("view.jsp").include(request, response);
			out.print("</table></body></html>");
		
		
		/*else
		{
			out.println("Sorry Wrong entry!!!!");
			request.getRequestDispatcher("index.html").include(request, response);
		}*/
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		
	}

}
