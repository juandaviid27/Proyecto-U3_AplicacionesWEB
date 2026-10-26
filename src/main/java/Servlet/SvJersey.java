package Servlet;

import Model.Jersey;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvJersey", urlPatterns = {"/SvJersey"})
public class SvJersey extends HttpServlet {

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=TiendaJerseys;encrypt=true;trustServerCertificate=true";
    private final String user = "JavaUser";
    private final String password = "password123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Driver JDBC no encontrado", e);
        }

        List<Jersey> lista = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Jersey")) {

            while (rs.next()) {
                Jersey j = new Jersey(
                        rs.getInt("id"),
                        rs.getString("equipo"),
                        rs.getString("temporada"),
                        rs.getString("talla"),
                        rs.getDouble("precio"),
                        rs.getString("foto"));
                lista.add(j);
            }

        } catch (SQLException e) {
            throw new ServletException("Error al leer de la base de datos: " + e.getMessage(), e);
        }

        request.setAttribute("lista", lista);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Driver JDBC no encontrado", e);
        }

        String equipo = request.getParameter("equipo");
        String temporada = request.getParameter("temporada");
        String talla = request.getParameter("talla");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String foto = request.getParameter("foto");

        String sql = "INSERT INTO Jersey(equipo, temporada, talla, precio, foto) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, equipo);
            ps.setString(2, temporada);
            ps.setString(3, talla);
            ps.setDouble(4, precio);
            ps.setString(5, foto);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ServletException("Error al guardar en la base de datos: " + e.getMessage(), e);
        }

        response.sendRedirect("SvJersey");
    }
}
