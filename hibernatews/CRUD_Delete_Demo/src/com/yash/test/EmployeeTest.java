package com.yash.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yash.model.Employee;

public class EmployeeTest {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Employee employee=(Employee) session.get(Employee.class, 5);
		employee.setName("Updated Employee");
		employee.setSalary(15400);
		session.update(employee);
		session.getTransaction().commit();
		session.close();		
	
	}
}
