package com.vms.common;

import org.hibernate.criterion.Order;

public class DaoUtils {

	public static Order getOrder(String propertyName, boolean ascending) {

		
		if("".equals(propertyName) || null== propertyName){
			return null;
		}
		Order order = null;
		if (ascending) {
			order = Order.asc(propertyName);
		} else {
			order = Order.desc(propertyName);
		}
		return order;
	}
}
