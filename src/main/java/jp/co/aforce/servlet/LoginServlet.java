package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.bean.Customer;
import jp.co.aforce.dao.CustomerDAO;

@WebServlet("/jsp/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        CustomerDAO dao = new CustomerDAO();
        try {
            Customer customer = dao.search(login, password);

            if (customer != null) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                response.sendRedirect("login-out.jsp");
            } else {
                request.setAttribute("login", login);
                request.setAttribute("error", "IDもしくはパスワードが違います");
                request.getRequestDispatcher("login-error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
