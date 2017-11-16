package org.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="SimpleServlet", urlPatterns={"/"})
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>EAP QE</h1>");

        out.println("<p><ul>");
        Properties properties = System.getProperties();
        for(String key : properties.stringPropertyNames()) {
          String value = properties.getProperty(key);
          out.println("<li>" + key + " =&gt; " + value + "</li>");
        }
        out.println("</ul></p>");
    }
}
