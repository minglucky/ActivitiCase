package com.zioer.controller;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;


@Controller
@RequestMapping(value = "/process")
public class ProcessController {
    @Autowired
    private RepositoryService repositoryService;
    
    
    /**
	 * 流程列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView listdeployed(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();

		long pageSize = 10;
    	long page = 0 ;
    	long totalPage = 0;
    	long totalRows = 0;
    	
    	long firstResult;	//查询的起始记录数
    	long maxResults = pageSize; //查询的每页显示记录数
    	
    	ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        
		totalRows = processDefinitionQuery.count();
        if ( totalRows % pageSize == 0){
    		totalPage = totalRows / pageSize;
    	}else{
    		totalPage = totalRows / pageSize + 1;
    	}
    	
    	if (request.getParameter("page") == null){
    		page = 1;
    	}else{
    		if (Integer.parseInt(request.getParameter("page"))<1) page = 1;
    		else if (Integer.parseInt(request.getParameter("page")) > totalPage) page = totalPage;
    		else page = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	firstResult = pageSize * (page - 1);
    	
    	List<ProcessDefinition> list = processDefinitionQuery.listPage((int)firstResult, (int)maxResults );
    	
        mv.setViewName("process_list");
        mv.addObject("currentPage", page);
        mv.addObject("totalPage", totalPage);
    	
    	mv.addObject("data", list);
    	
		return mv;
	}
    
	@RequestMapping(value = "/add")
    public String add() {
    	return "process_add";
    }
	
	@RequestMapping({"/uplaod_save"})
	public String uploadDeployedsave( @RequestParam(value="upFile", required=false) MultipartFile[] files) throws IOException{
		String fileOriginalname;
			
    	if(files!=null&&files.length>0){  
            //循环获取file数组中得文件  
            for(int i = 0;i<files.length;i++){  
                MultipartFile file = files[i]; 	            
                fileOriginalname = file.getOriginalFilename();
                
                String extension = FilenameUtils.getExtension(fileOriginalname);
                
                InputStream fileInputStream = file.getInputStream();
                
                if (extension.equals("zip") || extension.equals("bar")) {
                    ZipInputStream zip = new ZipInputStream(fileInputStream);
                    repositoryService.createDeployment().addZipInputStream(zip).deploy();
                } else {
                    repositoryService.createDeployment().addInputStream(fileOriginalname, fileInputStream).deploy();
                }
                
                
            }  
        }
    	
    	return "redirect:list/";
        
    }

	@RequestMapping(value = "/delete/{deploymentId}")
	public String deleteDeployed(@PathVariable String deploymentId) {
		
		try {
			//repositoryService.deleteDeployment(deploymentId);
			//级联删除:同时删除启动的流程，删除和当前规则相关的所有信息，正在执行的流程，包括历史信息
			repositoryService.deleteDeployment(deploymentId, true);
		} catch (Exception e) {
			
		} 

		return "redirect:../list/";
    }
	
	/**
     * 读取资源，通过部署ID
     */
    @RequestMapping(value = "/resource/read")
    public void readDep(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("reType") String reType,
                                 HttpServletResponse response) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (reType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (reType.equals("xml")) {
        	response.setContentType("text/xml;charset=utf-8");
        	response.setHeader("cache-control", "no-cache");
            resourceName = processDefinition.getResourceName();
        }
        
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    
    /**
     * 动态BPMN部署
     * @return
     */
    @RequestMapping(value = "/depbpmnmodel")
	public String deployementProDefByBPMN() {  
    	
    	BpmnModel bpmnModel=new BpmnModel();  
    	StartEvent startEvent=new StartEvent();  
    	startEvent.setId("start1");  
    	startEvent.setName("动态创建开始节点");  
    	UserTask userTask=new UserTask();  
    	userTask.setId("userTask1");  
    	userTask.setName("用户任务节点1");  
    	EndEvent endEvent=new EndEvent();  
    	endEvent.setId("endEvent1");  
    	endEvent.setName("动态创建结束节点");  
    	List<SequenceFlow> sequenceFlows=new ArrayList<SequenceFlow>();  
    	List<SequenceFlow> toEnd=new ArrayList<SequenceFlow>();  
    	SequenceFlow s1=new SequenceFlow();  
    	s1.setId("sequenceFlow1");  
    	s1.setName("开始节点指向用户任务节点");  
    	s1.setSourceRef("start1");  
    	s1.setTargetRef("userTask1");  
    	sequenceFlows.add(s1);  
    	SequenceFlow s2=new SequenceFlow();  
    	s2.setId("sequenceFlow2");  
    	s2.setName("用户任务节点指向结束节点");  
    	s2.setSourceRef("userTask1");  
    	s2.setTargetRef("endEvent1");  
    	toEnd.add(s2);  
    	
    	startEvent.setOutgoingFlows(sequenceFlows);  
    	userTask.setOutgoingFlows(toEnd);  
    	userTask.setIncomingFlows(sequenceFlows);  
    	endEvent.setIncomingFlows(toEnd);  
    	
    	Process process=new Process();  
    	process.setId("process1");  
    	process.setName("test");
    	process.addFlowElement(startEvent);  
    	process.addFlowElement(s1);  
    	process.addFlowElement(userTask);  
    	process.addFlowElement(s2);  
    	process.addFlowElement(endEvent);  
    	bpmnModel.addProcess(process);  
    	
    	new BpmnAutoLayout(bpmnModel).execute();
    	
        Deployment deployment = repositoryService.createDeployment()//获取流程定义和部署对象相关的Service  
                        .name("reimbursement") 
                        .addBpmnModel("dynamic-model.bpmn", bpmnModel)
                        .deploy();//完成部署  
        System.out.println("部署ID："+deployment.getId());//1  
        System.out.println("部署时间："+deployment.getDeploymentTime());  
        
        return "redirect:list";
    }  
	
    /**
     * Classpath部署
     * @return
     */
    @RequestMapping(value = "/depclasspath")
    public String deployementProDef(){  
    	Deployment dep = repositoryService.createDeployment()
    			.name("reimbursement")
    			.category("yourCategory")
    			.tenantId("dfdfe32fsdfsdfsf")
    			.addClasspathResource("workflow/reimbursement/reimbursement.bpmn").deploy();

        System.out.println("部署ID："+dep.getId());//1  
        System.out.println("部署时间："+dep.getDeploymentTime());  
        
        return "redirect:list";
    }  
    
    /**
     * 输入流部署
     * @return
     */
    @RequestMapping(value = "/depinputstream")
    public String deployementProDefByInS() throws FileNotFoundException{  
        //获取资源相对路径  
        String bpmnPath = "C:/Users/kitty/Workspaces/MyEclipse 2016 CI/13-1/resources/workflow/reimbursement/reimbursement.bpmn";  
          
        //读取资源作为一个输入流  
        FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);  
          
        Deployment deployment = repositoryService.createDeployment()//获取流程定义和部署对象相关的Service  
                        .name("reimbursement") 
                        .addInputStream("reimbursement.bpmn",bpmnfileInputStream)  
                        .deploy();//完成部署  
        System.out.println("部署ID："+deployment.getId());//1  
        System.out.println("部署时间："+deployment.getDeploymentTime());  
        
        return "redirect:list";
    }  
    /**
     * 压缩包部署
     * @return
     */
    @RequestMapping(value = "/depzip")
    public String deployementProDefByZIP() throws FileNotFoundException{  
        //获取资源相对路径  
        String zipPath = "d:/reimbursement.zip";  
        File f = new File(zipPath);   
        InputStream in = new FileInputStream(f);
        
        InputStream ins = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip");  
        
        //读取资源作为一个输入流  
        ZipInputStream zipfileInputStream = new ZipInputStream(in);  
          
        Deployment deployment = repositoryService.createDeployment()//获取流程定义和部署对象相关的Service  
                        .name("reimbursement") 
                        .addZipInputStream(zipfileInputStream)
                        .deploy();//完成部署  
        System.out.println("部署ID："+deployment.getId());//1  
        System.out.println("部署时间："+deployment.getDeploymentTime());  
        
        return "redirect:list";
    }  
    
    /**
     * 字符串部署
     * @return
     */
    @RequestMapping(value = "/depstring")
    public String deployementProDefByStr() throws FileNotFoundException{  
        
    	String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions>...</definitions>";  
        
        Deployment deployment = repositoryService.createDeployment()//获取流程定义和部署对象相关的Service  
                        .name("reimbursement") 
                        .addString("reimbursement.bpmn", str)
                        .deploy();//完成部署  
        System.out.println("部署ID："+deployment.getId());//1  
        System.out.println("部署时间："+deployment.getDeploymentTime());  
        
        return "redirect:list";
    }  
    
}
