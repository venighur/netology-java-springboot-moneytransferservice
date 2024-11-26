package ru.netology.moneytransferservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.netology.moneytransferservice.models.OperationResult;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneytransferserviceApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Container
	private static final GenericContainer<?> app = new GenericContainer<>("moneytransferservice:1.0")
			.withExposedPorts(8080);

	@Test
	void contextLoads() {
		ResponseEntity<OperationResult> result = restTemplate.getForEntity("http://localhost:" + app.getMappedPort(8080) + "/transfer", OperationResult.class);

		System.out.println(Objects.requireNonNull(result.getBody()).getOperationId());
	}
}
