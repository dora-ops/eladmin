package tomcat.yzl;

import me.zhengjie.domain.vo.ColumnInfo;
import me.zhengjie.utils.DBUtil;
import me.zhengjie.utils.GenUtil;
import me.zhengjie.utils.StringUtils;

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
        String tableName="test_graph";
        List<ColumnInfo> columnInfos = DBUtil.convertList(tableName, "test");
        Map<String, Object> test_api = GenUtil.convertTemplateMap(columnInfos, tableName);
        GenUtil.genFile("Model",System.getProperty("user.dir")+"/"+ StringUtils.toCapitalizeCamelCase(tableName)+".java",test_api);

    }


}
