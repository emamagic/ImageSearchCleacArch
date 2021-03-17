package com.example.imagesearchapp.util

/*

class PicassoTrustAll private constructor(context: Context) {
    companion object {
        private var mInstance: Picasso? = null
        fun getInstance(context: Context): Picasso? {
            if (mInstance == null) {
                PicassoTrustAll(context)
            }
            return mInstance
        }
    }

    init {
        val client = OkHttpClient()
        client.hostnameVerifier = HostnameVerifier { s, sslSession -> true }
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                x509Certificates: Array<X509Certificate?>?,
                s: String?
            ) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                x509Certificates: Array<X509Certificate?>?,
                s: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, SecureRandom())
            client.sslSocketFactory = sc.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mInstance = Picasso.Builder(context)
            .downloader(OkHttpDownloader(client))
            .listener { picasso, uri, exception -> Log.e("PICASSO", "$exception") }.build()
    }

    */
/*
    *
    *  implementation 'com.squareup.okhttp:okhttp:2.7.5'
       implementation 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
       implementation 'com.squareup.picasso:picasso:2.4.0'
    * *//*

}*/
