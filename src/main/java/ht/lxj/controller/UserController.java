package ht.lxj.controller;

import ht.lxj.bean.User;
import ht.lxj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    * 遍历所有数据
    * */
    @RequestMapping("/goList")
    public ModelAndView listUser() {
        ModelAndView mv = new ModelAndView();
        List<Object> listObj = userService.listAll();
        mv.addObject("listUser",listObj);
        mv.addObject("listUserSize",listObj.size());
        mv.setViewName("list");
        return mv;
    }

    /*
    * 根据用户id查询对象
    * */
    @RequestMapping("/getUser/{uid}")
    @ResponseBody
    public ModelAndView getById(@PathVariable("uid") Long uid){
        ModelAndView mv = new ModelAndView();
        Object objUser = userService.getById(uid);
        mv.addObject("user",objUser);
        mv.setViewName("edit");
        return  mv;
    }

    /*
    * 修改对象
    * */
    @RequestMapping("/goUpdate")
    public String updateUser(User user){
        userService.update(user);
        return "redirect:/user/goList.do";
    }

    /**
     * 根据id删除对象
     * */
    @RequestMapping("/goDelete/{uid}")
    public String goDelete (@PathVariable("uid") Long uid) {
        userService.removeById(uid);
        return "redirect:/user/goList.do";
    }

    /**
     * 打开新增用户窗口
     * */
    @RequestMapping("/goAddPage")
    public String goAddPage () {
        return "add";
    }

    /**
     * 增加新用户
     * */
    @RequestMapping("/goAdd")
    public String goAdd (User user) {
        userService.save(user);
        return "redirect:/user/goList.do";
    }
}
