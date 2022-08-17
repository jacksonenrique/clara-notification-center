package com.clara.notification.center.service;

import java.util.List;

public interface GenericService<T> {
    /**
     * Guarda o actualiza los datos de un actividad.
     *
     * @param entityDTO entidad que sera almacenada
     * @return entidad almacenada en la base de datos
     */
    T SaveUpdate(T entityDTO) throws Exception;

    /**
     * Lista todos las entidades (actividad) existentes
     *
     * @return lista de entidades almacenadas en la base de datos
     */
    List<T> GetEntities() throws Exception;

    /**
     * Obtiene el actividad segun el id suministrado.
     *
     * @param id entidad
     * @return entidad almacenada en la base de datos
     */
    T FindEntity(int id) throws Exception;

    /**
     * Guarda o actualiza los datos de un actividad.
     *
     * @param id entidad que sera eliminada
     */
    void RemoveEntity(int id) throws Exception;
}
