package com.example.myfridge.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
	
	

}
