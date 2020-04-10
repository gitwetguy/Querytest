package com.example;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.google.appengine.api.datastore.*;

@WebServlet(name = "HelloAppEngine", urlPatterns = { "/hello" })
public class HelloAppEngine extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		Query query1 = new Query("Employee");
		DatastoreService box = DatastoreServiceFactory.getDatastoreService();
		// deploy data once 
		/*Entity employee1 = new Entity("Employee");
		employee1.setProperty("name", "John");
		employee1.setProperty("age", 26);
		employee1.setProperty("math", 97);
		employee1.setProperty("chinese", 65);
		box.put(employee1);
		Entity employee2 = new Entity("Employee");
		employee2.setProperty("name", "Mary");
		employee2.setProperty("age", 19);
		employee2.setProperty("math", 90);
		employee2.setProperty("chinese", 45);
		box.put(employee2);
		Entity employee3 = new Entity("Employee");
		employee3.setProperty("name", "Tony");
		employee3.setProperty("age", 20);
		employee3.setProperty("math", 90);
		employee3.setProperty("chinese", 45);
		box.put(employee3);
		Entity employee4 = new Entity("Employee");
		employee4.setProperty("name", "Henry");
		employee4.setProperty("age", 29);
		employee4.setProperty("math", 90);
		employee4.setProperty("chinese", 45);
		box.put(employee4);
		Entity employee5 = new Entity("Employee");
		employee5.setProperty("name", "Trump");
		employee5.setProperty("age", 25);
		employee5.setProperty("math", 90);
		employee5.setProperty("chinese", 45);
		box.put(employee5);
		Entity employee6 = new Entity("Employee");
		employee6.setProperty("name", "Yoyo");
		employee6.setProperty("age", 14);
		employee6.setProperty("math", 90);
		employee6.setProperty("chinese", 30);
		box.put(employee6);*/
		
		// show the field names at first line
		String collectName = "";
		PreparedQuery pq1 = box.prepare(query1);
		String Data_Property[] = { "name", "age", "math", "chinese" };
		String Row = "";
		for (int i = 0; i < Data_Property.length; i++) {
			Row = Row+Data_Property[i]+"\t\t";
			
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(Row+"\n");
		
		//get property and value line by line
		for (Entity each : pq1.asIterable()) {
			collectName = collectName +each.getProperty("name")+"\t\t"
			+each.getProperty("age")+"\t\t "
			+each.getProperty("math")+"\t\t  "
			+each.getProperty("chinese")+"\n";
		}
		
		//display the sum of math,chinese 's score
		long Total_math =0;
		long Total_chinese =0;
		
		for (Entity each : pq1.asIterable()) {
			Total_math = Total_math+(long)each.getProperty("math");
			//System.out.println(each.getProperty("math"));
		}
		for (Entity each : pq1.asIterable()) {
			Total_chinese = Total_chinese+(long)each.getProperty("chinese");
			//System.out.println(each.getProperty("math"));
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(collectName+"\n---------------------------------------------------------\n");
		response.getWriter().print("Sum of math score : "+Total_math+"\n");
		response.getWriter().print("Sum of chinese score : "+Total_chinese+"\n");
		
		
		
		
		

	}
}