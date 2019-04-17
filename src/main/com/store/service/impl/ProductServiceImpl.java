package main.com.store.service.impl;

import main.com.store.dao.ProductDao;
import main.com.store.dao.impl.ProductDaoImpl;
import main.com.store.domain.PageModel;
import main.com.store.domain.Product;
import main.com.store.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> findHotProducts() throws Exception {
        return productDao.findHotProducts();
    }

    @Override
    public List<Product> findNewProducts() throws Exception {
        return productDao.findNewProducts();
    }

    @Override
    public PageModel findProductsByCategoryWithPage(String currentNum, String cid) throws Exception {
        int current = Integer.parseInt(currentNum);
        int total = (int) productDao.getTotalProdutsByCategory(cid);
        PageModel pageModel = new PageModel(12, total, current);
        List products = productDao.findProductsByCategoryWithPage(pageModel.getStartIndex(), pageModel.getPageSize(), cid);
        pageModel.setList(products);
        pageModel.setUrl("productServlet?method=findProductsByCategoryWithPage&cid="+cid);
        return pageModel;
    }

    @Override
    public Product getProductInfoByPid(String pid) throws Exception {
        return productDao.findProductInfoByPid(pid);
    }
}
