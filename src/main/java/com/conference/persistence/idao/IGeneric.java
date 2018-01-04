package com.conference.persistence.idao;

import com.conference.persistence.dao.PersistException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gleb on 12.12.17.
 */

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param <T>
 * @param <PK>
 */
public interface IGeneric<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Создает новую запись и соответствующий ей объект
     * @return
     * @throws PersistException
     */
    T create() throws PersistException;

    /**
     * Создает новую запись, соответствующую объекту object
     * @param object
     * @return
     * @throws PersistException
     */
    T persist(T object) throws PersistException;

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     * @param key
     * @return
     * @throws PersistException
     */
    T getByPK(PK key) throws PersistException;

    /**
     * Сохраняет состояние объекта group в базе данных
     * @param object
     * @throws PersistException
     */
    void update(T object) throws PersistException;

    /**
     * Удаляет запись об объекте из базы данных
     * @param object
     * @throws PersistException
     */
    void delete(T object) throws PersistException;

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     * @return
     */
    List<T> getAll() throws PersistException;

    /**
     * Возвращает список объектов соответствующих параметру
     * @param param
     * @param value
     * @return
     */
    List<T> getAllByParam(String param, String value) throws PersistException;

    /**
     * Возвращает объект соответствующий записи с заданым параметром
     * @param param
     * @param value
     * @return
     * @throws PersistException
     */
    T getByParam(String param, String value) throws PersistException;
}
