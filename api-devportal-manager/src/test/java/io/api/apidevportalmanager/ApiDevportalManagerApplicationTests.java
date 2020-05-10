package io.api.apidevportalmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListener;

@SpringBootTest(properties = {"spring.data.arangodb.port=9834"})
class ApiDevportalManagerApplicationTests implements TestExecutionListener {

	private static final ArangoDBContainer arangoDBContainer;

	static {
		arangoDBContainer = new ArangoDBContainer().setPort(9834).setPassword("root");
		arangoDBContainer.run();
	}

	@Test
	void contextLoads() {

	}
}
