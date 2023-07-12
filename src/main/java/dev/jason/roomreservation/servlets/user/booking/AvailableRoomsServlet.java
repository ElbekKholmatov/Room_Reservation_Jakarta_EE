package dev.jason.roomreservation.servlets.user.booking;

import dev.jason.roomreservation.dao.RoomDAO;
import dev.jason.roomreservation.domains.Field;
import dev.jason.roomreservation.domains.Room;
import dev.jason.roomreservation.domains.User;
import dev.jason.roomreservation.services.UserService;
import dev.jason.roomreservation.utils.ThreadSafeContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "AvailableRoomsServlet", value = "/book/available")
public class AvailableRoomsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        long id = Long.parseLong(userId);
        User user = UserService.getInstance().findById(id);
        Field field = user.getField();
        LocalDate reservationDate = ThreadSafeContainer.reservationDates.get(Long.parseLong(userId));
        Map<Room, Integer> availableRoomsAndSeats = RoomDAO.getInstance().getAvailableRoomsAndSeats(field, reservationDate);
        Set<Map.Entry<Room, Integer>> entries = availableRoomsAndSeats.entrySet();
        request.setAttribute("rooms", entries);

        System.out.println(availableRoomsAndSeats);
        request.getRequestDispatcher("/views/user/booking/available.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
