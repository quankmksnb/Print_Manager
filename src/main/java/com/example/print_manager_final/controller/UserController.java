package com.example.print_manager_final.controller;

import com.example.print_manager_final.dto.request.RoleRequest;
import com.example.print_manager_final.dto.request.UserCreateRequest;
import com.example.print_manager_final.dto.request.UserUpdateRequest;
import com.example.print_manager_final.dto.response.ApiResponse;
import com.example.print_manager_final.dto.response.UserResponse;
import com.example.print_manager_final.model.Role;
import com.example.print_manager_final.model.User;
import com.example.print_manager_final.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)       // khai báo biến thì để mặc định Private hết
public class UserController {
    UserService userService;

    @RequestMapping(value = "signup_user", method = RequestMethod.POST)
    public ApiResponse<UserResponse> addUser(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .code(200)
                .build();
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public ApiResponse<UserResponse> getById(@RequestParam("id") Long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .code(200)
                .build();
    }

    @RequestMapping(value = "myInfo", method = RequestMethod.GET)
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .code(200)
                .build();
    }

    @RequestMapping(value = "get_all_user", method = RequestMethod.GET)
    public ApiResponse<List<UserResponse>> getAllUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .code(200)
                .build();
    }

    @RequestMapping(value = "update_user", method = RequestMethod.PUT)
    public ApiResponse<UserResponse> updateUser(@RequestParam Long id, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateInfoUser(id, request))
                .code(200)
                .build();
    }

//    @RequestMapping(value = "update_role_user", method = RequestMethod.PUT)
//    public ApiResponse<UserResponse> updateRoleUser(@RequestParam Long id, @RequestBody Set<Role> roleRequests) {
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.updateRoleUser(id, roleRequests))
//                .code(200)
//                .build();
//    }

    @RequestMapping(value = "delete_user", method = RequestMethod.DELETE)
    public ApiResponse<String> deleteUser(@RequestParam Long userId){
        userService.deleteUser(userId);

        return ApiResponse.<String>builder()
                .result("Deleted successfully")
                .code(200)
                .build();
    }

}
