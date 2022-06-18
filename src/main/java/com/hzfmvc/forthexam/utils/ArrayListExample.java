package com.hzfmvc.forthexam.utils;

import com.hzfmvc.forthexam.entity.Submission;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import java.util.stream.Collectors;

public class ArrayListExample
{
    public static void main(String[] args)

    {

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList);
        List<Integer> listWithoutDuplicates = numbersList.stream().distinct().collect(Collectors.toList());

        System.out.println(listWithoutDuplicates);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list.get(list.size()-1));
    }
    public static List removeDuplicate(List<Submission> list) {
        List<String> result = new ArrayList<>(list.size());

        List<String> temp = new ArrayList<>(list.size());
        for (int j=0; j<list.size(); j++) {
            String stu_name = list.get(j).getStudentName();
            temp.add(stu_name);
        }

        result.add(temp.get(0));
        for(int i=1;i<temp.size();i++){
            if (!result.contains(temp.get(i))){
                result.add(temp.get(i));
            }
        }
        return result;
    }


}

