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

	@DisplayName("비밀번호 강도 높음")
	@Test
	void inputStrongPassword() {
		PasswordString result = passwordCheck.input("12Qw34qw");
		assertThat(result).isEqualTo(PasswordString.STRONG);
		PasswordString result2 = passwordCheck.input("1234Qwer");
		assertThat(result2).isEqualTo(PasswordString.STRONG);
		PasswordString result3 = passwordCheck.input("1Q2w3e4r");
		assertThat(result3).isEqualTo(PasswordString.STRONG);
	}

	@DisplayName("길이 8미만 다른조건 충족")
	@Test
	void lengthException() {
		PasswordString result = passwordCheck.input("123Qwe");
		assertThat(result).isEqualTo(PasswordString.WEEK);
		PasswordString result2 = passwordCheck.input("qwwe123");
		assertThat(result2).isEqualTo(PasswordString.WEEK);
		PasswordString result3 = passwordCheck.input("123456q");
		assertThat(result3).isEqualTo(PasswordString.WEEK);
	}

	@DisplayName("숫자만 사용한 비밀번호")
	@Test
	void useOnlyNumbers() {
		assertThatIllegalArgumentException()
				.isThrownBy(
						() -> passwordCheck.input("12345678")
				);
	}
}
