package com.example.littletreetest.network

import com.example.littletreetest.BuildConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class OkHttpFactory {


//    val HEADER_CONTENT_TYPE = "Content-Type: application/json;charset=utf-8"
//
//    val HEADER_TRAN_CODE_PREFIX = "tranCode: "

    //连接超时
    private val CONNECT_TIMEOUT = 60L

    //阅读超时
    private val READ_TIMEOUT = 30L

    //写入超时
    private val WRITE_TIMEOUT = 30L

    //设缓存有效期为1天
    private val CACHE_STALE_SEC = (60 * 60 * 24 * 1).toLong()

    //查询缓存的Cache-Control设置，为only-if-cached时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    val CACHE_CONTROL_CACHE = "only-if-cached, max-stale=$CACHE_STALE_SEC"

    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    val CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=10"

    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    private val AVOID_HTTP403_FORBIDDEN =
        "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11"


    private val requestLogIntercepter = Interceptor { chain ->
        val request = chain.request()
        val requestBody = request.body()
        val buffer = Buffer()
        requestBody?.writeTo(buffer)
        val charset = Charset.forName("UTF-8")
        val bodyStr = buffer.readString(charset)
        val str = formatJson(bodyStr)
        Timber.d("Sended Request:\n $str")
        chain.proceed(request)
    }


    /**
     * 日志拦截器
     */
    private val responseLogIntercepter = Interceptor { chain ->
        val request = chain.request()
//        Timber.d("LittleTreeTest_OKHttp: Send Request json string\n" +
////                " ${gson.toJson(request)}\n"+
//                " ${gson.toJson(request)}\n"
//        )
        val response = chain.proceed(request)
        val isSuccess = if (response.isSuccessful) "true" else "false"
        Timber.d(isSuccess)
        val body = response.body()
        val source = body!!.source()
        source.request(java.lang.Long.MAX_VALUE)
        val buffer = source.buffer
        var charset: Charset? = Charset.defaultCharset()
//        val contentType = body.contentType()
//        if (contentType != null) {
//            charset = contentType.charset()
//        }
        val bodyStr = buffer.clone().readString(charset!!)
//        val str = bodyString
////            .replace("\\","")
//            .replace("\"","")
////            .replace("\"","")
////            .replace("{", "{\n")
////            .replace("}", "}\n")
//            .replace("[", "\n[")
//            .replace("]", "]\n")
//            .replace(",", ",\n")

        val str = formatJson(bodyStr)
        Timber.d("Received Response:\n $str")
//        Logger.w(String.format("Received response json string $bodyString"))
        response
    }


    fun getOkHttpClient(): OkHttpClient {
        val okHttpConfig = OkHttpClient.Builder()
//            .eventListenerFactory(OkHttpEventListener.FACTORY)
//            .dns(OkHttpDNS.getInstance(context))
            .sslSocketFactory(SSL.getSSLSocketFactory(), SSL.getX509TrustManager())
            .hostnameVerifier(SSL.getHostnameVerifier())
//            .cache(cache)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//            .addInterceptor(mRewriteCacheControlInterceptor)
//            .addInterceptor(mRewriteCacheControlInterceptor)
            .addInterceptor(UserHeadInterceptor())

        if (BuildConfig.DEBUG) {
            okHttpConfig
                .addInterceptor(requestLogIntercepter)
                .addNetworkInterceptor(responseLogIntercepter)
        }

        return okHttpConfig.build()
    }


    fun formatJson(jsonStr: String?): String {
        if (null == jsonStr || "" == jsonStr) return ""
        val sb = StringBuilder()
        var last = '\u0000'
        var current = '\u0000'
        var indent = 0
        for (i in 0 until jsonStr.length) {
            last = current
            current = jsonStr[i]
            //遇到{ [换行，且下一行缩进
            when (current) {
                '{', '[' -> {
                    sb.append(current)
                    sb.append('\n')
                    indent++
                    addIndentBlank(sb, indent)
                }
                //遇到} ]换行，当前行缩进
                '}', ']' -> {
                    sb.append('\n')
                    indent--
                    addIndentBlank(sb, indent)
                    sb.append(current)
                }
                //遇到,换行
                ',' -> {
                    sb.append(current)
                    if (last != '\\') {
                        sb.append('\n')
                        addIndentBlank(sb, indent)
                    }
                }
                else -> sb.append(current)
            }
        }
        return sb.toString()
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     */
    private fun addIndentBlank(sb: StringBuilder, indent: Int) {
        for (i in 0 until indent) {
            sb.append('\t')
        }
    }

    fun getRetrofit(okhttpClient: OkHttpClient, gson: Gson): Retrofit {
//        return retrofit.create(clazz);
        return Retrofit.Builder()
            .baseUrl("https://www.baidu.com")
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


}