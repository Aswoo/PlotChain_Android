package com.example.yunseung_u.plotchain.util.api;

import com.example.yunseung_u.plotchain.model.Episode;
import com.example.yunseung_u.plotchain.model.RegisterNovel;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.EpisodeGetResponse;
import com.example.yunseung_u.plotchain.service.response.NovelGetResponse;
import com.example.yunseung_u.plotchain.service.response.NovelHeartResponse;
import com.example.yunseung_u.plotchain.service.response.NovelIntroResponse;
import com.example.yunseung_u.plotchain.service.response.ReadGetResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {


    @Headers("Content-type: application/json; charset=utf-8")
    @POST("/member")
    Call<ResponseBody> registerJSONRequest(@Body User user);

    @Headers("Content-type: application/json; charset=utf-8")
    @POST("/member/session")
    Call<ResponseBody> loginJSONRequest(@Body User user);

    @Headers("Content-type: application/json; charset=utf-8")
    @GET("/write/register")

    @DELETE("/member/session")
    Call<ResponseBody> logoutJSONRequest(@Body String session);

    @Headers("Content-type: application/json; charset=utf-8")
    @GET("/novel")
    Call<NovelGetResponse> getRecentlyNovel(@Query("session") String session,
                                            @Query("mode") String mode,
                                            @Query("position") Integer number);


    @Headers("Content-type: application/json; charset=utf-8")
    @GET("/novel")
    Call<NovelGetResponse> getRankedNovel(@Query("session") String session,
                                            @Query("mode") String mode,
                                            @Query("position") Integer number);


    @Headers("Content-type: application/json; charset=utf-8")
    @GET("/novel/list")
    Call<NovelGetResponse> getMyNovelList(@Query("session") String session,
                                      @Query("nickname") String name);



    @Headers("Content-type: application/json; charset=utf-8")
    @POST("/novel")
    Call<ResponseBody> registerNovel(
            @Body RegisterNovel registerNovel,@Query("session") String session);

    @Headers("Content-type: application/json; charset=utf-8")
    @POST("/novel/{id}")
    Call<ResponseBody> registerEpisode(
                                       @Path("id") String id,
                                       @Body Episode episode,
                                       @Query("session") String session);

    @GET("/novel/{id}")
    Call<NovelIntroResponse> getNovelIntro(
            @Path("id") String id,
            @Query("session") String session);

    @GET("/novel/{novelid}/{eid}")
    Call<EpisodeGetResponse> getEpisode(
            @Path("novelid") String novelid,
            @Path("eid") String eid,
            @Query("session") String session);

    @GET("/member/history")
    Call<ReadGetResponse> getReadNovel(
            @Query("session") String session);

    // heart 조절

    @POST("/heart")
    Call<ResponseBody> reqHeart(
            @Query("id") String id,
            @Query("session") String session
    );
    @DELETE("/heart")
    Call<ResponseBody> deleteHeart(
        @Query("id") String id,
        @Query("session") String session
    );

    @GET("/heart/history")
    Call<NovelHeartResponse> getNovelHeartShare(@Query("session") String session);

}
