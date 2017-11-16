package org.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="HostServlet", urlPatterns={"/host"})
public class HostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String id;

    static {
    	id = System.getProperty("id");
    	if(id == null) id = System.getenv("id");
    	if(id == null) id = Integer.toString(new Random().nextInt(16) + 1);
    }
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.addHeader("X-processed-by", id);

        InetAddress addr = InetAddress.getLocalHost();
        out.printf("url: %s at %s:%s, %s, %s%n", request.getRequestURL(),
            request.getServerName(), request.getServerPort(), addr.getHostAddress(), addr.getCanonicalHostName());

        String param = request.getParameter("foo");
        // String header = request.getHeader("Long-Running-Action");
        String header = request.getHeader("a");
        System.out.printf("Processing at %s... h:[%s], p:[%s]%n", id,
        header != null ? "a="+header : "", param != null ? "foo=" + param : "");
    }
}
