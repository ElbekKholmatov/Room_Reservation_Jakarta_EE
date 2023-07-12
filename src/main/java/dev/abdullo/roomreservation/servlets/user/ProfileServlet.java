package dev.abdullo.roomreservation.servlets.user;

import dev.abdullo.roomreservation.dao.FieldDAO;
import dev.abdullo.roomreservation.domains.Field;
import dev.abdullo.roomreservation.domains.User;
import dev.abdullo.roomreservation.services.UserService;
import dev.abdullo.roomreservation.utils.Util;
import dev.abdullo.roomreservation.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        System.out.println(userId + " " + "user id");
        User user = UserService.getInstance().findById(Long.valueOf(userId));
        List<Field> fields = FieldDAO.getInstance().findAll();
        request.setAttribute("fields", fields);
        request.setAttribute("user", user);
        request.getSession().setAttribute("user", user);
        System.out.println(user);

        request.getRequestDispatcher("/views/user/account-setting/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String userId = request.getParameter("user_id");
        String fieldId = request.getParameter("field_id");

        User user = userService.findById(Long.valueOf(userId));
        Map<String, String> errors = new HashMap<>();

        if (!username.trim().equals(user.getUsername())) {
            if (UserValidator.isDuplicateUsername(username)) {
                errors.put("username_error", "Username already taken!");
            } else if (username.contains(" ")) {
                errors.put("username_error", "Username can't contain spaces");
            } else {
                user.setUsername(username.trim());
            }
        }

        if (!firstname.trim().equals(user.getFirstname()) && !firstname.trim().equals("Firstname")) {
            user.setFirstname(Util.getInstance().capitalize(firstname.trim().toLowerCase()));
        }

        if (!lastname.trim().equals(user.getLastname()) && !lastname.trim().equals("Lastname")) {
            user.setLastname(Util.getInstance().capitalize(lastname.trim().toLowerCase()));
        }

        if (!email.trim().equals(user.getEmail())) {
            if (UserValidator.isDuplicateEmail(email)) {
                errors.put("email_error", "Email already taken");
            } else {
                user.setEmail(email);
            }
        }
        if (errors.isEmpty()) {
            userService.update(user);
            response.sendRedirect("/profile");
        } else {
            UserValidator.setErrorAttributes(request, errors);
            request.getRequestDispatcher("/views/user/account-setting/profile.jsp").forward(request, response);
        }
    }
}
