package pl.javastart.zadania.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@WebServlet("/text")
public class TextServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String text = request.getParameter("text_area");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/HTML");
        PrintWriter writer = response.getWriter();

        writer.println(text);
        writer.println("<p>Ilość słów: " + wordQty(text) + "</p>");
        writer.println("<p>Ilość znaków: " + charQty(text)+ "</p>");
        writer.println("<p>Ilość znaków bez spacji: " + charOnlyQty(text) + "</p>");
        writer.println("<p>Palindrom: " + isPalindrome(text) + "</p>");
        writer.close();
    }

    private int charQty (String text){
        char[] textToArrayString = text.toCharArray();
        return textToArrayString.length;
    }

    private int wordQty (String text){
        String[] textToArrayString = text.split(" ");
        return textToArrayString.length;
    }

    private int charOnlyQty (String text){
        String[] textToArrayString = text.split(" ");
        int result = 0;
        for (String s : textToArrayString) {
            result += s.length();
        }
        return result;
    }

    private boolean isPalindrome (String text){
        char[] textToArrayString = text.toCharArray();
        ArrayList<Character> characterTreeSet = new ArrayList<>();
        for (char c : textToArrayString) {
            if (c != ' ')
                characterTreeSet.add(c);
        }
        for (int i = 0; i < characterTreeSet.size()/2; i++)
            if (characterTreeSet.get(i) != characterTreeSet.get(characterTreeSet.size()-1-i))
                return false;
            return true;
    }
}
