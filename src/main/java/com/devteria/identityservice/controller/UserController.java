package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse getUsers(){
        ApiResponse usersList = userService.getUsers();
        return usersList;

//        List<UserResponse> usersList = userService.getUsers();
//        ApiResponse responseApi = new ApiResponse();
//        responseApi.setCode(200);
//        responseApi.setMessage("Success");
//        responseApi.setResult(usersList);
//        return responseApi;
//        return ApiResponse.<List<UserResponse>>builder()
//                .result(userService.getUsers())
//                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse getUser(@PathVariable("userId") String userId){
        ApiResponse usersList = userService.getUser(userId);
        return usersList;
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.getUser(userId))
//                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }
}
