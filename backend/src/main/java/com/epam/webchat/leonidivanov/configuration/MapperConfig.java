package com.epam.webchat.leonidivanov.configuration;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MapperConfig {

    private MapperFactory mapperFactory;


}
