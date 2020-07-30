package ru.bellintegrator.trainingproject.service.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Класс для конфигурации маппера
 */
@Component
public class Configure extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        ConverterFactory converterFactory = factory.getConverterFactory();
        converterFactory.registerConverter(new UserConverter());
    }
}
