package com.demo.common.util;

import com.demo.common.domain.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JdkStreamUtils {

    /** 过滤 */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /** 映射 */
    public static <T, R> List<R> map(List<T> list, Function<? super T, ? extends R> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /** 过滤和映射 */
    public static <T, R> List<R> filterAndMap(List<T> list,
                                              Predicate<? super T> predicate,
                                              Function<? super T, ? extends R> mapper) {
        return list.stream().filter(predicate).map(mapper).collect(Collectors.toList());
    }

    /** list 转 map */
    public static <T, R> Map<R, T> toMap(List<T> list, Function<? super T, ? extends R> mapper) {
        return list.stream().collect(Collectors.toMap(mapper, Function.identity(), (i1, i2) -> i1));
    }

    /** 分组 */
    public static <R, T> Map<R, List<T>> groupBy(List<T> list, Function<? super T, ? extends R> mapper) {
        return list.stream().collect(Collectors.groupingBy(mapper, Collectors.toList()));
    }

    public static void main(String[] args) {
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("1","张三", 12, null));
        list.add(new UserInfo("2","李四", 20,null));
        list.add(new UserInfo("3","王五", 19,null));
        list.add(new UserInfo("4","赵六", 17,null));
        list.add(new UserInfo("5","钱八", 18,null));

        // 过滤姓名中有五的用户
        List<UserInfo> result = list.stream().filter(item -> item.getName().contains("五")).collect(Collectors.toList());
        // 过滤年龄大于18的用户
        List<UserInfo> List_18 = list.stream().filter(item -> item.getAge().compareTo(18) > 0).collect(Collectors.toList());
        // 根据id映射成map
        Map<String, UserInfo> map = list.stream().collect(Collectors.toMap(UserInfo::getId, Function.identity()));

        /************************* 简化版 *********************************/
        List<UserInfo> result1 = JdkStreamUtils.filter(list, item -> item.getName().contains("五"));
        List<UserInfo> result2 = JdkStreamUtils.filter(list, item -> item.getAge().compareTo(18) > 0);
        Map<String, UserInfo> map1 = JdkStreamUtils.toMap(list, UserInfo::getId);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(map1);
    }

}
