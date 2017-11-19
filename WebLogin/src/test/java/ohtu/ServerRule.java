package ohtu;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import org.junit.rules.ExternalResource;
import spark.Spark;

public class ServerRule extends ExternalResource {
    
    private final int port;

    public ServerRule(int port) {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\chromedriver.exe");
    	
    	this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        Spark.port(port);
        UserDao dao = new UserDaoForTests();
        dao.add(new User("jukka", "akkuj"));
        Main.setDao(dao);
        Main.main(null);
    }

    @Override
    protected void after() {
        Spark.stop();
    }
    
}
