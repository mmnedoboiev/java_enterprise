package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = new ArrayList<>();

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

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Фільтруємо книги за ID користувача
            String sql = "SELECT id, name, author, year, genre FROM books WHERE userid = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String author = rs.getString("author");
                        String year = rs.getString("year");
                        String genre = rs.getString("genre");

                        Book book = new Book();
                        book.setId(id);
                        book.setName(name);
                        book.setAuthor(author);
                        book.setYear(year);
                        book.setGenre(genre);
                        books.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("books", books);
        request.getRequestDispatcher("/select.jsp").forward(request, response);
    }
}
