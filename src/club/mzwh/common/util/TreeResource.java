package club.mzwh.common.util;

import java.util.ArrayList;
import java.util.List;

import club.mzwh.security.model.ResourceModel;

public class TreeResource {

	private List<ResourceModel> resourceList = new ArrayList<ResourceModel>();
	
	public TreeResource(List<ResourceModel> list) {
		this.resourceList = list;
	}
	
	/**
	 * 得到所有树
	 * @return list（存放所有的树-）
	 */
	public List<ResourceModel> getTree() {
		return makeTreeList(getFatherNode());
	}
	
	/**
	  * @return 存放父节点的list：fatherList
      */
	public List<ResourceModel> getFatherNode() {
		int i, j, sum = 0;
		int size = resourceList.size();
		List<ResourceModel> fatherList = new ArrayList<ResourceModel>();
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				if (!resourceList.get(i).getPid().equals(resourceList.get(j).getId())) {
					sum++;
				} else {
					break;
				}
			}
			if (sum == size) {
				resourceList.get(i).setState("closed");
				fatherList.add(resourceList.get(i));
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
	public List<ResourceModel> makeTreeList(List<ResourceModel> fatherlist) {
		List<ResourceModel> allTree =new ArrayList<ResourceModel>();
		for(ResourceModel item:fatherlist) {
			allTree.add(makeTree(item));
		}
		return allTree;
	}
	
	/**
	 * 根据传进来的树制作一颗树
	 * 递归生成一棵树
	 * @param Node
	 * @return
	 */
	public ResourceModel makeTree(ResourceModel Node) {
		ResourceModel root = Node;
		List<ResourceModel> childrenResourceModel = this.getChildrenNodeById(Node.getId()); 
		for(ResourceModel item:childrenResourceModel) { 
			ResourceModel node = makeTree(item); 
			root.getChildren().add(node);
		} 
		return root;
	}
	
	/**
	 * @param nodeId（父节点对应的id）
	 * @return 获得所有子节点，并存放在childrenResourceModel中
	 */
	public List<ResourceModel> getChildrenNodeById(String nodeId) {
		List<ResourceModel> childrenResourceModel = new ArrayList<ResourceModel>();
		for (ResourceModel item : resourceList) {
			if (item.getPid().equals(nodeId)) {
				childrenResourceModel.add(item);
			}
		}
		return childrenResourceModel;
	}
}
