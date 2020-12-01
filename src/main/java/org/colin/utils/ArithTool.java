package org.colin.utils;

import org.apache.commons.collections.CollectionUtils;
import org.colin.exception.ServiceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 高精度计算工具类
 * @ClassName: ArithTool
 * @Author: wujiangbo
 * @Date: 2020/7/13 0013 10:27
 * @Version: 1.1.0
 */
public class ArithTool {

    public static void main(String[] args) {
//        String b1 = "26.3425";
//        String b2 = "7.3456";
//        int scale = 2;
//        System.out.println(add(b1, b2, scale));
//        System.out.println(sub(b1, b2, scale));
//        System.out.println(mul(b1, b2, scale));
//        System.out.println(div(b1, b2, scale));
    }

    private final static int DEFAULT_SCALE = 2;

    /**
     * @Description: 加法计算
     * @MethodName: add
     * @param:  [value_1, value_2, scale:精确范围]
     * @return: double
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/7/13 0013
     */
    public static double add(String value_1, String value_2, int scale){
        //如果精确范围小于0，则抛出异常信息
        if(scale < 0){
            scale = DEFAULT_SCALE;
        }
        BigDecimal b1 = new BigDecimal(value_1);
        BigDecimal b2 = new BigDecimal(value_2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    //多个值相加
    public static BigDecimal addByList(List<BigDecimal> strList, int scale){
        //如果精确范围小于0，则抛出异常信息
        if(scale < 0){
            scale = DEFAULT_SCALE;
        }
        BigDecimal count = new BigDecimal("0");
        if(CollectionUtils.isNotEmpty(strList)){
            for(int i=0; i<strList.size(); i++){
                count = count.add(strList.get(i)).setScale(scale, BigDecimal.ROUND_HALF_UP);
            }
        }
        return count;
    }

    /**
     * @Description: 减法计算
     * @MethodName: sub
     * @param:  [value_1, value_2, scale:精确范围]
     * @return: double
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/7/13 0013
     */
    public static double sub(String value_1, String value_2, int scale){
        //如果精确范围小于0，则抛出异常信息
        if(scale < 0){
            scale = DEFAULT_SCALE;
        }
        BigDecimal b1 = new BigDecimal(value_1);
        BigDecimal b2 = new BigDecimal(value_2);
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @Description: 乘法计算
     * @MethodName: mul
     * @param:  [value_1, value_2, scale:精确范围]
     * @return: double
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/7/13 0013
     */
    public static double mul(String value_1, String value_2, int scale){
        //如果精确范围小于0，则抛出异常信息
        if(scale < 0){
            scale = DEFAULT_SCALE;
        }
        BigDecimal b1 = new BigDecimal(value_1);
        BigDecimal b2 = new BigDecimal(value_2);
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @Description: 除法计算
     * @MethodName: div
     * @param:  [value_1:被除数, value_2:除数, scale:精确范围]
     * @return: double
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/7/13 0013
     */
    public static double div(String value_1, String value_2, int scale){
        //如果精确范围小于0，则抛出异常信息
        if(scale < 0){
            scale = DEFAULT_SCALE;
        }
        BigDecimal b1 = new BigDecimal(value_1);
        BigDecimal b2 = new BigDecimal(value_2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
