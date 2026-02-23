package com.software.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.software.app.data.remote.network.BiliApiService
import com.software.app.data.remote.network.BiliLoginApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient() // 允许不严谨的 JSON 格式
            // 如果你需要忽略没有在 data class 中声明的未知枚举值等，可以在此配置
            .create()
    }


    /**
     * 基础 OkHttpClient
     * 作用：添加通用的 Referer 和 User-Agent，防止 B 站返回 403
     */
    @Provides
    @Singleton
    fun provideBaseOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Referer", "https://www.bilibili.com")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @BiliApiNetwork
    @Provides
    @Singleton
    fun provideBiliNetwork(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://app.bilibili.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @BiliPlayNetwork
    @Provides
    @Singleton
    fun provideBiliPlayNetwork(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.bilibili.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @BiliApiNetwork
    @Provides
    @Singleton
    fun provideBiliApiService(@BiliApiNetwork retrofit: Retrofit): BiliApiService {
        return retrofit.create(BiliApiService::class.java)
    }


    @BiliLoginNetwork
    @Provides
    @Singleton
    fun provideBLBLNetwork(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://passport.bilibili.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @BiliLoginNetwork
    @Provides
    @Singleton
    fun provideBLBLApiService(@BiliLoginNetwork retrofit: Retrofit): BiliLoginApiService {
        return retrofit.create(BiliLoginApiService::class.java)
    }
    @BiliPlayNetwork
    @Provides
    @Singleton
    fun provideBiliPlayApiService(@BiliPlayNetwork retrofit: Retrofit): BiliApiService {
        return retrofit.create(BiliApiService::class.java)
    }
}