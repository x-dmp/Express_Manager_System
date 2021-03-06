package cn.edu.cuit.service;

import cn.edu.cuit.dao.UserDao;
import cn.edu.cuit.domain.PageResult;
import cn.edu.cuit.domain.User;
import cn.edu.cuit.utils.FastDFSClientUtil;
import cn.edu.cuit.utils.Md5EncodingUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    // 允许的文件类型
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static final String TRACKER_SERVER = "http://112.124.12.60/";

    @Autowired
    private UserDao userDao;

    @Transactional  // 添加事务
    public void saveUser(User user) {
        // 密码加密存储
        user.setPassword(Md5EncodingUtil.encode(user.getPassword()));
        user.setJoinTime(new Date());
        this.userDao.saveUser(user);
    }

    /**
     * 数据中存的是加密后的密码, 应该将加密后的password为条件进行查询,并且用户是管理员
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        User user = this.userDao.findByUsernameAndPassword(username, Md5EncodingUtil.encode(password));
        return user;
    }

    /**
     *
     * @param query 查询条件
     * @param pagenum 查第几页
     * @param pagesize 每页多少条数据
     * @return
     */
    public PageResult<User> findUserByPage(String query, Integer pagenum, Integer pagesize) {
        // 此句放到dao查询之前
        PageHelper.startPage(pagenum, pagesize);

        // 执行sql, 对用户名进行模糊匹配
        if (query == null) {  // 防止query为null
            query = "";
        }
        System.out.println(query);
        List<User> users = this.userDao.findByQuery("%" + query + "%");

        // pageHelper的pageInfo对象, 封装各种查询信息
        PageInfo<User> pageInfo = new PageInfo<>(users);

        // 返回自己封装的PageResult对象(包含总结果数,总页数 , user集合)
        return new PageResult<User>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }

    @Transactional
    public void modifyUser(User user) {
        // 对密码加密
//        user.setPassword(Md5EncodingUtil.encode(user.getPassword()));
        this.userDao.modifyUser(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        this.userDao.deleteUser(id);
    }

    public User findUserById(Integer id) {
        return this.userDao.findUserById(id);
    }

    /**
     * 给对应id的用户上传头像图片
     * @param file 文件数据
     * @param id 用户id
     * @return
     */
    public String uploadAvatarImage(MultipartFile file, Integer id) {
        // 获取文件的原始文件名
        String originalFilename = file.getOriginalFilename();

        // 检验文件后缀名是否合法(文件的媒体类型即MIME类型)
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {  // 文件后缀名不合法
            LOGGER.info("文件类型不合法: {}", originalFilename);
            return null;
        }

        try {  // 后缀名不能说明一个文件的类型, 得检验文件内容是否为图片的格式
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("文件内容不合法: {}", originalFilename);
                return null;
            }

            // 保存图片
            // 文件后缀名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            //上传文件
            String path = FastDFSClientUtil.uploadFile(file.getBytes(), suffix, null);
            String url = TRACKER_SERVER + path;  // 图片访问的url

            this.userDao.modifyUserAvatarUrl(id, url);

            LOGGER.info("用户id={}的头像保存成功: {}", id, originalFilename);
            return url;
        } catch (Exception e) {
            LOGGER.info("服务器内部错误: {}", originalFilename);
        }

        return null;
    }
}
