package com.example.todolist;

import com.example.todolist.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodolistApplicationTests {

	@Test
	void contextLoads() {
	}

	@Value("git_secret_string")
	private String secretUrl;

	@Test
	void tryAddEntity(){
		Category category = new Category();
		category.setName("all");
		//categoryRepo.save(category);

		/*var entityManager = Persistence.createEntityManagerFactory("tutorials").createEntityManager();
		Repository tutorialRepo= new TutorialRepo(entityManager);
		Model m=new Model();
		m.setTitle("Tut");
		m.setPublished(true);
		m.setDescription("Tut byl ya");
		Model saved= tutorialRepo.save(m);*/
	}

	@Test
	void tryController(){/*
		var controller = new Ccontroller(new TutorialRepo(Persistence.createEntityManagerFactory("tutorials").createEntityManager()));
		var a = controller.getAllTutorials("Tut");*/

	}
}
