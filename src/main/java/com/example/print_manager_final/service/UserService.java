package com.example.print_manager_final.service;

import com.example.print_manager_final.dto.request.UserCreateRequest;
import com.example.print_manager_final.dto.request.UserUpdateRequest;
import com.example.print_manager_final.dto.response.UserResponse;
import com.example.print_manager_final.exception.AppException;
import com.example.print_manager_final.exception.ErrorException;
import com.example.print_manager_final.mapper.IUserMapper;
import com.example.print_manager_final.model.Role;
import com.example.print_manager_final.model.User;
import com.example.print_manager_final.repository.RoleRepository;
import com.example.print_manager_final.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    IUserMapper userMapper;
    PasswordEncoder passwordEncoder;
    EmailService emailService;

    public UserResponse createUser(UserCreateRequest request) {
        Role userRole = roleRepository.findByRoleName("USER");
        if (userRole == null) {
            userRole = Role.builder().roleName("user").build();
            roleRepository.save(userRole);
        }

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorException.USER_EXISTED);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorException.EMAIL_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        if (request.getEmail() != null) {
            String subject = "Xin chào " + request.getFullName() + " đến với quandeptrai.com";
            String text = "Tài khoản của bạn:  " + request.getUserName() + "\n\nCảm ơn bạn đã đăng ký";
            emailService.sendSimpleMessage(request.getEmail(), subject, text);
        }

        return userMapper.toUserResponse(user);
    }

    // chỉ cần truyền token hiện tại vào
    public UserResponse getMyInfo() {
        // request thành công thì được lưu giữ trong SecurityContextHolder
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Optional<User> user = userRepository.findByUserName(name);
        if (user == null) {
            throw new AppException(ErrorException.USER_NO_EXISTED);
        }

        return userMapper.toUserResponse(user.get());
    }

    @PostAuthorize("returnObject.userName == authentication.name or hasRole('admin')")
    public UserResponse getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorException.USER_NO_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('admin')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @PostAuthorize("returnObject.userName == authentication.name")
    public UserResponse updateInfoUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorException.USER_NO_EXISTED));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

//  @PreAuthorize("hasRole('admin')")
    //    public UserResponse updateRoleUser(Long id, Set<Role> roles) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorException.USER_NO_EXISTED));
//        roles.forEach(roleName -> {
//            if (roleRepository.findByRoleName(roleName.getRoleName()) == null) {
//                throw new AppException(ErrorException.ROLE_NAME_NOT_EXISTED);
//            }
//        });
//        user.setRoles(roles);
//        userRepository.save(user);
//        return userMapper.toUserResponse(user);
//    }

    @PreAuthorize("hasRole('admin')")
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorException.USER_NO_EXISTED));
        userRepository.deleteById(userId);
    }
}
