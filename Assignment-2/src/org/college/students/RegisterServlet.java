package org.college.students;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_Name = req.getParameter("User_name");
		String college_Name = req.getParameter("College_name");
		String college_Place = req.getParameter("College_place");
		String email = req.getParameter("Email");
		String pincode = req.getParameter("Pincode");
		String password = req.getParameter("Password");
		String userId = req.getParameter("User_id");
		System.out.println(user_Name + "+" + college_Name + "+" + college_Place + "+" + email + "+" + pincode + "+"
				+ password + "+" + userId);
		System.out.println(checkLength(user_Name));
		System.out.println(checkLength(college_Name));
		System.out.println(checkLength(college_Place));
		System.out.println(checkEmail(email));
		System.out.println(checkPin(pincode));
		System.out.println(checkPassword(password));
		System.out.println(checkUserId(userId));
		PrintWriter ot = resp.getWriter();

		if (checkLength(user_Name) && checkLength(college_Name) && checkLength(college_Place) && checkEmail(email)
				&& checkPin(pincode) && checkUserId(userId) && checkPassword(password)) {
			try {
				PreparedStatement pstmt = JDBCUtility.get()
						.prepareStatement("insert into student.details values(?,?,?,?,?,?,?)");
				pstmt.setString(1, user_Name);
				pstmt.setString(2, college_Name);
				pstmt.setString(3, college_Place);
				pstmt.setString(4, email);
				pstmt.setInt(5, Integer.parseInt(pincode));
				pstmt.setString(6, password);
				pstmt.setString(7, userId);
				pstmt.executeUpdate();
				pstmt.close();

				ot.println("<!DOCTYPE html>" +

						"<html><head><meta charset='UTF-8'><title>LOGIN || REGISTER</title>"
						+ "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>"
						+ "<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>"
						+ "<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>"
						+ "<link rel='stylesheet' href='css/style.css'></head>"
						+ "<body><div class='pen-title'></div><div class='rerun'><a href='index.html'>Rerun to LOGIN</a></div><div class='container'>"
						+ "<div class='card'></div><div class='card'><h1 class='title'>Yuhuu...!!!</h1><form>"
						+ "<div class='input-container'><h3>" + "Congratulations...!!! You Have Registered Successfully"
						+ " </h3>"
						+ "</div><div class='button-container'><button><span>Congratulations...!!!</span></button></div></form></div>"
						+

						"</div><script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script src='js/index.js'></script>"
						+ "</body></html>");

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				resp.sendRedirect("index.html");
			}
		} else {
			String op = "";
			if (checkLength(user_Name) == false)
				op += "User Name Incorrect";
			if (checkLength(college_Name) == false)
				op += "<br><br>College Name Incorrect";
			if (checkLength(college_Place) == false)
				op += "<br><br>College Place Incorrect";
			if (checkEmail(email) == false)
				op += "<br><br>Email Incorrect";
			if (checkPin(pincode) == false)
				op += "<br><br>Pincode Incorrect";
			if (checkPassword(password) == false)
				op += "<br><br>Password Incorrect";
			if (checkUserId(userId) == false)
				op += "<br><br>User Id Incorrect";

			ot.println("<!DOCTYPE html>" +

					"<html><head><meta charset='UTF-8'><title>LOGIN || REGISTER</title>"
					+ "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>"
					+ "<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>"
					+ "<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>"
					+ "<link rel='stylesheet' href='css/style.css'></head>"
					+ "<body><div class='pen-title'></div><div class='rerun'><a href='index.html'>Rerun to LOGIN</a></div><div class='container'>"
					+ "<div class='card'></div><div class='card'><h1 class='title'>Error...!!!</h1><form>"
					+ "<div class='input-container'><h3>" + op + " </h3>"
					+ "</div><div class='button-container'><button><span>OOPs...!!! O_O</span></button></div></form></div>"
					+

					"</div><script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script src='js/index.js'></script>"
					+ "</body></html>");

		}
	}

	public static boolean checkLength(String line) {
		/**
		 * This method checks the length. If in between 6 to 20 returns true
		 * else false.
		 */
		if (line.length() >= 6 && line.length() <= 20)
			return checkSpecialChar(line);
		return false;
	}

	public static boolean checkPin(String pin) {
		/**
		 * This method validates the pin. Conditions are pin should contain only
		 * numbers. Pin length should be in between 6 to 8. If satisfies return
		 * true else false.
		 */
		if (pin.length() >= 6 && pin.length() <= 8) {
			if (pin.length() == pin.replaceAll("\\D+", "").length())
				return true;
		}
		return false;
	}

	public static boolean checkEmail(String email) {
		/**
		 * This method validates email. length must be in between 10 to 20 One
		 * '.' should come after '@'
		 */
		if (email.length() >= 10 && email.length() <= 20) {
			int num = email.indexOf("@");
			int num1 = email.lastIndexOf(".");
			if (num1 > num)
				return true;
			return false;
		}
		return false;
	}

	public static boolean checkUserId(String uid) {
		/**
		 * This method checks and says whether the username is proper or not.
		 * Conditions are it shouldn't contain any special characters. Length
		 * must be in between 6 to 20.
		 */
		if (uid.length() >= 6 && uid.length() <= 20) {
			return checkSpecialChar(uid);
		}
		return false;
	}

	public static boolean checkSpecialChar(String s) {
		/**
		 * This method helps in checking if the string contains any special
		 * characters or not.
		 * 
		 */
		for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) >= 32 && s.charAt(i) <= 47) || (s.charAt(i) >= 58 && s.charAt(i) <= 64)
					|| (s.charAt(i) >= 91 && s.charAt(i) <= 96) || (s.charAt(i) >= 123 && s.charAt(i) <= 126))
				return false;
		}
		return true;
	}

	public static boolean checkPassword(String s) {
		/**
		 * Checks the password is valid or not. conditions to be checked are 1.
		 * Length in between 6 to 20. 2. At least 1 numeric, 1 Upper case, 1
		 * lower case and one special character.
		 */
		if (s.length() >= 6 && s.length() <= 20) {
			int numeric = 0, upcase = 0, lowcase = 0, specialchar = 0;

			for (int i = 0; i < s.length(); i++) {
				if ((s.charAt(i) >= 32 && s.charAt(i) <= 47) || (s.charAt(i) >= 58 && s.charAt(i) <= 64)
						|| (s.charAt(i) >= 91 && s.charAt(i) <= 96) || (s.charAt(i) >= 123 && s.charAt(i) <= 126)) {
					specialchar = 1;
					break;
				}
			}
			if (specialchar == 0)
				return false;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
					upcase = 1;
					break;
				}
			}
			if (upcase == 0)
				return false;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
					lowcase = 1;
					break;
				}
			}
			if (lowcase == 0)
				return false;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
					numeric = 1;
					break;
				}
			}
			if (numeric == 0)
				return false;
			return true;
		}
		return false;
	}
}