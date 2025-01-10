package com.example.spring_boot_test.service;

import com.example.spring_boot_test.models.OrderDetail;

public interface OrderDetailService {

    void createOrder(Long idUser, Long idProduct);

    OrderDetail getOrderByUser(Long idUser);

    void deleteProductInCart(Long idUser, Long idProduct);

    void increaseProduct(Long idUser, Long idProduct);

    void decreaseProduct(Long idUser, Long idProduct);

    OrderDetail findOrderByUserId(Long idUser);
}
