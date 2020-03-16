package com.gemini.business.supermarket.platform.utils;

import com.gemini.business.supermarket.platform.po.OrgPo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下拉树工具类
 *
 * @author 小明不读书
 * @date 2018-10-13
 */
public class TreeSelectUtil {

    /**
     * @param orgList
     * @return
     */
    public static List<Map<String, Object>> getTreeSelect(List<OrgPo> orgList) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OrgPo org : orgList) {
            if (org.getPid() == 0) {
                List<Map<String, Object>> childrenList = getChildTreeSelect(org.getId(), orgList);
                Map<String, Object> map = new HashMap<>();
                map.put("id", org.getId());
                map.put("name", org.getName());
                map.put("open", true);
                if (childrenList != null && childrenList.size() > 0) {
                    map.put("children", childrenList);
                }
                map.put("checked", false);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 一层一层递归
     *
     * @param id
     * @param orgList
     * @return
     */
    public static List<Map<String, Object>> getChildTreeSelect(Long id, List<OrgPo> orgList) {
        List<Map<String, Object>> childrenList = new ArrayList<>();
        for (OrgPo org : orgList) {
            if (id.equals(org.getPid())) {
                List<Map<String, Object>> childList = getChildTreeSelect(org.getId(), orgList);
                Map<String, Object> childrenMap = new HashMap<>();
                childrenMap.put("id", org.getId());
                childrenMap.put("name", org.getName());
                if (childList != null && childList.size() > 0) {
                    childrenMap.put("children", childList);
                }
                childrenMap.put("open", false);
                childrenMap.put("checked", false);
                childrenList.add(childrenMap);
            }
        }
        return childrenList;
    }
}
