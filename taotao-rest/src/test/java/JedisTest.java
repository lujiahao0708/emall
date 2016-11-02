import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-19 14:19
 */
public class JedisTest {
    @Test
    public void testJedisSingle(){
        // 创建一个jedis对象
        Jedis jedis = new Jedis("192.168.2.12",6379);
        // 调用jedis对象方法,方法名称和redis的命令一直
        jedis.set("key1","jedis test");
        String key1 = jedis.get("key1");
        System.out.println(key1);
        // 关闭jedis
        jedis.close();
    }
}
