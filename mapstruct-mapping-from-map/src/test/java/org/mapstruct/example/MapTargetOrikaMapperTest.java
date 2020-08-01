package org.mapstruct.example;

import org.junit.Test;
import org.mapstruct.example.dto.Gender;
import org.mapstruct.example.dto.Target;
import org.mapstruct.example.mapper.MapTargetOrikaMapper;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Frankie Chao by 2020-07-30.
 */
public class MapTargetOrikaMapperTest {

    private MapTargetOrikaMapper mapTargetOrikaMapper = new MapTargetOrikaMapper();

    @Test
    public void testMapperOnExistingIpServerAndYear() {

        Map<String, Object> map = new HashMap<>();
        map.put("ip", "127.0.0.1");
        map.put("server", "168.192.1.1");
        map.put("year", 1986);
        map.put("gender", Gender.Male);

        Target target = mapTargetOrikaMapper.map(map);

        assertEquals(target.getIp(), "127.0.0.1");
        assertEquals(target.getServer(), "168.192.1.1");
        assertEquals(target.getYear(), Integer.valueOf(1986));
        assertEquals(target.getGender(), Gender.Male);
    }

    @Test
    public void testMapperOnNonExistingIpServerAndYear() {
        Map<String, Object> map = new HashMap<>();

        Target target = mapTargetOrikaMapper.map(map);

        assertNull(target.getIp());
        assertNull(target.getServer());
        assertNull(target.getYear());
        assertNull(target.getGender());
    }
}
