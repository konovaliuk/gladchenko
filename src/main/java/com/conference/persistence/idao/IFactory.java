package com.conference.persistence.idao;

import com.conference.persistence.dao.PersistException;


/**
 * Created by gleb on 12.12.17.
 */
public interface IFactory<Context>{

    /**
     * Интерфейс создания объектов для управления персистентным состоянием объекта
     * @param <Context>
     */
    interface IDaoCreator<Context> {
        IGeneric create(Context context);
    }

    /**
     * Возвращает подключение к базе данных
     * @return
     * @throws PersistException
     */
    Context getContext() throws PersistException;

    /**
     * Возвращает объект для управления персистентным состоянием объекта
     * @param context
     * @param dtoClass
     * @return
     * @throws PersistException
     */
    IGeneric getDao(Context context, Class dtoClass) throws PersistException;


}
