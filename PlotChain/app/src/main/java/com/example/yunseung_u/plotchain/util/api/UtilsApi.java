package com.example.yunseung_u.plotchain.util.api;

import com.example.yunseung_u.plotchain.application.PlotChainApplication;

/**
 * Created by fariz ramadhan.
 * website : www.farizdotid.com
 * github : https://github.com/farizdotid
 */
public class UtilsApi {

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return PlotChainApplication.getClient().create(BaseApiService.class);
    }
}
