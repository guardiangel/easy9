package org.colin.utils;

/**
 * @Description: 编码工具类
 * @ClassName: CodeUtil
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/21 15:41
 * @Version: 1.1.0
 */
public class CodeUtil {

    private static final String DEPT_TPYE = "YXD";

    /*
     * @Description: 右补位，左对齐
     * @MethodName: padRight
     * @param:  [oriStr, len目标字符串长度, alexin补位字符]
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/2/21 15:42
     */
    public static String padRight(String oriStr, int len, String alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = str + oriStr;
        return str;
    }

    /*
     * @Description: 获取机构编码 YXD0001
     * @MethodName: deptCode
     * @param:  [oriStr初始值, len位数, alexin不足以什么补充]
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/2/21 15:41
     */
    public static String deptCode(String oriStr, int len, String alexin) {
        return DEPT_TPYE + padRight(oriStr, len, alexin);
    }

    public static void main(String[] args) {
        System.out.println(deptCode(Tool.getRandomNum(5), 8, "0"));
    }
}
