package com.baicheng.resttemplatedemo;

import com.baicheng.resttemplatedemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
		log.info("-------------------------- Simple demo ----------------------------");

//		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/{id}")
//				.build(1);
//		ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
//		log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
//		log.info("Coffee: {}", c.getBody());
//
//		String coffeeUri = "http://localhost:8080/coffee/add";
//		Coffee americano = Coffee.builder()
//				.name("Americano")
//				.price(Money.ofMinor(CurrencyUnit.of("CNY"), 25))
//				.build();
//		Coffee postForObject = restTemplate.postForObject(coffeeUri, americano, Coffee.class);
//		log.info("New Coffee: {}", postForObject);
//
//		coffeeUri = "http://localhost:8080/coffee/";
//		String forObject = restTemplate.getForObject(coffeeUri, String.class);
//		log.info("All Coffee: {}", forObject);

		log.info("-------------------------- Complex demo ----------------------------");

		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/?name={name}")
				.build("mocha");
		RequestEntity<Void> requestEntity = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_XML)
				.build();
		ResponseEntity<String> entity = restTemplate.exchange(requestEntity, String.class);
		log.info("find Coffee by name: {}", entity.getBody());

		String coffeeUri = "http://localhost:8080/coffee/add";
		Coffee coffee = Coffee.builder()
				.name("Americano")
				.price(Money.ofMinor(CurrencyUnit.of("CNY"), 25))
				.build();
		Coffee postForObject = restTemplate.postForObject(coffeeUri, coffee, Coffee.class);
		log.info("New Coffee: {}", postForObject);

		ParameterizedTypeReference<List<Coffee>> ptr = new ParameterizedTypeReference<List<Coffee>>() {};
		ResponseEntity<List<Coffee>> responseEntity = restTemplate.exchange("http://localhost:8080/coffee/", HttpMethod.GET, null, ptr);
		responseEntity.getBody().forEach(item -> log.info("Coffee: {}", item));
	}
}
