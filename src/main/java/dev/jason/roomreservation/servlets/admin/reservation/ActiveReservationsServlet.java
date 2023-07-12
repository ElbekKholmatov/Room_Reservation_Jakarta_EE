package dev.jason.roomreservation.servlets.admin.reservation;

import dev.jason.roomreservation.dao.ReservationDAO;
import dev.jason.roomreservation.domains.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ActiveReservationsServlet", value = "/sheengoadmin/active")
public class ActiveReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = ReservationDAO.getInstance().getActiveReservations();
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/views/admin/active.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
