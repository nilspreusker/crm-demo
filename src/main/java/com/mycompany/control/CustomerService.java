package com.mycompany.control;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mycompany.entity.Customer;

public class CustomerService {

  @PersistenceContext
  private EntityManager entityManager;

  public void saveCustomer(Customer customer) {
    entityManager.persist(customer);
  }

  public List<Customer> findAllCustomers() {
    TypedQuery<Customer> query = entityManager.createQuery(
        "SELECT e FROM Customer e", Customer.class);
    return (List<Customer>) query.getResultList();
  }

  public Customer findCustomerById(Long id) {
    return entityManager.find(Customer.class, id);
  }

  public void updateCustomer(Customer customer) {
    entityManager.merge(customer);
  }

  public List<Customer> findCustomers(String searchString) {

    String[] searchTerms = new String[]{searchString}; 
    if(searchString.contains(", ")) {
      searchTerms = searchString.split(", ");
    } else if (searchString.contains("; ")) {
      searchTerms = searchString.split("; ");
    } else if (searchString.contains(",")) {
      searchTerms = searchString.split(",");
    } else if (searchString.contains(";")) {
      searchTerms = searchString.split(";");
    } else if (searchString.contains(" ")) {
      searchTerms = searchString.split(" ");
    }

    String queryString = "SELECT e FROM Customer e where ";
    
    int counter = 0;
    for(String searchTerm : searchTerms) {
      if(counter++ != 0) {
        queryString += "or ";
      }
      queryString += "e.firstName LIKE '" + searchTerm + "' OR e.lastName LIKE '" + searchTerm + "' ";
    }
    
    TypedQuery<Customer> query = entityManager.createQuery(queryString,
        Customer.class);

    return query.getResultList();
  }
  
  public void deleteCustomer(Long id) {
    Customer customer = findCustomerById(id);
    if(customer != null) {
      entityManager.remove(customer);
    }
  }

}
