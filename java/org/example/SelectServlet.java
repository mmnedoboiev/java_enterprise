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
        List<Book> books = new ArrayList<>(); // Створюємо список для збереження об'єктів книг.

        // Отримуємо сесію та перевіряємо наявність користувача
        HttpSession session = request.getSession(false); // Отримуємо існуючу сесію, не створюючи нову.
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login"); // Якщо користувач не авторизований, перенаправляємо на сторінку входу.
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId"); // Отримуємо ID користувача з сесії.

        if (userId == null) {
            response.sendRedirect("login"); // Якщо ID користувача відсутній, перенаправляємо на сторінку входу.
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, name, author, year, genre FROM books WHERE userid = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id"); // Отримуємо ID книги.
                        String name = rs.getString("name"); // Отримуємо назву книги.
                        String author = rs.getString("author"); // Отримуємо автора книги.
                        String year = rs.getString("year"); // Отримуємо рік видання книги.
                        String genre = rs.getString("genre"); // Отримуємо жанр книги.

                        Book book = new Book(); // Створюємо новий об'єкт книги.
                        book.setId(id); // Встановлюємо ID книги.
                        book.setName(name); // Встановлюємо назву книги.
                        book.setAuthor(author); // Встановлюємо автора книги.
                        book.setYear(year); // Встановлюємо рік видання книги.
                        book.setGenre(genre); // Встановлюємо жанр книги.
                        books.add(book); // Додаємо книгу до списку книг.
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Виводимо інформацію про помилку у разі виникнення виключення SQL.
        }

        request.setAttribute("books", books); // Встановлюємо атрибут для списку книг, щоб передати його на JSP-сторінку.
        request.getRequestDispatcher("/select.jsp").forward(request, response); // Перенаправляємо запит на JSP-сторінку для відображення книг.
    }
}
