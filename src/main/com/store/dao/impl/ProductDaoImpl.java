package main.com.store.dao.impl;

import main.com.store.dao.ProductDao;
import main.com.store.domain.Product;
import main.com.store.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public long getTotalProdutsByCategory(String cid) throws Exception {
        String sql = "SELECT COUNT(*) FROM product WHERE cid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql,new ScalarHandler<>(),cid);
    }

    @Override
    public List<Product> findProductsByCategoryWithPage(int startIndex, int pageSize, String cid) throws Exception {
        String sql = "select * from product where cid=? limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), cid, startIndex, pageSize);
    }

    @Override
    public Product findProductInfoByPid(String pid) throws Exception {
        String sql="select * from product where pid=?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql,new BeanHandler<>(Product.class),pid);
    }

    @Override
    public List<Product> findNewProducts() throws Exception {
        String sql = "SELECT * FROM product ORDER BY pdate DESC LIMIT 0,9";
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findHotProducts() throws Exception {
        String sql = "SELECT * FROM product WHERE is_hot=1 ORDER BY pdate DESC LIMIT 0,9";
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }
}
