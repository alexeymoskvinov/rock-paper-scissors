package ru.games.rps;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.stream.Stream;

@SpringBootTest
@DirtiesContext
public abstract class BaseTest {
	public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:14.0");
	@Autowired
	WebApplicationContext webApplicationContext;
	protected MockMvc mvc;

	static {
		Startables.deepStart(Stream.of(postgresContainer)).join();
		System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
		System.setProperty("spring.datasource.username", postgresContainer.getUsername());
		System.setProperty("spring.datasource.password", postgresContainer.getPassword());
	}

	@BeforeEach
	void setMvc() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Autowired
	private Flyway flyway;

	public void cleanAndMigrate() {
		flyway.clean();
		flyway.migrate();
	}
}
