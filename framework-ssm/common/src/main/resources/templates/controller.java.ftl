package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

/**
* ${table.comment!} 前端控制器
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/abt/<#if controllerMappingHyphenStyle??>${table.entityName?uncap_first}<#else>${controllerMappingHyphen}</#if>")
<#if superControllerClass??>
public class ${table.controllerName} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};
}
