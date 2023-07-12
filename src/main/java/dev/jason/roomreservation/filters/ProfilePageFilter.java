package dev.jason.roomreservation.filters;

import dev.jason.roomreservation.validator.UserValidator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "ProfilePageFilter", urlPatterns = {"/profile"})
public class ProfilePageFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        if (req.getMethod().equalsIgnoreCase("post")) {
            Map<String, String> errors = new HashMap<>();
            if (username == null || username.isBlank() || username.isEmpty()) {
                errors.put("username_error", "Username can not be blank");
            } else if (username.length() < 2) {
                errors.put("username_error", "Username must be at least 2 characters long");
            } else if (username.length() > 15) {
                errors.put("username_error", "Username must be less than 15 characters long");
            } else if (username.contains(" ")) {
                errors.put("username_error", "Username can not contain spaces");
            }

            if (firstname == null || firstname.isBlank() || firstname.isEmpty()) {
                errors.put("firstname_err", "Firstname can not be blank");
            } else if (firstname.length() < 2) {
                errors.put("firstname_err", "Firstname must be at least 2 characters long");
            } else if (firstname.length() > 15) {
                errors.put("firstname_err", "Firstname must be less than 15 characters long");
            } else if (!firstname.matches("[a-zA-Z]+")) {
                errors.put("firstname_err", "Firstname must contain only letters");
            } else if (firstname.contains(" ")) {
                errors.put("firstname_err", "Firstname can not contain spaces");
            } else if (firstname.matches("[0-9]+")) {
                errors.put("firstname_err", "Firstname can not contain numbers");
            }

            if (lastname == null || lastname.isBlank() || lastname.isEmpty()) {
                errors.put("lastname_err", "Lastname can not be blank");
            } else if (lastname.length() < 2) {
                errors.put("lastname_err", "Lastname must be at least 2 characters long");
            } else if (lastname.length() > 15) {
                errors.put("lastname_err", "Lastname must be less than 15 characters long");
            } else if (!lastname.matches("[a-zA-Z]+")) {
                errors.put("lastname_err", "Lastname must contain only letters");
            } else if (lastname.contains(" ")) {
                errors.put("lastname_err", "Lastname can not contain spaces");
            } else if (lastname.matches("[0-9]+")) {
                errors.put("lastname_err", "Lastname can not contain numbers");
            }

            if (email == null || email.isBlank() || email.isEmpty()) {
                errors.put("email_error", "Email can not be blank");
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                errors.put("email_error", "Email is not valid");
            } else if (email.contains(" ")) {
                errors.put("email_error", "Email can not contain spaces");
            } else if (email.length() < 5) {
                errors.put("email_error", "Email must be at least 5 characters long");
            } else if (email.length() > 30) {
                errors.put("email_error", "Email must be less than 30 characters long");
            }

            if (errors.isEmpty()) {
                chain.doFilter(request, response);
            } else {
                UserValidator.setErrorAttributes(req, errors);
                req.getRequestDispatcher("/views/user/account-setting/profile.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
