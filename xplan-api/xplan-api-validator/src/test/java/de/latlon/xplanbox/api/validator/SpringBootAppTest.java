package de.latlon.xplanbox.api.validator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import de.latlon.xplanbox.api.validator.config.TestContext;

@SpringBootTest
@ContextConfiguration(classes = { TestContext.class })
class SpringBootAppTest {

	@Test
	void contextLoads() {
	}

}
