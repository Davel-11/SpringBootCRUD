package com.developer.SpringMySql.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.developer.SpringMySql.models.AppUsers;
import com.developer.SpringMySql.models.AppUsersRepo;



@Controller
public class MainController {
	
	@Autowired
	AppUsersRepo appUsersRepo;
	
	//HOMEPAGE
	@RequestMapping("/")
	public ModelAndView doHome() {
			ModelAndView mv = new ModelAndView();
			
			mv.addObject("lists", appUsersRepo.findAll());			
			
			mv.setViewName("index");
			return mv;
	}
	
	//INSERTING DATA
	@RequestMapping(value= "/save", method = RequestMethod.POST)
	public ModelAndView doSave(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
			ModelAndView mv = new ModelAndView();
			
			AppUsers users = new AppUsers();
			users.setFirstName(firstName);
			users.setLastName(lastName);
			
			appUsersRepo.save(users);
			
			mv.setViewName("redirect:/");
			return mv;
	}
	
			
	   //VIEWING DATA
		@RequestMapping(value= "/view/{id}", method = RequestMethod.GET)
	public ModelAndView doView(@PathVariable("id") int id ) {
				
				ModelAndView mv = new ModelAndView();
							
				mv.addObject("lists", appUsersRepo.findById(id).get());
				
				mv.setViewName("view");
				return mv;
				
		}
		
		
	//DELETING DATA
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView doDelete(@PathVariable("id") int id ) {
						
						ModelAndView mv = new ModelAndView();
									
						appUsersRepo.deleteById(id);;
						
						mv.setViewName("redirect:/");
						return mv;
						
     }
		
	  
	   //EDITNG DATA
			@RequestMapping(value= "/edit/{id}", method = RequestMethod.GET)
			public ModelAndView doEdit(@PathVariable("id") int id ) {
					
					ModelAndView mv = new ModelAndView();
								
					mv.addObject("lists", appUsersRepo.findById(id).get());
					
					mv.setViewName("edit");
					return mv;
					
			}
			
			
			//INSERTING DATA2
			@RequestMapping(value= "/edit", method = RequestMethod.POST)
			public ModelAndView doEdit2(@RequestParam("id") String id, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
					ModelAndView mv = new ModelAndView();
					
					AppUsers users = new AppUsers();
					
					users.setId(Integer.parseInt(id));
					users.setFirstName(firstName);
					users.setLastName(lastName);
					
					appUsersRepo.save(users);
					
					mv.setViewName("redirect:/");
					return mv;
			}
	

}
