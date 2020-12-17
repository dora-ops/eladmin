import com.alibaba.fastjson.JSONObject;
import data.BodyGenerator;
import data.GeneratorEngine;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.JDBCDataUtil;
import model.*;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import me.zhengjie.utils.DBUtil;
import tomcat.yzl.Main;
import me.zhengjie.utils.SqlUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientTest {

    @Test
    public void apply() throws InterruptedException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        String json = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("stable/prize/2.json"));
        JSONObject jsonObject = JSONObject.parseObject(json);
        TestApi t = new TestApi();
        BeanUtils.populate(t, jsonObject);
        String sql = SqlUtil.getInsertSql("test_api", t);
        DBUtil.exe(sql);
    }

    @Test
    public void test() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        String json = FileUtil.getContentFromFile(BodyGenerator.class.getClassLoader().getResourceAsStream("2.json"));
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject body = jsonObject.getJSONObject("body");
        Map<Boolean, List<JSONObject>> map = GeneratorEngine.generate(body);
        List<JSONObject> genDataList = new ArrayList<>();
        for (Boolean key : map.keySet()) {
            List<JSONObject> list = map.get(key);
            for (JSONObject genBody : list) {
                JSONObject newJson = new JSONObject();
                newJson.putAll(jsonObject);
                newJson.put("body", genBody);
                TestApi t = new TestApi();
                BeanUtils.populate(t, newJson);
                t.setSuccess(key.toString());
                String sql = SqlUtil.getInsertSql("test_api", t);
                DBUtil.exe(sql);
            }
        }
    }


    @Test
    public void generateGraph() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        TestGraph graph = new TestGraph();
        graph.setDes("测试");
        String sql = SqlUtil.getInsertSql("test_graph", graph);
        int g_id = DBUtil.exe(sql);
        int n_id = 0;
        for (int i = 2; i <= 4; i++) {
            String json = FileUtil.getContentFromFile(BodyGenerator.class.getClassLoader().getResourceAsStream("stable/prize/" + i + ".json"));
            JSONObject jsonObject = JSONObject.parseObject(json);

            TestNode testNode = new TestNode();
            testNode.setGId(g_id);
            testNode.setUri(jsonObject.getString("url"));
            testNode.setNext(n_id);
            BeanUtils.populate(testNode, jsonObject);

            sql = SqlUtil.getInsertSql("test_node", testNode);
            n_id = DBUtil.exe(sql);

            JSONObject body = jsonObject.getJSONObject("body");
            Map<Boolean, List<JSONObject>> map = GeneratorEngine.generate(body);
            for (Boolean key : map.keySet()) {
                List<JSONObject> list = map.get(key);
                for (int j = 0; j < list.size(); j++) {
                    JSONObject genBody = list.get(j);
                    JSONObject newJson = new JSONObject();
                    newJson.put("body", genBody);
                    TestApi t = new TestApi();
                    BeanUtils.populate(t, newJson);
                    t.setSuccess(key.toString());
                    t.setNId(n_id);
                    t.setSeq(j);
                    sql = SqlUtil.getInsertSql("test_api", t);
                    DBUtil.exe(sql);
                }
            }
        }
    }

    @Test
    public void getGraph() throws IOException, SQLException, InvocationTargetException, IllegalAccessException {
        String sql = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("graph.sql"));
        ResultSet query = DBUtil.query(sql);
        List<Map<String, Object>> maps = JDBCDataUtil.convertList(query);
        for (Map<String, Object> map:maps) {
            Graph g=new Graph();
            BeanUtils.populate(g,map);
            sql = FileUtil.getContentFromFile(Main.class.getClassLoader().getResourceAsStream("1.sql"));
            query = DBUtil.query(sql);
            maps = JDBCDataUtil.convertList(query);
            for (Map<String, Object> node:maps) {
                Node n=new Node();
                BeanUtils.populate(n,map);
                Integer next = n.getNext();

            }
        }
    }


    @Test
    public void thenApply() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = future.thenApply(s -> s + "w");
        CompletableFuture<String> future1 = future.thenApply(s -> s + " world");
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future2, future1);
        CompletableFuture<String> future3 = voidCompletableFuture.thenApply((a) -> {
            System.out.println(a);
            return "finish";
        });
        System.out.println(future3.join());
    }

    @Test
    public void whenComplete() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String a = "1";
            return a;
        });
        CompletableFuture<String> future1 = future.whenComplete((s, t) -> {
            s = "3";
            System.out.println(s);
            System.out.println(t);
        });
        CompletableFuture<String> future2 = future.whenComplete((s, t) -> {
            s = "2";
            System.out.println(s);
            System.out.println(t);
        }).exceptionally(e -> {
            System.out.println(e);
            return "hello world";
        });
        String finish = "finish";
        future2.join();
        future1.join();
        System.out.println(finish);
    }


    @Test
    public void readAndWrite() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock();
        StampedLock stampedLock = new StampedLock();

    }


    class RWDictionary {
        private final Map<String, String> m = new TreeMap<String, String>();
        private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        private final Lock r = rwl.readLock();
        private final Lock w = rwl.writeLock();

        public String get(String key) {
            r.lock();
            try {
                return m.get(key);
            } finally {
                r.unlock();
            }
        }

        public String put(String key, String value) {
            w.lock();
            try {
                return m.put(key, value);
            } finally {
                w.unlock();
            }
        }

        public void clear() {
            w.lock();
            try {
                m.clear();
            } finally {
                w.unlock();
            }
        }
    }

    static long now = System.nanoTime();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> strings = Arrays.asList("1", "2", "3");
        Stream<String> stringStream = strings.stream();
        Collector<Object, ?, List<Object>> objectListCollector = Collectors.toList();
        Collectors.counting();
//        Collectors.summarizingInt()
        Collector<? super String, ? extends Object, List<String>> collect = Collector.of(ArrayList::new, (a, b) -> {
            a.add(b);
            System.out.println("a = [" + a + "]");
        }, (a, b) -> {
            return a;
        });
        Collector<String, ?, Map<Integer, List<String>>> stringMapCollector = Collectors.groupingBy(String::length);
        Optional<String> max = stringStream.collect(Collectors.maxBy((s1, s2) -> {
            return s1.length() > s2.length() ? 1 : -1;
        }));


        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(10);
        executScheduleWithFixedDelay(executorService);

//        executScheduleAtFixedRate(executorService);

//        executSchedule(executorService);

        System.out.println("over");

        executorService.shutdown();
        stringStream = strings.parallelStream();

        stringStream = strings.stream();

        Map<Boolean, List<String>> collect2 = stringStream.collect(Collectors.partitioningBy((str) -> {
            return Integer.parseInt(str) > 1;
        }));
        stringStream = strings.stream();
        List<String> collect1 = stringStream.collect(collect);
        System.out.println("args = [" + collect1 + "]");
    }

    static long triggerTime(long delay) {
        long now = System.nanoTime();
        System.out.println(now / (1000 * 1000));
        return now +
                ((delay < (Long.MAX_VALUE >> 1)) ? delay : 0L);
    }


    // 间隔3秒执行一次
    public static void executScheduleWithFixedDelay(ScheduledExecutorService executorService) throws InterruptedException, ExecutionException {
        long now = System.currentTimeMillis();
        ScheduledFuture<?> result = executorService.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println((System.currentTimeMillis() - now) / (1000));
//                        long l = triggerTime(-3000000000L);
//                        long time = l - System.nanoTime();
//                        System.out.println(time/(1000*1000));
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000, 30000, TimeUnit.MILLISECONDS
        );

        result.get();
    }

    // 线程在第4秒开始执行
    public static void executScheduleAtFixedRate(final ScheduledThreadPoolExecutor executorService) throws InterruptedException, ExecutionException {
        long now = System.currentTimeMillis();
        ScheduledFuture<?> result = executorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println((System.currentTimeMillis() - now) / (1000));
//                        BlockingQueue<Runnable> queue = executorService.getQueue();
//                        System.out.println("scheduleAtFixedRate-" + (triggerTime(-3000000000L)-now));
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000, 8000, TimeUnit.MILLISECONDS
        );

        result.get();
    }

    // 线程延迟4秒执行,仅执行一次
    public static void executSchedule(ScheduledExecutorService executorService) throws InterruptedException, ExecutionException {

        ScheduledFuture<?> result = executorService.schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("schedule-" + System.currentTimeMillis());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 4000, TimeUnit.MILLISECONDS
        );

        result.get();
    }


}
