package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession(false);

        String[] addressArr = httpRequest.getRequestURI().split("/");
        String page = addressArr[addressArr.length - 1];

        if (page.equals("login_servlet") || page.equals("user_registration_servlet")) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }

        if (session != null && session.getAttribute("user") != null) {
            filterChain.doFilter(httpRequest, httpResponse);
        } else {
            httpRequest.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
