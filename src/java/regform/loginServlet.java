package regform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Trim to remove extra spaces
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/university?useSSL=false&serverTimezone=UTC",
                "root",
                "Priya@1204"
            );

            String sql = "SELECT * FROM Application WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Login successful
                HttpSession session = request.getSession();
    session.setAttribute("username", username);
    session.setAttribute("fullname", rs.getString("fullname"));
    session.setAttribute("email", rs.getString("email"));
    session.setAttribute("phone", rs.getString("phone"));
    session.setAttribute("dob", rs.getString("dob"));
    session.setAttribute("gender", rs.getString("gender"));
    session.setAttribute("address", rs.getString("address"));

    // Redirect to profile page
    response.sendRedirect("profilejsp.jsp");
            } else {
                // Login failed
                out.println("<!DOCTYPE html>");
                out.println("<html><head><title>Login Failed</title></head><body>");
                out.println("<div style='text-align:center; margin-top:50px;'>");
                out.println("<h3 style='color:red;'>Invalid username or password!</h3>");
                out.println("<a href='login.html'>Try Again</a>");
                out.println("</div></body></html>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
