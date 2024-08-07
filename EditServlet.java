/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class EditServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            
            out.println("<style>\n" +
"            *{\n" +
"                font-family: Lucida Fax;\n" +
"            }\n" +
"            input,select{\n" +
"                padding: 5px 17px;\n" +
"            }\n" +
"            h1{\n" +
"                margin-top: 150px;\n" +
"            }\n" +
"            form{\n" +
"                border: 2px solid black;\n" +
"                width: 40%;\n" +
"                height: 40vh;\n" +
"                display: flex;\n" +
"                justify-content:center;\n" +
"            }\n" +
"        </style>");
            out.println("<h1>Edit Servlet </h1>");
            
             int id =Integer.parseInt(request.getParameter("id"));
             
            try{ 
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","");
                PreparedStatement ps = con.prepareStatement("select *from employee where id=?");
                ps.setInt(1, id);
                
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    out.print("<form action='EditServlet2?id="+rs.getString(1)+"' method='post'>");  
                    out.print("<table>");  
                    out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+rs.getString(2)+"'/></td></tr>");  
                    out.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+rs.getString(3)+"'/>  </td></tr>");  
                    out.print("<tr><td>Email:</td><td><input type='email' name='email' value='"+rs.getString(4)+"'/></td></tr>");  
                    out.print("<tr><td>Country:</td><td>");  
                    out.print("<select name='country' style='width:150px'>");  
                    out.print("<option>India</option>");  
                    out.print("<option>USA</option>");  
                    out.print("<option>UK</option>");  
                    out.print("<option>Other</option>");  
                    out.print("</select>");  
                    out.print("</td></tr>");  
                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
                    out.print("</table>");  
                    out.print("</form>"); 
                }
            }
            catch(Exception e){
                out.println("Exception : "+e);
            }
        
        
          
         
          
        out.close();
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
