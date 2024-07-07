package cn.regexp.coding.trainee.util;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import cn.binarywang.tools.generator.ChineseAreaList;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import cn.binarywang.tools.generator.EnglishNameGenerator;
import cn.binarywang.tools.generator.util.ChineseCharUtils;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import cn.regexp.coding.trainee.entity.User;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Regexpei
 * @date 2024/7/6 22:50
 * @description 用户数据生成工具类
 */
public class UserDataGenUtils {

    public static void main(String[] args) {
        User user = generateUser();
        System.out.println(JSON.toJSONString(user, JSONWriter.Feature.PrettyFormat));
    }

    public static User generateUser() {
        User user = new User();
        user.setNickname(EnglishNameGenerator.getInstance().generate());
        user.setName(ChineseNameGenerator.getInstance().generate());
        LocalDate birthDate = genBirthDate();
        user.setBirthDate(birthDate);
        user.setAge(calculateAge(birthDate));
        user.setGender(genGender());
        user.setNativePlace(genProvinceCity());
        user.setAddress(ChineseAddressGenerator.getInstance().generate());
        user.setPersonalSignature(ChineseCharUtils.genFixedLengthChineseChars(200));
        user.setCreateTime(DateTime.now());
        user.setUpdateTime(DateTime.now());
        return user;
    }

    public static final List<String> GENDERS = Arrays.asList("Male", "Female", "Other");

    public static String genGender() {
        return RandomUtil.randomEle(GENDERS);
    }

    public static String genProvinceCity() {
        List<String> provinceCityList = ChineseAreaList.provinceCityList;
        return provinceCityList.get(RandomUtil.randomInt(0, provinceCityList.size()));
    }


    /**
     * 生成随机出生日期（1950年至今）
     *
     * @return 随机生成的出生日期
     */
    public static LocalDate genBirthDate() {
        Random random = new Random();

        // 获取当前年份
        int currentYear = LocalDate.now().getYear();

        // 生成随机年份（1950年到当前年份之间）
        int randomYear = 1950 + random.nextInt(currentYear - 1950 + 1);

        // 生成随机月份（1月到12月）
        int randomMonth = 1 + random.nextInt(12);

        // 生成随机日（根据月份的天数随机）
        int randomDay = 1 + random.nextInt(LocalDate.of(randomYear, randomMonth, 1).lengthOfMonth());

        // 创建并返回随机生成的出生日期
        return LocalDate.of(randomYear, randomMonth, randomDay);
    }

    /**
     * 计算给定出生日期到当前日期的年龄
     *
     * @param birthDate 出生日期
     * @return 年龄
     */
    public static int calculateAge(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        return Period.between(birthDate, now).getYears();
    }

}
