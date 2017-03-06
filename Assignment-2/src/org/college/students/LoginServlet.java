package org.college.students;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId = req.getParameter("User_Id");
		String password = req.getParameter("Password");
		String passwordDB = "";
		ResultSet rs = null;
		ArrayList<Registration> al = new ArrayList<>();
		try {
			PreparedStatement pstmt = JDBCUtility.get()
					.prepareStatement("SELECT * FROM student.details where UserId = '" + userId + "'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				al.add(new Registration(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
				System.out.println("'" + (rs.getString(1)) + "' is trying to LogIn");
				passwordDB = (rs.getString(6));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (password.equals(al.get(0).getPassword())) {

			System.out.println("Success");
			resp.sendRedirect("https://www.google.co.in");
		} else {
			resp.sendRedirect("index.html");
			System.out.println("Failed");
		}

	}

}
