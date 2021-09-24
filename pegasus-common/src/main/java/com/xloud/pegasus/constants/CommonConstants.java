package com.xloud.pegasus.constants;

public class CommonConstants {

	private CommonConstants() {
	}

	/** 処理結果フラグ：成功. */
	public static final String API_RESPONSE_SUCCESS = "0";

	/** 処理結果フラグ：失敗. */
	public static final String API_RESPONSE_ERROR = "1";

	/** URLのベース部分：API用. */
	public static final String URL_BASE_API = "/${spring.profiles.active}/${application.version:v0.1}/api";

	/** URLのベース部分：SPA用. */
	public static final String URL_BASE_SPA = "/${spring.profiles.active}/${application.version:v0.1}/spa";

	/** URLのベース部分：MPA用. */
	public static final String URL_BASE_MPA = "/${spring.profiles.active}/${application.version:v0.1}/mpa";

}
