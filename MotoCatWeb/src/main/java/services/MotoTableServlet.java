package services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import model.Motorbike;
import model.MotorbikeType;
import model.Singleton;

@WebServlet("/table")
public class MotoTableServlet extends HttpServlet {

    @Override
    public void init() {
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        response.setContentType("text/html;charset=UTF-8");
        
        String modelName = request.getParameter("modelName");
        boolean showAll = modelName == null || modelName.length() == 0;
        
        PrintWriter out = response.getWriter();        
        for(Motorbike motorbike : Singleton.getInstance().getMotorbikeList()){
            if(showAll){
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"model\" name=\"model\" placeholder=\"model\" value=\""+ motorbike.model() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"price\" name=\"price\" placeholder=\"price\" value=\""+ motorbike.price() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"displacement\" name=\"displacement\" placeholder=\"displacement\" value=\""+ motorbike.displacement() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"power\" name=\"power\" placeholder=\"power\" value=\""+ motorbike.power() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<select id=\"type\" name=\"type\">");
                for(var option : MotorbikeType.getFormattedValues()){
                    out.println("<option value=\"" + option + "\" " + (motorbike.type().name().equals(option) ? "selected" : "" ) + "/>" + option + "</option>");
                }
                out.println("</select>");
                out.println("</td>");
                out.println("</tr>");
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
