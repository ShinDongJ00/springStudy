package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        printStartLine(request);
        printHeader(request);
        printHeadersUtils(request);
        printEtc(request);

        response.getWriter().write("ok");
    }

    private void printStartLine(HttpServletRequest request){
        System.out.println("--- REQUEST-LINT - START ---");

        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocol() = " + request.getProtocol());

        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());

        System.out.println("--- REQUEST-LINE - END ---");
        System.out.println();
    }

    private void printHeader(HttpServletRequest request){
        System.out.println("--- Headers - START ---");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ":" + request.getHeader(headerName)));

        System.out.println("--- Headers - END ---");
        System.out.println();
    }

    private void printHeadersUtils(HttpServletRequest request){
        System.out.println("--- Header ํธ์ ์กฐํ start ---");
        System.out.println("[Host ํธ์ ์กฐํ]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host ํค๋
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host ํค๋
        System.out.println();
        System.out.println("[Accept-Language ํธ์ ์กฐํ]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie ํธ์ ์กฐํ]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content ํธ์ ์กฐํ]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println("--- Header ํธ์ ์กฐํ end ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request){
        System.out.println("--- ๊ธฐํ ์กฐํ start ---");
        System.out.println("[Remote ์?๋ณด]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local ์?๋ณด]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //
        System.out.println("--- ๊ธฐํ ์กฐํ end ---");
        System.out.println();
    }
}
