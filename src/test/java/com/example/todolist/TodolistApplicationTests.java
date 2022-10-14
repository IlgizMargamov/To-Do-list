package com.example.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Persistence;

@SpringBootTest
class TodolistApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void tryAddEntity(){
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
