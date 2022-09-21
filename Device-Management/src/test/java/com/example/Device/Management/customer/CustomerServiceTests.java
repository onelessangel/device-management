package com.example.Device.Management.customer;

import com.example.Device.Management.design.customer.CustomerDTO;
import com.example.Device.Management.design.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // incarca contextul de Spring - clasele adnotate cu @Service, @Component, @Controller
public class CustomerServiceTests {
    @Autowired
    private CustomerService service;

    private CustomerDTO createCustomer1 () throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date firstConnection1 = sdf1.parse("2018-12-10T13:45:00.000Z");
        return new CustomerDTO("Teo", firstConnection1);
    }

    private CustomerDTO createCustomer2 () throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date firstConnection2 = sdf2.parse("2018-12-10T13:45:00.000Z");
        return new CustomerDTO("Alina", firstConnection2);
    }

    private CustomerDTO createCustomer3 () throws ParseException {
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date firstConnection3 = sdf3.parse("2018-12-10T13:45:00.000Z");
        return new CustomerDTO("Magda", firstConnection3);
    }

    private CustomerDTO createCustomer4() throws  ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date firstConnection2 = sdf2.parse("2020-12-10T13:45:00.000Z");
        return new CustomerDTO("Teo", firstConnection2);
    }

    @Test
    public void testFindAllUsers_emptyDB() {
        List<CustomerDTO> customers = service.findAll();
        assertEquals(0, customers.size());
    }

    @Test
    public void testFindAllUsers() throws ParseException {
        CustomerDTO customer1 = createCustomer1();
        CustomerDTO customer2 = createCustomer2();
        CustomerDTO customer3 = createCustomer3();

        service.save(customer1);
        service.save(customer2);
        service.save(customer3);

        List<CustomerDTO> customers = service.findAll();
        assertEquals(3, customers.size());
    }

    @Test
    public void testFindById_emptyDB() {
        assertThrows(NoSuchElementException.class, () -> {
            service.findById("2");
            fail(); // daca nu adaugam fail testul trece si daca gaseste, si daca nu
        });
    }

    @Test
    public void testFindById() throws ParseException {
        CustomerDTO customer = createCustomer1();

        String id = service.save(customer).getId();
        assertEquals("Teo", service.findById(id).getName());
    }

    @Test
    public void testFindByName_emptyDB() {
        assertNull(service.findByName("Teo"));
    }

    @Test
    public void testFindByName() throws ParseException {
        CustomerDTO customer = createCustomer1();

        service.save(customer);
        assertNotNull(service.findByName("Teo"));
    }

//    @Test
//    public void testDeleteById_emptyDB() {
//        assertThrows(EmptyResultDataAccessException.class, () -> {
//            service.deleteById("2");
//            fail();
//        });
//    }

    @Test
    public void testDeleteById() throws ParseException {
        CustomerDTO customer = createCustomer1();

        String id = service.save(customer).getId();
        assertEquals(1, service.findAll().size());

        service.deleteById(id);
        assertEquals(0, service.findAll().size());
    }

   @Test
    public void testDeleteByName() throws ParseException {
       CustomerDTO customer = createCustomer1();

       service.save(customer);
       assertEquals(1, service.findAll().size());

       service.deleteByName(customer.getName());
       assertEquals(0, service.findAll().size());
   }

    @Test
    public void testUpdate() throws ParseException {
        CustomerDTO customer1 = createCustomer1();
        CustomerDTO customer2 = createCustomer4();

        String idCustomer1 = service.save(customer1).getId();
        assertNotNull(service.findByName("Teo"));

        service.update(idCustomer1, customer2);
        assertNotEquals(customer1.getFirstConnection(), service.findByName("Teo").getFirstConnection());
    }
}
