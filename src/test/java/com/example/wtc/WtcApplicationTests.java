package com.example.wtc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class WtcApplicationTests {

	/**
	 * 비밀번호 생성 테스트코드 작성
	 * 길이 8 이상 영어 대 + 소문자 + 숫자 포함
	 */

	PasswordCheck passwordCheck = new PasswordCheck();
	@DisplayName("널값 출력")
	@Test
	void inputNull() {
		assertThatIllegalArgumentException()
				.isThrownBy(
						() -> passwordCheck.input(null)
				);
	}

	@DisplayName("빈값 입력")
	@Test
	void inputBlank() {
		assertThatIllegalArgumentException()
				.isThrownBy(
						() -> passwordCheck.input("")
				);
	}

	private void assertPasswordStrong(String a, PasswordString passwordString) {
		PasswordString input = passwordCheck.input(a);
		assertThat(input).isEqualTo(passwordString);
	}

	@DisplayName("비밀번호 강도 높음")
	@Test
	void inputStrongPassword() {
		assertPasswordStrong("12Qw34qw", PasswordString.STRONG);
		assertPasswordStrong("1234Qwer", PasswordString.STRONG);
		assertPasswordStrong("1Q2w3e4r", PasswordString.STRONG);
	}

	@DisplayName("길이 8미만 다른조건 충족")
	@Test
	void lengthException() {
		assertPasswordStrong("123Qwe", PasswordString.WEEK);
		assertPasswordStrong("qwwe123", PasswordString.WEEK);
		assertPasswordStrong("123456q", PasswordString.WEEK);
	}

	@DisplayName("숫자만 사용한 비밀번호")
	@Test
	void useOnlyNumbers() {
		assertPasswordStrong("123124111",PasswordString.WEEK);
		assertPasswordStrong("11111111111",PasswordString.WEEK);
		assertPasswordStrong("153145332",PasswordString.WEEK);
	}

	@DisplayName("문자만 사용한 비밀번호")
	@Test
	void useOnlyLetters() {
		assertPasswordStrong("QWeqwrqwq",PasswordString.MIDDLE);
	}

	@DisplayName("대문자가 없는 비밀번호")
	@Test
	void NoUpperCase() {
		assertPasswordStrong("1234qwerq", PasswordString.WEEK);
	}
}
