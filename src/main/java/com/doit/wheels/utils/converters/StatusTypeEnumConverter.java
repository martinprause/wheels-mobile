package com.doit.wheels.utils.converters;

import com.doit.wheels.utils.enums.StatusTypeEnum;
import org.springframework.core.convert.converter.Converter;

public class StatusTypeEnumConverter implements Converter<String, StatusTypeEnum> {

    @Override
    public StatusTypeEnum convert(String source) {
        return StatusTypeEnum.valueOf(source);
    }
}
