package org.mapstruct.example.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.mapstruct.example.dto.Target;

import java.util.Map;

/**
 * @author Frankie Chao by 2020-07-30.
 */
public class MapTargetOrikaMapper {

    private static MapperFacade mapperFacade;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Map.class, Target.class)
                .byDefault()
                .register();

        mapperFacade = mapperFactory.getMapperFacade();
    }

    public Target map(Map<String, Object> valueMap) {
        return mapperFacade.map(valueMap, Target.class);
    }
}
