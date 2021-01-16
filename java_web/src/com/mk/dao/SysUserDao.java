package com.mk.dao;

import com.mk.JDBCUtil;
import com.mk.entity.Stock;
import com.mk.entity.SysUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SysUserDao {


    /**
     * 通过用户名获取信息
     *
     * @param userName
     * @return
     */
    public SysUser getSysUserByUserNameAndPassword(String userName, String password) {
        String sql = "select * from user where username = ? and password = ?";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        SysUser sysUser = new SysUser();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                sysUser.setId(rs.getInt("id"));
                sysUser.setUserName(rs.getString("username"));
                sysUser.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, ps, connection);
        }
        return sysUser;
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    public int saveSysUser(SysUser user) {
        System.out.println("user = " + user);
        String sql = "insert into user(username,password) values(?,?)";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, connection);
        }
        return result;
    }

    /**
     * 查找所有 1-10
     *
     * @return
     */
    public List<SysUser> findAll() {
        String sql = "select * from user limit 0, 10";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SysUser> result = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                SysUser user = new SysUser();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, ps, connection);
        }

        return result;
    }

    public int deleteSysUser(String id) {
        String sql = "delete from user where id=?";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, connection);
        }
        return rs;
    }

    public int modifySysUser(SysUser user) {
        String sql = "update user set username=?,password=? where id=?";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, connection);
        }
        return rs;
    }

    //  id查找用户信息
    public SysUser findSysUserById(String id) {
        String sql = "select * from user where id=?";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        SysUser user = new SysUser();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, connection);
        }
        return user;
    }
    /**
     *  根据名称模糊查询
     *
     */
    public List<SysUser> findByName(String name) {
        String sql = "select * from user where username like '%"+name+"%'";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SysUser> list = null;
        try {
            ps = connection.prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                SysUser sysUser = new SysUser();
                sysUser.setId(rs.getInt("id"));
                sysUser.setUserName(rs.getString("username"));
                sysUser.setPassword(rs.getString("password"));
                list.add(sysUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
