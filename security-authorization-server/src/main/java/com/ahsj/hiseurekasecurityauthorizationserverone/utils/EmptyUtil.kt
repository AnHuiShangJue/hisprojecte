package com.ahsj.hiseurekasecurityauthorizationserverone.utils
/**
 * @program: praduationppmsystem
 * @description:空值工具
 * @author:  chenzhicai
 * @create: 2018-08-28-10-32
 **/
class EmptyUtil {
    companion object {
        fun isNullOrEmpty(str: String?): Boolean {
            return str == null || "" == str.trim { it <= ' ' } || "null".equals(str, ignoreCase = true) || "undefined".equals(str, ignoreCase = true)
        }

        fun isNullOrEmpty(str: StringBuffer?): Boolean {
            return str == null || str.toString().trim { it <= ' ' }.length == 0
        }

        fun isNullOrEmpty(str: Array<String>?): Boolean {
            return str == null || str.size == 0
        }

        fun isNullOrEmpty(longTime: Long?): Boolean {
            return longTime == null
        }

        fun isNullOrEmpty(obj: Any?): Boolean {
            return obj == null || "" == obj
        }

        fun isNullOrEmpty(obj: Array<Any>?): Boolean {
            return obj == null || obj.size == 0
        }

        fun isNullOrEmpty(list: List<*>?): Boolean {
            return list == null || list.isEmpty()
        }

        fun isNullOrEmpty(map: Map<*, *>?): Boolean {
            return map == null || map.isEmpty()
        }
    }
}