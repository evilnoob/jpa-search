package ru.evilnoob.jpasearch.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.evilnoob.jpasearch.model.entity.User;

@Data
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private List<UserDto> users;

    @Data
    @AllArgsConstructor
    @Builder
    public static class UserDto {

        private Long id;
        private String name;
        private List<RoleDto> roles;
        private DepartmentDto department;
        private List<UserLoginDto> logins;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class RoleDto {

        private Long id;
        private String name;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class DepartmentDto {

        private Long id;
        private String refId;
        private String title;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class UserLoginDto {

        private Long id;
        private String login;

    }

    public static UserResponseDto getUserResponseDto(List<User> users) {
        return UserResponseDto.builder()
                .users(users.stream()
                        .map(u -> UserDto.builder()
                                .id(u.getId())
                                .name(u.getName())
                                .department(DepartmentDto.builder()
                                        .id(u.getDepartment().getId())
                                        .refId(u.getDepartment().getRefId())
                                        .title(u.getDepartment().getTitle())
                                        .build())
                                .roles(u.getRoles().stream()
                                        .map(r -> RoleDto.builder()
                                                .id(r.getId())
                                                .name(r.getName())
                                                .build())
                                        .toList())
                                .logins(u.getUserLogins().stream()
                                        .map(l -> UserLoginDto.builder()
                                                .id(l.getId())
                                                .login(l.getLogin())
                                                .build())
                                        .toList())
                                .build())
                        .toList())
                .build();
    }

}
