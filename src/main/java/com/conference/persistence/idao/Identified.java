package com.conference.persistence.idao;

/**
 * Created by gleb on 12.12.17.
 */

import java.io.Serializable;

/**
 * Интерфейс идентифицируемых объектов.
 * @param <PK>
 */
public interface Identified<PK extends Serializable> {

    /** Возвращает идентификатор объекта */
    PK getId();
}
