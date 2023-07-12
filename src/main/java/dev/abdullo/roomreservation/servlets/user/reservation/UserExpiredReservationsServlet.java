package dev.abdullo.roomreservation.servlets.user.reservation;

import dev.abdullo.roomreservation.dao.ReservationDAO;
import dev.abdullo.roomreservation.domains.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserExpiredReservationsServlet", value = "/user/expired")
public class UserExpiredReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        List<Reservation> reservations = ReservationDAO.getInstance().getExpiredReservationsForUser(userId);
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/views/user/reservation/expired.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
