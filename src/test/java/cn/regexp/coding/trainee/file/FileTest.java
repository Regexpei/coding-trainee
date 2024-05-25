package cn.regexp.coding.trainee.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.regexp.coding.trainee.utils.MyFileUtil;

import java.io.File;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/4/25 21:40
 * @description
 */
public class FileTest {

    public static void main(String[] args) {
        File dir = new File("E:\\IdeaProjects");

        ThreadUtil.execAsync(() -> {
            long start = System.currentTimeMillis();
            List<File> files = MyFileUtil.loopFilesByRecursion(dir);
            long end = System.currentTimeMillis();
            System.out.printf("递归方式：%d，文件总数：%d%n", end - start, files.size());
        });

        ThreadUtil.execAsync(() -> {
            long start = System.currentTimeMillis();
            List<File> files = FileUtil.loopFiles(dir);
            long end = System.currentTimeMillis();
            System.out.printf("工具类方式：%d，文件总数：%d%n", end - start, files.size());
        });

        ThreadUtil.execAsync(() -> {
            long start = System.currentTimeMillis();
            List<File> files = MyFileUtil.loopFilesByWhile(dir);
            long end = System.currentTimeMillis();
            System.out.printf("非递归方式：%d，文件总数：%d%n", end - start, files.size());
        });

        /*
             FileUtil.loopFiles(dir);
             底层使用的是 java.nio.file.Files.walkFileTree()
         */
    }
}
