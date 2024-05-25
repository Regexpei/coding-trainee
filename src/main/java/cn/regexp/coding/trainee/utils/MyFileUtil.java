package cn.regexp.coding.trainee.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/4/25 21:20
 * @description 文件处理相关工具类
 */
public class MyFileUtil {

    /**
     * 获取某个目录下的所有文件（递归方式）
     *
     * @param dir 目录
     * @return 文件列表
     */
    public static List<File> loopFilesByRecursion(File dir) {
        if (!dir.isDirectory()) {
            return Collections.singletonList(dir);
        }

        List<File> files = new ArrayList<>();

        File[] fileArr = dir.listFiles();
        if (fileArr != null) {
            for (File file : fileArr) {
                if (file.isDirectory()) {
                    files.addAll(loopFilesByRecursion(file));
                } else {
                    files.add(file);
                }
            }
        }

        return files;
    }

    /**
     * 获取某个目录下的所有文件（非递归方式）
     *
     * @param dir 目录
     * @return 文件列表
     */
    public static List<File> loopFilesByWhile(File dir) {
        if (!dir.isDirectory()) {
            return Collections.singletonList(dir);
        }

        List<File> dirs = new ArrayList<>(Collections.singletonList(dir));
        List<File> files = new ArrayList<>();

        while (!dirs.isEmpty()) {
            File file = dirs.remove(0);

            if (file.isDirectory()) {
                File[] fileArr = file.listFiles();
                if (fileArr != null) {
                    dirs.addAll(0, Arrays.asList(fileArr));
                }
            } else {
                files.add(file);
            }
        }

        return files;
    }

    /**
     * 过滤出路径列表中的最大范围路径
     *
     * @param paths 路径列表
     * @return 过滤后的最大范围路径列表
     */
    public static List<String> filterMaxRangePaths(List<String> paths) {
        List<String> result = new ArrayList<>();

        // 遍历路径列表
        for (String path : paths) {
            // 标识路径是否为最大范围
            boolean isMaxRange = true;

            // 检查当前路径是否是其他路径的子路径
            for (String otherPath : paths) {
                /*
                   若当前路径等于其它路径，跳过
                   若当前路径不等于其它路径，
                        并且当前路径不包含其它路径，则跳过
                        并且当前路径包含其它路径，则说明当前路径不是最大范围路径
                 */
                if (!path.equals(otherPath) && path.startsWith(otherPath)) {
                    isMaxRange = false;
                    break;
                }
            }

            // 到了这里，若 isMaxRange 没有被重新赋值，则说明当前路径是最大范围路径，将其添加到结果列表中
            if (isMaxRange) {
                result.add(path);
            }
        }

        return result;
    }
}
