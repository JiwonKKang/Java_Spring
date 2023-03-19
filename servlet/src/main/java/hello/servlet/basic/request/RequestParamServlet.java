package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1.   파라미터 전송기능
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

        req.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");

        resp.getWriter().write(req.getParameter("username") + req.getParameter("age"));

    }
}
