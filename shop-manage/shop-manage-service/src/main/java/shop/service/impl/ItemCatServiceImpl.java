package shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.common.pojo.LayuiTree;
import shop.mapper.TbItemCatMapper;
import shop.pojo.TbItemCat;
import shop.pojo.TbItemCatExample;
import shop.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	
	@Override
	public List<LayuiTree> getItemCatList(long parentId) {
		//根据parentId查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		//Criteria criteria = example.createCriteria();
		//criteria.andParentIdEqualTo();

		//根据条件查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<LayuiTree> resultList = new ArrayList<>();
		//把列表转换成LayuiTree
		for (TbItemCat tbItemCat : list) {
			LayuiTree node = new LayuiTree();
			node.setId(tbItemCat.getId());
			node.setParentId(tbItemCat.getParentId());
			node.setName(tbItemCat.getName());
			//node.setSpread(tbItemCat.getIsParent());
			//node.setChildren(new ArrayList<>());
			resultList.add(node);
		}
		
		//-方式1 循环
		List<LayuiTree> nodeList = new ArrayList<>();
	    for(LayuiTree node1 : resultList){//resultList 是数据库获取的List列表数据或者来自其他数据源的List

            boolean mark = false;
            for(LayuiTree node2 : resultList){
                if(node1.getParentId()!=null && node1.getParentId().equals(node2.getId())){
                    mark = true;
                    if(node2.getChildren() == null)
                        node2.setChildren(new ArrayList<LayuiTree>());
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
            }
        }
		
		return nodeList;
		
		//返回结果
		//return constructTaskDTOToTree(resultList);

	}
	
	
	/*//方式2 递归
	List<XMGLTaskDTO > nodeList = new ArrayList();  
	nodeList = constructTaskDTOToTree(taskDTOList );//taskDTOList 是数据库获取的List列表数据或者来自其他数据源的List
	
    public List<XMGLTaskDTO> constructTaskDTOToTree(List<XMGLTaskDTO> taskDTOList){
        //key:父节点ID value:子节点集合
        Map<Long,List<XMGLTaskDTO>> taskDTOMap = new HashMap<>();

        //将List重组到Map中
        taskDTOList.forEach(dto -> {
            List<XMGLTaskDTO> tempTaskDTOList = taskDTOMap.get(dto.getParentId());
            if (tempTaskDTOList == null){
                tempTaskDTOList = new ArrayList<XMGLTaskDTO>();
                taskDTOMap.put(dto.getParentId(),tempTaskDTOList);
            }
            tempTaskDTOList.add(dto);
        });

        //顶级节点集合
        List<XMGLTaskDTO> resultTaskDTOList = taskDTOMap.get(null);

        recurTaskDTOList(resultTaskDTOList,taskDTOMap);

        return resultTaskDTOList;
    }

    
    public void recurTaskDTOList(List<XMGLTaskDTO> taskDTOList,Map<Long,List<XMGLTaskDTO>> sourceMap){
        if(CollectionUtils.isEmpty(taskDTOList))
            return;
        taskDTOList.forEach(dto -> {
            dto.setChildrenTaskList(sourceMap.get(dto.getId()));
            recurTaskDTOList(dto.getChildrenTaskList(),sourceMap);
        });
    }
	
	*/

	
	

}
