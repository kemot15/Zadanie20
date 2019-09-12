package pl.javastart.zadania.servlet;

import pl.javastart.zadania.service.TextService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/text")
public class TextServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String text = request.getParameter("text_area");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/HTML");
        PrintWriter writer = response.getWriter();
        TextService textService = new TextService();

        writer.println(text);
        writer.println("<p>Ilość słów: " + textService.wordQuantity(text) + "</p>");
        writer.println("<p>Ilość znaków: " + textService.charQuantity(text)+ "</p>");
        writer.println("<p>Ilość znaków bez spacji: " + textService.charOnlyQuantity(text) + "</p>");
        writer.println("<p>Palindrom: " + textService.isPalindrome(text) + "</p>");
        writer.close();
    }


}
