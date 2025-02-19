package com.backend.com.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "AUTH_REQ_01 : 로그인 요청 DTO")

public class LoginDto {
	@NotNull(message = "userId 입력은 필수입니다.")
	private String userId;
	@NotNull(message = "password 입력은 필수입니다.")
	private String password;

}
