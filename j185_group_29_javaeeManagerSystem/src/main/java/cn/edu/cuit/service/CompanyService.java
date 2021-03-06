package cn.edu.cuit.service;

import cn.edu.cuit.dao.CompanyDao;
import cn.edu.cuit.domain.PageResult;
import cn.edu.cuit.domain.Company;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Transactional  // 添加事务
    public void saveCompany(Company company) {
        this.companyDao.saveCompany(company);
    }

    /**
     *
     * @param query 查询条件
     * @param pagenum 查第几页
     * @param pagesize 每页多少条数据
     * @return
     */
    public PageResult<Company> findCompanyByPage(String query, Integer pagenum, Integer pagesize) {
        // 此句放到dao查询之前
        PageHelper.startPage(pagenum, pagesize);

        // 执行sql, 对用户名进行模糊匹配
        if (query == null) {  // 防止query为null
            query = "";
        }
        List<Company> companies = this.companyDao.findByQuery("%" + query + "%");

        // pageHelper的pageInfo对象, 封装各种查询信息
        PageInfo<Company> pageInfo = new PageInfo<>(companies);

        // 返回自己封装的PageResult对象(包含总结果数,总页数 , company集合)
        return new PageResult<Company>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }

    @Transactional
    public void modifyCompany(Company company) {
        this.companyDao.modifyCompany(company);
    }

    @Transactional
    public void deleteCompany(Integer id) {
        this.companyDao.deleteCompany(id);
    }

    public Company findCompanyById(Integer id) {
        return this.companyDao.findCompanyById(id);
    }

}
