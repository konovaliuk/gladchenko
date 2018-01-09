import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.*;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import com.conference.persistence.idao.Identified;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;


/**
 * Created by gleb on 13.12.17.
 */

public class DaoTest extends IGenericTest<Connection> {
    private String user = "root";
    private String password = "qwer";
    private String url = "jdbc:mysql://localhost:3306/conference";
    private Connection connection;
    private IGeneric dao;
    private static final IFactory<Connection> factory = new MySqlDaoFactory();

    public DaoTest(Class clazz, Identified<Long> notPersistedDto) {
        super(clazz, notPersistedDto);
    }

    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][]{
                //{Event.class, new Event()},
                {User.class, new User()},
                {Report.class, new Report()},
                {Salary.class, new Salary()},
                {Registration.class, new Registration()},
                {Topic.class, new Topic()}
        });
    }

    @Before
    public void setUp() throws PersistException, SQLException {
        connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        dao = factory.getDao(connection, daoClass);
    }

    @After
    public void tearDown() throws SQLException {
        context().rollback();
        context().close();
    }

    @Override
    public IGeneric dao() {
        return dao;
    }

    @Override
    public Connection context() {
        return connection;
    }

}
