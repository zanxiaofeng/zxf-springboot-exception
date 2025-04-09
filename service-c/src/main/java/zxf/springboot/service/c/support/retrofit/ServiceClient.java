package zxf.springboot.service.c.support.retrofit;

import retrofit2.Response;
import zxf.springboot.service.b.feign.model.CreateUserRequest;
import zxf.springboot.service.b.feign.model.CreateUserResponse;
import zxf.springboot.service.b.feign.model.FindUserByIdResponse;

import java.io.IOException;

public class ServiceClient {

    private final ServiceApi serviceApi;

    public ServiceClient(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        try {
            Response<CreateUserResponse> response =
                    serviceApi.createUser(request).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw toException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FindUserByIdResponse findUserById(String userId, Integer age) {
        try {
            Response<FindUserByIdResponse> response = serviceApi.findUserById(userId, age).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                // if collection is not present, Chroma returns: Status - 500
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static RuntimeException toException(Response<?> response) throws IOException {
        int code = response.code();
        String body = response.errorBody().string();

        String errorMessage = String.format("status code: %s; body: %s", code, body);
        return new RuntimeException(errorMessage);
    }
}
