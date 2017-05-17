package com.lcw.one.workflow.config.listener;

import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.workflow.entity.FlowTaskInfoEO;
import com.lcw.one.workflow.service.FlowTaskInfoEOService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VariableCreateListener implements ActivitiEventListener {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FlowTaskInfoEOService processTaskInfoEOService;

    @Override
    public void onEvent(ActivitiEvent event) {
        switch (event.getType()) {
            case TASK_CREATED:
                handleTaskCreatedEvent(event);
        }
    }

    private void handleTaskCreatedEvent(ActivitiEvent event) {
        ActivitiEntityEventImpl eventImpl = (ActivitiEntityEventImpl) event;
        TaskEntity taskEntity = (TaskEntity) eventImpl.getEntity();

        String taskName = taskEntity.getName();
        String taskDefinitionKey = taskEntity.getTaskDefinitionKey(); // 流程节点Id
        String processDefinitionKey = taskEntity.getProcessInstance().getProcessDefinitionKey(); // 流程ID
        logger.info("Task Name[{}], TaskDefinitionKey[{}], ProcessDefinitionKey[{}]", taskName, taskDefinitionKey, processDefinitionKey);

        // 获取该节点绑定的表单
        FlowTaskInfoEO flowTaskInfoEO = processTaskInfoEOService.getFlowTaskInfoEOAndValid(processDefinitionKey, taskDefinitionKey);
        if(flowTaskInfoEO == null) {
            throw new OneBaseException("流程[" + processDefinitionKey + "]的节点[" + taskDefinitionKey + "]未配置角色或表单");
        }


        String assignee =  taskEntity.getAssignee();
        //无需配置 默认启动时已添加指定人员
        if(StringUtils.isEmpty(assignee)){
            taskEntity.addCandidateGroup(flowTaskInfoEO.getBindRole());
            taskEntity.setFormKey(flowTaskInfoEO.getBindForm());
            logger.info("Assign Task To [{}]", flowTaskInfoEO.getBindRole());
        }
    }


    @Override
    public boolean isFailOnException() {
        return false;
    }
}
