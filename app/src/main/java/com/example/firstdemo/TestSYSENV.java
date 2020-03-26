package com.example.firstdemo;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Map;

public class TestSYSENV {
    public static void main(String[] args)   {
        System.out.println("******************************Environment Vars*****************************");
        Map<String, String> enviorntmentVars  = System.getenv();
        //appVersion:300000150phone num:deviceId:99001155052774phoneBrand:OnePlus-ONEPLUS A5010androidApiVer:28channel:null
        String rule="hitGray(%d,'%s','%s','%s',%d,'%s')";
//        enviorntmentVars.entrySet().forEach(System.out::println);
//        System.out.println(String.format(rule,300000150,"","99001155052774","OnePlus-ONEPLUS A5010",28,null));
        System.out.println(DoubleFormat2(0.018));
        System.out.println(DoubleFormat2(00.018));
        System.out.println(DoubleFormat2(120.018));
        System.out.println(DoubleFormat2(201.00));
        System.out.println(DoubleFormat2(01.229));
        System.out.println(DoubleFormat2(0339.45678));
        System.out.println(DoubleFormat2(0000.018));

         LimitedQueue<Integer> integers=new LimitedQueue<>(3);

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);

        System.out.println(integers.size());
    }

    public static String DoubleFormat2(double a) {
        try {
            NumberFormat nf = new DecimalFormat("#.00");
            return nf.format(a);
        } catch (Exception e) {
            return "";
        }
    }


    static class LimitedQueue<E> extends LinkedList<E> {
        private static final long serialVersionUID = 1L;
        private int limit;

        public LimitedQueue(int limit) {
            this.limit = limit;
        }

        @Override
        public boolean add(E o) {
            super.add(o);
            while (size() > limit) { super.remove(); }
            return true;
        }
    }



}
