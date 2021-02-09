package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.MessageClass;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogOutServlet", urlPatterns = {"/LogOutServlet"})
public class LogOutServlet extends HttpServlet {
    private final Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String device = request.getParameter("device");
        String action = request.getParameter("action");

        if(device != null && device !="" && device.equals("Mobile")){
            if(action.equals("logout")){
                HttpSession session=request.getSession();
                session.invalidate();
                MessageClass messageClass = new MessageClass(true, "Logged out Successfully!");
                String output = this.gson.toJson(messageClass);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(output);
            }else{
                MessageClass messageClass = new MessageClass(false, "Technical Failure!");
                String output = this.gson.toJson(messageClass);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(output);
            }

        }else{
            HttpSession session=request.getSession();
            session.invalidate();

            request.setAttribute("showLogout", true);
            request.setAttribute("showLogoutMsg", "Successfully Logged out!");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }


    }
}
