package servlets;

import services.BookService;
import services.UserService;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private BookService bookService;
    @Override
    public void init(ServletConfig config)  {
        ServletContext context = config.getServletContext();
        bookService = (BookService) context.getAttribute("bookService");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("books", bookService.getAllBooks());
            request.getServletContext().getRequestDispatcher("/WEB-INF/ftl/pages/main.ftl").forward(request, response);
        } catch (ServletException e) {
            throw new IllegalArgumentException("Не удаетс получить main.jsp");
        } catch (IOException e) {
            throw new IllegalArgumentException("Не удаетчя получить все книги через  bookService");
        }
    }
}