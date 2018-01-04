import com.conference.persistence.idao.IGeneric;
import com.conference.persistence.idao.Identified;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gleb on 13.12.17.
 */
@RunWith(Parameterized.class)
public abstract class IGenericTest <Context> {
    /**
     * Класс тестируемого дао объекта.
     */
    protected Class daoClass;

    /**
     * Экземпляр доменного объекта, которому не соответствует запись в системе хранения.
     */
    protected Identified notPersistedDto;

    /**
     * Экземпляр тестируемого дао объекта.
     * @return
     */
    public abstract IGeneric dao();

    /**
     * Контекст взаимодействия с системой хранения данных.
     * @return
     */
    public abstract Context context();



    public IGenericTest(Class clazz, Identified<Long> notPersistedDto) {
        this.daoClass = clazz;
        this.notPersistedDto = notPersistedDto;
    }

    @Test
    public void testCreate() throws Exception {
        Identified dto = dao().create();
        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getId());
    }

    @Test
    public void testPersist() throws Exception {
        Assert.assertNull("Id befor persist is not null.", notPersistedDto.getId());
        notPersistedDto = dao().persist(notPersistedDto);
        Assert.assertNotNull("After persist id is null.", notPersistedDto.getId());
    }

    @Test
    public void testGetByPK() throws Exception {
        Serializable id = dao().create().getId();
        Identified dto = dao().getByPK(id);
        Assert.assertNotNull(dto);
    }

    @Test
    public void testDelete() throws Exception {
        Identified dto = dao().create();
        Assert.assertNotNull(dto);
        List list = dao().getAll();
        Assert.assertNotNull(list);
        int oldSize = list.size();
        Assert.assertTrue(oldSize > 0);
        dao().delete(dto);
        list = dao().getAll();
        Assert.assertNotNull(list);
        int newSize = list.size();
        Assert.assertEquals(1, oldSize - newSize);
    }

    @Test
    public void testGetAll() throws Exception {
        List list = dao().getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

}
