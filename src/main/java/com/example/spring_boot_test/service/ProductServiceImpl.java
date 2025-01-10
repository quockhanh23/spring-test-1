package com.example.spring_boot_test.service;

import com.example.spring_boot_test.common.StatusProduct;
import com.example.spring_boot_test.models.Product;
import com.example.spring_boot_test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Hàm này làm chức năng gì..
    private void validateProduct(Product product) {
        if ((null == product.getProductName())
                || !(product.getProductName().length() >= 3
                && product.getProductName().length() <= 50)) {
            throw new RuntimeException("Tên sản phẩm không đúng định dạng");
        }
        if ((null == product.getProductType())
                || !(product.getProductType().length() >= 3
                && product.getProductType().length() <= 50)) {
            throw new RuntimeException("Loại sản phẩm không đúng định dạng");
        }
        if (product.getDescription().length() > 500) {
            throw new RuntimeException("Mô tả sản phẩm không vượt quá 500 kí tự");
        }
    }

    @Override
    public void createProduct(Product product) {
        validateProduct(product);
        product.setCreatedAt(new Date());
        product.setStatus(StatusProduct.ACTIVE.toString());
        productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long idProduct, Product product) {
        validateProduct(product);
        Product productUpdated = findById(idProduct);
        productUpdated.setProductName(product.getProductName());
        productUpdated.setProductType(product.getProductType());
        productUpdated.setDescription(product.getDescription());
        productUpdated.setQuantity(product.getQuantity());
        productUpdated = productRepository.save(productUpdated);
        return productUpdated;
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty()) {
            throw new RuntimeException("Sản phẩn không tồn tại");
        }
        return product.get();
    }
}
