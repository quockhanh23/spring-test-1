package com.example.spring_boot_test.service;

import com.example.spring_boot_test.models.OrderDetail;
import com.example.spring_boot_test.models.Product;
import com.example.spring_boot_test.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductService productService;

    @Override
    public void createOrder(Long idUser, Long idProduct) {
        Product product = productService.findById(idProduct);
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.getFirstByIdUser(idUser);
        if (optionalOrderDetail.isEmpty()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setIdUser(idUser);
            orderDetail.setCreatedAt(new Date());
            orderDetail.setProducts(Collections.singletonList(product));
            orderDetailRepository.save(orderDetail);
        } else {
            List<Product> productList = optionalOrderDetail.get().getProducts();
            productList.add(product);
            orderDetailRepository.save(optionalOrderDetail.get());
        }
    }

    @Override
    public OrderDetail getOrderByUser(Long idUser) {
        return null;
    }

    @Override
    public void deleteProductInCart(Long idUser, Long idProduct) {

    }

    @Override
    public void increaseProduct(Long idUser, Long idProduct) {

    }

    @Override
    public void decreaseProduct(Long idUser, Long idProduct) {

    }

    @Override
    public OrderDetail findOrderByUserId(Long idUser) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.getFirstByIdUser(idUser);
        if (orderDetail.isEmpty()) {
            throw new RuntimeException("Không tìm thấy giỏ hàng");
        }
        return orderDetail.get();
    }
}
