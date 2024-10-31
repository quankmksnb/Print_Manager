package com.example.print_manager_final.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorException {
    USER_EXISTED(101,"User existed"),
    USER_NO_EXISTED(102,"User not existed"),
    INVALID_USERNAME(103,"Username must be at min  3 character"),
    INVALID_PASSWORD(104,"Password must be at min 8 character"),
    INVALID_KEY(105,"INVALID message key error"),
    ERROR_EXCEPTION(99,"err exception"),
    NOT_EMAIL(106,"Not email"),
    EMAIL_EXISTED(107,"Email existed"),
    NOT_FILE(108,"Not file"),
    DIRECTORY_CREATION_FAILED(109,"Directory creation failed"),
    FILE_STORAGE_FAILED(110,"File storage failed"),
    INCORECT_PASSWORD(111,"password incorrect"),
    ROLE_NAME_EXISTED(112,"Role name existed"),
    ROLE_NAME_NOT_EXISTED(113,"Role name not existed"),
    UNAUTHORIZED(114, "You do not have permission")
    ;
    private int code;
    private String message;
}
