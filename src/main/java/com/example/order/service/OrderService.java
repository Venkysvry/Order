
package com.example.order.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.order.domain.OrderRequest;
import com.example.order.domain.OrderResponse;
import com.example.order.domain.OrderTable;
import com.example.order.repo.OrderRepository;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderService {
	private final OrderRepository repo;
	// private final KafkaTemplate<String, String> kafkaTemplate;
	private final InventoryClient inventoryClient;

	public OrderService(OrderRepository repo, InventoryClient inventoryClient) {
		this.repo = repo;
		// this.kafkaTemplate = kafkaTemplate;
		this.inventoryClient = inventoryClient;
	}

	@Retry(name = "inventory")
	public OrderResponse createOrder(OrderRequest req) {
		int stock = inventoryClient.getStock(req.getSku());
		if (stock < req.getQuantity()) {
			return new OrderResponse(null, "INSUFFICIENT_STOCK");
		}
		OrderTable order = new OrderTable();
		order.setSku(req.getSku());
		order.setQuantity(req.getQuantity());
		order = repo.save(order);
		// kafkaTemplate.send("orders", order.getSku(), "ORDER_CREATED:" +
		// order.getId());
		return new OrderResponse(order.getId(), "CREATED");
	}

	// @Cacheable(value = "order", key = "#id")
	public Optional<OrderTable> getOrder(Long id) {
		return repo.findById(id);
	}

	public Object getReserve(String sku, int qnty) {
		Map<String, Object> o = inventoryClient.reserve(sku, qnty);
		Set<Entry<String, Object>> entries = o.entrySet();
		for (Entry<String, Object> entry : entries) {
			System.out.println(entry.getKey() + " " + entry.getValue());

		}

		return o.entrySet();

	}
}
