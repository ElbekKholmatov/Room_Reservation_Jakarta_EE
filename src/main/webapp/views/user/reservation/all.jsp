<%@ page import="java.time.format.DateTimeFormatterBuilder" %>
<%--
  Created by IntelliJ IDEA.
  User: javohir
  Date: 16/02/2023
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/dataTables.bootstrap5.min.css"/>
</head>
<body >
<c:set var="format" value='<%=new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy | HH:mm:ss").toFormatter() %>'/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/user">StudySpot</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/user/all"><i><u>All Reservations</u></i></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/active">Active Reservations</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/expired">Expired Reservations</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: red" href="/logout">Log out</a>
            </li>
        </ul>
    </div>
</nav>
<%-- End Header --%>

<div class="container mt-5">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
        <tr>
            <th>№</th>
            <th>Room</th>
            <th>Reservation Date</th>
            <th>Created at</th>
            <th>Seat Number</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${i=i+1}</td>
                <td>${reservation.getRoom().getName()}</td>
                <td>${reservation.getReservationDate().toString()}</td>
                <td>${reservation.getCreatedAt().format(format)}</td>
                <td>${reservation.getSeatNumber()}</td>
                <td>${(reservation.isExpired())? 'Expired' : 'Active'}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="/resources/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/dataTables.bootstrap5.min.js"></script>
<script src="/views/admin/js/choose.js"></script>
<script src="/views/admin/js/diagram.js"></script>


<script>

    $(document).ready(function () {
        $('#example').DataTable()
    })
</script>
</body>
</html>