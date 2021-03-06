package cn.edu.cuit.controller;

import cn.edu.cuit.domain.Express;
import cn.edu.cuit.domain.pageVO;
import cn.edu.cuit.service.expressService;
import cn.edu.cuit.utils.ResponseInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("express")
public class ExpressController {

    @Autowired
    expressService expressService;

    @DeleteMapping("{id}")
    public Map<String, Object> deleteExpress(@PathVariable("id") String id) {
        this.expressService.deleteUser(id);
        return ResponseInfoUtil.responseDataAndMeta("", "删除成功", HttpStatus.OK);
    }

    @PostMapping
    public Map<String, Object> saveExpress(@RequestBody Express express) {
        this.expressService.saveUser(express);
        return ResponseInfoUtil.responseDataAndMeta("", "快递创建成功", HttpStatus.CREATED);
    }

    @PutMapping
    public Map<String, Object> modifyExpress(@RequestBody Express express) {
        this.expressService.changeUser(express);
        return ResponseInfoUtil.responseDataAndMeta("", "更新成功", HttpStatus.OK);
    }

    @DeleteMapping("many")
    public Map<String, Object> deleteExpress(@RequestParam("id") String[] ids) {
        this.expressService.deleteUser(ids);
        return ResponseInfoUtil.responseDataAndMeta("", "删除成功", HttpStatus.OK);
    }

    @GetMapping
    public Map<String, Object> queryUserByPage(
            @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
            @RequestParam(name = "pagesize", defaultValue = "5") Integer pagesize) {

        if (pagenum <= 0 || pagesize <= 0) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }

        pageVO<Express> pageVO = this.expressService.search(pagenum, pagesize);

        if (CollectionUtils.isEmpty(pageVO.getList())) {
            return ResponseInfoUtil.responseDataAndMeta("", "没有结果", HttpStatus.NOT_FOUND);
        }
        return ResponseInfoUtil.responseDataAndMeta(pageVO, "获取成功", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Map<String, Object> findExpressByUuid(@PathVariable(value = "id", required = true) Integer id) {
        Express express = this.expressService.findUserbyId(id);
        if (express == null) {
            return ResponseInfoUtil.responseDataAndMeta("", "查询结果集为空", HttpStatus.NOT_FOUND);
        }
        return ResponseInfoUtil.responseDataAndMeta(express, "查询成功", HttpStatus.OK);
    }
}
