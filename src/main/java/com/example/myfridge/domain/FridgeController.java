package com.example.myfridge.domain;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

	

@Controller
public class FridgeController {
	List<Item> itemlist = new ArrayList<Item>();
	String message2;
	LocalDate date2;
	int edays;
	
	@Autowired
	private ItemRepository itemrepo;
	@Autowired
	private AlertRepository alertrepo;
	
	@Autowired
	private SeasoningRepository seasonrepo;
	
	
	
	@RequestMapping("/seasoninglist")
	public String seasoningList(Model model) {
		
	
		
		model.addAttribute("seasonings", seasonrepo.findAll());
		return "seasoningslist";
		
	}
	
	@RequestMapping(value = "/addseasoning")
    public String addSeasoning(Model model){
    	model.addAttribute("seasoning", new Seasoning());
        return "addseasoning";
    }     
	
	@RequestMapping(value = "/saveseasoning", method = RequestMethod.POST)
    public String save(Seasoning seasoning){
		
		

			
			String message;
			
			LocalDate datenow = LocalDate.now();
			
			Period diff = Period.between(datenow,seasoning.getDate());
			
			int days = diff.getDays();
			
			seasoning.setEday(days);
			message = "tuote loppuupian! " + seasoning.getQuan()+ "/10 jäljellä";
			
			seasoning.setMessage(message);
		
			
	        seasonrepo.save(seasoning);
	      			
	
		
		  return "redirect:seasoninglist";

		
    }    
	
	@RequestMapping(value = "/deleteseasoning/{id}", method = RequestMethod.GET)
    public String deleteSeasoning(@PathVariable("id") Long itemId, Model model) {
    	seasonrepo.deleteById(itemId);
  
    	
        return "redirect:../seasoninglist";
    }     
	
	
	
	
	@RequestMapping(value = "/editseasoning/{id}", method = RequestMethod.GET)
	public String editSeasoning(@PathVariable("id") Long itemId, Model model ) {
		
		
		message2 = seasonrepo.findById(itemId).get().getMessage();
		
		date2 = seasonrepo.findById(itemId).get().getDate();
		
		edays = seasonrepo.findById(itemId).get().getEday();
		
		
	
		model.addAttribute("seasoning",seasonrepo.findById(itemId));
		
				
		
		
		
		
			
		return "editseasoning";
	}
	
	@RequestMapping(value = "/editseasoningsave", method = RequestMethod.POST)
    public String saveeditseasoning(Seasoning seasoning){
		
		seasoning.setDate(date2);
		seasoning.setMessage(message2);
		seasoning.setEday(edays);
		String message = "tuote loppuupian! " + seasoning.getQuan()+ "/10 jäljellä";
		
		seasoning.setMessage(message);
			
	        seasonrepo.save(seasoning);
	      			
	
		
		  return "redirect:seasoninglist";

		
    }    
	
	
	
	
	
	
	
	
	@RequestMapping("/itemlist")
	public String itemList(Model model) {
		
	
		model.addAttribute("alertitems", alertrepo.findAll());
		model.addAttribute("items", itemrepo.findAll());
		return "itemlist";
		
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editItem(@PathVariable("id") Long itemId, Model model ) {
		
		
		message2 = itemrepo.findById(itemId).get().getMessage();
		
		date2 = itemrepo.findById(itemId).get().getDate();
		
		edays = itemrepo.findById(itemId).get().getEday();
		
		
	
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
		
		

			
			String message;
			
			LocalDate datenow = LocalDate.now();
			
			Period diff = Period.between(datenow,item.getDate());
			
			int days = diff.getDays();
			
			item.setEday(days);
			message = "tuote vanhenee " + days+ " päivän päästä!";
			
			item.setMessage(message);
		
			
	        itemrepo.save(item);
	      			
	
		
		  return "redirect:itemlist";

		
    }    
	
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String saveedit(Item item){
		
		item.setDate(date2);
		item.setMessage(message2);
		item.setEday(edays);
		
			
	        itemrepo.save(item);
	      			
	
		
		  return "redirect:itemlist";

		
    }    
	
}
