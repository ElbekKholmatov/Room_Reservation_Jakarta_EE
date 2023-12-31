package dev.jason.roomreservation.servlets.auth;

import dev.jason.roomreservation.domains.User;
import dev.jason.roomreservation.services.UserService;
import dev.jason.roomreservation.utils.AES;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getQueryString();
        UserService userService = UserService.getInstance();
        String username = request.getParameter("in_username");
        String password = request.getParameter("in_password");

        User user = userService.findByUsername(username);
// mvc design pattern
        // where it is used and what it is used for
        //
        //types
        // 1. strategy   design pattern
        // 2. proxy      design pattern
        // 3. facade     design pattern
        // 4. decorator  design pattern
        // 5. mvc        design pattern

        if (user == null) {
            request.setAttribute("error", "Username or password is incorrect");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        } else {
            if (user.isDeleted()) {
                request.setAttribute("error", "Your account has been blocked!");
                request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
            } else if (UserService.getInstance().isCorrectPassword(password, user.getPassword())) {

                String userId = String.valueOf(user.getId());
                String encrypt = AES.encrypt(userId);
                Cookie cookie = UserService.getInstance().createCookie(encrypt);
                response.addCookie(cookie);
                response.sendRedirect((url != null) ? url.substring(5) : userService.redirectByRole(user));
            } else {
                request.setAttribute("error", "Username or password is incorrect");
                request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
            }
        }
    }
}
