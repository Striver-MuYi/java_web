package com.mk.servlet;

import com.mk.dao.StockDao;
import com.mk.entity.Order;
import com.mk.entity.Stock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 库存信息管理
 * /stock/*    表示匹配所有
 */
@WebServlet("/stock/*")
public class StockServlet extends HttpServlet {

    //  创建dao对象
    StockDao stockDao = new StockDao();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String uri = req.getRequestURI();
        if (uri.contains("/addStock")) {
            addStock(req, resp);
        } else if (uri.contains("/modifyStock")) {
            modifyStock(req, resp);
        } else if (uri.contains("/deleteStockById")) {
            deleteStock(req, resp);
        } else if (uri.contains("/findById")) {
            findById(req, resp);
        } else if (uri.contains("/listAll")) {
            listAll(req, resp);
        }else if (uri.contains("/findByName")){
            findByName(req, resp);
        }
    }
    //  通过名称查找
    private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String productName = req.getParameter("productName");
        //  获取数据
        List<Stock> list = stockDao.findByName(productName);
        //  存request
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/stockView.jsp").forward(req, resp);
    }

    //  查找所有库存信息
    private void listAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  执行查询
        List<Stock> list = stockDao.list();
        //  将数据放入request中
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/stockView.jsp").forward(req, resp);
    }

    //  通过ID查找库存信息
    private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取id
        String sid = req.getParameter("sid");
        //  执行查询
        Stock stock = stockDao.findById(sid);
        System.out.println("stock = " + stock);
        if (stock == null) {
            System.out.println("没有此库存");
            req.setAttribute("msg", "没有此库存");
        }
        //  放入request
        req.setAttribute("stock", stock);
        //  转发页面
        req.getRequestDispatcher("/editStock.jsp").forward(req, resp);
    }

    //  添加库存信息
    private void addStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String productName = req.getParameter("productName");
        String code = req.getParameter("code");
        String price = req.getParameter("price");
        String number = req.getParameter("number");
        String supplier = req.getParameter("supplier");
        Stock stock = new Stock();
        //  绑定参数
        stock.setProductName(productName);
        stock.setCode(code);
        stock.setPrice(price);
        stock.setNumber(number);
        stock.setSupplier(supplier);
        //  执行查询
        int result = stockDao.addStock(stock);
        if (result == 0) {
            req.setAttribute("msg", "添加库存失败");
        } else {
            req.setAttribute("msg", "添加库存成功");
        }
        req.setAttribute("returnPageName", "返回库存列表");
        List<Stock> list = stockDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/stockView.jsp").forward(req, resp);
    }

    //  删除库存信息
    private void deleteStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取id
        String sid = req.getParameter("sid");
        //  执行删除
        int result = stockDao.deleteStock(sid);
        if (result == 0) {
            req.setAttribute("msg", "删除库存失败");
        } else {
            req.setAttribute("msg", "删除库存成功");
        }
        req.setAttribute("returnPageName", "返回库存列表");
        List<Stock> list = stockDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/stockView.jsp").forward(req, resp);
    }

    //  修改库存信息
    private void modifyStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String id = req.getParameter("id");
        String productName = req.getParameter("productName");
        String code = req.getParameter("code");
        String price = req.getParameter("price");
        String number = req.getParameter("number");
        String supplier = req.getParameter("supplier");
        Stock stock = new Stock();
        //  绑定参数
        stock.setId(Integer.parseInt(id));
        stock.setProductName(productName);
        stock.setCode(code);
        stock.setPrice(price);
        stock.setNumber(number);
        stock.setSupplier(supplier);
        //  执行查询
        int result = stockDao.modifyStock(stock);
        if (result == 0) {
            req.setAttribute("msg", "修改库存失败");
        } else {
            req.setAttribute("msg", "修改库存成功");
        }
        req.setAttribute("returnPageName", "返回库存列表");
        List<Stock> list = stockDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/stockView.jsp").forward(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }
}
