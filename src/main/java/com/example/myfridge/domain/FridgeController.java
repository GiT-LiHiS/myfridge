package com.example.myfridge.domain;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.bcel.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FridgeController {
	
	
	List<Item> itemlist = new ArrayList<Item>();
	String message2;
	String emessage;
	String name;
	String quan;
	String comment;
	Long id;
	LocalDate date2;
	int edays;
	
	
	//autowire repositorys for use
	@Autowired
	private ItemRepository itemrepo;
	@Autowired
	private AlertRepository alertrepo;
	@Autowired
	private UtilyRepository utilyrepo;
	
	@Autowired
	private SeasoningRepository seasonrepo;
	
	
	//LOGIN CONTROLLER

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	//TOOLS AND UTILITIES
	

	@RequestMapping(value="utilities",method = RequestMethod.GET)
	public @ResponseBody List<Utily> utilylistRest(){

		return (List<Utily>) utilyrepo.findAll();

	}

	//utilities and tools main page
	@RequestMapping("/utilylist")
	public String utilyList(Model model) {
		

		model.addAttribute("utilys", utilyrepo.findAll());
		return "utilylist";
		
	
	}
	
	
	//gets user date from user input on thymeleaf
	@RequestMapping(value = "/addutility")
    public String addUtility(Model model){
    	model.addAttribute("utility", new Utily());
        return "addutility";
    }     
	
	//save utility to repository
	@RequestMapping(value = "/saveutility", method = RequestMethod.POST)
    public String save(Utily utily){
		
		
			//set message that is shown on notification 
			String message;
	
			message = "tuote loppuupian! " + utily.getQuan()+ "/10 jäljellä";
		
			utily.setMessage(message);
			
	        utilyrepo.save(utily);
	      			
		  return "redirect:utilylist";

    }   
	
	
	//delete utility using entity id
	@RequestMapping(value = "/deleteutility/{id}", method = RequestMethod.GET)
    public String deleteUtility(@PathVariable("id") Long itemId, Model model) {
		
		
     utilyrepo.deleteById(itemId);
  
    	
        return "redirect:../utilylist";
    }   
	
	//edit utility using entity id
	@RequestMapping(value = "/editutility/{id}", method = RequestMethod.GET)
	public String editUtility(@PathVariable("id") Long itemId, Model model ) {
		
		
		
	
		model.addAttribute("utility",utilyrepo.findById(itemId));
		
			
			
		return "editutily";
	}
	
	//save edited utility
	@RequestMapping(value = "/editutilitysave", method = RequestMethod.POST)
    public String saveeditutility(Utily utily){
		
		
		//generate and set new message for notification based on entitys quanitity
		String message = "tuote loppuupian! " + utily.getQuan()+ "/10 jäljellä";
		
		utily.setMessage(message);
		
			
	        utilyrepo.save(utily);
	      			
	
		
		  return "redirect:utilylist";

		
    }    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//SEASONINGS
	
	//main seasoning page with seasoning list
	@RequestMapping("/seasoninglist")
	public String seasoningList(Model model) {
		
		
	
		
		
		
		model.addAttribute("seasonings", seasonrepo.findAll());
		return "seasoningslist";
		
	}
	
	//get user input for date from thymeleaf page
	@RequestMapping(value = "/addseasoning")
    public String addSeasoning(Model model){
    	model.addAttribute("seasoning", new Seasoning());
        return "addseasoning";
    }     
	
	//save seasoning using user inputed data
	@RequestMapping(value = "/saveseasoning", method = RequestMethod.POST)
    public String save(Seasoning seasoning){
		
		//seasoning ex date message disable on thymeleaf atm
		//Calculate days between now and expiration
		//set message with expiration day

		String emessage;

			
			String message;
			
			LocalDate datenow = LocalDate.now();
			
			Period diff = Period.between(datenow,seasoning.getDate());
			
			int days = diff.getDays();
			
			int months = diff.getMonths();
			int years = diff.getYears();
			
			seasoning.setEday(days);
			seasoning.setEmonth(months);
			seasoning.setEyear(years);
			
			
			seasoning.setEday(days);
			message = "tuote loppuupian! " + seasoning.getQuan()+ "/10 jäljellä";
			emessage = seasoning.getEyear()+ "y " +seasoning.getEmonth()+ "m " + seasoning.getEday()+"d" ;
			
			seasoning.setMessage(message);
			seasoning.setEmessage(emessage);
			
			
	        seasonrepo.save(seasoning);
	      			
	
		
		  return "redirect:seasoninglist";

		
    }    
	
	//delete seasoning using entity id
	@RequestMapping(value = "/deleteseasoning/{id}", method = RequestMethod.GET)
    public String deleteSeasoning(@PathVariable("id") Long itemId, Model model) {
    	seasonrepo.deleteById(itemId);
  
    	
        return "redirect:../seasoninglist";
    }     
	
	
	
	//edit seasoning using entity id
	@RequestMapping(value = "/editseasoning/{id}", method = RequestMethod.GET)
	public String editSeasoning(@PathVariable("id") Long itemId, Model model ) {
		
		
		//save messages and expiration days from unedited entity
		//expiration date cannot be changed
		message2 = seasonrepo.findById(itemId).get().getMessage();
		emessage = seasonrepo.findById(itemId).get().getEmessage();
		date2 = seasonrepo.findById(itemId).get().getDate();
		
		edays = seasonrepo.findById(itemId).get().getEday();
		
		
	
		model.addAttribute("seasoning",seasonrepo.findById(itemId));
		
				
		
		
		
		
			
		return "editseasoning";
	}
	
	//save editet entity
	@RequestMapping(value = "/editseasoningsave", method = RequestMethod.POST)
    public String saveeditseasoning(Seasoning seasoning){
		
		//set edited entitys expiration date and message with unedited data
		seasoning.setDate(date2);
		seasoning.setMessage(message2);
		seasoning.setEday(edays);
		String message = "tuote loppuupian! " + seasoning.getQuan()+ "/10 jäljellä";
		
		seasoning.setMessage(message);
		seasoning.setEmessage(emessage);
			
	        seasonrepo.save(seasoning);
	      			
	
		
		  return "redirect:seasoninglist";

		
    }    
	
	
	
	
	
	//ITEMS
	
	//main page for fridge items
	@RequestMapping("/itemlist")
	public String itemList(Model model) {
		
		String message;
		String emessage;
		
		
		
		
		LocalDate datenow = LocalDate.now();
		
		
	
		
		
		//calculate days between current date and expiration date every time user enters main fridge page
		//itemlist contains same entitys that itemrepository has
		//iterate itemlist and updates the days to expiration
		//save updated entity to repositor 
		
		int i = 0;
		
		while (i < itemlist.size()) {
			
			Period diff = Period.between(datenow,itemlist.get(i).getDate());
			
			int days = diff.getDays();
			int months = diff.getMonths();
			int years = diff.getYears();
			
			itemlist.get(i).setEday(days);
			itemlist.get(i).setEmonth(months);
			itemlist.get(i).setEyear(years);
			
			message = "tuote vanhenee " + days+ " päivän päästä!";
			emessage = itemlist.get(i).getEyear()+ "y " +itemlist.get(i).getEmonth()+ "m " + itemlist.get(i).getEday()+"d" ;
			
			itemlist.get(i).setMessage(message);
			itemlist.get(i).setEmessage(emessage);
			itemrepo.save(itemlist.get(i));
			
			i++;
			
			System.out.println("testi");
		}
				
				
		
		
	
		
		model.addAttribute("items", itemrepo.findAll());
		return "itemlist";
		
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editItem(@PathVariable("id") Long itemId, Model model ) {
		
		id = itemId;
		
		message2 = itemrepo.findById(itemId).get().getMessage();
		emessage = itemrepo.findById(itemId).get().getEmessage();
		
		date2 = itemrepo.findById(itemId).get().getDate();
		
		edays = itemrepo.findById(itemId).get().getEday();
		
		
	
		model.addAttribute("item",itemrepo.findById(itemId));
		

		
		
		
		
			
		return "edititem";
	}
	
	

	
	
	
	
	
	
	
	//delete item with using items id
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable("id") Long itemId, Model model) {
    	itemrepo.deleteById(itemId);
    	
    	//because we need track items dates we need to update the list also
    	int i = 0;
    	
    	while ( i < itemlist.size()) {
    		
    		if ( itemlist.get(i).getId() == itemId) {
    			
    			itemlist.remove(i);
    			
    			
    			
    		}
    		
    		i++;
    		
    	}
    	
    	System.out.println(itemlist.size());
    	
        return "redirect:../itemlist";
    }     
	
	//add item
	
	@RequestMapping(value = "/add")
    public String addITem(Model model){
    	model.addAttribute("item", new Item());
        return "additem";
    }     
	
	
	// save added item to repository
	//because we need track item dates we need to update the list also
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Item item){
		
		

			
			String message;
			String emessage;
			
			LocalDate datenow = LocalDate.now();
			
			Period diff = Period.between(datenow,item.getDate());
			
			int days = diff.getDays();
			int months = diff.getMonths();
			int years = diff.getYears();
			
			item.setEday(days);
			item.setEmonth(months);
			item.setEyear(years);
			
			message = "tuote vanhenee " + days+ " päivän päästä!";
			emessage = item.getEyear()+ "y " +item.getEmonth()+ "m " + item.getEday()+"d" ;
			
			item.setMessage(message);
			item.setEmessage(emessage);
		
			itemlist.add(item);
	        itemrepo.save(item);
	        System.out.println(itemlist.size());
	        
	
		
		  return "redirect:itemlist";

		
    }    
	
	//update item
	//because we need track item dates we need to update the list also
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String saveedit(Item item){
		
		item.setDate(date2);
		item.setMessage(message2);
		item.setEmessage(emessage);
		item.setEday(edays);
		
		String name = item.getName();
		String comment = item.getComment();
		String quan = item.getQuan();
		int i = 0;
    	
    	while ( i < itemlist.size()) {
    		
    		if ( itemlist.get(i).getId() == id) {
    			
    			itemlist.get(i).setName(name);
    			itemlist.get(i).setComment(comment);
    			itemlist.get(i).setQuan(quan);
    			
    			
    			
    		}
    		
    		i++;
    		
    	}
			
	        itemrepo.save(item);
	      			
	
		
		  return "redirect:itemlist";

		
    }    
	
}
