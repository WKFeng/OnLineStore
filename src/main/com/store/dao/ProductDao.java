package main.com.store.dao;

import main.com.store.domain.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findHotProducts() throws Exception;

    List<Product> findNewProducts() throws Exception;

    long getTotalProdutsByCategory(String cid) throws Exception;

    List<Product> findProductsByCategoryWithPage(int startIndex, int pageSize, String cid) throws Exception;

    Product findProductInfoByPid(String pid) throws Exception;
}
