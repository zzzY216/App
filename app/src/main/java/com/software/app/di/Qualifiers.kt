package com.software.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BiliLoginNetwork

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BiliApiNetwork

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BiliPlayNetwork // 专门给 api.bilibili.com 使用