package pl.javastart.zadania.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/calcmeter")
public class CalcServlet extends HttpServlet {
    private static String KILOMETR = "kilometr";
    private static String CENTYMETR = "centymetr";
    private static String MILIMETR = "milimetr";
    private static String KILOGRAM = "kilogram";
    private static String GRAM = "gram";
    private static String MILIGRAM = "miligram";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/HTML");
        PrintWriter writer = response.getWriter();
        Map<String, Double> map = getValues(request);
        if(checkValues(map)){
            showValues(calculator(map), writer);
        }else
            writer.println("<h1>Podaj wiecej niż jedną wartość!</h1>");
        writer.close();
    }

    private Map<String, Double> getValues(HttpServletRequest request){
        String kilometr = request.getParameter(KILOMETR);
        String centymetr = request.getParameter(CENTYMETR);
        String milimetr = request.getParameter(MILIMETR);
        String kilogram = request.getParameter(KILOGRAM);
        String gram = request.getParameter(GRAM);
        String miligram = request.getParameter(MILIGRAM);
        Map<String, Double> map = new HashMap<>();

        setValue(kilometr, centymetr, milimetr, map, KILOMETR, CENTYMETR, MILIMETR);
        setValue(kilogram, gram, miligram, map, KILOGRAM, GRAM, MILIGRAM);
        return map;
    }

    private void setValue(String kilogram, String gram, String miligram, Map<String, Double> map, String kilogram2, String gram2, String miligram2) {
        if ((kilogram != null) && kilogram !="")
            map.put(kilogram2, Double.valueOf(kilogram));
        if ((gram != null) && gram !="")
            map.put(gram2, Double.valueOf(gram));
        if ((miligram != null) && miligram !="")
            map.put(miligram2, Double.valueOf(miligram));
    }

    private boolean checkValues (Map<String, Double> map){
        return (map.size()==1);
    }

    private Map<String, Double> calculator (Map<String, Double> map){

        if (map.containsKey(KILOMETR)) {
            map.put(MILIMETR, map.get(KILOMETR) * 1000);
            map.put(CENTYMETR, map.get(KILOMETR) * 100);
        }
        if (map.containsKey(CENTYMETR)) {
            map.put(MILIMETR, map.get(CENTYMETR) * 10);
            map.put(KILOMETR, map.get(CENTYMETR) / 100);
        }
        if (map.containsKey(MILIMETR)) {
            map.put(KILOMETR, map.get(MILIMETR) / 1000);
            map.put(CENTYMETR, map.get(MILIMETR) / 10);
        }
        if (map.containsKey(KILOGRAM)) {
            map.put(MILIGRAM, map.get(KILOGRAM) * 1000);
            map.put(GRAM, map.get(KILOGRAM) * 100);
        }
        if (map.containsKey(GRAM)) {
            map.put(MILIGRAM, map.get(GRAM) * 10);
            map.put(KILOGRAM, map.get(GRAM) / 100);
        }
        if (map.containsKey(MILIGRAM)) {
            map.put(KILOGRAM, map.get(MILIGRAM) / 1000);
            map.put(GRAM, map.get(MILIGRAM) / 10);
        }
        return map;
    }

    private void showValues (Map<String, Double> map, PrintWriter writer) {
        writer.println("<h1>Podana wartość w przeliczeniu na:</h1>");
        for (Map.Entry<String, Double> entry: map.entrySet()){
            writer.println("<p>"+ entry.getKey() + ": " + entry.getValue() + "</p>");
        }
    }
}
