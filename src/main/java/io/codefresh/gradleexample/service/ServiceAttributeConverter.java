package io.codefresh.gradleexample.service;
import io.codefresh.gradleexample.entity.enums.service_type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ServiceAttributeConverter implements AttributeConverter<service_type, Integer> {

    @Override
    public Integer convertToDatabaseColumn(service_type attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
            case Construction:
                return 1;

            case Delivery:
                return 2;

            case Manufacture:
                return 3;

            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    @Override
    public service_type convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return service_type.Construction;

            case 2:
                return service_type.Delivery;

            case 3:
                return service_type.Manufacture;

            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}