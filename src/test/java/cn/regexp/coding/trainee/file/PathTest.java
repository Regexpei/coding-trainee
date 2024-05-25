package cn.regexp.coding.trainee.file;

import cn.regexp.coding.trainee.utils.MyFileUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PathTest {

    @Test
    public void testFilterMaxRangePaths() {
        List<String> paths = Arrays.asList("/src/main/java/cn/regexp/coding/trainee/entity/Person.java", "/doc",
                "/src/main/resources/application.properties", "/src/main/resources", "/");
        List<String> filteredPaths = MyFileUtil.filterMaxRangePaths(paths);

        for (String filteredPath : filteredPaths) {
            System.out.println(filteredPath);
        }
    }
}