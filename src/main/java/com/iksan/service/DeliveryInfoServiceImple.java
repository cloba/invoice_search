package com.iksan.service;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.iksan.repository.DeliveryInfo;



@Service("deliveryInfoService")
public class DeliveryInfoServiceImple implements DeliveryInfoService{

	
	@Override
	public void DeliveryInfoSearch(DeliveryInfo deliveryInfo) {
		
		//System.out.println("DeliveryInfoServiceImple 파일");
		String vendor = deliveryInfo.getDeliveryVendor();
		System.out.println("vendor : " + vendor);
		
		if(vendor.equals("kg")) {
			//System.out.println("kg안으로");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.getForEntity("http://www.kglogis.co.kr/delivery/delivery_result.jsp?item_no=149480994106", String.class);
			
			// 배송 관련 전체를 페이지(html)
			String html = response.getBody();
			//System.out.println(html);
			//Pattern pattern = Pattern.compile("(?s)<table class=\"c_table_01\">(.*?)</table>");
			//Pattern pattern = Pattern.compile("(?s)<table class=\"i_table_01\">(.*?)</table>");
			//Pattern pattern = Pattern.compile("(?s)<table class=\"c_table_01\">(.*?)</table>");
			Pattern pattern = Pattern.compile("(?s)<tbody>(.*?)</tbody>");
			Matcher m = pattern.matcher(html);
			// 기본 정보(운송장, 상품명, 개수)
			if(m.find()) {
				String infos = m.group(1);
				System.out.println(infos);
				Pattern pattern2 = Pattern.compile("(?si)<td>(.*?)</td>");
				Matcher m2 = pattern2.matcher(infos);
				
				Pattern list_pattern = Pattern.compile("(?si)<th scope=\"row\">(.*?)</th>");
				Matcher list_nm = list_pattern.matcher(infos);
				
				//System.out.println(matcher2.group(1));
				//System.out.println(m2.matches());
				if(m2.find() && list_nm.find()) {
					System.out.println("11111");
					//System.out.println(m2.find());
					System.out.println(m2.group(1));
					System.out.println(list_nm.group(1));
					System.out.println("22222");
/*					System.out.println(m2.group(1));
					System.out.println(m2.group(2));
					System.out.println(m2.group(3));
		*/			
					//System.out.println(m2.group(1));
					
				}
			}
		}	
	}

	@Override
	public void kg(DeliveryInfo deliveryInfo) {
		System.out.println("kg service");
		
	}
	
	

}
