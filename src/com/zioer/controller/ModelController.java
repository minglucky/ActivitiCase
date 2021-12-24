package com.zioer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/model")
public class ModelController {
    
    @Autowired
    private RepositoryService repositoryService;
    @RequestMapping(value = "/add")
    public String add() {
        return "model_add";
    }

	@RequestMapping(value = "/bpmn")
	public String bpmn() {
		return "bpmn";
	}
    
    /**
     * 创建模型
     */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, HttpServletResponse response) {
    	Map formData = PageData(request);
    	String modelName = formData.get("modelName").toString();
    	String modelDescription = formData.get("modelDescription").toString();
    	modelDescription = StringUtils.defaultString(modelDescription);
    	
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode oNode = objectMapper.createObjectNode();
            ObjectNode namespaceNode = objectMapper.createObjectNode();
            namespaceNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            
            oNode.put("id", "canvas");
            oNode.put("resourceId", "canvas");
            
            oNode.put("stencilset", namespaceNode);
            
            org.activiti.engine.repository.Model model = repositoryService.newModel();

            ObjectNode modelNode = objectMapper.createObjectNode();
            modelNode.put(ModelDataJsonConstants.MODEL_NAME, modelName);
            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            
            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, modelDescription);
            
            model.setMetaInfo(modelNode.toString());
            model.setName(modelName);

            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), oNode.toString().getBytes("utf-8"));

            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + model.getId());
        } catch (Exception e) {
            System.out.println("error："+e);
        }
    }

	@RequestMapping(value = "/list")
	public String list(Model model) {
	  	List<org.activiti.engine.repository.Model> datas = repositoryService.createModelQuery().list();
	  	
	  	model.addAttribute("models", datas);    	
	  	return "model_list";
	}
	
	@RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id){
    	repositoryService.deleteModel(id);
    	return "redirect:/model/list";
    } 
	
	@RequestMapping(value = "/deploy/{id}")
    public String deployModelerModel(@PathVariable String id){
		try {
			org.activiti.engine.repository.Model modelData = repositoryService.getModel(id);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            org.activiti.bpmn.model.BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            String processName = modelData.getName() + ".bpmn20.xml";
            
            repositoryService.createDeployment()
            	.name(modelData.getName())
            	.addString(processName, new String(bpmnBytes,"UTF-8"))
            	.deploy();
        } catch (Exception e) {
        }
		return "redirect:/model/list";
    } 
	
    public Map PageData(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		return returnMap;
	}
}
