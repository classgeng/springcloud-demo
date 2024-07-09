package com.demo.common.algorithm;

import com.demo.common.domain.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataCompress {

    public static List<Status> compress(List<Status> arr){
        Status tem = null;
        List<Status> arr1 = new ArrayList<>();
        for (int i=0;i< arr.size(); i++){
            Status status = arr.get(i);
            if(null == tem) {
                arr1.add(status);
            }else {
                if(!status.getStatus().equals(tem.getStatus())) {
                    arr1.add(status);
                }
            }
            tem = status;
        }
        return arr1;
    }

    public static void compressTwo(List<Status> arr){
        Status tem = null;
        Iterator<Status> iterator = arr.iterator();
        while (iterator.hasNext()){
            Status status = iterator.next();
            if(null != tem && status.getStatus().equals(tem.getStatus())) {
                iterator.remove();
            }
            tem = status;
        }
    }

    public static void main(String[] args) {
        List<Status> arr = new ArrayList<>();
        arr.add(new Status(1,"R"));
        arr.add(new Status(2,"R"));
        arr.add(new Status(3,"R"));
        arr.add(new Status(4,"G"));
        arr.add(new Status(5,"G"));
        arr.add(new Status(6,"Y"));
        arr.add(new Status(7,"R"));
        arr.add(new Status(8,"R"));

        // 数据压缩
        compressTwo(arr);

        for (Status status:arr) {
            System.out.println(status.getIndex()+":"+status.getStatus());
        }
    }

}
