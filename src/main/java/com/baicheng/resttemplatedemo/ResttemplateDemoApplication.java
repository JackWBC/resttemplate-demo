package com.baicheng.resttemplatedemo;

import com.baicheng.resttemplatedemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class ResttemplateDemoApplication implements ApplicationRunner {

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
//		SpringApplication.run(ResttemplateDemoApplication.class, args);

		new SpringApplicationBuilder()
				.sources(ResttemplateDemoApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.web(WebApplicationType.NONE)
				.run(args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/{id}")
				.build(1);
		ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
		log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
		log.info("Coffee: {}", c.getBody());

		String coffeeUri = "http://localhost:8080/coffee/add";
		Coffee americano = Coffee.builder()
				.name("Americano")
				.price(new BigDecimal(25))
				.build();
		Coffee postForObject = restTemplate.postForObject(coffeeUri, americano, Coffee.class);
		log.info("New Coffee: {}", postForObject);

		coffeeUri = "http://localhost:8080/coffee/";
		String forObject = restTemplate.getForObject(coffeeUri, String.class);
		log.info("All Coffee: {}", forObject);
	}
}
