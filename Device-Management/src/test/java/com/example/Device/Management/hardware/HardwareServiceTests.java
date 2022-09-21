package com.example.Device.Management.hardware;

import com.example.Device.Management.design.hardware.HardwareDTO;
import com.example.Device.Management.design.hardware.HardwareService;
import com.example.Device.Management.enums.IdentifierType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HardwareServiceTests {
    @Autowired
    private HardwareService service;

    private HardwareDTO createHardware1 () {
        return new HardwareDTO("hw exp1");
    }

    private HardwareDTO createHardware2 () {
        return new HardwareDTO("hw exp2");
    }

    private HardwareDTO createHardware3 () {
        return new HardwareDTO("hw exp3");
    }

    @Test
    public void testFindAllHardwares_emptyDB() {
        List<HardwareDTO> hardwares = service.findAll();
        assertEquals(0, hardwares.size());
    }

    @Test
    public void testFindAllHardwares() throws ParseException {
        HardwareDTO hardware1 = createHardware1();
        HardwareDTO hardware2 = createHardware2();
        HardwareDTO hardware3 = createHardware3();

        service.save(hardware1);
        service.save(hardware2);
        service.save(hardware3);

        List<HardwareDTO> hardwares = service.findAll();
        assertEquals(3, hardwares.size());
    }

    @Test
    public void testFindById_emptyDB() {
        assertThrows(NoSuchElementException.class, () -> {
            service.findById(2L);
            fail(); // daca nu adaugam fail testul trece si daca gaseste, si daca nu
        });
    }

    @Test
    public void testFindById() {
        HardwareDTO hardware = createHardware1();

        String id = service.save(hardware);
        assertEquals("hw exp1", service.findById(Long.parseLong(id)).getName());
    }

    @Test
    public void testFindByName_emptyDB() {
        assertNull(service.findByName("hw exp1"));
    }

    @Test
    public void testFindByName() {
        HardwareDTO hardware = createHardware1();

        service.save(hardware);
        assertNotNull(service.findByName("hw exp1"));
    }

    @Test
    public void testDeleteById() {
        HardwareDTO hardware = createHardware1();

        String id = service.save(hardware);
        assertEquals(1, service.findAll().size());

        service.deleteById(Long.parseLong(id));
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testDeleteByName() {
        HardwareDTO hardware = createHardware1();

        service.save(hardware);
        assertEquals(1, service.findAll().size());

        service.deleteByName(hardware.getName());
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testUpdateById() {
        HardwareDTO hardware1 = createHardware1();
        HardwareDTO hardware2 = createHardware2();

        String idHardware1 = service.save(hardware1);
        assertNotNull(service.findByName("hw exp1"));

        System.out.println(hardware2.getName());

        service.update(idHardware1, hardware2, IdentifierType.ID);
        assertEquals("hw exp2", service.findById(Long.parseLong(idHardware1)).getName());
    }

    @Test
    public void testUpdateByName() {
        HardwareDTO hardware1 = createHardware1();
        HardwareDTO hardware2 = createHardware2();

        service.save(hardware1);
        assertNotNull(service.findByName("hw exp1"));

        service.update("hw exp1", hardware2, IdentifierType.NAME);
        assertNotNull(service.findByName("hw exp2"));
    }
}
