package com.backend.com.codes;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.http.HttpStatus;

import com.backend.com.exception.GeneralException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HttpCodes {
	OK(0, HttpStatus.OK, "Ok"), VALIDATION_ERROR(400, HttpStatus.BAD_REQUEST, "VALIDATION_ERROR"),
	NOT_FOUND(404, HttpStatus.NOT_FOUND, "NOT_FOUND"),
	INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR"),
	TOKEN_EXPIRED(401, HttpStatus.UNAUTHORIZED, "JWT 토큰이 만료되었습니다."),
	INVALID_TOKEN(401, HttpStatus.UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다."),
	MISSING_AUTH_HEADER(401, HttpStatus.UNAUTHORIZED, "Authorization 헤더가 없습니다."),
	UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED, "인증되지 않았습니다.");

	private final Integer code;
	private final HttpStatus httpStatus;
	private final String message;

	public String getMessage(Throwable e) {
		return this.getMessage(this.getMessage() + "-" + e.getMessage());

	}

	public String getMessage(String message) {
		return Optional.ofNullable(message).filter(Predicate.not(String::isBlank)).orElse(message);
	}

	public static HttpCodes valueOf(HttpStatus httpStatus) {
		if (httpStatus == null) {
			throw new GeneralException("HttpStatus 가 널입니다");
		}

		return Arrays.stream(values()).filter(errorCode -> errorCode.getHttpStatus() == httpStatus).findFirst()
				.orElseGet(() -> {
					if (httpStatus == HttpStatus.UNAUTHORIZED) {
						return HttpCodes.UNAUTHORIZED;
					} else if (httpStatus == HttpStatus.NOT_FOUND) {
						return HttpCodes.NOT_FOUND;
					} else if (httpStatus.is4xxClientError()) {
						return HttpCodes.NOT_FOUND;
					} else if (httpStatus.is5xxServerError()) {
						return HttpCodes.INTERNAL_SERVER_ERROR;
					} else {
						return HttpCodes.OK;
					}

				});
	}

	public String getCodeMessage() {
		return String.format("%s (%d)", this.name(), this.getCode());
	}

}
