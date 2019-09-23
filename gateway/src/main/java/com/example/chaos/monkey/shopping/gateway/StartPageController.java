package com.example.chaos.monkey.shopping.gateway;

import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.Function;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.chaos.monkey.shopping.domain.Product;
import com.example.chaos.monkey.shopping.gateway.domain.ProductResponse;
import com.example.chaos.monkey.shopping.gateway.domain.ResponseType;
import com.example.chaos.monkey.shopping.gateway.domain.Startpage;

/**
 * @author Ryan Baxter
 */
@RestController
public class StartPageController implements ApplicationListener<WebServerInitializedEvent> {

	private ParameterizedTypeReference<Product> productParameterizedTypeReference =
			new ParameterizedTypeReference<Product>() {};

	private Function<ClientResponse, Mono<ProductResponse>> responseProcessor = clientResponse -> {
		HttpHeaders headers = clientResponse.headers().asHttpHeaders();
		if(headers.containsKey("fallback") && headers.get("fallback").contains("true")) {
			return Mono.just(new ProductResponse(ResponseType.FALLBACK, Collections.emptyList()));
		}
		return clientResponse.bodyToFlux(productParameterizedTypeReference).collectList()
				.flatMap(products -> Mono.just(new ProductResponse(ResponseType.REMOTE_SERVICE, products)));
	};
	private WebClient client;
	private ProductResponse errorResponse;

	public StartPageController() {

		this.errorResponse = new ProductResponse();
		errorResponse.setResponseType(ResponseType.ERROR);
		errorResponse.setProducts(Collections.emptyList());
	}


	@GetMapping("/startpage")
	public Mono<Startpage> getStartpage() {
		long start = System.currentTimeMillis();
		Mono<ProductResponse> hotdeals = client.get().uri("/hotdeals").exchange().flatMap(responseProcessor)
				.onErrorResume(t -> {
					t.printStackTrace();
					return Mono.just(errorResponse);
				});
		Mono<ProductResponse> fashionBestSellers = client.get().uri("/fashion/bestseller").exchange().flatMap(responseProcessor)
				.onErrorResume(t -> {
					t.printStackTrace();
					return Mono.just(errorResponse);
				});
		Mono<ProductResponse> toysBestSellers = client.get().uri("/toys/bestseller").exchange().flatMap(responseProcessor)
				.onErrorResume(t -> {
					t.printStackTrace();
					return Mono.just(errorResponse);
				});

		Mono<Startpage> page = Mono.zip(hotdeals, fashionBestSellers, toysBestSellers).flatMap(t -> {
			Startpage p = new Startpage();
			ProductResponse deals = t.getT1();
			ProductResponse fashion = t.getT2();
			ProductResponse toys = t.getT3();
			p.setFashionResponse(fashion);
			p.setHotDealsResponse(deals);
			p.setToysResponse(toys);
			p.setStatusFashion(fashion.getResponseType().name());
			p.setStatusHotDeals(deals.getResponseType().name());
			p.setStatusToys(toys.getResponseType().name());
			// Request duration
			p.setDuration(System.currentTimeMillis() - start);
			return Mono.just(p);
		});

		return page;
	}

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		this.client = WebClient.builder().baseUrl("http://localhost:" + event.getWebServer().getPort()).build();
	}
}
