package main.com.store.dao.impl;

import main.com.store.dao.CategoryDao;
import main.com.store.domain.Category;
import main.com.store.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> findAllCategories() throws Exception {
        String sql="select * from category";
        QueryRunner queryRunner=new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
    }
}
