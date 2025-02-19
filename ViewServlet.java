package CRUD;
import java.util.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
        out.println("<style>"
                + "*{font-family:Lucida Fax;}"
                + "</style>");
        out.println("<a href='index.html'>Add New Employee</a>");  
        out.println("<h1>Employees List</h1>"); 
        
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","");
                PreparedStatement ps = con.prepareStatement("select * from employee");
                ResultSet rs = ps.executeQuery();
                out.print("<table border='1' width='100%' cellpadding='8' cellspacong='6'");
                out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");  
                while(rs.next())
                {
                    out.print("<tr align='center'>"
                            + "<td>"+rs.getString(1)+"</td>"
                            + "<td>"+rs.getString(2)+"</td>"
                            + "<td>"+rs.getString(3)+"</td>"
                            + "<td>"+rs.getString(4)+"</td>"
                            + "<td>"+rs.getString(5)+"</td>"
                            + "<td><a href='EditServlet?id="+rs.getString(1)+"'>edit</a></td>"
                            + "<td><a href='DeleteServlet?id="+rs.getString(1)+"'>delete</a></td></tr>");  
                }
                out.print("</table>");   
            }
            catch(Exception e){
                out.println("Error : "+e);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
