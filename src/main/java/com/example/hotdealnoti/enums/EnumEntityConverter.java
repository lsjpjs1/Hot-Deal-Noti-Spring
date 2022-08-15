package com.example.hotdealnoti.enums;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

@AllArgsConstructor
public class EnumEntityConverter<E extends Enum& EnumEntityConvertable> implements AttributeConverter<EnumEntityConvertable, String> {

    private Class<E> typeToken;
    @Override
    public String convertToDatabaseColumn(EnumEntityConvertable attribute) {
        if (attribute==null){
            return null;
        }
        return attribute.getEntityValue();
    }

    @SneakyThrows
    @Override
    public EnumEntityConvertable convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        if (dbData.isEmpty()){
            return null;
        }
        return EnumEntityConvertable.getEnumValue(typeToken,dbData);
    }


}
