package com.example.myfridge;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.myfridge.domain.Item;
import com.example.myfridge.domain.ItemRepository;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest

public class RepositoryTests {

	@Autowired ItemRepository itemrepo;
	
	
	@Test public void createitem() {
		
		Item item = new Item();
		item.setName("testi");
		itemrepo.save(item);
		assertThat(item.getId()).isNotNull();
		
		
	}
	
	
	@Test
	
	public void findbyiteName() {
		
		List<Item> items = itemrepo.findByname("testi");
		
		assertThat(items).hasSize(1);
	}
	
}
