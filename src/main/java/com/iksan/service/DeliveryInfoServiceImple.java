package com.iksan.service;



import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.iksan.repository.DeliveryInfo;



@Service("deliveryInfoService")
public class DeliveryInfoServiceImple implements DeliveryInfoService{

	@Override
	public ArrayList<String> DeliveryInfoSearch(DeliveryInfo deliveryInfo) {
		
		//System.out.println("DeliveryInfoServiceImple 파일");
		String vendor = deliveryInfo.getDeliveryVendor();
		System.out.println("vendor : " + vendor);
		ArrayList<String> list = new ArrayList<String>();
		
		// 택배사가 없을 때
		if(!vendor.equals("kg")) {
			list.add("0");
			return list;
		}	
		
		//System.out.println("kg안으로");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://www.kglogis.co.kr/delivery/delivery_result.jsp?item_no=149480994106", String.class);
		
		// 배송 관련 전체를 페이지(html)
		String html = response.getBody();
		Pattern pattern = Pattern.compile("(?s)<tbody>(.*?)</tbody>");
		Matcher m = pattern.matcher(html);
		/* 유효성
		if(true) {
			System.out.println("html");
			System.out.println(html);
			list.add("0");
			return list;
		}
		*/
		// 기본 정보(운송장, 상품명, 개수) 조회 실패
		if(!m.find()) {
			list.add("0");
			return list;
		}
		
		String infos = m.group(1);
		System.out.println(infos);
		Pattern pattern2 = Pattern.compile("(?si)<td>(.*?)</td>");
		Matcher m2 = pattern2.matcher(infos);
		
		Pattern list_pattern = Pattern.compile("(?si)<th scope=\"row\">(.*?)</th>");
		Matcher list_nm = list_pattern.matcher(infos);
		
		ArrayList<String> field_nm = new ArrayList<String>();
		list.add("1");
		while(m2.find() && list_nm.find()) { 
			list.add(m2.group(1));
			field_nm.add(list_nm.group(1));
		}
		
		Pattern pattern_detail = Pattern.compile("(?s)<article class=\"inquiry_result\">(.*?)</article>");
		Matcher m_datail = pattern_detail.matcher(html);
		m_datail.find(); 
		list.add(m_datail.group(1));
		
		System.out.println("m_datail");
		System.out.println(m_datail.group(1));
		
		return list;
				
			
	}

	@Override
	public void kg(DeliveryInfo deliveryInfo) {
		System.out.println("kg service");
		
	}
	
	

}
