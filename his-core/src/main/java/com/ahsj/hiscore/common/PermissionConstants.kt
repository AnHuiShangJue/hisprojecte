package com.ahsj.userinfor.common


/**
 *@Description 权限常量
 *@Params
 *@return
 *@Author chenzhicai
 *@Date 2019-03-15
 *@Time 16:37
**/
class PermissionConstants {
  companion object {


      const   val OR = "OR"

      const  val AND = "AND"


      /*
      *  管理员
      * */
      @JvmField
     val MANAGER = "manager"

      /** 角色管理---------------------------------------------------------------------------------------------------BEGIN**/

      // 菜单权限
      @JvmField
      val ROLE_MENU = "sys.role"
      // 新增
      @JvmField
      val ROLE_ADD = "sys.role.add"
      // 修改
      @JvmField
      val ROLE_UPDATE = "sys.role.update"
      // 删除
      @JvmField
      val ROLE_DELETE = "sys.role.delete"
      // 复制
      @JvmField
      val ROLE_COPY = "sys.role.copy"
      // 配置人员
      @JvmField
      val ROLE_SET_PEOPLE = "sys.role.setpeople"
      // 分配权限
      @JvmField
      val ROLE_SET_PRIVILEGE = "sys.role.setprivilege"

      @JvmStatic
      fun hasAuthority(permission:String):String{
          val stringBuffer = StringBuffer()
          stringBuffer.append("hasAuthority(${permission})")
          return stringBuffer.toString()
      }

      @JvmStatic
      fun hasAuthority(permissions:Array<String>,type:String):String{
          val permissionStr = StringBuffer("")
          for(i in 0.. permissions.size-1 step 1){
              if(i < permissions.size -1){
                  permissionStr.append(hasAuthority(permissions.get(i)))
                  when(type){
                      OR -> permissionStr.append(OR)
                      AND -> permissionStr.append(AND)
                  }
              }else{
                  permissionStr.append(hasAuthority(permissions.get(i)))
              }
          }
          return permissionStr.toString()
      }
  }
}