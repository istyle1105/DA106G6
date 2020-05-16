package com.product.model;

import java.util.*;

public interface ProductDAO_interface {
    public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public ProductVO findByPrimaryKey(String p_id);
    public List<ProductVO> getAll();
    public List<ProductVO> get_ALLByP_status();
    public List<ProductVO> getAllByCat(String p_cat);
}
