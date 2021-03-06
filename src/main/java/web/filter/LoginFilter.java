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
        filterName = "LoginFilter",
        urlPatterns = {"/account/*","/order/*","/play/*","/schedule/*","/seat/*","/studio/*","/ticket/*"}
)
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("text/json; charset=UTF-8");
        }catch (Exception e){

        }

        if(((HttpServletRequest)servletRequest).getSession().getAttribute("user") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        else {

            PrintWriter printWriter = ((HttpServletResponse)servletResponse).getWriter();
            printWriter.print(new Gson().toJson(new Result("登录信息失效！")));
            printWriter.close();
        }
    }

    public void init(FilterConfig config) throws ServletException{

    }
    public void destroy(){

    }
}
