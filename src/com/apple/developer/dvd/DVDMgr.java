package com.apple.developer.dvd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("all")
public class DVDMgr {
    private Scanner input = new Scanner(System.in);

    private ArrayList dvd = new ArrayList();           //属性:类型为ArrayList

    protected void start() throws ParseException {                           //方法：开始，先进行系统初始化，然后菜单。
        this.initial();
        while (true) {
            this.menu();
        }
    }

    private void menu() throws ParseException {
        System.out.println("\n欢迎来到迷你DVD管理器");
        System.out.println("__________________");
        System.out.println("0、DVD排行榜");
        System.out.println("1、新增DVD");
        System.out.println("2、查看DVD");
        System.out.println("3、删除DVD");
        System.out.println("4、借出DVD");
        System.out.println("5、归还DVD");
        System.out.println("6、退出");
        System.out.println("__________________");
        System.out.print("请输入您的选择：");
        try {
            int choice = input.nextInt();

            switch (choice) {
                case 0:
                    this.sortDVD();
                    break;
                case 1:
                    this.addDVD();
                    break;
                case 2:
                    this.printDVD();
                    break;
                case 3:
                    this.delDVD();
                    break;
                case 4:
                    this.lendDVD();
                    break;
                case 5:
                    this.returnDVD();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("输入不正确，请重新输入！");
                    this.menu();
                    break;
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return;
    }

    private void delDVD() {
        this.printDVD();
        System.out.print("请输入您要删除的DVD序号：");
        int n = (input.nextInt()) - 1;
        DVDSet del = (DVDSet) dvd.get(n);
        if (del.getState().equals("已借出")) {
            System.out.println("这部DVD已借出，无法删除。请在归还后重试！");
        } else {
            dvd.remove(n);
            System.out.println("删除成功......");
            this.printDVD();
            System.out.print("是否继续删除：（y/n）");
            String s = input.next();
            if (s.equals("y")) {
                this.delDVD();
            } else {
                return;
            }
        }
    }

    private void addDVD() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入您要添加的DVD名字：");
        String name = input.nextLine();
        DVDSet ds = this.findDVDByName(name);
        if (ds != null) {
            System.out.println("对不起，" + name + "已经存在了,不能添加！");
        } else {
            DVDSet add = new DVDSet(name);
            dvd.add(add);
            System.out.println("添加成功.......");
            this.printDVD();
            System.out.print("是否继续添加：（y/n）");
            String s = input.next();
            if (s.equals("y")) {
                this.addDVD();
            } else {
                return;
            }
        }
    }

    private void lendDVD() {
        this.printDVD();
        Scanner input = new Scanner(System.in);
        System.out.print("请输入您要借出的DVD序号：");
        int n = input.nextInt() - 1;
        DVDSet ds = (DVDSet) dvd.get(n);
        if (ds.getState().equals("已借出")) {
            System.out.println("这部DVD已经借出去了，请看看别的DVD吧...");
            this.lendDVD();
        } else {
            ds.countsAdd();
            ds.setState("已借出");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(date);
            ds.setDate(str);
            System.out.println("借出DVDV名字：" + ds.getName() + "\t\t\t借出日期：" + str);
            System.out.println("借出成功...");
            this.printDVD();
            System.out.print("是否继续借DVD：（y/n）");
            String s = input.next();
            if (s.equals("y")) {
                this.lendDVD();
            } else {
                return;
            }
        }
    }

    private void returnDVD() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入您要归还的DVD名字：");
        String name = in.next();
        DVDSet ds = this.findDVDByName(name);
        if (ds == null) {
            System.out.println("对不起，您要归还的DVD不存在");
        } else {
            if (ds.getState().equals("可出借")) {
                System.out.println("对不起，这部DVD未被借出，无需归还！");
            } else {
                Date dateRetuen = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateLend = sdf.parse(ds.getDate());
                long day = (dateRetuen.getTime() - dateLend.getTime()) / (1000 * 24 * 60 * 60);
                double money = day * 2;
                System.out.println("请付款：" + money);
                System.out.println("归还成功...");
                ds.setDate(" ");
                ds.setState("可出借");
                this.printDVD();
                System.out.print("是否继续归还：（y/n）");
                String s = input.next();
                if (s.equals("y")) {
                    this.returnDVD();
                } else {
                    return;
                }
            }
        }
    }

    private DVDSet findDVDByName(String name) {
        DVDSet dvdFind = null;
        Iterator iter = dvd.iterator();
        while (iter.hasNext()) {
            DVDSet ds = (DVDSet) iter.next();
            if (ds.getName().equals(name)) {
                dvdFind = ds;
                break;
            }
        }
        return dvdFind;
    }

    private void initial() {                //系统初始化，添加三部片子
        //dvd.add(new DVDSet("罗马假日"));
        DVDSet ds = new DVDSet("罗马假日");
        ds.countsAdd();
        ds.setState("已借出");
        ds.setDate("2010-07-01");
        dvd.add(ds);
        dvd.add(new DVDSet("风声鹤唳"));
        dvd.add(new DVDSet("浪漫满屋"));
    }

    private void sortDVD() {
        //DVDSet[] dsArray= (DVDSet[])dvd.toArray();
        Object[] dsArray = dvd.toArray();
        DVDSet[] dsArray1 = new DVDSet[dsArray.length];
        for (int i = 0; i < dsArray.length; i++) {
            dsArray1[i] = (DVDSet) dsArray[i];
        }
        Arrays.sort(dsArray1, new DVDComparator());
        int count = 0;
        count++;
        System.out.println("序号\t\t\t名字\t\t\t出借次数\t\t\t状态\t\t\t\t出借日期");
        for (DVDSet da : dsArray1) {
            //System.out.println(count+"\t\t\t"+da.getName()+"\t\t\t"+da.getCounts()+"\t\t\t"+da.getState()+"\t\t\t\t"+da.getDate());
            System.out.println(count + "\t\t\t" + da.getInfo());
            count++;
        }
        return;
    }

    private void printDVD() {               //打印所有的DVD信息
        //for循环遍历
//        for(int i=0;i<dvd.size();i++){
//            DVDSet ds= (DVDSet) dvd.get(i);
//            System.out.println(ds.getInfo());
//        }

        //foreach遍历
//        for(Object obj:dvd){
//            DVDSet ds =(DVDSet)obj;
//            System.out.println(ds.getInfo());
//        }

        //迭代器Iterator遍历
        Iterator iter = dvd.iterator();
        int count = 0;
        count++;
        System.out.println("序号\t\t\t名字\t\t\t出借次数\t\t\t状态\t\t\t\t出借日期");
        while (iter.hasNext()) {
            DVDSet ds = (DVDSet) iter.next();
            System.out.println(count + "\t\t\t" + ds.getInfo());
            count++;
        }
    }

}
