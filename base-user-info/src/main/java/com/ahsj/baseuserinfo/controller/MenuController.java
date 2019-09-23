package com.ahsj.baseuserinfo.controller;

import com.ahsj.baseuserinfo.entity.Menu;
import com.ahsj.baseuserinfo.entity.MenuOperate;
import com.ahsj.baseuserinfo.entity.TreeEntity;
import com.ahsj.baseuserinfo.services.MenuService;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/menu/")
public class MenuController extends BaseOAController {

    @Autowired
    private MenuService menuService;


    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.Menu>
     * @Description 新增或者更新
     * @Params [sysMenu]
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:40
     **/
    @RequestMapping("treeCode.ahsj")
    @ResponseBody
    public List<TreeEntity> treeCode(Integer lv, String id) throws Exception {
        if (lv == null) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setId("1001");
            treeEntity.setName("智慧园区系统菜单");
            treeEntity.setIsParent(true);
            treeEntity.setOpen(true);
            List<TreeEntity> lst = new ArrayList<TreeEntity>();
            lst.add(treeEntity);
            return lst;
        }
        return menuService.selectTreeCode(id);
    }

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.Menu>
     * @Description list初始化
     * @Params [sysMenu]
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:42
     **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView UserIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/sys/menu/list");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 查询
     * @Params [id, parentId, parentName]
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:40
     **/
    @RequestMapping("list.ahsj")
    @ResponseBody
    public PageBean<Menu> list(Menu sysMenu) throws Exception {
        PageBean<Menu> pageEntity = new PageBean<Menu>();
        pageEntity.setParameter(sysMenu);
        return menuService.list(pageEntity);
    }

    /**
     * @return core.message.Message
     * @Description 更新初期化
     * @Params [sysMenu]
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:39
     **/
    @RequestMapping("update/index.ahsj")
    public ModelAndView updateInit(Long id, String parentId, String parentName, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/sys/menu/update");
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            Menu sysMenu = new Menu();
            sysMenu.setParentId(parentId);
            sysMenu.setParentName(parentName);
            modelAndView.addObject("sysMenu", sysMenu);
        } else {
            modelAndView.addObject("sysMenu", menuService.updateInit(id));
        }
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 新增或者更新
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:39
     **/
    @RequestMapping("saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(Menu sysMenu) throws Exception {
        return menuService.saveOrUpdate(sysMenu);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 删除
     * @Params [id, permission]
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:39
     **/
    @RequestMapping("delete.ahsj")
    @ResponseBody
    public Message delete(Long[] id) throws Exception {
        return menuService.delete(id);
    }

    /**
     * @return core.message.Message
     * @Description 添加功能点初始化
     * @Params [operate, operatePermission, menuId]
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:39
     **/
    @RequestMapping("addOperate/index.ahsj")
    public ModelAndView addOperateInit(Integer id, String permission,String token) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> lst = menuService.addOperateInit(id);
        modelAndView.addObject("operateList", lst.get("operateList"));
        modelAndView.addObject("menuOperate", lst.get("menuOperate"));
        modelAndView.addObject("permission", permission);
        modelAndView.addObject("menuId", id);
        modelAndView.addObject("token", token);
        modelAndView.setViewName("backend/sys/menu/menu_add_operate");
        return modelAndView;
    }

    /**
     * @return
     * @Description 添加操作
     * @Params
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:39
     **/
    @RequestMapping("addOperate/saveOrUpdate.ahsj")
    @ResponseBody
    public Message addOperate(Integer[] operate, String[] operatePermission, Integer menuId) throws Exception {
        return menuService.addOperate(operate, operatePermission, menuId);
    }

    /**
     * @return
     * @Description 生成菜单
     * @Params
     * @Author chenzhicai
     * @Date 2019-04-15
     * @Time 0:39
     **/
    @RequestMapping("/layout/lstMenu.ahsj")
    @ResponseBody
    Map<String, String> listMenu(HttpServletRequest request) {
        List<Menu> lstMenu = menuService.listSysMenu(getId());
        HashMap<String, String> menuMap = new HashMap<String, String>();
        String ctx = "/accounts";
        StringBuffer temp = new StringBuffer();
        String eachData = null;
        Menu sysMenu = null;
        StringBuffer leftMenuTemp = null;
        for (int i = 0; i < lstMenu.size(); i++) {
            sysMenu = lstMenu.get(i);
            if (!EmptyUtil.Companion.isNullOrEmpty(sysMenu.getServiceName())) {
                ctx = "/" + sysMenu.getServiceName();
            }
            if (sysMenu.getParentId().length() == 4) {
                temp.append(" <li class=\"bold\"><a class=\"collapsible-header waves-effect waves-cyan \" href=\"#\"><i class=\"material-icons\">");
                temp.append(sysMenu.getIconCls());
                temp.append("</i><span class=\"menu-title\" data-i18n=\"\">");
                temp.append(sysMenu.getMenuName());
                temp.append("</span></a>");
                temp.append("<div class=\"collapsible-body\">");
                temp.append(" <ul class=\"collapsible collapsible-sub\" data-collapsible=\"accordion\">");
                temp.append("{");
                temp.append(sysMenu.getTreeId());
                temp.append("}");
                temp.append("</ul>");
                temp.append("</div>");
                temp.append("</li>");
            } else {
                leftMenuTemp = new StringBuffer();
//                leftMenuTemp = leftMenuTemp.append("<li class=\"bold waves-effect waves-cyan\">");
                leftMenuTemp = leftMenuTemp.append("<li >");
                leftMenuTemp.append("<a href=\"javascript:;\"class=\"collapsible-body\"rel=\"");
                leftMenuTemp.append(ctx);
                leftMenuTemp.append(sysMenu.getMenuUrl());
                leftMenuTemp.append("\"data-i18n=\"\">");
                leftMenuTemp.append("<i class=\"material-icons\">");
                leftMenuTemp.append(sysMenu.getIconCls());
                leftMenuTemp.append("</i><span>");
                leftMenuTemp.append(sysMenu.getMenuName());
                leftMenuTemp.append("</span></a>");
                leftMenuTemp.append("</li>");
                leftMenuTemp.append("{");
                leftMenuTemp.append(sysMenu.getParentId());
                leftMenuTemp.append("}");
                eachData = temp.toString();
                eachData = eachData.replace("{" + sysMenu.getParentId() + "}", leftMenuTemp.toString());
                temp = new StringBuffer(eachData);
            }
        }
        String leftMenuStr = temp.toString().replaceAll("\\{+[0-9]*\\}", "");
        menuMap.put("leftMenu", leftMenuStr);
        return menuMap;
    }

    /**
     *@Description 内部访问菜单接口
     *@Params [id]
     *@return java.util.List<com.ahsj.userinfor.entity.Menu>
     *@Author chenzhicai
     *@Date 2019/7/1
     *@Time 3:36 PM
    **/
    @RequestMapping("info/inner/listSysMenu.ahsj")
    @ResponseBody
    List<Menu> listSysMenu(@RequestParam("id") Long id) throws Exception {
        return menuService.listSysMenu(id);
    }
    /**
     *@Description 内部访问菜单操作权限
     *@Params [id]
     *@return java.util.List<com.ahsj.userinfor.entity.MenuOperate>
     *@Author chenzhicai
     *@Date 2019/7/1
     *@Time 3:36 PM
    **/
    @RequestMapping("info/inner/listSysMenuOperate.ahsj")
    @ResponseBody
    List<MenuOperate> listSysMenuOperate(@RequestParam("id") Long id) throws Exception {
        return menuService.listSysMenuOperate(id);
    }
}
