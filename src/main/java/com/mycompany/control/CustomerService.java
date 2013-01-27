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

  public List<Customer> findCustomer(String firstName, String lastName) {
    TypedQuery<Customer> query = entityManager.createQuery(
        "SELECT e FROM Customer e where e.firstName LIKE ? AND e.name LIKE ?",
        Customer.class);

    query.setParameter(1, "%" + firstName + "%");
    query.setParameter(2, "%" + lastName + "%");

    return query.getResultList();
  }

}
