package com.example.myfridge.domain;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

	

@Controller
public class FridgeController {
	List<Item> itemlist = new ArrayList<Item>();
	@Autowired
	private ItemRepository itemrepo;
	@Autowired
	private AlertRepository alertrepo;
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/itemlist")
	public String itemList(Model model) {
		
	
		model.addAttribute("alertitems", alertrepo.findAll());
		model.addAttribute("items", itemrepo.findAll());
		return "itemlist";
		
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editItem(@PathVariable("id") Long itemId, Model model ) {
		model.addAttribute("item",itemrepo.findById(itemId));
			
		return "edititem";
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable("id") Long itemId, Model model) {
    	itemrepo.deleteById(itemId);
  
    	
        return "redirect:../itemlist";
    }     
	
	@RequestMapping(value = "/add")
    public String addITem(Model model){
    	model.addAttribute("item", new Item());
        return "additem";
    }     
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Item item){
		
		
		try {
			
			String message;
			
			LocalDate datenow = LocalDate.now();
			
			Period diff = Period.between(datenow,item.getDate());
			
			int days = diff.getDays();
			
			item.setEday(days);
			message = "tuote vanhenee " + days+ " päivän päästä!";
			
			item.setMessage(message);
			itemlist.add(item);
			
	        itemrepo.save(item);
	      			
			
		}
		
              catch (Exception ex) {
            	 
			System.out.print(ex.getMessage());
			
		}
		
		  return "redirect:itemlist";

		
    }    
	
}
