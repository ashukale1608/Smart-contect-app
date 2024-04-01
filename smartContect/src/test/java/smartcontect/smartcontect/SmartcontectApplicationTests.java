package smartcontect.smartcontect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import smartcontect.smartcontect.controller.UserDataController;

@SpringBootTest
class SmartcontectApplicationTests {

	UserDataController userDataController = new UserDataController();

	@Test
	void contextLoads() {
		
		assertEquals(3, userDataController.jastForTest(2, 1));
	}

}