package com.ahsj.hiseurekasecurityauthorizationserverone.utils

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @program: ssmtest
 * @description:MD5
 * @author:  chenzhicai
 * @create: 2018-08-28-10-55
 **/
class MD5Util {

    companion object {

        fun getMD5String(a: String, b: String): String {
            return toHexString(getMD5ByteArray(a, b)!!)
        }

        fun getMD5ShiftJISString(a: String, b: String): String {
            return toHexString(getMD5ShiftJISByteArray(a, b)!!)
        }

        fun getMD5ShiftJISByteArray(a: String, b: String): ByteArray? {
            var md: MessageDigest? = null
            var ret: ByteArray? = null

            try {
                md = MessageDigest.getInstance("MD5")
                md!!.update(a.toLowerCase().toByteArray())

                try {
                    md.update(b.toByteArray(charset("Shift-JIS")))
                } catch (var5: UnsupportedEncodingException) {
                    var5.printStackTrace()
                }

                ret = md.digest()
            } catch (var6: NoSuchAlgorithmException) {
                var6.printStackTrace()
            }

            return ret
        }

        fun getMD5ByteArray(a: String, b: String): ByteArray? {
            var md: MessageDigest? = null
            var ret: ByteArray? = null

            try {
                md = MessageDigest.getInstance("MD5")
                md!!.update(a.toLowerCase().toByteArray())
                md.update(b.toByteArray())
                ret = md.digest()
            } catch (var5: NoSuchAlgorithmException) {
                var5.printStackTrace()
            }

            return ret
        }

        private fun toHexString(x: ByteArray): String {
            val sb = StringBuffer(x.size * 2)

            for (i in x.indices) {
                val n = x[i]
                //kotlin中的位操作符都是Int包中附带的
                var nibble = n.toInt().shr(4 and 15)
                sb.append((if (nibble >= 10) 97 + nibble - 10 else 48 + nibble).toChar())
                //kotlin中的位操作符都是Int包中附带的
                nibble = n.toInt().and(15)
                sb.append((if (nibble >= 10) 97 + nibble - 10 else 48 + nibble).toChar())
            }

            return sb.toString()
        }

    }
}