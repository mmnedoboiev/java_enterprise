package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addbook")
public class AddBooks extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримуємо сесію та перевіряємо наявність користувача
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId"); // Отримуємо ID користувача з сесії

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }



        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String year = request.getParameter("year");
        String genre = request.getParameter("genre");
        request.setCharacterEncoding("UTF-8");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO books (name,author,year,genre,userid) VALUES (?,?,?,?,?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, author);
                stmt.setString(3, year);
                stmt.setString(4, genre);
                stmt.setInt(5, userId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("addbook.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/addbook.jsp").forward(request, response);
    }

}
