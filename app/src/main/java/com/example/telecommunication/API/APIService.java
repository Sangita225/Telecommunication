package com.example.telecommunication.API;

import com.example.telecommunication.Notification.MyResponse;
import com.example.telecommunication.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA4uABeGQ:APA91bEQC-mH3c_iP4pZWPE96XoS_Xo9l-Sl226nHxdkJFV_qfOqW1olaHkVfKA-Tb-5YhjlUrU_bx18ZsJ4CYfOoXEncRpuTmvblOJBMLKh0tL6wq2M0rOlpzYZ-_ysoH5fKIOqjU8r"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
