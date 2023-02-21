/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import sample.dao.AccountDAO;

/**
 *
 * @author kietl
 */
public class registerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /* TODO output your page here. You may use following sample code. */
            ArrayList<String> err = new ArrayList<String>();
            String email = request.getParameter("txtemail");
            if (email.isEmpty()) {
                err.add("email is not null");
            } else if (!email.matches(".+@.+")) {
                err.add("email is not accepted");
            }
            String fullname = request.getParameter("txtfullname");
            if (fullname.isEmpty()) {
                err.add("full name is not null");
            }
            String password = request.getParameter("txtpassword");
            if (password.isEmpty()) {
                err.add("password is not null");
            }
            String phone = request.getParameter("txtphone");
            if (phone.isEmpty()) {
                err.add("phone is not null");
            } else if (phone.matches(".*[a-z].*") || phone.matches(".*[A-Z].*")) {
                err.add("phone is not accepted");
            }
            int status = 1;
            int role = 0;//detaul: 4 new account's raie 2 ou
            if (!err.isEmpty()) {
                request.setAttribute("err", err);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
            if (AccountDAO.insertAccount(email, password, fullname, phone, status, role)) {
                //response.sendRedirect("index.html");
                request.setAttribute("email_newAccount", email);
                RequestDispatcher rd = request.getRequestDispatcher("sendOTP");
                rd.forward(request, response);
            } else {
                response.sendRedirect("errorpage.html");
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
