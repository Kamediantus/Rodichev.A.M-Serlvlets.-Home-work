package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = response.getWriter();
//
//        int id = Integer.parseInt(request.getParameter("id"));
//        if (id == 0){
//            pw.print("<html> + " +
//                    "<h3>Доступные пользователи:</h3><br/>" +
//                    "ID пользователя: " +
//                    "<ul>"
//                    );
//            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()){
//                pw.print("<li>" + entry.getKey() + " </li>" +
//                        "<ul>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>");
//            }
//            pw.print("</ul>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        } else if (id > 0){
//            if (id > model.getFromList().size()){
//                pw.print("<html>" +
//                        "<h3>Такогоо пользователя нет((</h3>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "<br/></html>");
//            } else pw.print("<html>" +
//                    "<h3>Запрошенный пользователь: </h3><br/>" +
//                    "Имя: " + model.getFromList().get(id).getName() +
//                    "<br/>Фамилия: " + model.getFromList().get(id).getSurname() +
//                    "<br/>Зарплата: " + model.getFromList().get(id).getSalary() +
//                    "<br/><a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//
//        }
//        else pw.print("<html>" +
//                    "<h3>ID должен быть больше 0((</h3>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "<br/></html>");
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter pw = response.getWriter();
        StringBuffer jb = new StringBuffer();

            String line;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null ){
                    jb.append(line);
                }
            } catch (Exception e){
                System.out.println("error");
            }
        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
//        int id = Integer.parseInt(request.getParameter("id"));
        int id = jobj.get("id").getAsInt();
        if (id == 0){
            pw.print(gson.toJson(model.getFromList()));
        } else if (id > 0){
            if (id > model.getFromList().size()){
                pw.print(gson.toJson("Такого пользователя не существует"));
            } else pw.print(gson.toJson(model.getFromList().get(id)));
        }
        else pw.print(gson.toJson("ID должен быть больше 0"));
    }
}



