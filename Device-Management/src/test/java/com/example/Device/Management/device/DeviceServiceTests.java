package com.example.Device.Management.device;

import com.example.Device.Management.design.device.DeviceDTO;
import com.example.Device.Management.design.device.DeviceService;
import com.example.Device.Management.enums.IdentifierType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeviceServiceTests {
    @Autowired
    private DeviceService service;

    private DeviceDTO createDevice1() throws ParseException {
        SimpleDateFormat sdfFirst1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date firstConnection1 = sdfFirst1.parse("2018-12-10T13:45:00.000Z");
        SimpleDateFormat sdfLast1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date lastConnection1 = sdfLast1.parse("2019-02-11T13:45:00.000Z");
        return new DeviceDTO("D8:13:13:1F:4C:F5", "100F",
                2L, 3L, 1L, "hsjhdakjd", firstConnection1, lastConnection1);
    }

    private DeviceDTO createDevice2() throws ParseException {
        SimpleDateFormat sdfFirst2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date firstConnection2 = sdfFirst2.parse("2018-12-10T13:45:00.000Z");
        SimpleDateFormat sdfLast2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date lastConnection2 = sdfLast2.parse("2019-02-11T13:45:00.000Z");
        return new DeviceDTO("D9:10:13:55:4C:F5", "2A3",
                2L, 3L, 1L, "slckaslmdc", firstConnection2, lastConnection2);
    }

    private DeviceDTO createDevice3() throws ParseException {
        SimpleDateFormat sdfFirst3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date firstConnection3 = sdfFirst3.parse("2018-12-10T13:45:00.000Z");
        SimpleDateFormat sdfLast3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date lastConnection3 = sdfLast3.parse("2019-02-11T13:45:00.000Z");
        return new DeviceDTO("EE:13:99:1F:FA:F5", "523",
                2L, 3L, 1L, "awelosdij", firstConnection3, lastConnection3);
    }

    @Test
    public void testFindAllDevices_emptyDB() {
        List<DeviceDTO> devices = service.findAll();
        assertEquals(0, devices.size());
    }

    @Test
    public void testFindAllDevices() throws ParseException {
        DeviceDTO device1 = createDevice1();
        DeviceDTO device2 = createDevice2();
        DeviceDTO device3 = createDevice3();

        service.save(device1);
        service.save(device2);
        service.save(device3);

        List<DeviceDTO> customers = service.findAll();
        assertEquals(3, customers.size());
    }

    @Test
    public void testFindById_emptyDB() {
        assertThrows(NoSuchElementException.class, () -> {
            service.findById(2L);
            fail(); // daca nu adaugam fail testul trece si daca gaseste, si daca nu
        });
    }

    @Test
    public void testFindById() throws ParseException {
        DeviceDTO device = createDevice1();

        String id = service.save(device);

        assertEquals("D8:13:13:1F:4C:F5", service.findById(Long.parseLong(id)).getMacAddr());
    }

    @Test
    public void testDeleteById_emptyDB() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            service.deleteById(2L);
            fail();
        });
    }

    @Test
    public void testDeleteById() throws ParseException {
        DeviceDTO device = createDevice1();

        String id = service.save(device);
        assertEquals(1, service.findAll().size());

        service.deleteById(Long.parseLong(id));
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testDeleteByMacAddr() throws ParseException {
        DeviceDTO device = createDevice1();

        service.save(device);
        assertEquals(1, service.findAll().size());

        service.deleteByMacAddr(device.getMacAddr());
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testDeleteBySerial() throws ParseException {
        DeviceDTO device = createDevice1();

        service.save(device);
        assertEquals(1, service.findAll().size());

        service.deleteBySerial(device.getSerial());
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testUpdateById() throws ParseException {
        DeviceDTO device1 = createDevice1();
        DeviceDTO device2 = createDevice2();

        String idDevice1 = service.save(device1);
        assertNotNull(service.findById(Long.parseLong(idDevice1)));

        service.update(idDevice1, device2, IdentifierType.ID);
        assertNotEquals(device1.getMacAddr(), service.findById(Long.parseLong(idDevice1)).getMacAddr());
    }

    @Test
    public void testUpdateByMacAddress() throws ParseException {
        DeviceDTO device1 = createDevice1();
        DeviceDTO device2 = createDevice2();

        String idDevice1 = service.save(device1);
        assertNotNull(service.findById(Long.parseLong(idDevice1)));

        service.update(device1.getMacAddr(), device2, IdentifierType.MAC_ADDRESS);
        assertNotEquals(device1.getMacAddr(), service.findById(Long.parseLong(idDevice1)).getMacAddr());
    }

    @Test
    public void testUpdateBySerial() throws ParseException {
        DeviceDTO device1 = createDevice1();
        DeviceDTO device2 = createDevice2();

        String idDevice1 = service.save(device1);
        assertNotNull(service.findById(Long.parseLong(idDevice1)));

        service.update(device1.getSerial(), device2, IdentifierType.SERIAL);
        assertNotEquals(device1.getMacAddr(), service.findById(Long.parseLong(idDevice1)).getMacAddr());
    }
}
