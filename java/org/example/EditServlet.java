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
        String idParam = request.getParameter("id"); // Отримуємо параметр id з запиту.
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("select"); // Якщо параметр id відсутній або порожній, перенаправляємо на список книг.
            return;
        }

        int id = Integer.parseInt(idParam); // Перетворюємо id з String у int.
        Book book = null; // Ініціалізуємо об'єкт book.

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, name, author, year, genre FROM books WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) { // Виконуємо SQL-запит і обробляємо результати.
                    if (rs.next()) { // Якщо книга знайдена.
                        book = new Book(); // Створюємо новий об'єкт книги.
                        book.setId(rs.getInt("id")); // Встановлюємо ID книги.
                        book.setName(rs.getString("name")); // Встановлюємо назву книги.
                        book.setAuthor(rs.getString("author")); // Встановлюємо автора книги.
                        book.setYear(rs.getString("year")); // Встановлюємо рік видання книги.
                        book.setGenre(rs.getString("genre")); // Встановлюємо жанр книги.
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Виводимо інформацію про помилку у разі виникнення виключення SQL.
        }

        if (book == null) {
            response.sendRedirect("select"); // Якщо книга не знайдена, перенаправляємо на список книг.
            return;
        }

        request.setAttribute("book", book); // Встановлюємо атрибут для об'єкта книги, щоб передати його на JSP-сторінку.
        request.getRequestDispatcher("/edit.jsp").forward(request, response); // Перенаправляємо запит на JSP-сторінку для редагування книги.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Отримуємо параметр id з запиту та перетворюємо його в int.
        String name = request.getParameter("name"); // Отримуємо нову назву книги з форми.
        String author = request.getParameter("author"); // Отримуємо нового автора книги з форми.
        String year = request.getParameter("year"); // Отримуємо новий рік видання книги з форми.
        String genre = request.getParameter("genre"); // Отримуємо новий жанр книги з форми.

        try (Connection connection = DatabaseConnection.getConnection()) { // Отримуємо з'єднання з базою даних.
            // SQL-запит для оновлення даних книги.
            String sql = "UPDATE books SET name = ?, author = ?, year = ?, genre = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) { // Підготовлений вираз для безпечного оновлення даних.
                stmt.setString(1, name); // Встановлюємо нове значення для параметра name.
                stmt.setString(2, author); // Встановлюємо нове значення для параметра author.
                stmt.setString(3, year); // Встановлюємо нове значення для параметра year.
                stmt.setString(4, genre); // Встановлюємо нове значення для параметра genre.
                stmt.setInt(5, id); // Встановлюємо значення для параметра id.
                stmt.executeUpdate(); // Виконуємо SQL-запит для оновлення даних книги.
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Виводимо інформацію про помилку у разі виникнення виключення SQL.
        }

        response.sendRedirect("select"); // Після успішного оновлення даних книги перенаправляємо на сторінку зі списком книг.
    }
}
