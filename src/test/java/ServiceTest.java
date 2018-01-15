import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Salary;
import com.conference.service.SalaryService;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by gleb on 29.12.17.
 */
public class ServiceTest {

    @Test
    public void salaryServiceTest() throws PersistException {
        SalaryService salaryService = mock(SalaryService.class);
        Salary salary = new Salary();
        when(salaryService.getSalary(3L)).thenReturn(salary);
        Salary result = salaryService.getSalary(3L);
        assertEquals(salary, result);
    }
}
