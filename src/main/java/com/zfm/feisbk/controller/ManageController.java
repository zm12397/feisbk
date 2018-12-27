package com.zfm.feisbk.controller;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.TableResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.UserService;
import com.zfm.feisbk.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JTR
 * @date 2018/12/24 14:22
 */

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     * @param page
     * @param rows
     * completed
     */
    @RequestMapping(path = "/listuser.action", method = RequestMethod.POST)
    public TableResultDTO getUserList(@RequestParam("page") int page, @RequestParam(value = "rows", defaultValue = "10") int rows){
        TableResultDTO result = new TableResultDTO("9999","request error",null, 0L);
        try {
            Pageable pageable = new PageRequest(page, rows);
            Page<UserDO> userList = userService.getUserList(pageable);
            if (userList.hasContent()) {
                result.setCode("0000");
                result.setMessage("successful");
                result.setRows(userList);
                result.setTotal(userService.getUserSize());
            }else {
                result.setMessage("no more users");
                result.setRows(null);
                result.setTotal(userService.getUserSize());
            }
        }catch (CustomerException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @param name
     * @param tel
     * @param email
     * @param address
     * @param sex
     * @param birthday
     * @param desciption
     */
    @RequestMapping(path = "/adduser.action", method = RequestMethod.POST)
    public NormalResultDTO addUser(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("name") String name,
                                    @RequestParam("tel") String tel,
                                    @RequestParam("email") String email,
                                    @RequestParam("address") String address,
                                    @RequestParam("sex") Short sex,
                                    @RequestParam("birthday") Long birthday,
                                    @RequestParam("description") String desciption) {
        NormalResultDTO result = new NormalResultDTO("9999","not unique username",null);
        try {
            NormalResultDTO verify = userService.verify(username);
            if (verify.getCode().equals("0000")) {
                result.setCode("0000");
                result.setMessage("successful");
            }else {
                return result;
            }
            UserDO user = new UserDO();
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setTel(tel);
            user.setEmail(email);
            user.setAddress(address);
            user.setSex(sex);
            user.setBirthday(birthday);
            user.setDescription(desciption);
            Long current = TimeUtils.getNow();
            user.setCreateTime(current);
            user.setModifyTime(current);
            Short s = 1;
            user.setState(s);
            NormalResultDTO addUser = userService.addUser(user);
            if (!addUser.getCode().equals("0000")) {
                result.setCode("9999");
                result.setMessage("register failed");
            }
        }catch (CustomerException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 修改用户信息
     * @param username
     * @param password
     * @param name
     * @param tel
     * @param email
     * @param address
     * @param sex
     * @param birthday
     * @param desciption
     */
    @RequestMapping(value = "/updateuser.action", method = RequestMethod.POST)
    public NormalResultDTO modifyUser(@RequestParam("id") Long id,
                                      @RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "password", required = false) String password,
                                      @RequestParam(value = "name",required = false) String name,
                                      @RequestParam(value = "tel", required = false) String tel,
                                      @RequestParam(value = "email",required = false) String email,
                                      @RequestParam(value = "address", required = false) String address,
                                      @RequestParam(value = "sex", required = false) Short sex,
                                      @RequestParam(value = "birthday", required = false) Long birthday,
                                      @RequestParam(value = "description", required = false) String description,
                                      @RequestParam(value = "state", required = false) Short state) {
        NormalResultDTO result = new NormalResultDTO("9999","not such user",null);
        try {
            Long current = TimeUtils.getNow();
            UserDO user = userService.findUserById(id);
            result.setCode("0000");
            result.setMessage("successful");
            if (username != null) {
                user.setUsername(username);
            }
            if (password != null) {
                user.setPassword(password);
            }
            if (name != null) {
                user.setName(name);
            }
            if (tel != null) {
                user.setTel(tel);
            }
            if (email != null) {
                user.setEmail(email);
            }
            if (address != null) {
                user.setAddress(address);
            }
            if (sex != null) {
                user.setSex(sex);
            }
            if (birthday != null) {
                user.setBirthday(birthday);
            }
            if (description != null) {
                user.setDescription(description);
            }
            if (state != null) {
                user.setState(state);
            }
            user.setModifyTime(current);
            userService.addUser(user);
        }catch (CustomerException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
