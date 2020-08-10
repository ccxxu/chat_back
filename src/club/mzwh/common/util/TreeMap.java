package club.mzwh.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeMap {

	private List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
	
	public TreeMap(List<Map<String, Object>> list) {
		this.maplist = list;
	}
	
	/**
	 * 得到所有树
	 * @return list（存放所有的树-）
	 */
	public List<Map<String, Object>> getTree() {
		return makeTreeList(getFatherNode());
	}
	
	/**
	  * @return 存放父节点的list：fatherList
      */
	public List<Map<String, Object>> getFatherNode() {
		int i, j, sum = 0;
		int size = this.maplist.size();
		List<Map<String, Object>> fatherList = new ArrayList<Map<String, Object>>();
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				if (!maplist.get(i).get("pid").equals(maplist.get(j).get("id"))) {
					sum++;
				} else {
					break;
				}
			}
			if (sum == size) {
				maplist.get(i).put("state", "closed");
				fatherList.add(maplist.get(i));
				sum = 0;
			} else {
				sum = 0;
			}
		}
		return fatherList;
	}
	
	/**
	 * 遍历fatherlist中的父节点
	 * 调用maketree（）方法根据每个父节点产生相应的树
	 * 并将产生的树放到list中
	 * @param fatherlist
	 * @return allTree（存放的都是树）
	 */
	public List<Map<String, Object>> makeTreeList(List<Map<String, Object>> fatherlist) {
		List<Map<String, Object>> allTree =new ArrayList<Map<String, Object>>();
		for(Map<String, Object> item:fatherlist) {
			allTree.add(makeTree(item));
		}
		return allTree;
	}
	
	/**根据传进来的树制作一颗树
	 * 递归生成一棵树
	 * @param Node
	 * @return
	 */
	public Map<String, Object> makeTree(Map<String, Object> Node) {
		Map<String, Object> root = Node;
		List<Map<String, Object>> childrenMap = this.getChildrenNodeById(Node.get("id")+""); 
		root.put("children", childrenMap);
		return root;
	}
	
	/**
	 * @param nodeId（父节点对应的id）
	 * @return 获得所有子节点，并存放在childrenMap<String, Object>中
	 */
	public List<Map<String, Object>> getChildrenNodeById(String nodeId) {
		List<Map<String, Object>> childrenMap = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> item : maplist) {
			if (nodeId.equals(item.get("pid")+"")) {
				childrenMap.add(item);
			}
		}
		return childrenMap;
	}
}
