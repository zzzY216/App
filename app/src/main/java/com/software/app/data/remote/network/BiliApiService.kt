package com.software.app.data.remote.network

import com.software.app.data.remote.model.blbl.BiliResponse
import com.software.app.data.remote.model.blbl.BiliVideoUrlResponse
import com.software.app.data.remote.model.blbl.PlayUrlData
import com.software.app.data.remote.model.blbl.RecommendData
import com.software.app.data.remote.model.blbl.UserInfo
import com.software.app.data.remote.model.blbl.VideoDetail
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BiliApiService {
    /**
     * @param idx 上一次返回列表中最后一条视频的 idx
     * @param pull true 为下拉刷新(获取最新), false 为上拉加载更多(获取下一页)
     * @param login_event 登录事件，翻页自增或固定传 2
     */
    @GET("/x/v2/feed/index")
    suspend fun getRecommendList(
        @Query("idx") idx: Long = 0,
        @Query("pull") pull: Boolean = true, // 翻页必须传 false
        @Query("login_event") loginEvent: Int = 1,
        @Query("flush") flush: Int = 0
    ): BiliResponse<RecommendData>

    /**
     * 获取视频播放流地址 (HTML5 接口，最易于在播放器中使用)
     * @param aid 视频 avid
     * @param cid 视频内部分片 id
     * @param qn 想要获取的画质 (16: 360P, 64: 720P, 80: 1080P)
     * @param type mp4 格式在 Android 端兼容性最好
     */
    @GET("/x/player/playurl")
    suspend fun getVideoPlayUrl(
        @Query("avid") aid: String,
        @Query("cid") cid: String,
        @Query("qn") qn: Int = 64,
        @Query("type") type: String = "mp4",
        @Query("platform") platform: String = "html5",
    ): BiliVideoUrlResponse<PlayUrlData>

    @GET("/x/web-interface/view")
    suspend fun getVideoDetail(
        @Query("aid") aid: String? = null,
        @Query("bvid") bvid: String? = null
    ): BiliResponse<VideoDetail>

    @GET("/x/web-interface/nav")
    suspend fun getUserInfo(@Header("Cookie") cookie: String): BiliResponse<UserInfo>
}