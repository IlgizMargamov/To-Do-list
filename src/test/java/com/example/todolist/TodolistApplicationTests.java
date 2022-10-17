package com.example.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Persistence;
import java.util.Map;

@SpringBootTest
class TodolistApplicationTests {

	@Test
	void contextLoads() {
	}

	@Value("git_secret_string")
	private String secretUrl;

	@Test
	void tryAddEntity(){
		Map<String,String> env = System.getenv();

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
