package cn.edu.cuit.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.cuit.domain.Express;
import cn.edu.cuit.utils.SqlUtil;
import org.springframework.stereotype.Repository;

@Repository
public class expressDao {
    //五个字符串
    private static String DBDRIVER = "com.mysql.jdbc.Driver";
    private static String DBURL = "jdbc:mysql://localhost:3306/express";
    private static String DBUSER = "root";
    private static String DBPASS = "root";
    private String sql = null;
    //创建连接
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //1加载驱动
    static {
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //2获得连接conn
    public expressDao() {
        try {
            this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //查询操作
    public List<Express> selectPage(int page, int recordOfPage) {

        List<Express> expressList = new ArrayList<Express>();

        String sql = "select * from express"
                + " order by id "
                + SqlUtil.getPageSql(page, recordOfPage);
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Express Ex = new Express();
                Ex.setId(rs.getInt("id"));
                Ex.setName(rs.getString("name"));
                Ex.setPrice(rs.getDouble("price"));
                Ex.setIntroduce(rs.getString("introduce"));

                expressList.add(Ex);
            }
            return expressList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //查看所有数据
    public List<Express> findAll() {

        String sql = "select * from express order by id";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Express> expressList = new ArrayList<Express>();
            while (rs.next()) {
                Express Ex = new Express();
                Ex.setId(rs.getInt("id"));
                Ex.setName(rs.getString("name"));
                Ex.setPrice(rs.getDouble("price"));
                Ex.setIntroduce(rs.getString("introduce"));
                expressList.add(Ex);
            }
            return expressList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 按姓名模糊查找用户返回用户列表

    public List<Express> findByName(String name) {


        String sql = "select * from express where name like ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            List<Express> expressList = new ArrayList<Express>();
            while (rs.next()) {
                Express Ex = new Express();
                Ex.setId(rs.getInt("id"));
                Ex.setName(rs.getString("name"));
                Ex.setPrice(rs.getDouble("price"));
                Ex.setIntroduce(rs.getString("introduce"));
                expressList.add(Ex);
            }
            return expressList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //按id号查找用户 返回一个用户

    public Express findById(Integer id) {

        String sql = "select * from express where id like ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + id + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                Express Ex = new Express();
                Ex.setId(rs.getInt("id"));
                Ex.setName(rs.getString("name"));
                Ex.setPrice(rs.getDouble("price"));
                Ex.setIntroduce(rs.getString("introduce"));
                return Ex;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    //更新操作部分
    //删除数据
    public void deleteUser(String id) {
        String sql = "delete from express where id like ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + id + "%");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //更新数据
    public void updateUser(Express Ex) {

        String sql = "update express set " +
                " name = ?" +
                " ,price = ?" +
                " ,introduce = ? where id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, Ex.getName());
            ps.setDouble(2, Ex.getPrice());
            ps.setString(3, Ex.getIntroduce());
            ps.setInt(4, Ex.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //插入数据
    public void insertExpress(Express Ex) {

        String sql = "insert into express(" +
                "name,price,introduce" + ") values (" + "?,?,?" + ");";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, Ex.getName());
            ps.setDouble(2, Ex.getPrice());
            ps.setString(3, Ex.getIntroduce());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询记录总数
     *
     * @return
     */
    public int getCount() {

        String sql = "select count(*) from express";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //资源回收
    public void close(Connection conn, PreparedStatement ps, ResultSet rs)
            throws RuntimeException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
