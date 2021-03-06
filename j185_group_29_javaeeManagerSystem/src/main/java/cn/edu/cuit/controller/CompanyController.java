package cn.edu.cuit.controller;

import cn.edu.cuit.domain.PageResult;
import cn.edu.cuit.domain.Company;
import cn.edu.cuit.service.CompanyService;
import cn.edu.cuit.utils.ResponseInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 保存公司
     * 以Company接受json对象，Company属性与json对象键对应
     * @param company
     * @return
     */
    @PostMapping
    public Map<String, Object> saveCompany(@RequestBody Company company) {
        this.companyService.saveCompany(company);

        return ResponseInfoUtil.responseDataAndMeta("", "公司创建成功", HttpStatus.CREATED);
    }

    /**
     * 用户分页查询
     * @param query 公司名(需要对公司名进行模糊匹配)
     * @param pagenum 需要查询的第几页
     * @param pagesize 每页多少条数据
     * @return
     */
    @GetMapping
    public Map<String, Object> queryCompanyByPage(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
            @RequestParam(name = "pagesize", defaultValue = "5") Integer pagesize) {

        if (pagenum <= 0 || pagesize <= 0) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }

        // 包含 数据总条数,总页数,查询到的Company集合
        PageResult<Company> pageResult = this.companyService.findCompanyByPage(query, pagenum, pagesize);

        if (CollectionUtils.isEmpty(pageResult.getItems())) {  // 查询结果集为空(spring提供的工具类检查集合是否为空)
            return ResponseInfoUtil.responseDataAndMeta("", "没有结果", HttpStatus.NOT_FOUND);
        }
        return ResponseInfoUtil.responseDataAndMeta(pageResult, "获取成功", HttpStatus.OK);
    }

    /**
     * 修改公司(包含id,name,introduce)
     * @return
     */
    @PutMapping
    public Map<String, Object> modifyCompany(@RequestBody Company company) {
        this.companyService.modifyCompany(company);

        return ResponseInfoUtil.responseDataAndMeta("", "更新成功", HttpStatus.OK);
    }

    /**
     * 根据id删除公司，restful参数{xxx}利用@PathVariable接受
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Map<String, Object> modifyCompany(@PathVariable(name = "id", required = true) Integer id) {
        if (id < 0) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }
        this.companyService.deleteCompany(id);

        return ResponseInfoUtil.responseDataAndMeta("", "删除成功", HttpStatus.OK);
    }

    /**
     * 根据id查询公司，restful参数{xxx}利用@PathVariable接受
     * @return
     */
    @GetMapping("{id}")
    public Map<String, Object> findCompany(@PathVariable(name = "id", required = true) Integer id) {
        if (id < 0) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }
        Company company = this.companyService.findCompanyById(id);
        if (company == null) {
            return ResponseInfoUtil.responseDataAndMeta("", "查询结果集为空", HttpStatus.NOT_FOUND);
        }
        return ResponseInfoUtil.responseDataAndMeta(company, "查询成功", HttpStatus.OK);
    }
}
