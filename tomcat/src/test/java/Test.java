import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Test {

    @org.junit.Test
    public void whenComplete() throws InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        Integer join = future.join();


        System.out.println(join);
        Thread.currentThread().join();
    }

    @org.junit.Test
    public void whenComplete1() throws InterruptedException, IOException {

        StringWriter writer = new StringWriter();
        writeRow(Arrays.asList("中文,\"原因\""), writer);
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("1.csv"));
//        byte[] bytes = writer.getBuffer().toString().getBytes("UTF-8");
//        byte[] gb2312s = new String(writer.getBuffer().toString().getBytes("UTF-8"), "GBK").getBytes();
//
//
//        fileOutputStream.write(bytes);
//        fileOutputStream.write(gb2312s);
        FileInputStream fileInputStream = new FileInputStream("1.csv");

        byte[] content=new byte[fileInputStream.available()];
        fileInputStream.read(content);
         writer = new StringWriter();
         CharArrayWriter charArrayWriter=new CharArrayWriter();


        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(writer.toString());
        byte[] buffer = new byte[stringBufferInputStream.available()];
        stringBufferInputStream.read(buffer);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("2.csv"));
        fileOutputStream.write(buffer);
    }


    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, StringWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.append("\n");
    }


    public void thenApply() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        future.thenApply(i -> -i);
    }

    public void thenAccept() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        future.thenAccept(System.out::println);
    }

    public void thenRun() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        future.thenRun(() -> System.out.println("Done"));
    }

    public void thenAcceptBoth() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> other = CompletableFuture.supplyAsync(() -> 200);
        future.thenAcceptBoth(other, (x, y) -> System.out.println(x + y));
    }

    public void acceptEither() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> other = CompletableFuture.supplyAsync(() -> 200);
        future.acceptEither(other, System.out::println);

    }

    @org.junit.Test
    public void allOf() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> 200);
        CompletableFuture<Integer> third = CompletableFuture.supplyAsync(() -> 300);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future, second, third);
        voidCompletableFuture.join();

    }

    public void anyOf() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> 200);
        CompletableFuture<Integer> third = CompletableFuture.supplyAsync(() -> 300);
        CompletableFuture.anyOf(future, second, third);
    }
}
