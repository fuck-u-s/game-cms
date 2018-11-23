package com.cms.utils;

import com.cms.model.sys.TreeObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 把一个list集合,里面的bean含有 parentId 转为树形式
 *
 */
public class TreeUtil {

    List<TreeObject> returnList = new ArrayList<TreeObject>();
	
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list 分类表
	 * @param praentId 传入的父节点ID
	 * @return String
	 */
	public List<TreeObject> getChildTreeObjects(List<TreeObject> list,int praentId) {
        //创建一个集合
		List<TreeObject> returnList = new ArrayList<TreeObject>();
        //遍历所有菜单
		for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext();) {
			//菜单对象
            TreeObject t = (TreeObject) iterator.next();
			//根据传入的父节点parentId遍历所有子节点
			if (t.getParent_id()==praentId) {
                //遍历父节点所有子节点
				recursionFn(list, t);
                //保存当前父节点菜单对象
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * 递归列表
	 * @date 2013-12-4 下午7:27:30
	 * @param list 所有菜单集合
	 * @param t 递归第一次的父节点对象
	 */
	private void  recursionFn(List<TreeObject> list, TreeObject t) {
        // 得到当前传入父节点的所有子节点
		List<TreeObject> childList = getChildList(list, t);
        //设置TreeObject对象中的子节点集合数据
		t.setChildren(childList);
        //遍历子节点集合
		for (TreeObject tChild : childList) {
            // 判断是否有子节点
			if (hasChild(list, tChild)) {
				//returnList.add(TreeObject);
				Iterator<TreeObject> it = childList.iterator();
				while (it.hasNext()) {
					TreeObject n = (TreeObject) it.next();
					recursionFn(list, n);
				}
			}
		}
	}

    /**
     * 子节点列表
     * @param list 所有菜单集合
     * @param t 父节点对象
     * @return
     */
	private List<TreeObject> getChildList(List<TreeObject> list, TreeObject t) {
        //子节点集合
		List<TreeObject> tlist = new ArrayList<TreeObject>();
        //遍历菜单对象
		Iterator<TreeObject> it = list.iterator();
		while (it.hasNext()) {
			TreeObject n = (TreeObject) it.next();
			if (n.getParent_id() == t.getId()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<TreeObject> getChildTreeObjects(List<TreeObject> list, int typeId,String prefix){
        if(list == null) return null;
        //遍历所有菜单
        for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext();) {
            //当前菜单对象
            TreeObject node = (TreeObject) iterator.next();
            //根据传入父节点遍历所有子节点
            if (node.getParent_id()==typeId) {
                recursionFn(list, node,prefix);
            }
        }
		System.out.println(returnList.size());
		return returnList;
    }


    private void recursionFn(List<TreeObject> list, TreeObject node,String p) {
        // 得到子节点列表
        List<TreeObject> childList = getChildList(list, node);
        // 判断是否有子节点
        if (hasChild(list, node)) {
            returnList.add(node);
            Iterator<TreeObject> it = childList.iterator();
            while (it.hasNext()) {
                TreeObject n = (TreeObject) it.next();
                n.setName(p+n.getName());
                recursionFn(list, n,p+p);
            }
        } else {
            returnList.add(node);
        }
    }

	// 判断是否有子节点
	private boolean hasChild(List<TreeObject> list, TreeObject t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
	
	// 本地模拟数据测试
	public void main(String[] args) {
//		long start = System.currentTimeMillis();
//		List<TreeObject> TreeObjectList = new ArrayList<TreeObject>();
//
//		TreeObjectUtil mt = new TreeObjectUtil();
//		List<TreeObject> ns=mt.getChildTreeObjects(TreeObjectList,0);
//		for (TreeObject m : ns) {
//			System.out.println(m.getName());
//			System.out.println(m.getChildren());
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("用时:" + (end - start) + "ms");
	}
	
}
