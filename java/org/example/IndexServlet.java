package org.example;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Встановлення кодування для відповіді
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Connection connection = DatabaseConnection.getConnection();
        String connectionStatus;

        // Перевірка, чи з'єднання успішно встановлено
        if (connection != null) {
            connectionStatus = "З'єднання успішно встановлено!";
        } else {
            connectionStatus = "Не вдалося встановити з'єднання.";
        }

        System.out.println("Connection Status: " + connectionStatus); // Перевірка в консолі

        // Передача результату перевірки на JSP-сторінку
        request.setAttribute("connectionStatus", connectionStatus);
        request.getRequestDispatcher("index").forward(request, response);
    }
}
