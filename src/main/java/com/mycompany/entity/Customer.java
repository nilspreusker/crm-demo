package com.mycompany.entity;

import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Customer entity, representing a customer object that can be persisted to a
 * relational database and, at the same time, be used as a transfer object for
 * customer entities that are used in the user interface.
 * 
 * @author Nils Preusker - n.preusker@gmail.com
 */
@Entity
public class Customer {

  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String fax;
  private Sex sex;
  private String country;
  private Locale locale;
  private Date createDate;
  @ManyToOne(cascade = { CascadeType.ALL })
  private Company company;

  /**
   * Default constructor for JAX-RS (object <> JSON serialization)
   */
  public Customer() {
  }

  public Customer(String firstName, String lastName, String email,
      String phone, String fax, Sex sex, String country, Locale locale,
      Date createDate, Company company) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.fax = fax;
    this.sex = sex;
    this.country = country;
    this.locale = locale;
    this.createDate = createDate;
    this.company = company;
  }

  // -------- getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

}
