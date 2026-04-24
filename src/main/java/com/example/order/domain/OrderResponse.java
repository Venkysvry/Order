
package com.example.order.domain;

public class OrderResponse {
	private int orderid;
	private String status;

	public OrderResponse(Object object, String status) {
		this.status = status;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
