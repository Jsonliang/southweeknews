package com.example.administrator.southweek.ui.utils;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class RegexUtils {
    /**
     * 验证Email
     *
     * @param email
     *            email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false ^ ：匹配输入的开始位置。 \：将下一个字符标记为特殊字符或字面值。
     *         ：匹配前一个字符零次或几次。 + ：匹配前一个字符一次或多次。 (pattern) 与模式匹配并记住匹配。 x|y：匹配 x 或
     *         y。 [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。 \w ：与任何单词字符匹配，包括下划线。
     *
     *         {n,m} 最少匹配 n 次且最多匹配 m 次 $ ：匹配输入的结尾。
     */
    public static boolean checkEmail(String email) {
        String regex = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile
     *            移动、联通、电信运营商的号码段
     *            <p>
     *            移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *            、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
     *            </p>
     *            <p>
     *            联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
     *            </p>
     *            <p>
     *            电信的号段：133、153、180（未启用）、189
     *            </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[3458]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
}
