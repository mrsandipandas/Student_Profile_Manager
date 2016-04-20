/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.VO.CategoryVO;
import com.in.dbEntry.Card;
import com.in.dbEntry.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sandi
 */
public class AdminFunctionalitiesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = null;
        Category category = new Category();
        Card card = new Card();
        String newCategoryId = null;
        try {
            String typeOfDelete = (String) request.getParameter("typeOfDelete");
            String menuName = (String) request.getParameter("categoryType");
            String menuId = (String) request.getParameter("categoryId");
            String ecardId = (String) request.getParameter("ecardId");
            String newCategoryName = (String) request.getParameter("newCategoryName");
            ArrayList<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();

            if (typeOfDelete != null) {
                if (typeOfDelete.equalsIgnoreCase("category")) {
                    try {
                        //Write the Business logic of deleting the Card category here
                        System.out.println("********" + typeOfDelete);

                        if (menuId != null) {
                            card.deleteAllCategoryCards(menuId);
                            category.deleteCategory(menuId);
                        } else {
                            System.out.println("Empty menuId!!!!!");
                        }
                    } catch (Exception ex) {
                    }
                }
                if (typeOfDelete.equalsIgnoreCase("card")) {
                    try {
                        //Write the Business logic of deleting the Card here
                        card.deleteCategoryCards(ecardId);
                        System.out.println("********" + typeOfDelete);
                    } catch (Exception ex) {
                    }
                }
            }
            if (newCategoryName != null) {
                try {
                    //Write the business logic for adding a new card category
                    System.out.println("********" + newCategoryName);

                    newCategoryId = category.addCategory(newCategoryName);

                } catch (Exception ex) {
                }
            }
            categoryVOs = category.getCategory();
            
            if (newCategoryName != null && newCategoryId != null) {
                request.setAttribute("menuName", newCategoryName);
                request.setAttribute("menuId", newCategoryId);
            } else {
                request.setAttribute("menuName", menuName);
                request.setAttribute("menuId", menuId);
            }

            request.setAttribute("categoryVOs", categoryVOs);
            request.setAttribute("adminButtonState", "admin");
            request.setAttribute("linkType", "Administrator");
            rd = getServletContext().getRequestDispatcher("/ViewGreetingsCard.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
}

