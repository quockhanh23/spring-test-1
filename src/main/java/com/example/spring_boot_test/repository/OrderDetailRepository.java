package com.example.spring_boot_test.repository;

import com.example.spring_boot_test.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Optional<OrderDetail> getFirstByIdUser(Long idUser);
}
