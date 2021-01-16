package com.mk.dao;

import com.mk.JDBCUtil;
import com.mk.entity.Goods;
import com.mk.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {


    /**
     *  获取订单信息列表
     */
    public List<Order> list() {
        String sql = "select * from goods_order";
        Connection conn = JDBCUtil.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Order> list = new ArrayList<Order>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderName(rs.getString("orderName"));
                order.setOrderPrice(rs.getString("orderPrice"));
                order.setOrderNum(rs.getString("orderNum"));
                order.setAddress(rs.getString("address"));
                order.setPhone(rs.getString("phone"));
                order.setUser_name(rs.getString("user_name"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, ps, conn);
        }

        return list;
    }

    /**
     *  添加订单信息
     */
    public int addOrder(Order order) {
        String sql = "insert into goods_order(orderName,orderPrice,orderNum,address,phone,user_name) values(?,?,?,?,?,?)";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, order.getOrderName());
            ps.setString(2, order.getOrderPrice());
            ps.setString(3, order.getOrderNum());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPhone());
            ps.setString(6, order.getUser_name());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return rs;
    }

    /**
     * 修改订单信息
     */

    public int modifyOrder(Order order) {
        String sql = "update goods_order set orderName=?,orderPrice=?,orderNum=?,address=?,phone=?,user_name=? where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, order.getOrderName());
            ps.setString(2, order.getOrderPrice());
            ps.setString(3, order.getOrderNum());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPhone());
            ps.setString(6, order.getUser_name());
            ps.setInt(7,order.getId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return rs;
    }

    /**
     * 删除订单信息
     */
    public int deleteOrder(String id) {
        String sql = "delete from goods_order where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return rs;
    }

    /**
     * 根据id查找商品信息
     */
    public Order findById(String id) {
        String sql = "select * from goods_order where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderName(rs.getString("orderName"));
                order.setOrderPrice(rs.getString("orderPrice"));
                order.setOrderNum(rs.getString("orderNum"));
                order.setAddress(rs.getString("address"));
                order.setPhone(rs.getString("phone"));
                order.setUser_name(rs.getString("user_name"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return order;
    }
    /**
     *  根据名称模糊查询
     *
     */
    public List<Order> findByName(String name) {
        String sql = "select * from goods_order where orderName like '%"+name+"%'";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> list = null;
        try {
            ps = connection.prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderName(rs.getString("orderName"));
                order.setOrderPrice(rs.getString("orderPrice"));
                order.setOrderNum(rs.getString("orderNum"));
                order.setAddress(rs.getString("address"));
                order.setPhone(rs.getString("phone"));
                order.setUser_name(rs.getString("user_name"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
