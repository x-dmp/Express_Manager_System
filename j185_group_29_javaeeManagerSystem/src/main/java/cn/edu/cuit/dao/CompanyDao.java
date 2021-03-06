package cn.edu.cuit.dao;

import cn.edu.cuit.domain.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CompanyDao {
    @Insert("INSERT INTO express_company(id, name, introduce, address) VALUES(#{id}, #{name}, #{introduce}, #{address})")
    @Options(useGeneratedKeys=true, keyProperty="id")  // 插入后自动注入用户id到company对象
    void saveCompany(Company company);

    @Select("SELECT * FROM express_company WHERE name LIKE #{query}")
    List<Company> findByQuery(String query);

    @Update("UPDATE express_company set name = #{name}, introduce = #{introduce}, address = #{address} WHERE id = #{id}")
    void modifyCompany(Company company);

    @Delete("DELETE FROM express_company WHERE id = #{id}")
    void deleteCompany(Integer id);

    @Select("SELECT * FROM express_company WHERE id = #{id}")
    Company findCompanyById(Integer id);
}
