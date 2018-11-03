package com.vishesh.webapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.varun.webapp.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
											Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = session.createQuery("delete from Customer where id=:id");
		
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
		
	}

}
