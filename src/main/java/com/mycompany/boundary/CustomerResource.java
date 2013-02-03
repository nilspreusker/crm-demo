package com.mycompany.boundary;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.mycompany.control.CustomerService;
import com.mycompany.entity.Customer;

@Path("/customer")
@Stateless
public class CustomerResource {

  @Inject
  private CustomerService customerService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findCustomers(@QueryParam("searchString") String searchString,
      @QueryParam("name") String name) {
    List<Customer> customers;
    if (searchString != null) {
      customers = customerService.findCustomers(searchString);
    } else {
      customers = customerService.findAllCustomers();
    }
    return Response.ok(customers).build();
  }

  @POST
  public Response saveCustomer(@Context UriInfo uriInfo, Customer customer)
      throws URISyntaxException {
    customerService.saveCustomer(customer);
    return Response.created(
        new URI(uriInfo.getRequestUri() + "/" + customer.getId())).build();
  }

  @GET
  @Path("/{customerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findCustomerById(@PathParam(value = "customerId") String customerId) {
    Customer customer = customerService.findCustomerById(Long
        .parseLong(customerId));

    if (customer != null) {
      return Response.ok().entity(customer).build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @PUT
  @Path("/{customerId}")
  public Response updateCustomer(Customer customer) {
    customerService.updateCustomer(customer);
    return Response.status(Status.ACCEPTED).build();
  }

  @DELETE
  @Path("/{customerId}")
  public Response deleteCustomer(@PathParam("customerId") Long customerId) {
    customerService.deleteCustomer(customerId);
    return Response.ok().build();
  }

}
