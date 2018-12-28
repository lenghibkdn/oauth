package com.oauth.oauth;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class HomeController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpServletRequest request;
	
	
	@RequestMapping(value = "/header", method = RequestMethod.GET, produces = "application/json")
	public void header(HttpServletResponse rs) {
		Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            //map.put(key, value);
            System.out.println("Header: "+value);
        }
        rs.setHeader("test", "test");
		//System.out.println("Header: "+request.getParameter("authorization"));
	}
	
	@RequestMapping("/")
	public String login(Model model) {

		String url = "https://github.com/login/oauth/authorize";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("client_id", "e441b77adea4957773ea")
		        .queryParam("redirect_uri", "http://localhost:8080/home");
		
		System.out.println(builder.toUriString());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> code = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
				
		model.addAttribute("body", code.getBody());
		return "home";
	}
	
	@RequestMapping("/home")
	public String login(@RequestParam("code") String code, Model model) {
		String url = "https://github.com/login/oauth/access_token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("client_id", "e441b77adea4957773ea");
		map.add("client_secret", "828410e33ccb68502904d344e43de4f8d3d397be");
		map.add("code", code);
		map.add("redirect_uri", "http://localhost:8080/home");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
		
		System.out.println(response.getBody());
		
		model.addAttribute("body", response.getBody());
		return "home";
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
}

