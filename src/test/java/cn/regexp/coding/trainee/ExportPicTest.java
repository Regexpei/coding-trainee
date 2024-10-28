package cn.regexp.coding.trainee;

import cn.regexp.coding.trainee.entity.ExportData;
import cn.regexp.coding.trainee.entity.ImageDemoData;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.ImageData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author Regexpei
 * @date 2024/10/28 20:08
 * @description
 * @since 1.0.0
 */
public class ExportPicTest {


    @Test
    public void columnsWrite() throws IOException {
        String fileName = "E:\\Desktop\\test\\" + System.currentTimeMillis() + ".xlsx";

        List<String> imagePaths = Arrays.asList("E:\\Desktop\\001.jpg", "E:\\Desktop\\002.jpg");
        ExportData exportData = new ExportData();
        exportData.setDesc("图片");

        List<ImageData> imageDataList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ImageData imageData = new ImageData();
            imageData.setImage(FileUtils.readFileToByteArray(new File(imagePaths.get(i))));
            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_JPEG);
            imageData.setLeft(2);
            imageData.setRight(2);

            if (i != 1) {
                imageData.setRelativeFirstRowIndex(0);
                imageData.setRelativeFirstColumnIndex(0);
                imageData.setRelativeLastRowIndex(0);
                // 前面3个可以不写  下面这个需要写 也就是 结尾 需要相对当前单元格 往右移动一格
                // 也就是说 这个图片会覆盖当前单元格和 后面的那一格
                imageData.setRelativeLastColumnIndex(1);
            }

            imageDataList.add(imageData);

            Map<String, Integer> map = new HashMap<>();

            // map.put("图片", )
            // strategy.setZdyColumnWidth(map);
        }

        WriteCellData<Void> writeCellData = new WriteCellData<>();
        writeCellData.setImageDataList(imageDataList);
        exportData.setWriteCellDataFile(writeCellData);

        List<ExportData> list = ListUtils.newArrayList(exportData);

        EasyExcel.write(fileName, ExportData.class).sheet().doWrite(list);
    }

    @Test
    public void columnsWrite2() throws IOException {
        String fileName = "E:\\Desktop\\test\\" + System.currentTimeMillis() + ".xlsx";

        List<String> imagePaths = Arrays.asList("E:\\Desktop\\001.jpg", "E:\\Desktop\\002.jpg");
        List<List<Object>> data = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            List<Object> line = new ArrayList<>();
            line.add("图片" + i);

            for (int j = 0; j < 2; j++) {
                line.add(FileUtils.readFileToByteArray(new File(imagePaths.get(i))));
            }
            data.add(line);
        }

        int[] columnWidths = {25, 50, 50};

        EasyExcel.write(fileName, ExportData.class)
                .registerWriteHandler(new ColumnWidthStyleStrategy(columnWidths))
                .sheet().doWrite(data);
    }

    // 自定义列宽设置策略
    public static class ColumnWidthStyleStrategy implements CellWriteHandler {
        private final int[] columnWidths;

        public ColumnWidthStyleStrategy(int[] columnWidths) {
            this.columnWidths = columnWidths;
        }

        @Override
        public void afterCellCreate(CellWriteHandlerContext context) {
            Sheet sheet = context.getWriteSheetHolder().getSheet();
            for (int i = 0; i < columnWidths.length; i++) {
                sheet.setColumnWidth(i, columnWidths[i] * 256); // 设置列宽
            }
        }
    }


    /**
     * 图片导出
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ImageDemoData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void imageWrite() throws Exception {
        String fileName = "E:\\Desktop\\test\\" + System.currentTimeMillis() + ".xlsx";

        // 这里注意下 所有的图片都会放到内存 暂时没有很好的解法，大量图片的情况下建议 2选1:
        // 1. 将图片上传到oss 或者其他存储网站: https://www.aliyun.com/product/oss ，然后直接放链接
        // 2. 使用: https://github.com/coobird/thumbnailator 或者其他工具压缩图片

        String imagePath = "E:\\Desktop\\001.jpg";
        try (InputStream inputStream = FileUtils.openInputStream(new File(imagePath))) {
            List<ImageDemoData> list =  ListUtils.newArrayList();
            ImageDemoData imageDemoData = new ImageDemoData();
            list.add(imageDemoData);
            // 放入五种类型的图片 实际使用只要选一种即可
            imageDemoData.setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
            imageDemoData.setFile(new File(imagePath));
            imageDemoData.setString(imagePath);
            imageDemoData.setInputStream(inputStream);
            imageDemoData.setUrl(new URL(
                    "https://img2.baidu.com/it/u=1767403591,4121365246&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500"));

            // 这里演示
            // 需要额外放入文字
            // 而且需要放入2个图片
            // 第一个图片靠左
            // 第二个靠右 而且要额外的占用他后面的单元格
            WriteCellData<Void> writeCellData = new WriteCellData<>();
            imageDemoData.setWriteCellDataFile(writeCellData);
            // 这里可以设置为 EMPTY 则代表不需要其他数据了
            writeCellData.setType(CellDataTypeEnum.STRING);
            writeCellData.setStringValue("额外的放一些文字");

            // 可以放入多个图片
            List<ImageData> imageDataList = new ArrayList<>();
            ImageData imageData = new ImageData();
            imageDataList.add(imageData);
            writeCellData.setImageDataList(imageDataList);
            // 放入2进制图片
            imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
            // 图片类型
            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
            // 上 右 下 左 需要留空
            // 这个类似于 css 的 margin
            // 这里实测 不能设置太大 超过单元格原始大小后 打开会提示修复。暂时未找到很好的解法。
            imageData.setTop(5);
            imageData.setRight(5);
            imageData.setBottom(5);
            imageData.setLeft(5);

            // 放入第二个图片
            imageData = new ImageData();
            imageDataList.add(imageData);
            writeCellData.setImageDataList(imageDataList);
            imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
            imageData.setTop(5);
            imageData.setRight(5);
            imageData.setBottom(5);
            imageData.setLeft(50);
            // 设置图片的位置 假设 现在目标 是 覆盖 当前单元格 和当前单元格右边的单元格
            // 起点相对于当前单元格为0 当然可以不写
            imageData.setRelativeFirstRowIndex(0);
            imageData.setRelativeFirstColumnIndex(0);
            imageData.setRelativeLastRowIndex(0);
            // 前面3个可以不写  下面这个需要写 也就是 结尾 需要相对当前单元格 往右移动一格
            // 也就是说 这个图片会覆盖当前单元格和 后面的那一格
            imageData.setRelativeLastColumnIndex(1);

            // 写入数据
            EasyExcel.write(fileName, ImageDemoData.class).sheet().doWrite(list);
        }
    }
}
