package com.atguigu.Stream;

import com.suixingpay.profit.bean.UserRegion;
import com.suixingpay.profit.bean.UserRegionTree;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IteraterToStream {
    /**
     * 查询子机构列表
     *
     * @param
     * @param
     * @param
     * @return
     * @auth huang_xg
     */
    public List<UserRegionTree> findChildren() {
        //获取区域表中的所有数据
        List<UserRegion> allUserRegion = new ArrayList<>();//可以是通过数据库查询出来的数据
        //获取省市区集合数据
        List<UserRegion> allFilter = allUserRegion.stream().filter(s -> s.getRegionLevel() <= 3)
                .collect(Collectors.toList());
        List<UserRegionTree> userRegionTrees = new ArrayList<>();
        //生成机构树
        allFilter.stream().forEach(
                s->{
                    UserRegionTree userRegionTree = new UserRegionTree();
                    BeanUtils.copyProperties(s,userRegionTree);
                    userRegionTrees.add(userRegionTree);
                }
        );
        //对应的省信息查出来
        List<UserRegionTree> rootUserRegions = userRegionTrees.stream().filter(perm -> "0".equals(perm.getParentRegionCode())).collect(Collectors.toList());

        for (UserRegionTree parentUserRegion : rootUserRegions) {
            findChildPerms(parentUserRegion, userRegionTrees);
        }
        return rootUserRegions;

    }

    /**
     * 用sream流的方式转成机构树
     * @param parentUserRegion
     * @param allPerm
     * @auth huang_xg
     */
    private void findChildPerms(UserRegionTree parentUserRegion, List<UserRegionTree> allPerm) {
        List<UserRegionTree> childPerms = allPerm.stream().filter(child -> parentUserRegion.getRegionCode().equals(child.getParentRegionCode())).collect(Collectors.toList());

        parentUserRegion.setChildren(childPerms);
        if (childPerms != null && childPerms.size() > 0) {
            for (UserRegionTree perm : childPerms) {
                findChildPerms(perm, allPerm);
            }
        }
    }


}
