package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?userName=hello&age=20
 *
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?userName=hello&userName=kim&age=20
 */
@WebServlet(name="requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("전체 파라미터 조회 - START");

        request.getParameterNames().asIterator()
                .forEachRemaining(param -> System.out.println(param + " = " + request.getParameter(param)));

        System.out.println("전체 파라미터 조회 - END");

        //단일 파라미터 조회시 request.getParameterValues의 첫번째값을 반환한다.
        System.out.println("단일 파라미터 조회 ");
        String userName = request.getParameter("userName");
        System.out.println("request.getParameter(\"userName\") = " + userName);

        String age = request.getParameter("age");
        System.out.println("request.getParameter(\"age\") = " + age);
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] userNames = request.getParameterValues("userName");
        System.out.println("request.getParameterValues(\"userName\")");
        for(String name : userNames){
            System.out.println("userName = " + name);
        }

        response.getWriter().write("ok");
    }
}
