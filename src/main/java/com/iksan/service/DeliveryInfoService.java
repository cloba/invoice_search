package com.iksan.service;

import java.util.ArrayList;

import com.iksan.repository.DeliveryInfo;

public interface DeliveryInfoService {
	public ArrayList<String> DeliveryInfoSearch(DeliveryInfo deliveryInfo);
	public void kg(DeliveryInfo deliveryInfo);
}
