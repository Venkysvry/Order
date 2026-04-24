
package com.example.order.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", path = "/inventory")
public interface InventoryClient {
	@GetMapping("/getStock/{sku}")
	int getStock(@PathVariable("sku") String sku);
	@PostMapping("/reserve")
	Map<String,Object> reserve(@RequestParam String sku,@RequestParam int qty);

}
