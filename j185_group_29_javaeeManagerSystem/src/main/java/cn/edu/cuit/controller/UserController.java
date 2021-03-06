package cn.edu.cuit.controller;

import cn.edu.cuit.domain.PageResult;
import cn.edu.cuit.domain.User;
import cn.edu.cuit.service.UserService;
import cn.edu.cuit.utils.ResponseInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户
     * 以User接受json对象，user属性与json对象键对应
     * @param user
     * @return
     */
    @PostMapping
    public Map<String, Object> saveUser(@RequestBody User user) {
        this.userService.saveUser(user);

        return ResponseInfoUtil.responseDataAndMeta(user, "用户创建成功", HttpStatus.CREATED);
    }

    /**
     * 登陆验证
     * @param user 将json对象封装为user对象(只含username和password属性)
     * @return
     */
    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User user) {
        user = this.userService.login(user.getUsername(), user.getPassword());
        if (user == null) {  // unauthorized 未经许可的
            return ResponseInfoUtil.responseDataAndMeta("", "权限不足/用户名或密码错误", HttpStatus.UNAUTHORIZED);
        }

        return ResponseInfoUtil.responseDataAndMeta(user, "登陆成功", HttpStatus.OK);
    }

    /**
     * 用户分页查询
     * @param query 用户名(需要对用户名进行模糊匹配)
     * @param pagenum 需要查询的第几页
     * @param pagesize 每页多少条数据
     * @return
     */
    @GetMapping
    public Map<String, Object> queryUserByPage(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
            @RequestParam(name = "pagesize", defaultValue = "5") Integer pagesize) {

        if (pagenum <= 0 || pagesize <= 0) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }

        // 包含 数据总条数,总页数,查询到的User集合
        PageResult<User> pageResult = this.userService.findUserByPage(query, pagenum, pagesize);

        if (CollectionUtils.isEmpty(pageResult.getItems())) {  // 查询结果集为空(spring提供的工具类检查集合是否为空)
            return ResponseInfoUtil.responseDataAndMeta("", "没有结果", HttpStatus.NOT_FOUND);
        }
        return ResponseInfoUtil.responseDataAndMeta(pageResult, "获取成功", HttpStatus.OK);
    }

    /**
     * 修改用户(包含id,username,password,status,power)
     * @return
     */
    @PutMapping
    public Map<String, Object> modifyUser(@RequestBody User user) {
        this.userService.modifyUser(user);

        return ResponseInfoUtil.responseDataAndMeta("", "更新成功", HttpStatus.OK);
    }

    /**
     * 根据id删除用户，restful参数{xxx}利用@PathVariable接受
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Map<String, Object> modifyUser(@PathVariable(name = "id", required = true) Integer id) {
        if (id < 0) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }
        this.userService.deleteUser(id);

        return ResponseInfoUtil.responseDataAndMeta("", "删除成功", HttpStatus.OK);
    }

    /**
     * 根据id查询用户，restful参数{xxx}利用@PathVariable接受
     * @return
     */
    @GetMapping("{id}")
    public Map<String, Object> findUser(@PathVariable(name = "id", required = true) Integer id) {
        if (id < 0) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.findUserById(id);
        if (user == null) {
            return ResponseInfoUtil.responseDataAndMeta("", "查询结果集为空", HttpStatus.NOT_FOUND);
        }
        return ResponseInfoUtil.responseDataAndMeta(user, "查询成功", HttpStatus.OK);
    }


    /**
     * 给对应id的用户上传头像图片
     * @param file 文件数据
     * @param id 用户id
     * @return
     */
    @PostMapping("{id}/avatar")
    public Map<String, Object> userAvatorUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) {
        // 调用service返回存储图片的url
        String url = this.userService.uploadAvatarImage(file, id);

        if (url == null || url.length() <= 0) {
            // 文件不合法/参数不合法/服务器内部错误
            return ResponseInfoUtil.responseDataAndMeta("", "上传图片失败", HttpStatus.BAD_REQUEST);
        }
        return ResponseInfoUtil.responseDataAndMeta(url, "上传图片成功", HttpStatus.OK);
    }

}
