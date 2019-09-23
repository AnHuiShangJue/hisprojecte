package com.ahsj.translate

import java.net.URL
import java.net.URLEncoder
import java.nio.charset.Charset
class GoogleTranslate{

    object demo{


        @JvmStatic
        open fun main(args:Array<String>){
            println(translate("别让这么应景的天空放晴啊"))
            println(translate("空気を読んだ空晴れないでよ"))

            println(translate("别降下这么看场合的雨啊"))
            println(translate("空気を読んだ雨降らないでよ"))

            println(translate("He sits no sure that sits too high"))
            println(translate("高处不胜寒", target = "km"))
        }
    }

    fun translate(text: String, source: String = "auto", target: String = "zh-CN"): Pair<String, String> {
        val textChecked = if (text.isBlank()) "null" else URLEncoder.encode(text, "utf8")
        val userAgent = "Mozilla/5.0"
        val url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=$source&tl=$target&dt=t&q=$textChecked"
        val connection = URL(url).openConnection().apply { setRequestProperty("User-Agent", userAgent) }
        val raw = connection.getInputStream().use { it.readBytes() }.toString(Charset.forName("utf8"))
        val p1 = raw.indexOf("\",\"")
        val p2 = raw.indexOf("\",", p1 + 1)
        val result = raw.substring(4, p1)
        val query = raw.substring(p1 + 3, p2)
        return Pair(result, query)
    }
   companion object {

       fun translate(text: String, source: String = "auto", target: String = "zh-CN"): Pair<String, String> {
           val textChecked = if (text.isBlank()) "null" else URLEncoder.encode(text, "utf8")
           val userAgent = "Mozilla/5.0"
           val url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=$source&tl=$target&dt=t&q=$textChecked"
           val connection = URL(url).openConnection().apply { setRequestProperty("User-Agent", userAgent) }
           val raw = connection.getInputStream().use { it.readBytes() }.toString(Charset.forName("utf8"))
           val p1 = raw.indexOf("\",\"")
           val p2 = raw.indexOf("\",", p1 + 1)
           val result = raw.substring(4, p1)
           val query = raw.substring(p1 + 3, p2)
           return Pair(result, query)
       }
   }
}