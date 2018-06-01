package web.filter;

import com.google.gson.Gson;
import web.model.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Enumeration;


@WebFilter(
        filterName = "TestFilter",
        urlPatterns = {"/play/*"}
)
public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            servletResponse.setCharacterEncoding("UTF-8");
        } catch (Exception e) {

        }
     //   BufferedReader br = servletRequest.getReader();
    //    System.out.println(br.readLine());
     //   br.close();
        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }
}
