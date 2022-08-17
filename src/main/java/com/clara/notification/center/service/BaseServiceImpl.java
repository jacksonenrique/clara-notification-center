package com.clara.notification.center.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseServiceImpl<T> implements GenericService<T> {

    private Class<T> type;
    private final Logger log = LoggerFactory.getLogger(type);

    public BaseServiceImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T SaveUpdate(T entidadDTO) throws Exception {
        return null;
    }

    @Override
    public List<T> GetEntities() throws Exception {
        return null;
    }

    @Override
    public T FindEntity(int id) throws Exception {
        return null;
    }

    @Override
    public void RemoveEntity(int id) throws Exception {}
}
