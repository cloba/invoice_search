package com.iksan.web;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.iksan.repository.DeliveryInfo;
import com.iksan.service.DeliveryInfoService;

@RestController
public class MainController {
	
	@Autowired
	private DeliveryInfoService deliveryInfoService;
	
	@GetMapping("/helloworld")
	public String welcome(String invoice, String deliverier) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://info.sweettracker.co.kr/v2/api-docs?item_no=149480994106", String.class);
		
		String html = response.getBody();
		System.out.println(html);
		
	    return "welcome";
	}	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		System.out.println("main");

	    return new ModelAndView("/index");
	}
	/*
	@GetMapping("/helloworld")
	public String welcome() {
		
		System.out.println("부트");
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> map = new HashMap<String, String>();
		map.put("item_no", "149480994106");
		//
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://www.kglogis.co.kr/delivery/delivery_result.jsp", map, String.class);
		HttpHeaders headers = response.getHeaders();
		String set_cookie = headers.getFirst(headers.SET_COOKIE);
		
		String html = response.getBody();
		//System.out.println(html);
		//System.out.println(set_cookie);
		
	    return "welcome";
	}
	
	*/
	
	@GetMapping("/delivery_info_input")
	public ModelAndView delivery_info_input(DeliveryInfo deliveryInfo) {

		String vendor = deliveryInfo.getDeliveryVendor();		
		System.out.println(deliveryInfo.getInvoice());
	
		ArrayList<String> res = deliveryInfoService.DeliveryInfoSearch(deliveryInfo);
		System.out.println("res:");
		System.out.println(res);
		return null;

		//ModelAndView modelAndView = new ModelAndView();
		//modelAndView.setViewName("welcome");
		//modelAndView.addObject("deliveryVendor", deliveryVendor);
		//modelAndView.addObject("invoice", invoice);
		//return modelAndView;
		
	}
	
}
