package com.mytaxi.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

/**
 * This class helps in the conversion of DTOs and DOs using the ModelMapper API
 */
public final class ModelMapperHelper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    /**
     * Maps all values from a object of one type to a new object of a different type.
     * 
     * @param obj
     * @param targetClass
     * @return
     */
    public static <E, T> T map(E obj, Class<T> targetClass) {
	return (T) MODEL_MAPPER.map(obj, targetClass);
    }

    /**
     * Maps all values from all objects in a list of one type to a new list of objects of a different type.
     * 
     * @param objList
     * @param targetClass
     * @return
     */
    public static <E, T> List<E> mapList(List<T> objList, Class<E> targetClass) {
	return objList.stream().map(x -> ModelMapperHelper.map(x, targetClass))
		.collect(Collectors.toList());
    }
}
