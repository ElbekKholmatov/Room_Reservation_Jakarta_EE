package dev.jason.roomreservation.servlets.user.reservation;

import dev.jason.roomreservation.services.ReservationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteReservationServlet", urlPatterns = "/user/reservation/*")
public class DeleteReservationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long reservationId = Long.valueOf(request.getParameter("id"));
        ReservationService.getInstance().changeDeleted(reservationId);
        response.sendRedirect("/user/active");
    }
}
