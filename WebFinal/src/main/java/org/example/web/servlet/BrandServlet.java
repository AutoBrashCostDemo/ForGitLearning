package org.example.web.servlet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Brand;
import org.example.pojo.PageBean;
import org.example.service.BrandService;
import org.example.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/Brand/*")
// BrandServlet这个子类调用了BaseServlet里的service方法，所以this代表BrandServlet
public class BrandServlet extends BaseServlet {
    private final BrandService brandService = new BrandServiceImpl();

    // 需要修改数据的id
    private int id;

    // 查询所有
    public void selectAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 1.调用service查询
        List<Brand> brands = brandService.selectAll();

        // 2.转换为json
        String jsonString = JSON.toJSONString(brands);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 添加
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 1.接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine(); // json字符串

        // 转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2.调用service添加
        brandService.add(brand);

        // 3.响应成功的标识
        response.getWriter().write("success");
    }

    // 返回要修改数据的id
    public void infoReturn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 1.接收要修改的行的id
        BufferedReader reader = request.getReader();
        String param = reader.readLine(); // json字符串

        // 转为int类型
        id = JSON.parseObject(param, int.class);
    }

    // 修改数据
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 1.接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine(); // json字符串

        // 转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        brand.setId(id);

        // 2.调用service更新
        brandService.update(brand);

        // 3.响应成功的标识
        response.getWriter().write("success");
    }

    // 删除单个数据
    public void deleteSingle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 1.接收品牌数据
        BufferedReader reader = request.getReader();
        String param = reader.readLine();

        // 接收页面传回的该行的brand信息
        Brand brand = JSON.parseObject(param, Brand.class);

        // 2.调用service删除
        brandService.deleteSingle(brand.getId());

        // 3.响应成功的标识
        response.getWriter().write("success");
    }

    // 批量删除
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 1.接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine(); // json字符串

        // 转为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        // 2.调用service添加
        brandService.deleteByIds(ids);

        // 3.响应成功的标识
        response.getWriter().write("success");
    }

    // 分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 1.接收 当前页码 和 每页显示条数 ur;?currentPage=1&pageSize=5
        // url内部的参数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        // 请求体内部的参数
        BufferedReader reader = request.getReader();
        String params =  reader.readLine();

        // 转为brand
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2.调用service查询
        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        // 3.转换为JSON
        String jsonString = JSON.toJSONString(brandPageBean);

        // 4.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
