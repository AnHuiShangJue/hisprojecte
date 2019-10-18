//package com.ahsj.userinfor.services.impl
//
//import com.ahsj.baseuserinfo.dao.*
//import com.ahsj.baseuserinfo.entity.*
//import com.ahsj.userinfor.services.RoleService
//import core.entity.PageBean
//import core.message.Message
//import core.message.MessageUtil
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//import utils.EmptyUtil
//
//@Service
//class RoleServiceImpl(@Autowired val roleMapper: RoleMapper,
//                      @Autowired val userRoleMapper: UserRoleMapper,
//                      @Autowired val rolePermissionMapper: RolePermissionMapper,
//                      @Autowired val menuMapper: MenuMapper,
//                      @Autowired val userInfoMapper: UserInfoMapper,
//                      @Autowired val organizationMapper : OrganizationMapper
//                      ) :RoleService {
//
//
//
//    /**
//     *@Description 新增或者更新
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-04-09
//     *@Time 14:36
//     **/
//    @Throws(Exception::class)
//    override fun saveOrUpdate(role: Role): Message {
//        if(EmptyUtil.isNullOrEmpty(role.id)){
//            //执行插入角色逻辑
//            if(!EmptyUtil.isNullOrEmpty(role.roleName)){
//                val check = roleMapper.selecByRoleName(role.roleName)
//                if(EmptyUtil.isNullOrEmpty(check)){
//                    roleMapper.insert(role)
//                    return MessageUtil.createMessage(true,"创建角色${role.roleName}成功!")
//                }else{
//                    return MessageUtil.createMessage(false,"创建角色${role.roleName}失败!该角色已经存在!")
//                }
//            }else{
//                return MessageUtil.createMessage(false,"创建角色${role.roleName}失败!输入信息不合法!")
//            }
//        }
//        //执行更新逻辑
//        else{
//            val check = roleMapper.selectByPrimaryKey(role.id)
//            //如果角色存在
//            if(!EmptyUtil.isNullOrEmpty(check)){
//                roleMapper.updateByPrimaryKeySelective(role)
//                return MessageUtil.createMessage(false,"更新角色${role.roleName}成功!")
//            }
//            //如果角色不存在
//            else{
//                return MessageUtil.createMessage(false,"更新角色${role.roleName}失败!该角色不存在!")
//            }
//        }
//    }
//
//
//    @Throws(Exception::class)
//    override fun selectRoleByUserId(id: Long): List<Role> {
//        print("role name is:"+ roleMapper.selectByPrimaryKey(id).roleName)
//       //userRoleMapper.selectRoleByUserId(id)
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    @Throws(Exception::class)
//    override fun delete(id: Array<Long>): Message {
//        for(index in 0..id.size -1 step 1){
//            roleMapper.deleteByPrimaryKey(id.get(index))
//        }
//        return MessageUtil.createMessage(true,"删除成功！")
//    }
//
//
//    /**
//     *@Description 给角色设置用户
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-04-09
//     *@Time 14:35
//     **/
//
//    @Throws(Exception::class)
//    override fun roleAddUser(userIds: Array<Long>, roleId: Long): Message {
//        val lstUserRole = ArrayList<UserRole>()
//        val userRole = UserRole();
//        var i = 0;
//        for(item in userIds){
//            val tmp  = UserRole()
//            tmp.roleId = roleId
//            tmp.userId = item
//
//            lstUserRole.add(tmp)
//
//        }
//        //先删除之前的关联数据
//        userRole.roleId = roleId
//        userRole.userIds = userIds
//        userRoleMapper.deleteBatch(userRole)
//        //再重新插入
//        userRoleMapper.insertBatch(lstUserRole)
//        return MessageUtil.createMessage(true,"用户添加成功！")
//    }
//
//    /**
//     *@Description  给角色设置权限
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-04-09
//     *@Time 14:35
//     **/
//
//    @Throws(Exception::class)
//    override fun setPermission(permissionIds: Array<String>, roleId: Long, organizationIds :Array<String>): Message {
//        val lstRolePermission = ArrayList<RolePermission>()
//        val organizationPerms = ArrayList<Organization>()
//        var menuPerms = ArrayList<Menu>()
//
//        for(item in permissionIds){
//            val tmp = RolePermission()
//            tmp.roleId = roleId
//            tmp.menuTreeId = item
//            lstRolePermission.add(tmp)
//        }
//        // 根据角色ID删除角色权限表
//        rolePermissionMapper.deletByRoleId(roleId)
//        // 批量插入
//        rolePermissionMapper.insertBatch(lstRolePermission)
//
////        //将所有的组织取出，和前端传来的值进行比较，若为选中的记录则将其加到准备拼接权限字段的list中
////        val organizationList = organizationMapper.selectAll()
////        for(item in organizationIds){
////            for (item1 in organizationList){
////                if(item == item1.treeId){
////                    organizationPerms.add(item1)
////                }
////            }
////        }
////        //区分部门科室等级
////        val organizationParent = ArrayList<Organization>()
////        val organizaitonChild = ArrayList<Organization>()
////
////        for(item in organizationPerms){
////            if(item.type == "1"){
////                organizationParent.add(item)
////            }else if(item.type == "2"){
////                organizaitonChild.add(item)
////            }
////        }
////        val orgMenuList = ArrayList<OrgMenu>()
////        for(item in organizationParent){
////            var flag = 0 //用于检测部门下是否存在科室
////            for (item1 in organizaitonChild){
////                if(item.treeId == item1.parentId){//如果目录下存在子科室
////                    val orgMenu = OrgMenu()
////                    orgMenu.orgId = item1.id
////                    orgMenu.permssionPermsId = item.perms + "." + item1.perms
////                    orgMenuList.add(orgMenu)
////                    flag = 1
////                }
////            }
////            //目录下不存在子科室
////            if (flag == 0){
////                val orgMenu = OrgMenu()
////                orgMenu.orgId = item.id
////                orgMenu.permssionPermsId = item.perms
////                orgMenuList.add(orgMenu)
////            }
////        }// 组织部分权限字段拼接结束
//
//
//        return MessageUtil.createMessage(true,"权限设置成功！")
//    }
//
//    override fun list(pageEntity: PageBean<Role>): PageBean<Role> {
//        pageEntity.setData(roleMapper.list(pageEntity))
//        return pageEntity
//    }
//
//    override fun selectById(id: Long): Role {
//        return roleMapper.selectByPrimaryKey(id);
//    }
//
//    override fun listMenuTree(sysRole: Role): List<TreeEntity> {
//        return menuMapper.listMenuTree(sysRole)
//    }
//
//    override fun updateInit(id: Long): Role {
//        return roleMapper.selectByPrimaryKey(id)
//    }
//
//    override fun listUserInfo(sysUserInfo: UserInfo): MutableList<SelectEntity> {
//        return userInfoMapper.listUserInfo(sysUserInfo)
//    }
//}