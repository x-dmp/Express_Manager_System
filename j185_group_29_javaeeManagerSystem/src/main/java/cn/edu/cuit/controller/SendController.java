package cn.edu.cuit.controller;

import cn.edu.cuit.domain.PageResult;
import cn.edu.cuit.domain.Send;
import cn.edu.cuit.domain.User;
import cn.edu.cuit.service.SendService;
import cn.edu.cuit.utils.ResponseInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("sends")
public class SendController {

    @Autowired
    private SendService sendService;

    /**
     * 保存快递资料
     * 以Send接受json对象，Send属性与json对象键对应
     *
     * @param send
     * @return
     */
    @PostMapping
    public Map<String, Object> saveUser(@RequestBody Send send) {
        send.setUuid(UUID.randomUUID().toString().replace("-", ""));
        this.sendService.saveSend(send);

        return ResponseInfoUtil.responseDataAndMeta("", "快递资料创建成功", HttpStatus.CREATED);
    }


    /**
     * 用户分页查询
     *
     * @param query    快递单号
     * @param pagenum  需要查询的第几页
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

        // 包含 数据总条数,总页数,查询到的Send集合
        PageResult<Send> pageResult = this.sendService.findSendByPage(query, pagenum, pagesize);

        if (CollectionUtils.isEmpty(pageResult.getItems())) {  // 查询结果集为空(spring提供的工具类检查集合是否为空)
            return ResponseInfoUtil.responseDataAndMeta("", "没有结果", HttpStatus.NOT_FOUND);
        }
        return ResponseInfoUtil.responseDataAndMeta(pageResult, "获取成功", HttpStatus.OK);
    }

    /**
     * 修改快递(包含price,post_user,receive_user,company,position,send_time,from_address,to_address)
     *
     * @return
     */
    @PutMapping
    public Map<String, Object> modifySend(@RequestBody Send send) {
        this.sendService.modifySend(send);

        return ResponseInfoUtil.responseDataAndMeta("", "更新成功", HttpStatus.OK);
    }

    /**
     * 根据id删除快递，restful参数{xxx}利用@PathVariable接受
     *
     * @param uuid
     * @return
     */
    @DeleteMapping("{uuid}")
    public Map<String, Object> modifySend(@PathVariable(name = "uuid", required = true) String uuid) {
        if (Objects.equals(uuid, "")) {
            return ResponseInfoUtil.responseDataAndMeta("", "参数错误", HttpStatus.BAD_REQUEST);
        }
        this.sendService.deleteSend(uuid);

        return ResponseInfoUtil.responseDataAndMeta("", "删除成功", HttpStatus.OK);
    }
}


