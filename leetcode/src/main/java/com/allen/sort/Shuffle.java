package com.allen.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffle {

    public static void main(String[] args) {
        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group("第一组", "刘鹏飞、赵攀"));
        groupList.add(new Group("第二组", "王晨、商智栋"));
        groupList.add(new Group("第三组", "韩宏凯、霍雨佳"));
        groupList.add(new Group("第四组", "王向、陈浚"));
        groupList.add(new Group("第五组", "尹少朋、栾力鹏"));
        groupList.add(new Group("第六组", "王磊、张丹"));
        groupList.add(new Group("第七组", "许倩文、宫思然"));

        Collections.shuffle(groupList);
        for (Group group : groupList) {
            System.out.print(group);
        }

    }

    private static class Group {
        String no;
        String name;

        public Group(String no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return no + ", " + name;
        }
    }
}
