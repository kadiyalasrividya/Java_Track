package com.yash.test;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.yash.model.Employee;

public class EmployeeTest {
	public static void main(String[] args) {		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.setProjection(Projections.property("name"));
		//criteria.add(Restrictions.gt("salary", 4500.00));
		List<String>  empList=criteria.list();
		session.getTransaction().commit();
		session.close();		
		for (String employee : empList) {
			System.out.println(employee);
		}		
	}
}
