package com.github.glomadrian.androidsamples.datapolicies.data.mapper;

/**
 * @author Adrián García Lomas
 */
public interface Mapper<TFrom, TTo> {

  TTo map(TFrom from);
}