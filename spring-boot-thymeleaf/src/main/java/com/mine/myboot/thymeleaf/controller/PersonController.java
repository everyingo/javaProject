package com.mine.myboot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mine.myboot.thymeleaf.pojo.Person;

@Controller
@RequestMapping("person")
public class PersonController {

	@RequestMapping("personList")
	public String personList(Model model) {
		List<Person> people = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Person person = new Person();
			person.setId((i + 1));
			person.setName("name-" + (i + 1));
			person.setDescription("description-" + (i + 1));
			people.add(person);
		}
		model.addAttribute("people", people);
		return "person/person_list";
	}

	@RequestMapping("toAddPerson")
	public String personAdd() {

		return "person/person_add";
	}

	@RequestMapping("toEditPerson")
	public String personEdit() {

		return "person/person_edit";
	}
}
