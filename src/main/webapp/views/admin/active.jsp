<%@ page import="dev.abdullo.roomreservation.domains.User" %>
<%--
  Created by IntelliJ IDEA.
  User: javohir
  Date: 16/02/2023
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/dataTables.bootstrap5.min.css"/>
</head>
<body >

<%--Begin Header--%>

<%--<nav class="navbar navbar-expand-sm bg-dark navbar-dark">--%>
<%--<header>--%>
<%--    <div class="container-fluid">--%>
<%--        <div class="row align-items-center justify-content-between d-flex">--%>
<%--            <nav id="nav-menu-container">--%>
<%--                <ul class="nav-menu ">--%>
<%--                    <li class="menu-active"><a href="/logout">Log out</a></li>--%>
<%--                    <li class="menu"><a href="/admin/active">Active Reservations</a></li>--%>
<%--                    <li class="menu"><a href="/admin/all">All Reservations</a></li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</header>--%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">StudySpot</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/tafakkooradmin">All users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/tafakkooradmin/all">All Reservations</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/tafakkooradmin/active"><i><u>Active Reservations</u></i></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/tafakkooradmin/expired">Expired Reservations</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: red" href="/logout">Log out</a>
            </li>
            <%--            <li class="nav-item dropdown">--%>
            <%--                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
            <%--                    Dropdown link--%>
            <%--                </a>--%>
            <%--                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">--%>
            <%--                    <a class="dropdown-item" href="#">Action</a>--%>
            <%--                    <a class="dropdown-item" href="#">Another action</a>--%>
            <%--                    <a class="dropdown-item" href="#">Something else here</a>--%>
            <%--                </div>--%>
            <%--            </li>--%>
        </ul>
    </div>
</nav>
<%-- End Header --%>

<div class="container mt-5">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
        <tr>
            <th>№</th>
            <th>Username</th>
            <th>Fullname</th>
            <th>Room</th>
            <th>Reservation Date</th>  <%-- used to be EMAIL --%>
            <th>Field</th>
            <th>Created at</th>    <%-- user to be ROLE --%>
            <th>Seat Number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${i=i+1}</td>
                <td>${reservation.getUser().getUsername()}</td>
                <td>${reservation.getUser().getLastname()} ${reservation.getUser().getFirstname()}</td>
                <td>${reservation.getRoom().getName()}</td>
                <td>${reservation.getReservationDate().toString()}</td>
                <td>${reservation.getUser().getField().getName()}</td>
                <td>${reservation.getCreatedAt().toString()}</td>
                <td>${reservation.getSeatNumber()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>




<!-- Modal -->
<div class="modal fade" id="deleteUserModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">User delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/user/delete/*" id="v-deleted>">
                <div class="modal-body">
                    <input type="hidden" name="id" id="deleted_id" value="${deleted_id}">
                    Do you really want to delete this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="activateUserModal" tabindex="-1" aria-labelledby="activateUserLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="activateUserLabel">User active</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/user/activated/*" id="v-activated">
                <div class="modal-body">
                    <input type="hidden" name="id" id="activated_id" value="${activated_id}">
                    Do you really want to activate this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="changeRoleModal" tabindex="-1" aria-labelledby="changeRoleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changeRoleModalLabel">User change role </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/tafakkooradmin/change-role/*" id="new-line">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="${id}">
                    <input type="hidden" name="role" id="role" value="${role}">
                    Do you really change role this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Save changes</button>
                </div>
            </form>
        </div>
    </div>
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