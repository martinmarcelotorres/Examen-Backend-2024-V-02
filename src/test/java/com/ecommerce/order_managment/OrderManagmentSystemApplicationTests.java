package com.ecommerce.order_managment;

import com.ecommerce.order_managment.domain.dto.OrderRequestDto;
import com.ecommerce.order_managment.domain.model.Order;
import com.ecommerce.order_managment.domain.model.OrderItem;
import com.ecommerce.order_managment.service.OrderService;
import com.ecommerce.order_managment.web.OrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(OrderController.class)
class OrderControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private OrderService orderService;

	@Test
	public void createOrder() {
		// Crear un solo elemento para la lista de items
		OrderItem item = new OrderItem(/* parámetros del constructor */);
		// Suponiendo que item requiere un id de producto, una cantidad y un subtotal
		item.setProductId(1); // Supongamos que el id del producto es 1
		item.setQuantity(2);   // Supongamos que la cantidad es 2
		item.setSubtotal(50.0); // Supongamos que el subtotal es 50.0

		// Crear una lista con un solo elemento
		List<OrderItem> items = Collections.singletonList(item);

		// Datos de prueba para la solicitud
		OrderRequestDto requestDto = new OrderRequestDto();
		requestDto.set_id("123");
		requestDto.setClientId(123);
		requestDto.setItems(items); // Asignar la lista al objeto requestDto
		requestDto.setTotal(100.0);
		requestDto.setStatus("P");

		// Respuesta simulada del servicio para createOrder
		Order order = new Order();
		order.set_id("1");
		order.setClientId(123);
		order.setItems(items); // Asignar la lista al objeto order
		order.setTotal(100.0);
		order.setStatus("P");
		when(orderService.createOrder(any())).thenAnswer(invocation -> Mono.just(order));

		// Realizar la solicitud POST y verificar la respuesta
		webTestClient.post()
				.uri("/orders/create")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(requestDto)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Order.class)
				.isEqualTo(order);
	}

	@Test
	public void findAllOrders() {
		// Datos de prueba para la respuesta del servicio findAll
		Order order1 = new Order();
		order1.set_id("1");
		order1.setClientId(123);
		order1.setTotal(50.0);

		Order order2 = new Order();
		order2.set_id("2");
		order2.setClientId(456);
		order2.setTotal(150.0);

		List<Order> responseList = List.of(order1, order2);

		// Respuesta simulada del servicio findAll
		when(orderService.findAll()).thenAnswer(invocation -> Flux.fromIterable(responseList));

		// Realizar la solicitud GET y verificar la respuesta
		webTestClient.get()
				.uri("/orders/list")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Order.class)
				.isEqualTo(responseList);
	}
}
