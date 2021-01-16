package com.mk.servlet;

import com.mk.dao.OrderDao;
import com.mk.entity.Goods;
import com.mk.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 订单信息管理
 * /order/*    表示匹配所有
 */
@WebServlet("/order/*")
public class OrderServlet extends HttpServlet {

    //  创建dao对象
    OrderDao orderDao = new OrderDao();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String uri = req.getRequestURI();
        if (uri.contains("/addOrder")) {
            addOrder(req, resp);
        } else if (uri.contains("/modifyOrder")) {
            modifyOrder(req, resp);
        } else if (uri.contains("/deleteOrderById")) {
            deleteOrder(req, resp);
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
        String orderName = req.getParameter("orderName");
        //  获取数据
        List<Order> list = orderDao.findByName(orderName);
        //  存request
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/orderView.jsp").forward(req, resp);
    }

    //  查找所有订单信息
    private void listAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  执行查询
        List<Order> list = orderDao.list();
        for (Order order : list) {
            System.out.println("order = " + order);
        }
        //  将数据放入request中
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/orderView.jsp").forward(req, resp);
    }
    //  通过ID查找订单
    private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取id
        String param = req.getParameter("oid");
        //  执行查询
        Order order = orderDao.findById(param);
        if (order == null) {
            System.out.println("没有此订单");
            req.setAttribute("msg","没有此订单");
        }
        //  放入request
        req.setAttribute("order",order);
        //  转发页面
        req.getRequestDispatcher("/editOrder.jsp").forward(req, resp);
    }
    //  添加
    private void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String orderName = req.getParameter("orderName");
        String orderPrice = req.getParameter("orderPrice");
        String orderNum = req.getParameter("orderNum");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String user_name = req.getParameter("user_name");
        Order order = new Order();
        //  绑定参数
        order.setOrderName(orderName);
        order.setOrderPrice(orderPrice);
        order.setOrderNum(orderNum);
        order.setAddress(address);
        order.setPhone(phone);
        order.setUser_name(user_name);
        //  执行查询
        int result = orderDao.addOrder(order);
        if (result == 0) {
            req.setAttribute("msg", "添加订单失败");
        } else {
            req.setAttribute("msg", "添加订单成功");
        }
        req.setAttribute("returnPageName", "返回订单列表");
        List<Order> list = orderDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/orderView.jsp").forward(req, resp);
    }
    //  删除
    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取id
        String oid = req.getParameter("oid");
        //  执行删除
        int result = orderDao.deleteOrder(oid);
        if (result == 0) {
            req.setAttribute("msg", "删除订单失败");
        } else {
            req.setAttribute("msg", "删除订单成功");
        }
        req.setAttribute("returnPageName", "返回订单列表");
        List<Order> list = orderDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/orderView.jsp").forward(req, resp);
    }
    //  修改
    private void modifyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String id = req.getParameter("id");
        String orderName = req.getParameter("orderName");
        String orderPrice = req.getParameter("orderPrice");
        String orderNum = req.getParameter("orderNum");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String user_name = req.getParameter("user_name");
        Order order = new Order();
        //  绑定参数
        order.setId(Integer.parseInt(id));
        order.setOrderName(orderName);
        order.setOrderPrice(orderPrice);
        order.setOrderNum(orderNum);
        order.setAddress(address);
        order.setPhone(phone);
        order.setUser_name(user_name);
        //  执行查询
        int result = orderDao.modifyOrder(order);
        if (result == 0) {
            req.setAttribute("msg", "修改订单失败");
        } else {
            req.setAttribute("msg", "修改订单成功");
        }
        req.setAttribute("returnPageName", "返回订单列表");
        List<Order> list = orderDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/orderView.jsp").forward(req, resp);
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
