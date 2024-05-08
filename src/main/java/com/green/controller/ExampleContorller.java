package com.green.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.entity.Person;

import ch.qos.logback.core.model.Model;
import lombok.Getter;
import lombok.Setter;

@Controller
public class ExampleContorller {
	@GetMapping("/thymeleaf/example")
	public ModelAndView thymeleafExample() {
		Person examplePerson = new Person();
		examplePerson.setId(1L);
		examplePerson.setName("홍길동");
		examplePerson.setAge(11);
		examplePerson.setHobbies(List.of("운동", "독서"));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("person", examplePerson);
		mv.addObject("today", LocalDateTime.now());
		mv.setViewName("example");
		
		return mv;
	}
}
