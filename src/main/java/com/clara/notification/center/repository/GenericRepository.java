package com.clara.notification.center.repository;

public interface GenericRepository<T> {

    T SaveData(T t);

    void RemoveData(T t);

    T GetData(T t);

    T UpdateData(T t);

    Iterable<T> GetAll();
}
