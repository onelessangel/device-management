package com.example.Device.Management.devicetype;

import com.example.Device.Management.design.devicetype.DeviceTypeController;
import com.example.Device.Management.design.devicetype.DeviceTypeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeviceTypeControllerTests {
    @Autowired
    private DeviceTypeController controller;

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
        List<DeviceTypeDTO> deviceTypes = controller.findAll();
        assertEquals(0, deviceTypes.size());
    }

    @Test
    public void testFindAllDeviceTypes() {
        DeviceTypeDTO deviceType1 = createDeviceType1();
        DeviceTypeDTO deviceType2 = createDeviceType2();
        DeviceTypeDTO deviceType3 = createDeviceType3();

        controller.save(deviceType1);
        controller.save(deviceType2);
        controller.save(deviceType3);

        List<DeviceTypeDTO> deviceTypes = controller.findAll();
        assertEquals(3, deviceTypes.size());

        controller.deleteByName(deviceType1.getName());
        controller.deleteByName(deviceType2.getName());
        controller.deleteByName(deviceType3.getName());
    }

    @Test
    public void testFindById_emptyDB() {
        assertThrows(NoSuchElementException.class, () -> {
            controller.findById(2L);
            fail(); // daca nu adaugam fail testul trece si daca gaseste, si daca nu
        });
    }

    @Test
    public void testFindById() throws ParseException {
        DeviceTypeDTO deviceType = createDeviceType1();

        String id = controller.save(deviceType);
        assertEquals("Samsung", controller.findById(Long.parseLong(id)).getName());

        controller.deleteByName(deviceType.getName());
    }

    @Test
    public void testFindByName_emptyDB() {
        assertNull(controller.findByName("Samsung"));
    }

    @Test
    public void testFindByName() {
        DeviceTypeDTO deviceType = createDeviceType1();

        controller.save(deviceType);
        assertNotNull(controller.findByName("Samsung"));

        controller.deleteByName(deviceType.getName());
    }

    @Test
    public void testDeleteById() throws ParseException {
        DeviceTypeDTO deviceType = createDeviceType1();

        String id = controller.save(deviceType);
        assertEquals(1, controller.findAll().size());

        controller.deleteById(Long.parseLong(id));
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testDeleteByName() throws ParseException {
        DeviceTypeDTO deviceType = createDeviceType1();

        controller.save(deviceType);
        assertEquals(1, controller.findAll().size());

        controller.deleteByName(deviceType.getName());
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void testUpdateById() throws ParseException {
        DeviceTypeDTO deviceType1 = createDeviceType1();
        DeviceTypeDTO deviceType2 = createDeviceType2();

        String idDeviceType1 = controller.save(deviceType1);
        assertNotNull(controller.findByName("Samsung"));

        controller.updateById(Long.valueOf(idDeviceType1), deviceType2);
        assertEquals("Apple", controller.findById(Long.parseLong(idDeviceType1)).getName());

        controller.deleteByName(deviceType1.getName());
        controller.deleteByName(deviceType2.getName());
    }

    @Test
    public void testUpdateByName() throws ParseException {
        DeviceTypeDTO deviceType1 = createDeviceType1();
        DeviceTypeDTO deviceType2 = createDeviceType2();

        controller.save(deviceType1);
        assertNotNull(controller.findByName("Samsung"));

        controller.updateByName(deviceType1.getName(), deviceType2);
        assertNull(controller.findByName("Samsung"));

        controller.deleteByName(deviceType1.getName());
        controller.deleteByName(deviceType2.getName());
    }
}
