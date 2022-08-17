package com.clara.notification.center.service.mapping;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface GenericEntityMapping<D, E> {

    E getEntity(D dto);

    D getDto(E entity);

    List<E> getEntity(List<D> entities);

    List<D> getDto(List<E> entities);
}
