package com.gabransel.toDoList.v2.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModelMapperUtil {

    private final ModelMapper modelMapper;
    private static ModelMapper staticModelMapper;

    @PostConstruct
    public void initialize(){
        staticModelMapper = this.modelMapper;
    }

    /**
     * Used for the DTO's transformations
     *
     * @param source source entity
     * @param destination destination class that will generate a new entity of the source entity
     */
    public static <S,D> D map(S source, Class<D> destination){
        return staticModelMapper.map(source,destination);
    }

    public static<S,D> D partialUpdate(S source, D destination){
        staticModelMapper.getConfiguration().setSkipNullEnabled(true);
        staticModelMapper.map(source, destination);
        return destination;
    }
}