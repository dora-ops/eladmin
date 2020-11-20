package tomcat.yzl;

import java.io.*;

public class FileUtil {

    public static File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }

    public static Object getObjectFromFile(File file) throws IOException, ClassNotFoundException {
        if (file.length()==0){
            return null;
        }else {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
           return objectInputStream.readObject();
        }
    }

    public static String getContentFromFile(File file) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
        StringBuilder stringBuilder=new StringBuilder();
        String s;
        while ((s=reader.readLine())!=null){
            stringBuilder.append(s);
        }
       return stringBuilder.toString();
    }

    public static String getContentFromFile(InputStream inputStream) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        StringBuilder stringBuilder=new StringBuilder();
        String s;
        while ((s=reader.readLine())!=null){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
