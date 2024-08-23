package org.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("select");
            return;
        }

        int id = Integer.parseInt(idParam);
        Book book = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, name, author, year, genre FROM books WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        book = new Book();
                        book.setId(rs.getInt("id"));
                        book.setName(rs.getString("name"));
                        book.setAuthor(rs.getString("author"));
                        book.setYear(rs.getString("year"));
                        book.setGenre(rs.getString("genre"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (book == null) {
            response.sendRedirect("select");
            return;
        }

        request.setAttribute("book", book);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String year = request.getParameter("year");
        String genre = request.getParameter("genre");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE books SET name = ?, author = ?, year = ?, genre = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, author);
                stmt.setString(3, year);
                stmt.setString(4, genre);
                stmt.setInt(5, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("select");
    }
}
