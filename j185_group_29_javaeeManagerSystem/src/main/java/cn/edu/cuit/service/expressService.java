package cn.edu.cuit.service;

import java.util.List;

import cn.edu.cuit.dao.expressDao;
import cn.edu.cuit.domain.Express;
import cn.edu.cuit.domain.User;
import cn.edu.cuit.domain.pageVO;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class expressService {
	expressDao expressDao = new expressDao();

	//查询服务

	/**
	 * 分页查询
	 * @param page 当前页
	 * @param recordOfPage 每一页记录数
	 * @return 当前页的用户记录
	 */
	public pageVO<Express> search(int page, int recordOfPage){

		System.out.println("第"+ page + "页:" +recordOfPage +"条记录");

		pageVO<Express> pageVO = new pageVO<Express>();
		// 1 查询总记录数量
		int recordCount = expressDao.getCount();
		// 2 计算总页数
		int pageCount = (recordCount-1) / recordOfPage + 1;
		// 3 约束当前页码在允许范围
		if(page < 1) { page = 1; }
		if(page > pageCount ) {page = pageCount;}
		// 4 获取当前页的数据记录
		List<Express> expressList = expressDao.selectPage(page, recordOfPage);

		pageVO.setRecordOfPage(recordOfPage);
		pageVO.setRecordCount(recordCount);
		pageVO.setPageCount(pageCount);
		pageVO.setPage(page);
		pageVO.setList(expressList);
		//6返回分页查询结果
		return pageVO;
	}

	/**
	 * 查找所用用户
	 * @return
	 */
	public List<Express> findUserAll(){

		return expressDao.findAll();
	}

	/**
	 * 按姓名查找用户
	 * @param name
	 * @return
	 */
	public List<Express> findUserbyName(String name){

		return expressDao.findByName(name);
	}

	/**
	 * 按Id号查找用户
	 * @param id
	 * @return
	 */
	public Express findUserbyId(Integer id) {

		return expressDao.findById(id);
	}

	//更新业务
	/**
	 * 新增用户
	 * @param Ex
	 */
	@Transactional
	public void saveUser(Express Ex) {
		expressDao.insertExpress(Ex);
	}

	/**
	 * 修改用户
	 * @param Ex
	 */
	@Transactional
	public void changeUser(Express Ex) {

		expressDao.updateUser(Ex);
	}

	/**
	 * 删除单个用户
	 * @param id
	 */
	@Transactional
	public void deleteUser(String id) {

		expressDao.deleteUser(id);
	}

	/**
	 * 批量删除用户
	 * @param ids
	 */
	@Transactional
	public void deleteUser(String[] ids) {

		for (String id : ids) {
			expressDao.deleteUser(id);
		}
	}

}
