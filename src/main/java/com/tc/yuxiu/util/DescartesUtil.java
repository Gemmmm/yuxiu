package com.tc.yuxiu.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author DELL
 * @date 2020/9/27 18:24
 */
public class DescartesUtil {
    public static void main(String[] args) {
        String[] list = {"10,11,12", "1,2","5,6,7"};
        List<String> strings1 = Arrays.asList(list);
        List<String> strings = descartesNew1(strings1);
        System.out.println(strings);
    }

    public static List<String> descartesNew(List<String> list) {
        List<List> strs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> strings = Arrays.asList(list.get(i).split(","));
            if (strings.size() > 1 || (strings.size() == 1 && !"".equals(strings.get(0)))) {
                strs.add(strings);
            }
        }
        System.out.println("strs:" + strs);
        int total = 1;
        for (int i = 0; i < strs.size(); i++) {
            total *= strs.get(i).size();
        }
        System.out.println("total:" + total);
        String[] mysesult = new String[total];
        int now = 1;
        //每个元素每次循环打印个数
        int itemLoopNum = 1;
        //每个元素循环的总次数
        int loopPerItem = 1;
        for (int i = 0; i < strs.size(); i++) {
            List temp = strs.get(i);
            now = now * temp.size();
            //目标数组的索引值
            int index = 0;
            int currentSize = temp.size();
            itemLoopNum = total / now;
            loopPerItem = total / (itemLoopNum * currentSize);
            int myindex = 0;
            for (int j = 0; j < temp.size(); j++) {

                //每个元素循环的总次数
                for (int k = 0; k < loopPerItem; k++) {
                    if (myindex == temp.size()) {
                        myindex = 0;
                    }
                    //每个元素每次循环打印个数
                    for (int m = 0; m < itemLoopNum; m++) {
                        mysesult[index] = (mysesult[index] == null ? "" : mysesult[index] + "_") + ((String) temp.get(myindex));
                        index++;
                    }
                    myindex++;

                }
            }
        }
        return Arrays.asList(mysesult);
    }

    public static List<String> descartesNew1(List<String> list) {
        List<List> strs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> strings = Arrays.asList(list.get(i).split(","));
            if (strings.size() > 1 || (strings.size() == 1 && !"".equals(strings.get(0)))) {
                strs.add(strings);
            }
        }
        System.out.println("strs:" + strs);
        int total = 1;
        for (int i = 0; i < strs.size(); i++) {
            total *= strs.get(i).size();
        }
        System.out.println("total:" + total);
        String[] mysesult = new String[total];

        int now = 1;
        //每个元素每次循环打印个数
        int itemLoopNum = 1;
        //每个元素循环的总次数
        int loopPerItem = 1;
        for (int i = 0; i < strs.size(); i++) {
            List temp = strs.get(i);
            now = now * temp.size();
            //目标数组的索引值
            int index = 0;
            int currentSize = temp.size();
            itemLoopNum = total / now;
            loopPerItem = total / (itemLoopNum * currentSize);
            int myindex = 0;
            for (int j = 0; j < temp.size(); j++) {

                //每个元素循环的总次数
                for (int k = 0; k < loopPerItem; k++) {
                    if (myindex == temp.size()) {
                        myindex = 0;
                    }
                    //每个元素每次循环打印个数
                    for (int m = 0; m < itemLoopNum; m++) {
                        mysesult[index] = (mysesult[index] == null ? "" : mysesult[index] + "_") + ((String) temp.get(myindex));
                        index++;
                    }
                    myindex++;

                }
            }
        }
        List<String> result = new ArrayList<>();
        for (String mysult : mysesult) {
            String[] mysesultStrs =null;
            if(mysult!=null){
                mysesultStrs = mysult.split("_");
            }
            List<Integer> integers = new ArrayList<>();
            if(mysesultStrs!=null){
                for (String mysesultStr : mysesultStrs) {
                    integers.add(Integer.valueOf(mysesultStr));
                }
            }
            Collections.sort(integers);
            System.out.println("integers::" + integers);
            StringBuffer sb = new StringBuffer();
            for (int k = 0; k < integers.size(); k++) {
                if (k < integers.size() - 1) {
                    sb = sb.append(integers.get(k)).append("_");
                } else {
                    sb = sb.append(integers.get(k));
                }

            }
            result.add(sb.toString());
        }
        return result;
    }

}
