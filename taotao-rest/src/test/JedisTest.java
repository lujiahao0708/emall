import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * Created by lujiahao on 2017/4/6.
 */
public class JedisTest {
    String ipSingle = "192.168.2.30";
    int portSingle = 6371;
    String ipCluster = "192.168.2.30";
    int portCluster = 6370;

    @Test
    public void testJedisSingle(){
        Jedis jedis = new Jedis(ipSingle,portSingle);
        jedis.set("key1","jedis test");
        String str = jedis.get("key1");
        System.out.println("获取到的数据 : " + str);
        jedis.close();
    }

    @Test
    public void testJedisPool(){
        JedisPool jedisPool = new JedisPool(ipSingle,portSingle);
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("key1");
        System.out.println("使用连接池获取 : " + str);
        jedis.close();
        jedisPool.close();
    }

//    @Test
//    public void testJedisCluster(){
//        HashSet<HostAndPort> nodes = new HashSet<>();
//        nodes.add(new HostAndPort(ipCluster,portCluster));
//        JedisCluster cluster = new JedisCluster(nodes);
//        cluster.set("key1","1000");
//        String str = cluster.get("key1");
//        System.out.println("集群获取数据 : " + str);
//        cluster.close();
//    }

    @Test
    public void testSpringJedisSingle(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String str = jedis.get("key1");
        System.out.println(str);
        jedis.close();
        pool.close();

    }
}
