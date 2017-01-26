package com.refactor.case3;

import com.google.gson.Gson;
import com.refactor.case3.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller("realTimeQualController")
@RequestMapping("/ams/cases/realtimeQual/")
public class RealTimeQualController {

    @Resource
    private CasesService casesService;
    @Resource
    private UDRTagRequestService udrTagRequestService;

    @RequestMapping(value = "nameList/get", method = RequestMethod.GET)
    @ResponseBody
	//一个TreeRoot与Node有一对一关系，Node下有多个Leaf或TreeRoot。大致了解即可，不影响重构过程
	public String getNameList(Long caseId) throws Exception {
    	Case casee = casesService.queryById(caseId);
    	Set<NameList> nameA = casee.getNamelistGroup(NameListGroup.NameListGroupType.A组).getLists();
    	//目前只有一个A名单组,无需遍历
    	if(nameA.size()>0){
    		
    		ConditionNameList nameListA = (ConditionNameList)nameA.iterator().next();
    		
    		List<Condition> conditions = new ArrayList<>();
    		conditions.addAll(nameListA.getConditions());
    		
    		//获取表达式A AND B...
    		String expression = nameListA.getLogic_exp();
    		
    		TreeRoot treeRoot = new TreeRoot();
    		treeRoot.setClazz("tree-root");
    		treeRoot.setConditionType(ExpressionConvertor.getExpressionType(expression));
    		treeRoot.setId(nameListA.getId().toString());
    		Node rootNode = new Node();
    		List<Leaf> primaryLeafs = new ArrayList<Leaf>();
    		List<TreeRoot> secondaryTreeRoots = new ArrayList<TreeRoot>();
    		
    		List<UDRTag> udrTagList= udrTagRequestService.getAllUDRTags();
    		
    		List<UiConditionNameListTag> uiConditionNameListTags = UiEntityConverter.convert(udrTagList, UiConditionNameListTag.class);
    		Map<String, UiConditionNameListTag> mappedTag = uiConditionNameListTags.stream().collect(
    				Collectors.toMap(UiConditionNameListTag::getId, (p) -> p));
    		
    		
    		for(Condition condition : conditions){
    			List<String> primaryLeafIds = ExpressionConvertor.getNode(expression);
    			for(String leafId : primaryLeafIds){
    				if(leafId.trim().length()>0 && leafId.trim().equals(condition.getSymbol())){
    					Leaf leaf = new Leaf();
    					leaf.setClazz("primary-leaf");
    					leaf.setLeafId(leafId);
    					leaf.setOpType(condition.getOpType().name());
    					leaf.setOpValue(condition.getOpValue());
    					leaf.setTagId(condition.getTagId());
    					leaf.setTag(mappedTag.get(condition.getTagId()));
    					primaryLeafs.add(leaf);
    				}
    			}
    		}
    		List<String> secondLeafExpressions = ExpressionConvertor.getSecondExpression(expression);
    		for(String secondLeafExpression : secondLeafExpressions){
    			List<String> secondLeafs = ExpressionConvertor.getNode(secondLeafExpression);
    			List<Leaf> secondaryLeafs = new ArrayList<Leaf>();
    			TreeRoot secondTreeRoot = new TreeRoot();
    			secondTreeRoot.setClazz("tree-root");
    			secondTreeRoot.setConditionType(ExpressionConvertor.getExpressionType(secondLeafExpression));
    			for(Condition condition : conditions){	
    				for(String leafId : secondLeafs){
    					if(leafId.trim().length()>0 && leafId.trim().equals(condition.getSymbol())){
    						Leaf leaf = new Leaf();
    						leaf.setClazz("secondary-leaf");
    						leaf.setLeafId(leafId);
    						leaf.setOpType(condition.getOpType().name());
    						leaf.setOpValue(condition.getOpValue());
    						leaf.setTagId(condition.getTagId());
    						leaf.setTag(mappedTag.get(condition.getTagId()));
    						secondaryLeafs.add(leaf);
    					}
    				}
    			}
    			Node secondaryNode = new Node();
    			secondaryNode.setLeafs(secondaryLeafs);
    			secondTreeRoot.setNode(secondaryNode);
    			secondaryTreeRoots.add(secondTreeRoot);
    		}
    		rootNode.setTreeRoots(secondaryTreeRoots);
    		rootNode.setLeafs(primaryLeafs);
    		treeRoot.setNode(rootNode);
    		Gson gson = new Gson();
    		String json = gson.toJson(treeRoot).replaceAll("clazz", "class");
    		return json;
    	}else{
    		return null;
    	}
    	
    }
}
