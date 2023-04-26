package org.example.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet，根据请求的最后一段路径进行方法分发
 * 而不是根据请求方法进行方法分发
 */
public class BaseServlet extends HttpServlet {
    // 根据请求的最后一段路径进行分发

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求路径
        String uri = req.getRequestURI(); // /WebFinal_war/Brand/selectAll

        // 2.获取最后一段路径（方法名）
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1); // 去掉'/'

        // 3.执行方法（反射）
        // 3.1 获取BrandServlet字节码对象
        // 谁（某个类/子类）调用我（this 所在的方法），我（this）代表谁
        Class<? extends BaseServlet> cls = this.getClass();

        // 3.2 获取方法的method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 执行方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
