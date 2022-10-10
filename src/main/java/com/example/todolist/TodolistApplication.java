package com.example.todolist;

import org.postgresql.Driver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication(/*exclude={DataSourceAutoConfiguration.class}*/)
public class TodolistApplication {

	public static void main(String[] args) throws SQLException, IOException {

		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\Gizon\\Desktop\\To-Do-list\\src\\main\\resources\\application.properties"));
		String connurl = prop.getProperty("connurl");
		String driver = prop.getProperty("driver");
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "Aa123456";/*
		DriverManager.registerDriver(new );*/
		DriverManager.registerDriver(new Driver());
		Connection connection = DriverManager.getConnection(url, user, password);
		SpringApplication.run(TodolistApplication.class, args);
	}

}
