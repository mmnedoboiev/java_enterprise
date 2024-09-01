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

@WebServlet("/addbook") // Анотація, що вказує URL-адресу, за якою буде доступний цей сервлет.
public class AddBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримуємо сесію та перевіряємо наявність користувача
        HttpSession session = request.getSession(false); // Отримуємо існуючу сесію, не створюючи нову.
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp"); // Якщо користувач не авторизований, перенаправляємо на сторінку входу.
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId"); // Отримуємо ID користувача з сесії.

        if (userId == null) {
            response.sendRedirect("login.jsp"); // Якщо ID користувача відсутній, перенаправляємо на сторінку входу.
            return;
        }

        // Отримуємо параметри книги з запиту.
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String year = request.getParameter("year");
        String genre = request.getParameter("genre");
        request.setCharacterEncoding("UTF-8"); // Встановлюємо кодування для запиту.

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO books (name,author,year,genre,userid) VALUES (?,?,?,?,?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name); // Встановлюємо значення для параметра name.
                stmt.setString(2, author); // Встановлюємо значення для параметра author.
                stmt.setString(3, year); // Встановлюємо значення для параметра year.
                stmt.setString(4, genre); // Встановлюємо значення для параметра genre.
                stmt.setInt(5, userId); // Встановлюємо значення для параметра userid.
                stmt.executeUpdate(); // Виконуємо SQL-запит.
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Виводимо інформацію про помилку у разі виникнення виключення SQL.
        }

        response.sendRedirect("addbook.jsp"); // Після успішного додавання книги перенаправляємо на сторінку додавання.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Обробка GET-запиту: перенаправляємо на сторінку addbook.jsp.
        request.getRequestDispatcher("/addbook.jsp").forward(request, response);
    }

}
