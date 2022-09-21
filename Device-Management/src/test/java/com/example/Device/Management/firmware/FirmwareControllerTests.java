package com.example.Device.Management.firmware;

import com.example.Device.Management.design.firmware.FirmwareController;
import com.example.Device.Management.design.firmware.FirmwareDTO;
import com.example.Device.Management.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FirmwareControllerTests {
    @Autowired
    private FirmwareController controller;

    private FirmwareDTO createFirmware1 () {
        return new FirmwareDTO("exp", State.DEPLOYED);
    }

    private FirmwareDTO createFirmware2 () {
        return new FirmwareDTO("exp", State.FAULTY);
    }

    private FirmwareDTO createFirmware3 () {
        return new FirmwareDTO("exp", State.OBSOLETE);
    }

    @Test
    public void testFindAllFirmwares_emptyDB() {
        List<FirmwareDTO> firmwares = controller.findAll();
        assertEquals(0, firmwares.size());
    }

    @Test
    public void testFindAllFirmwares() throws ParseException {
        FirmwareDTO firmware1 = createFirmware1();
        FirmwareDTO firmware2 = createFirmware2();
        FirmwareDTO firmware3 = createFirmware3();

        controller.save(firmware1);
        controller.save(firmware2);
        controller.save(firmware3);

        List<FirmwareDTO> firmwares = controller.findAll();
        assertEquals(3, firmwares.size());
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
        FirmwareDTO firmware = createFirmware1();

        String id = controller.save(firmware);
        assertEquals("exp", controller.findById(Long.parseLong(id)).getName());
    }

    @Test
    public void testFindByName_emptyDB() {
        assertNull(controller.findByName("exp"));
    }

    @Test
    public void testFindByName() {
        FirmwareDTO firmware = createFirmware1();

        controller.save(firmware);
        assertNotNull(controller.findByName("exp"));
    }

    @Test
    public void testDeleteById() {
        FirmwareDTO firmware = createFirmware1();

        String id = controller.save(firmware);
        assertEquals(1, controller.findAll().size());

        controller.deleteById(Long.parseLong(id));
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testDeleteByName() {
        FirmwareDTO firmware = createFirmware1();

        controller.save(firmware);
        assertEquals(1, controller.findAll().size());

        controller.deleteByName(firmware.getName());
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testUpdateById() {
        FirmwareDTO firmware1 = createFirmware1();
        FirmwareDTO firmware2 = createFirmware2();

        String idDeviceType1 = controller.save(firmware1);
        assertNotNull(controller.findByName("exp"));

        controller.updateById(Long.valueOf(idDeviceType1), firmware2);
        assertEquals(State.FAULTY, controller.findById(Long.parseLong(idDeviceType1)).getState());
    }

    @Test
    public void testUpdateByName() {
        FirmwareDTO firmware1 = createFirmware1();
        FirmwareDTO firmware2 = createFirmware2();

        controller.save(firmware1);
        assertNotNull(controller.findByName("exp"));

        controller.updateByName("exp", firmware2);
        assertEquals(State.FAULTY, controller.findByName("exp").getState());
    }
}
