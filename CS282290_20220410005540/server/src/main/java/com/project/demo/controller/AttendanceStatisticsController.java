package com.project.demo.controller;

import com.project.demo.entity.AttendanceStatistics;
import com.project.demo.service.AttendanceStatisticsService;
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
 *考勤统计：(AttendanceStatistics)表控制层
 *
 */
@RestController
@RequestMapping("/attendance_statistics")
public class AttendanceStatisticsController extends BaseController<AttendanceStatistics,AttendanceStatisticsService> {

    /**
     *考勤统计对象
     */
    @Autowired
    public AttendanceStatisticsController(AttendanceStatisticsService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
