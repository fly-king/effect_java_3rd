package com.alibaba.dt.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;

/**
 * @author 天大 tianda.lt@alibaba-inc.com
 * @version HashCodeDemo.java, v0.1
 * @date 2018/3/30 13:39
 */
public class HashCodeDemo  {

    public static Integer hashCode(String word, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < word.length(); i++) {
            hash = multiplier * hash + word.charAt(i);
        }

        return hash;
    }

    /**
     * 计算 hash code 冲突率，顺便分析一下 hash code 最大值和最小值，并输出
     * @param multiplier 算子ß
     * @param hashCodeList hashcode 列表
     */
    public static void calculateConflictRate(Integer multiplier, List<Integer> hashCodeList) {
        Comparator<Integer> cp = (x, y) -> x > y ? 1 : (x < y ? -1 : 0);
        int maxHash = hashCodeList.stream().max(cp).get();
        int minHash = hashCodeList.stream().min(cp).get();

        // 计算冲突数及冲突率
        int uniqueHashNum = (int) hashCodeList.stream().distinct().count();
        int conflictNum = hashCodeList.size() - uniqueHashNum;
        double conflictRate = (conflictNum * 1.0) / hashCodeList.size();

        System.out.println(String.format("multiplier=%4d,wordCount=%10d ,minHash=%11d, maxHash=%10d, conflictNum=%6d, conflictRate=%.4f%%",
            multiplier,hashCodeList.size(), minHash, maxHash, conflictNum, conflictRate * 100));
    }


    public static void analysis(String text,Integer multiplier){
        List<String> wordList= Splitter.on('\n').splitToList(text);
        List<Integer> hashCodeList=wordList.parallelStream().map(word-> hashCode(word,multiplier)).collect(Collectors.toList());
        calculateConflictRate(multiplier,hashCodeList);
    }

    public static void main(String[] args) throws Exception {
        String content = IOUtils.toString(new FileInputStream(new File(HashCodeDemo.class.getClassLoader().getResource("linux.words").getPath())));

        List<Integer> multiplierList= Lists.newArrayList(2,3,16,17,31,32,43,101);
        multiplierList.forEach(multiplier-> analysis(content,multiplier));

    }
}
