package main.com.store.service;

import main.com.store.domain.PageModel;
import main.com.store.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHotProducts() throws Exception;

    List<Product> findNewProducts() throws Exception;

    PageModel findProductsByCategoryWithPage(String currentNum,String cid) throws Exception;

    Product getProductInfoByPid(String pid) throws Exception;
}
