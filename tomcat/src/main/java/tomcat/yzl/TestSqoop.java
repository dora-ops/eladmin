package tomcat.yzl;

import me.zhengjie.domain.vo.ColumnInfo;
import me.zhengjie.utils.GenUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author Administrator
 * @Date 2019/11/15 9:59
 **/
public class TestSqoop {


    public static void main(String[] arg) throws SQLException, IOException {
        List<ColumnInfo> columnInfos = DBUtil.convertList("test_api", "test");
        Map<String, Object> test_api = GenUtil.convertTemplateMap(columnInfos, "test_api");
        GenUtil.genFile("Dto",System.getProperty("user.dir")+"/TestApiDTO.java",test_api);

    }


}
