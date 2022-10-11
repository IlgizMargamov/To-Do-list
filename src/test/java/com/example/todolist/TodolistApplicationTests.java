package com.example.todolist;

import controller.Model;
import controller.Repository;
import controller.TutorialRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.Persistence;

@SpringBootTest
class TodolistApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void tryAddEntity(){
		var entityManager = Persistence.createEntityManagerFactory("tutorials").createEntityManager();
		Repository tutorialRepo= new TutorialRepo(entityManager);
		Model m=new Model();
		m.setTitle("Tut");
		m.setPublished(true);
		m.setDescription("Tut byl ya");
		Model saved= tutorialRepo.save(m);

	}
}
