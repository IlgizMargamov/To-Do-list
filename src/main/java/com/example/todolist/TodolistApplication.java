package com.example.todolist;

import com.JavaPostgreSqlVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Driver;

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
		DriverManager.registerDriver(new JavaPostgreSqlVersion());
		Connection connection = DriverManager.getConnection(url, user, password);
		SpringApplication.run(TodolistApplication.class, args);
	}

}
