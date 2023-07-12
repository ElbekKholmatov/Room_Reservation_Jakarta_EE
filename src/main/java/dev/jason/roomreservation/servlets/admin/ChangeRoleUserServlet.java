package dev.jason.roomreservation.servlets.admin;

import dev.jason.roomreservation.services.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "ChangeRoleUserServlet", value = "/sheengoadmin/change-role/*")
public class ChangeRoleUserServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer id = Integer.valueOf(request.getParameter("id"));
        String role = request.getParameter("role");

        AdminService.getInstance().changeRole(id, role);
        response.sendRedirect("/sheengoadmin");

    }
}
