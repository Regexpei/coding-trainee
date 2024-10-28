package cn.regexp.coding.trainee.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/10/28 20:16
 * @description
 * @since 1.0.0
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(100)
public class ExportData {
    @ExcelProperty("描述")
    private String desc;
    @ExcelIgnore
    private List<InputStream> images;

    @ExcelProperty("图片")
    @ColumnWidth(30)
    private WriteCellData<Void> writeCellDataFile;
}
