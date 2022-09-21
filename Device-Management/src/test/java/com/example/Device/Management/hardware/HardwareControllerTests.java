package com.example.Device.Management.hardware;

import com.example.Device.Management.design.hardware.HardwareController;
import com.example.Device.Management.design.hardware.HardwareDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HardwareControllerTests {
    @Autowired
    private HardwareController controller;

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
        List<HardwareDTO> hardwares = controller.findAll();
        assertEquals(0, hardwares.size());
    }

    @Test
    public void testFindAllHardwares() throws ParseException {
        HardwareDTO hardware1 = createHardware1();
        HardwareDTO hardware2 = createHardware2();
        HardwareDTO hardware3 = createHardware3();

        controller.save(hardware1);
        controller.save(hardware2);
        controller.save(hardware3);

        List<HardwareDTO> hardwares = controller.findAll();
        assertEquals(3, hardwares.size());
    }

    @Test
    public void testFindById_emptyDB() {
        assertThrows(NoSuchElementException.class, () -> {
            controller.findById(2L);
            fail(); // daca nu adaugam fail testul trece si daca gaseste, si daca nu
        });
    }

    @Test
    public void testFindById() {
        HardwareDTO hardware = createHardware1();

        String id = controller.save(hardware);
        assertEquals("hw exp1", controller.findById(Long.parseLong(id)).getName());
    }
    
    @Test
    public void testFindByName_emptyDB() {
        assertNull(controller.findByName("hw exp1"));
    }

    @Test
    public void testFindByName() {
        HardwareDTO hardware = createHardware1();

        controller.save(hardware);
        assertNotNull(controller.findByName("hw exp1"));
    }

    @Test
    public void testDeleteById() {
        HardwareDTO hardware = createHardware1();

        String id = controller.save(hardware);
        assertEquals(1, controller.findAll().size());

        controller.deleteById(Long.parseLong(id));
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testDeleteByName() {
        HardwareDTO hardware = createHardware1();

        controller.save(hardware);
        assertEquals(1, controller.findAll().size());

        controller.deleteByName(hardware.getName());
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testUpdateById() {
        HardwareDTO hardware1 = createHardware1();
        HardwareDTO hardware2 = createHardware2();

        String idHardware1 = controller.save(hardware1);
        assertNotNull(controller.findByName("hw exp1"));

        System.out.println(hardware2.getName());

        controller.updateById(Long.valueOf(idHardware1), hardware2);
        assertEquals("hw exp2", controller.findById(Long.parseLong(idHardware1)).getName());
    }

    @Test
    public void testUpdateByName() {
        HardwareDTO hardware1 = createHardware1();
        HardwareDTO hardware2 = createHardware2();

        controller.save(hardware1);
        assertNotNull(controller.findByName("hw exp1"));

        controller.updateByName("hw exp1", hardware2);
        assertNotNull(controller.findByName("hw exp2"));
    }
}
