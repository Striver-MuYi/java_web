package com.mk.servlet;

import com.mk.dao.SysUserDao;
import com.mk.entity.SysUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录管理
 */
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("登陆请求");
        //  获取参数
        String name = request.getParameter("userName");
        String pwd = request.getParameter("password");
//        if (name == null || name.length() <= 0 && pwd == null || pwd.length() <= 0) { // null/""
//            // 表单为空校验
//            request.getRequestDispatcher("/login").forward(request, response);
//            request.setAttribute("msg","用户名密码不能为空");
//            return;
//        }
        //  创建用户对象
        SysUserDao sysUserDao = new SysUserDao();
        //  执行查询
        SysUser sysUser = sysUserDao.getSysUserByUserNameAndPassword(name, pwd);

        if (null != sysUser.getUserName()) {
            // 用户存在
            System.out.println("sysUser = " + sysUser);
            // 转发页面
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } else {
            // 用户不存在
            System.out.println("sysUser = " + sysUser);
            request.setAttribute("msg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
