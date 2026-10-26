<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${lista == null}">
    <jsp:forward page="SvJersey" />
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tienda de Jerseys</title>
    </head>
    <body>

        <h1>Tienda de Jerseys de Fútbol</h1>

        <h2>Catálogo</h2>
        <c:choose>
            <c:when test="${not empty lista}">
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Equipo</th>
                        <th>Temporada</th>
                        <th>Talla</th>
                        <th>Precio</th>
                        <th>Foto</th>
                    </tr>
                    <c:forEach var="j" items="${lista}">
                        <tr>
                            <td><c:out value="${j.id}" /></td>
                            <td><c:out value="${j.equipo}" /></td>
                            <td><c:out value="${j.temporada}" /></td>
                            <td><c:out value="${j.talla}" /></td>
                            <td>$<c:out value="${j.precio}" /></td>
                            <td><img src="<c:out value='${j.foto}' />" width="80" height="80"></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>No hay jerseys registrados.</p>
            </c:otherwise>
        </c:choose>

        <h2>Agregar jersey</h2>
        <form method="post" action="SvJersey">
            Equipo: <input type="text" name="equipo" required><br><br>
            Temporada: <input type="text" name="temporada" required><br><br>
            Talla:
            <select name="talla">
                <option>S</option>
                <option>M</option>
                <option>L</option>
                <option>XL</option>
            </select><br><br>
            Precio: <input type="number" name="precio" step="0.01" min="0" required><br><br>
            URL de la foto: <input type="text" name="foto" required><br><br>
            <button type="submit">Guardar</button>
        </form>

    </body>
</html>
