package com.example.myfridge;

import static org.assertj.core.api.Assertions.*;

import com.example.myfridge.domain.FridgeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyfridgeApplicationTests {


	@Autowired
	private FridgeController controller;

	@Test
	public void contextLoads() {

		assertThat(controller).isNotNull();
	}

}
