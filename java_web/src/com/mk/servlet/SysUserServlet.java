package com.mk.servlet;

import com.mk.dao.SysUserDao;
import com.mk.entity.Order;
import com.mk.entity.SysUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *  用户管理
 */
@WebServlet("/sysUser/*")
public class SysUserServlet extends HttpServlet {

    // 创建dao层
    private SysUserDao userDao = new SysUserDao();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        req.getServerName()
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        // 核心处理器
        // 获取请求路径
        String uri = req.getRequestURI();
        if (uri.contains("/addSysUser")) {
            addSysUser(req, res);
        } else if (uri.contains("/listAll")) {
            listAll(req,res);
        }else if (uri.contains("/modify")) {
            modify(req,res);
        } else if (uri.contains("/delete")) {
            delete(req, res);
        } else if (uri.contains("/findById")) {
            findById(req, res);
        }else if (uri.contains("/findByName")){
            findByName(req, res);
        }
    }

    //  通过名称查找
    private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取参数
        String username = req.getParameter("username");
        //  获取数据
        List<SysUser> list = userDao.findByName(username);
        //  存request
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/sysUserView.jsp").forward(req, resp);
    }

    //  通过id查找用户
    private void findById(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //  获取id
        String param = req.getParameter("uid");
        //  执行查询
        SysUser user = userDao.findSysUserById(param);
        if (user == null) {
            System.out.println("没有此用户");
        }
        //  放入request
        req.setAttribute("user",user);
        //  转发页面
        req.getRequestDispatcher("/editSysUser.jsp").forward(req, res);
    }

    //  删除用户信息
    private void delete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //  获取id
        String id = req.getParameter("uid");
        //  执行删除
        int result = userDao.deleteSysUser(id);
        if (result == 0) {
            req.setAttribute("msg", "删除用户失败");
        } else {
            req.setAttribute("msg", "删除用户成功");
        }
        req.setAttribute("returnPageName", "返回用户列表");
        List<SysUser> list = userDao.findAll();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/sysUserView.jsp").forward(req, res);
    }

    // 修改用户信息
    private void modify(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //  获取参数
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        SysUser user = new SysUser();
        //  绑定参数
        user.setId(Integer.parseInt(id));
        user.setUserName(username);
        user.setPassword(password);
        //  执行查询
        int result = userDao.modifySysUser(user);
        if (result == 0) {
            req.setAttribute("msg", "修改用户失败");
        } else {
            req.setAttribute("msg", "修改用户成功");
        }
        req.setAttribute("returnPageName", "返回用户列表");
        List<SysUser> list = userDao.findAll();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/sysUserView.jsp").forward(req, res);
    }

    // 添加用户信息
    private void addSysUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //  获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null && password ==null) return;
        SysUser user = new SysUser();
        //  绑定参数
        user.setUserName(username);
        user.setPassword(password);
        //  执行保存
        int result = userDao.saveSysUser(user);
        if (result == 0) {
            req.setAttribute("msg", "保存用户失败");
        } else {
            req.setAttribute("msg", "保存用户成功");
        }
        req.setAttribute("returnPageName", "返回用户列表");
        List<SysUser> list = userDao.findAll();
        req.setAttribute("list", list);
        //  转发页面
        req.getRequestDispatcher("/sysUserView.jsp").forward(req, res);

    }

    // 获取1-10
    private void listAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //  执行查询
        List<SysUser> list = userDao.findAll();
        //  将数据放入request中
        req.setAttribute("list", list);
        //  转发
        req.getRequestDispatcher("/sysUserView.jsp").forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req,resp);
    }

}
