package com.project.demo.controller;

import com.project.demo.entity.HospitalLeaders;
import com.project.demo.service.HospitalLeadersService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *院领导：(HospitalLeaders)表控制层
 *
 */
@RestController
@RequestMapping("/hospital_leaders")
public class HospitalLeadersController extends BaseController<HospitalLeaders,HospitalLeadersService> {

    /**
     *院领导对象
     */
    @Autowired
    public HospitalLeadersController(HospitalLeadersService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapinstitute_leader_no = new HashMap<>();
        mapinstitute_leader_no.put("institute_leader_no",String.valueOf(paramMap.get("institute_leader_no")));
        List listinstitute_leader_no = service.select(mapinstitute_leader_no, new HashMap<>()).getResultList();
        if (listinstitute_leader_no.size()>0){
            return error(30000, "字段院领导编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
