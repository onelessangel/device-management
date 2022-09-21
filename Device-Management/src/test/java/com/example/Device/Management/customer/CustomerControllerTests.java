package com.example.Device.Management.customer;

import com.example.Device.Management.design.customer.CustomerController;
import com.example.Device.Management.design.customer.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerControllerTests {
    @Autowired
    private CustomerController controller;

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
        List<CustomerDTO> customers = controller.findAll();
        assertEquals(0, customers.size());
    }

    @Test
    public void testFindAllUsers() throws ParseException {
        CustomerDTO customer1 = createCustomer1();
        CustomerDTO customer2 = createCustomer2();
        CustomerDTO customer3 = createCustomer3();

        controller.save(customer1);
        controller.save(customer2);
        controller.save(customer3);

        List<CustomerDTO> customers = controller.findAll();
        assertEquals(3, customers.size());
    }

    @Test
    public void testFindById_emptyDB() {
        assertThrows(NoSuchElementException.class, () -> {
            controller.findById("2");
            fail(); // daca nu adaugam fail testul trece si daca gaseste, si daca nu
        });
    }

    @Test
    public void testFindById() throws ParseException {
        CustomerDTO customer = createCustomer1();

        String id = controller.save(customer);
        assertEquals("Teo", controller.findById(id).getName());
    }

    @Test
    public void testFindByName_emptyDB() {
        assertNull(controller.findByName("Teo"));
    }

    @Test
    public void testFindByName() throws ParseException {
        CustomerDTO customer = createCustomer1();

        controller.save(customer);
        assertNotNull(controller.findByName("Teo"));
    }

    @Test
    public void testDeleteById() throws ParseException {
        CustomerDTO customer = createCustomer1();

        String id = controller.save(customer);
        assertEquals(1, controller.findAll().size());

        controller.deleteById(id);
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testDeleteByName() throws ParseException {
        CustomerDTO customer = createCustomer1();

        controller.save(customer);
        assertEquals(1, controller.findAll().size());

        controller.deleteByName(customer.getName());
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testUpdate() throws ParseException {
        CustomerDTO customer1 = createCustomer1();
        CustomerDTO customer2 = createCustomer4();

        String idCustomer1 = controller.save(customer1);
        assertNotNull(controller.findByName("Teo"));

        controller.update(idCustomer1, customer2);
        assertNotEquals(customer1.getFirstConnection(), controller.findByName("Teo").getFirstConnection());
    }
}
