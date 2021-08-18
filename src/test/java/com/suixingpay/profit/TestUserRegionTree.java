package com.suixingpay.profit;

import com.alibaba.fastjson.JSONObject;
import com.suixingpay.profit.bean.UserRegionTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUserRegionTree {
    @Test
    public void test(){
        List children = new ArrayList();
//        UserRegionTree userRegionTree = new UserRegionTree();
//        userRegionTree.setRegionName("北京");
//        userRegionTree.setRegionCode("12");

//        UserRegionTree userRegionTree1 = new UserRegionTree();
//        userRegionTree1.setRegionName("北京市");
//        userRegionTree1.setRegionCode("1201");
        UserRegionTree userRegionTree2 = new UserRegionTree();
        userRegionTree2.setRegionName("海淀区");
        userRegionTree2.setRegionCode("120101");
        UserRegionTree userRegionTree3 = new UserRegionTree();

        userRegionTree3.setRegionName("石景山区");
        userRegionTree3.setRegionCode("120102");
        children.add(userRegionTree2);
        children.add(userRegionTree3);
        System.out.println(JSONObject.toJSONString(children));

    }
}
