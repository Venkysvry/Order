
package com.example.order.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.HttpService;
import com.example.order.domain.OrderRequest;
import com.example.order.domain.OrderResponse;
import com.example.order.domain.OrderTable;
import com.example.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;
	@Autowired
	HttpService httpService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/postOrder")
	public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest req) {

		return ResponseEntity.ok(orderService.createOrder(req));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderTable> get(@PathVariable Long id) {
		return orderService.getOrder(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/getReserve/{sku}/{qnty}")
	public ResponseEntity<Object> getReserve(@PathVariable String sku, @PathVariable int qnty) {
		Object o = orderService.getReserve(sku, qnty);
		return ResponseEntity.ok(o);

	}

	@GetMapping("/getReserve1/{sku}")
	public CompletableFuture<ResponseEntity<Integer>> getReserve(@PathVariable String sku)
			throws NumberFormatException, ExecutionException {

		return httpService.getPostBystring(sku).thenApply(response -> {
			int result = Integer.parseInt(response.strip());
			return ResponseEntity.ok(result);
		}).exceptionally(e -> ResponseEntity.internalServerError().build());

	}

	@GetMapping("/userInfo")
	public String getUserInfo() {
		return "user name is venkatesh";
	}
}
