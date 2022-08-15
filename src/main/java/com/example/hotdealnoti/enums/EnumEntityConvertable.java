package com.example.hotdealnoti.enums;


import java.lang.reflect.Method;

public interface EnumEntityConvertable<E extends Enum & EnumEntityConvertable>{

    //Enum -> DB에 들어갈 문자열
    String getEntityValue();

    //문자열에 해당하는 Enum 값이 맞는지 여부
    Boolean isTargetEnum(String s);


    static <E extends Enum & EnumEntityConvertable> E getEnumValue(Class<E> typeToken,String s) throws Exception {
        Method valuesMethod = typeToken.getDeclaredMethod("values", null);
        E[] values = (E[]) valuesMethod.invoke(null);
        for(E enumValue : values){
            if (enumValue.isTargetEnum(s)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException();
    }

}
