package com.example.Device.Management.devicetype;

import com.example.Device.Management.design.devicetype.DeviceTypeDTO;
import com.example.Device.Management.design.devicetype.DeviceTypeService;
import com.example.Device.Management.enums.IdentifierType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeviceTypeServiceTests {
    @Autowired
    private DeviceTypeService service;

    private DeviceTypeDTO createDeviceType1 () {
        return new DeviceTypeDTO("Samsung");
    }

    private DeviceTypeDTO createDeviceType2 () {
        return new DeviceTypeDTO("Apple");
    }

    private DeviceTypeDTO createDeviceType3 () {
        return new DeviceTypeDTO("HP");
    }

    @Test
    public void testFindAllDeviceTypes_emptyDB() {
        List<DeviceTypeDTO> deviceTypes = service.findAll();
        assertEquals(0, deviceTypes.size());
    }

    @Test
    public void testFindAllDeviceTypes() throws ParseException {
        DeviceTypeDTO deviceType1 = createDeviceType1();
        DeviceTypeDTO deviceType2 = createDeviceType2();
        DeviceTypeDTO deviceType3 = createDeviceType3();

        service.save(deviceType1);
        service.save(deviceType2);
        service.save(deviceType3);

        List<DeviceTypeDTO> deviceTypes = service.findAll();
        assertEquals(3, deviceTypes.size());
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
        DeviceTypeDTO deviceType = createDeviceType1();

        String id = service.save(deviceType);
        assertEquals("Samsung", service.findById(Long.parseLong(id)).getName());
    }

    @Test
    public void testFindByName_emptyDB() {
        assertNull(service.findByName("Samsung"));
    }

        @Test
        public void testFindByName() {
            DeviceTypeDTO deviceType = createDeviceType1();

            service.save(deviceType);
            assertNotNull(service.findByName("Samsung"));
        }

    @Test
    public void testDeleteById() {
        DeviceTypeDTO deviceType = createDeviceType1();

        String id = service.save(deviceType);
        assertEquals(1, service.findAll().size());

        service.deleteById(Long.parseLong(id));
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testDeleteByName() {
        DeviceTypeDTO deviceType = createDeviceType1();

        service.save(deviceType);
        assertEquals(1, service.findAll().size());

        service.deleteByName(deviceType.getName());
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void testUpdateById() {
        DeviceTypeDTO deviceType1 = createDeviceType1();
        DeviceTypeDTO deviceType2 = createDeviceType2();

        String idDeviceType1 = service.save(deviceType1);
        assertNotNull(service.findByName("Samsung"));

        service.update(idDeviceType1, deviceType2, IdentifierType.ID);
        assertEquals("Apple", service.findById(Long.parseLong(idDeviceType1)).getName());
    }

    @Test
    public void testUpdateByName() {
        DeviceTypeDTO deviceType1 = createDeviceType1();
        DeviceTypeDTO deviceType2 = createDeviceType2();

        service.save(deviceType1);
        assertNotNull(service.findByName("Samsung"));

        service.update("Samsung", deviceType2, IdentifierType.NAME);
        assertNull(service.findByName("Samsung"));
    }
}
