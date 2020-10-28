package com.bone.october.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerMessage {

    HTTP_200("OK"),
    HTTP_404("NOT FOUND"),
    HTTP_500("INTERNAL_SERVER_ERROR");

    private String message;
}
