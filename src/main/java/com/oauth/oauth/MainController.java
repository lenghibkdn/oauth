//package com.oauth.oauth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.client.RestTemplate;
//
//@Controller
//public class MainController {
//	
//	@Autowired
//	RestTemplate restTemplate;
//    
//	@RequestMapping("/login")
//	public String login(@RequestParam("code") String code, Model model) {
//		String url = "https://slack.com/api/oauth.access";
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		
//		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//		map.add("client_id", "499055849781.498253479841");
//		map.add("client_secret", "3eacac07622fe9953ff827268a04536f");
//		map.add("code", code);
//		map.add("redirect_uri", "http://localhost:8080/hoge");
//		
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//
//		ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class);
//		
//		System.out.println(response.getBody());
//		
//		model.addAttribute("body", response.getBody());
//		//System.out.println(code);
//		return "redirect:/hoge";
//	}
//	
//	/**
//     * Topページ
//     * @return
//     */
//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }
//    
//    /**
//     * Topページ
//     * @return
//     */
//    @RequestMapping("/test")
//    public String test() {
//        return "index";
//    }
//
//
//    /**
//     * ログイン後のページ
//     * @return
//     */
//    @RequestMapping("/hoge")
//    public String hoge() {
//        return "home";
//    }
//	
//	
//	@Bean
//	public RestTemplate restTemplate() {
//	    return new RestTemplate();
//	}
//}
