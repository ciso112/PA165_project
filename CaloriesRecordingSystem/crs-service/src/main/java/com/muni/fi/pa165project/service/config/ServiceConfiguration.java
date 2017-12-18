package com.muni.fi.pa165project.service.config;

import com.muni.fi.pa165project.config.AppContextConfiguration;
import com.muni.fi.pa165project.dto.*;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.entity.Record;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.service.UserServiceImpl;
import com.muni.fi.pa165project.service.facade.UserFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Radoslav Karlik
 */
@Configuration
@Import({AppContextConfiguration.class})
@ComponentScan(basePackageClasses={UserServiceImpl.class, UserFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(mappingConfiguration());
        //fix for Dozer issues with JDK8 Time
        mapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        return mapper;
    }

    private BeanMappingBuilder mappingConfiguration() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Activity.class, ActivityCreateDTO.class, TypeMappingOptions.oneWay())
                        .fields("categoryObject", "category");
                mapping(ActivityDTO.class, ActivityUpdateDTO.class, TypeMappingOptions.oneWay())
                        .fields("category.id", "category");
                mapping(RecordDTO.class, Record.class, TypeMappingOptions.oneWay())
                        .fields("activityName", "activity.name")
                        .fields("activityId", "activity.id");
                mapping(UserDetailDTO.class, User.class, TypeMappingOptions.oneWay())
                        .fields("username", "loginDetails.username")
                        .fields("email", "loginDetails.email");
                mapping(User.class, UserRegisterDTO.class)
                        .fields("loginDetails.username", "username")
                        .fields("loginDetails.password", "password")
                        .fields("loginDetails.email", "email")
                        .fields("birthDate", "birthDate", FieldsMappingOptions.customConverter(LocalDateConverter.class));
                mapping(User.class, UserUpdateDTO.class)
                        .fields("loginDetails.password", "password")
                        .fields("loginDetails.email", "email");
                mapping(Record.class, RecordCreateDTO.class)
                        .fields("atTime", "atTime", FieldsMappingOptions.customConverter(LocalDateTimeConverter.class));
                mapping(RecordGetUpdateDTO.class, RecordDTO.class)
                        .fields("atTime", "atTime", FieldsMappingOptions.customConverter(LocalDateTimeConverter.class));
            }
        };

        return builder;
    }
}