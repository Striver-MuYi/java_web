package com.mk.dao;

import com.mk.JDBCUtil;
import com.mk.entity.Order;
import com.mk.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDao {


    /**
     * 获取库存信息列表
     */
    public List<Stock> list() {
        String sql = "select id,productName,code,price,numb,supplier from stock";
        Connection conn = JDBCUtil.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Stock> list = new ArrayList<Stock>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt("id"));
                stock.setProductName(rs.getString("productName"));
                stock.setCode(rs.getString("code"));
                stock.setPrice(rs.getString("price"));
                stock.setNumber(rs.getString("numb"));
                stock.setSupplier(rs.getString("supplier"));
                list.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, ps, conn);
        }

        return list;
    }

    /**
     * 添加库存信息
     */
    public int addStock(Stock stock) {
        String sql = "insert into stock(productName,code,price,numb,supplier) values(?,?,?,?,?)";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, stock.getProductName());
            ps.setString(2, stock.getCode());
            ps.setString(3, stock.getPrice());
            ps.setString(4, stock.getNumber());
            ps.setString(5, stock.getSupplier());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return rs;
    }

    /**
     * 修改库存信息
     */

    public int modifyStock(Stock stock) {
        String sql = "update stock set productName=?,code=?,price=?,numb=?,supplier=? where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, stock.getProductName());
            ps.setString(2, stock.getCode());
            ps.setString(3, stock.getPrice());
            ps.setString(4, stock.getNumber());
            ps.setString(5, stock.getSupplier());
            ps.setInt(6, stock.getId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return rs;
    }

    /**
     * 删除库存信息
     */
    public int deleteStock(String id) {
        String sql = "delete from stock where id=?";
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
    public Stock findById(String id) {
        String sql = "select * from stock where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Stock stock = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                stock = new Stock();
                stock.setId(rs.getInt("id"));
                stock.setProductName(rs.getString("productName"));
                stock.setCode(rs.getString("code"));
                stock.setPrice(rs.getString("price"));
                stock.setNumber(rs.getString("numb"));
                stock.setSupplier(rs.getString("supplier"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return stock;
    }
    /**
     *  根据名称模糊查询
     *
     */
    public List<Stock> findByName(String name) {
        String sql = "select * from stock where productName like '%"+name+"%'";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Stock> list = null;
        try {
            ps = connection.prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt("id"));
                stock.setProductName(rs.getString("productName"));
                stock.setCode(rs.getString("code"));
                stock.setPrice(rs.getString("price"));
                stock.setNumber(rs.getString("numb"));
                stock.setSupplier(rs.getString("supplier"));
                list.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
