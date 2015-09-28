package com.github.glomadrian.androidsamples.datapolicies.domain.mapper;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public interface NewsMapper<T> extends Mapper<T, List<NewItem>> {
}
