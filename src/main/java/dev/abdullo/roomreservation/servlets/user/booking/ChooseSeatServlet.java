package dev.abdullo.roomreservation.servlets.user.booking;

import dev.abdullo.roomreservation.dao.RoomDAO;
import dev.abdullo.roomreservation.domains.Room;
import dev.abdullo.roomreservation.utils.ThreadSafeContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ChooseSeatServlet", value = "/book/user/choose-seat/*")
public class ChooseSeatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getPathInfo().substring(1);
        String userId = request.getSession().getAttribute("user_id").toString();

        long uId = Long.parseLong(userId);
        long rooId = Long.parseLong(roomId);

        Room room = RoomDAO.getInstance().findById(rooId);
        LocalDate date = ThreadSafeContainer.reservationDates.get(uId);
        List<String> seats = RoomDAO.getInstance().getReservedSeatsOnDate(date, room);  // reserved seats
        request.setAttribute("room", room);
        request.setAttribute("seats", seats);
        request.getRequestDispatcher("/views/user/booking/choose-seat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
