package dev.jason.roomreservation.servlets.admin;

import dev.jason.roomreservation.domains.User;
import dev.jason.roomreservation.services.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPageServlet", value = "/sheengoadmin")
public class AdminPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = AdminService.getInstance().usersList(Long.parseLong(request.getSession().getAttribute("user_id").toString()));
        request.setAttribute("users", users);

        request.getRequestDispatcher("/views/admin/adminpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
