package cn.edu.cuit.service;

import cn.edu.cuit.dao.CompanyDao;
import cn.edu.cuit.dao.SendDao;
import cn.edu.cuit.dao.UserDao;
import cn.edu.cuit.dao.expressDao;
import cn.edu.cuit.dao.SendDao;
import cn.edu.cuit.domain.PageResult;
import cn.edu.cuit.domain.Send;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SendService {

    @Autowired
    private SendDao sendDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;

    private expressDao expressDao = new expressDao();

    @Transactional  // 添加事务
    public void saveSend(Send send) {
        send.setSendTime(new Date());
        this.sendDao.saveSend(send);
    }


    /**
     * @param query    查询条件
     * @param pagenum  查第几页
     * @param pagesize 每页多少条数据
     * @return
     */
    public PageResult<Send> findSendByPage(String query, Integer pagenum, Integer pagesize) {
        // 此句放到dao查询之前
        PageHelper.startPage(pagenum, pagesize);

        // 执行sql, 对快递单号进行匹配
        if (query == null | Objects.equals(query, "")) {  // 防止query为null
            List<Send> sends = this.sendDao.find();

            sends.stream().forEach(send -> {
                send.setpUser(this.userDao.findUserById(send.getPostUser()));
                send.setrUser(this.userDao.findUserById(send.getReceiveUser()));
                send.seteCompany(this.companyDao.findCompanyById(send.getCompany()));
                send.setEx(this.expressDao.findById(send.getExpress()));
            });

            // pageHelper的pageInfo对象, 封装各种查询信息
            PageInfo<Send> pageInfo = new PageInfo<>(sends);

            // 返回自己封装的PageResult对象(包含总结果数,总页数 , user集合)
            return new PageResult<Send>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        } else {
            List<Send> sends = this.sendDao.findByQuery(query);

            sends.stream().forEach(send -> {
                send.setpUser(this.userDao.findUserById(send.getPostUser()));
                send.setrUser(this.userDao.findUserById(send.getReceiveUser()));
                send.seteCompany(this.companyDao.findCompanyById(send.getCompany()));
                send.setEx(this.expressDao.findById(send.getExpress()));
            });

            // pageHelper的pageInfo对象, 封装各种查询信息
            PageInfo<Send> pageInfo = new PageInfo<>(sends);

            // 返回自己封装的PageResult对象(包含总结果数,总页数 , user集合)
            return new PageResult<Send>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        }
    }

    @Transactional
    public void modifySend(Send send) {

        this.sendDao.modifySend(send);
    }

    @Transactional
    public void deleteSend(String id) {
        this.sendDao.deleteSend(id);
    }

    public Send findSendById(Integer id) {
        return this.sendDao.findSendById(id);
    }


}