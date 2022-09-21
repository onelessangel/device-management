package com.example.Device.Management.firmware;

import com.example.Device.Management.design.firmware.FirmwareDTO;
import com.example.Device.Management.design.firmware.FirmwareService;
import com.example.Device.Management.enums.IdentifierType;
import com.example.Device.Management.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FirmwareServiceTests {
    @Autowired
    private FirmwareService service;

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
        List<FirmwareDTO> firmwares = service.findAll();
        assertEquals(0, firmwares.size());
    }

    @Test
    public void testFindAllFirmwares() throws ParseException {
        FirmwareDTO firmware1 = createFirmware1();
        FirmwareDTO firmware2 = createFirmware2();
        FirmwareDTO firmware3 = createFirmware3();

        service.save(firmware1);
        service.save(firmware2);
        service.save(firmware3);

        List<FirmwareDTO> firmwares = service.findAll();
        assertEquals(3, firmwares.size());
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
        FirmwareDTO firmware = createFirmware1();

        String id = service.save(firmware);
        assertEquals("exp", service.findById(Long.parseLong(id)).getName());
    }

    @Test
    public void testFindByName_emptyDB() {
        assertNull(service.findByName("exp"));
    }

    @Test
    public void testFindByName() {
        FirmwareDTO firmware = createFirmware1();

        service.save(firmware);
        assertNotNull(service.findByName("exp"));
    }

    @Test
    public void testDeleteById() {
        FirmwareDTO firmware = createFirmware1();

        String id = service.save(firmware);
        assertEquals(1, service.findAll().size());

        service.deleteById(Long.parseLong(id));
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testDeleteByName() {
        FirmwareDTO firmware = createFirmware1();

        service.save(firmware);
        assertEquals(1, service.findAll().size());

        service.deleteByName(firmware.getName());
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testUpdateById() {
        FirmwareDTO firmware1 = createFirmware1();
        FirmwareDTO firmware2 = createFirmware2();

        String idFirmware1 = service.save(firmware1);
        assertNotNull(service.findByName("exp"));

        service.update(idFirmware1, firmware2, IdentifierType.ID);
        assertEquals(State.FAULTY, service.findById(Long.parseLong(idFirmware1)).getState());
    }

    @Test
    public void testUpdateByName() {
        FirmwareDTO firmware1 = createFirmware1();
        FirmwareDTO firmware2 = createFirmware2();

        service.save(firmware1);
        assertNotNull(service.findByName("exp"));

        service.update("exp", firmware2, IdentifierType.NAME);
        assertEquals(State.FAULTY, service.findByName("exp").getState());
    }
}
