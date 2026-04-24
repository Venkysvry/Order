
package com.example.order.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.order.domain.OrderTable;
@Repository
public interface OrderRepository extends JpaRepository<OrderTable, Long> {}
