package com.example.littletreetest.network

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*


class SSL {


    companion object {

        fun getSSLSocketFactory(): SSLSocketFactory {
            try {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, getTrustManager(), SecureRandom())
                return sslContext.socketFactory
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }


        fun getX509TrustManager(): X509TrustManager {
            return object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        }


        fun getTrustManager(): Array<TrustManager> {
            return arrayOf<TrustManager>(object : X509TrustManager {

                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })
        }


        //获取HostnameVerifier
        fun getHostnameVerifier(): HostnameVerifier {
            return HostnameVerifier { s, sslSession -> true }
        }

    }


}