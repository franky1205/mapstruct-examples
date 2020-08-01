package org.mapstruct.example.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.builtin.FromStringConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.mapstruct.example.dto.Target;

import java.util.Map;

/**
 * @author Frankie Chao by 2020-07-30.
 */
public class MapTargetOrikaMapper {

    private static MapperFacade mapperFacade;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter("objectToEnumConverter", new ObjectEnumConverter());

        mapperFactory.classMap(Map.class, Target.class)
                .fieldMap("gender", "gender").converter("objectToEnumConverter").add()
                .byDefault()
                .register();

        mapperFacade = mapperFactory.getMapperFacade();
    }

    public Target map(Map<String, Object> valueMap) {
        return mapperFacade.map(valueMap, Target.class);
    }

    private static class ObjectEnumConverter extends FromStringConverter {

        @Override
        public boolean canConvert(Type<?> sourceType, Type<?> destinationType) {
            return Object.class == sourceType.getRawType() && destinationType.isEnum();
        }

        @Override
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public Object convert(Object source, Type<?> destinationType, MappingContext context) {
            if (source == null) {
                return null;
            }
            return Enum.valueOf((Class<Enum>) destinationType.getRawType(), source.toString());
        }
    }
}
