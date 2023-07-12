package dev.abdullo.roomreservation.servlets.auth;

import dev.abdullo.roomreservation.dao.UserDAO;
import dev.abdullo.roomreservation.domains.User;
import dev.abdullo.roomreservation.services.UserService;
import dev.abdullo.roomreservation.utils.Encrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", value = "/change-password")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = UserService.getInstance();
        Long userId = Long.parseLong((session.getAttribute("user_id").toString()));
        User user = userService.findById(userId);
        request.setAttribute("user", user);
        request.setAttribute("user_id", user.getId());

        request.getRequestDispatcher("/views/auth/change-password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        String newPassword = request.getParameter("new_password");
        UserDAO instance = UserDAO.getInstance();

        User user = instance.findById(Long.parseLong(userId));
        user.setPassword(Encrypt.hashPassword(newPassword));
        instance.update(user);

        request.setAttribute("success_message", "Password updated successfully");
        request.getRequestDispatcher("/views/auth/change-password.jsp").forward(request, response);
    }
}
