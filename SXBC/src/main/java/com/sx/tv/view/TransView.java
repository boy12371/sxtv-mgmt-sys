package com.sx.tv.view;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.Status;

public class TransView {

	private Long id;

	private String name;
	private int count;
	private Status status;
	private Channel channel;

	private int conPrice;

	private float price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getConPrice() {
		return conPrice;
	}

	public void setConPrice(int conPrice) {
		this.conPrice = conPrice;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
