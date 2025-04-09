package zxf.springboot.service.c.support.retrofit;

import retrofit2.Call;
import retrofit2.http.*;
import zxf.springboot.service.b.feign.model.CreateUserRequest;
import zxf.springboot.service.b.feign.model.CreateUserResponse;
import zxf.springboot.service.b.feign.model.FindUserByIdResponse;

public interface ServiceApi {

    @POST("/users")
    @Headers({"Content-Type: application/json"})
    Call<CreateUserResponse> createUser(@Body CreateUserRequest request);

    @GET("/users/{userId}")
    @Headers({"Content-Type: application/json"})
    Call<FindUserByIdResponse> findUserById(@Path("userId") String userId, @Header("X-AGE") Integer age);
}