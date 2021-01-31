package ru.appline;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
            pw.print("Все пользователи удалены");
            model.clear();
        } else if (id > 0){
            if (id > model.getFromList().size()){
                pw.print(gson.toJson("Такого пользователя не существует"));
            } else pw.print("Удален пользователь с ID:" + id);
            model.remove(id);
        }
        else pw.print(gson.toJson("ID должен быть больше 0"));
    }
}

