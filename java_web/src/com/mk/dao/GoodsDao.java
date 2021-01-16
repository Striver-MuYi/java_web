package com.mk.dao;

import com.mk.JDBCUtil;
import com.mk.entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {

    /**
     *  获取商品信息列表
     */
    public List<Goods> list() {
        String sql = "select id,goodsName,goodsPrice,goodsType from goods";
        Connection conn = JDBCUtil.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Goods> list = new ArrayList<Goods>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods Goods = new Goods();
                Goods.setId(rs.getInt("id"));
                Goods.setGoodsName(rs.getString("goodsName"));
                Goods.setGoodsPrice(rs.getString("goodsPrice"));
                Goods.setGoodsType(rs.getString("goodsType"));
                list.add(Goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, ps, conn);
        }

        return list;
    }

    /**
     *  添加商品信息
     */
    public int addGoods(Goods goods) {
        String sql = "insert into goods(goodsName,goodsPrice,goodsType) values(?,?,?)";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsName());
            ps.setString(2, goods.getGoodsPrice());
            ps.setString(3, goods.getGoodsType());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return rs;
    }

    /**
     * 修改商品信息
     */

    public int modifyGoods(Goods goods) {
        String sql = "update goods set goodsName=?,goodsPrice=?,goodsType=? where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsName());
            ps.setString(2, goods.getGoodsPrice());
            ps.setString(3, goods.getGoodsType());
            ps.setInt(4, goods.getId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return rs;
    }

    /**
     * 删除商品信息
     */
    public int deleteGoods(String id) {
        String sql = "delete from goods where id=?";
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
    public Goods findById(String id) {
        String sql = "select * from goods where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Goods goods = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setGoodsName(rs.getString("goodsName"));
                goods.setGoodsPrice(rs.getString("goodsPrice"));
                goods.setGoodsType(rs.getString("goodsType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(null, ps, conn);
        }
        return goods;
    }

    /**
     *  根据名称模糊查询
     *
     */
    public List<Goods> findByName(String name) {
        String sql = "select * from goods where goodsName like '%"+name+"%'";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Goods> list = null;
        try {
            ps = connection.prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setGoodsName(rs.getString("goodsName"));
                goods.setGoodsPrice(rs.getString("goodsPrice"));
                goods.setGoodsType(rs.getString("goodsType"));
                list.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
