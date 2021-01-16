package com.mk.servlet;

import com.mk.dao.GoodsDao;
import com.mk.entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 商品信息管理
 * /goods/*    表示匹配所有
 */
@WebServlet("/goods/*")
public class GoodsServlet extends HttpServlet {

    //  创建dao对象
    GoodsDao goodsDao = new GoodsDao();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String uri = req.getRequestURI();
        if (uri.contains("/addGoods")) {
            addGoods(req, resp);
        } else if (uri.contains("/editGoods")) {
            modifyGoods(req, resp);
        } else if (uri.contains("/deleteGoodsById")) {
            deleteGoods(req, resp);
        } else if (uri.contains("/findById")) {
            findById(req, resp);
        } else if (uri.contains("/listAll")) {
            listAll(req, resp);
        } else if (uri.contains("/findByName")){
            findByName(req, resp);
        }
    }
    //  通过名称查找
    private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String goodsName = req.getParameter("goodsName");
        //  获取数据
        List<Goods> list = goodsDao.findByName(goodsName);
        //  存request
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/goodsView.jsp").forward(req, resp);
    }

    //  查询所有
    private void listAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  执行查询
        List<Goods> list = goodsDao.list();
        //  将数据放入request中
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/goodsView.jsp").forward(req, resp);
    }
    // 根据ID查找
    private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取id
        String param = req.getParameter("gid");
        //  执行查询
        Goods goods = goodsDao.findById(param);
        if (goods == null) {
            System.out.println("没有此商品");
            req.setAttribute("msg","没有此商品");
        }
        //  放入request
        req.setAttribute("goods",goods);
        //  转发页面
        req.getRequestDispatcher("/editGoods.jsp").forward(req, resp);
    }
    //  添加
    private void addGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String goodsName = req.getParameter("goodsName");
        String goodsPrice = req.getParameter("goodsPrice");
        String goodsType = req.getParameter("goodsType");
        Goods goods = new Goods();
        //  绑定参数
        goods.setGoodsName(goodsName);
        goods.setGoodsPrice(goodsPrice);
        goods.setGoodsType(goodsType);
        //  执行查询
        int result = goodsDao.addGoods(goods);
        System.out.println("goodsresult = " + result);
        if (result == 0) {
            req.setAttribute("msg", "添加商品失败");
        } else {
            req.setAttribute("msg", "添加商品成功");
        }
        req.setAttribute("returnPageName", "返回商品列表");
        List<Goods> list =  goodsDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/goodsView.jsp").forward(req, resp);
    }
//    删除
    private void deleteGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取id
        String id = req.getParameter("gid");
        //  执行删除
        int result = goodsDao.deleteGoods(id);
        if (result == 0) {
            req.setAttribute("msg", "删除商品失败");
        } else {
            req.setAttribute("msg", "删除商品成功");
        }
        req.setAttribute("returnPageName", "返回商品列表");
        List<Goods> list =  goodsDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/goodsView.jsp").forward(req, resp);
    }
    //  修改
    private void modifyGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String id = req.getParameter("id");
        String goodsName = req.getParameter("goodsName");
        String goodsPrice = req.getParameter("goodsPrice");
        String goodsType = req.getParameter("goodsType");
        Goods goods = new Goods();
        //  绑定参数
        goods.setId(Integer.parseInt(id));
        goods.setGoodsName(goodsName);
        goods.setGoodsPrice(goodsPrice);
        goods.setGoodsType(goodsType);
        //  执行查询
        int result = goodsDao.modifyGoods(goods);
        if (result == 0) {
            req.setAttribute("msg", "修改商品失败");
        } else {
            req.setAttribute("msg", "修改商品成功");
        }
        req.setAttribute("returnPageName", "返回商品列表");
        List<Goods> list =  goodsDao.list();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/goodsView.jsp").forward(req, resp);
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
