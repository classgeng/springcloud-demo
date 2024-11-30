package com.demo.common.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author classgeng
 * @date 2024/10/18 15:13
 * @description 开放平台签名工具类
 **/

public class HMacSignUtilSDK {

	// url参数连接符
	static final String SEP_URL_PARAM = "\\?";
	// 参数连接符
	static final String SEP_PARAM_PAIR = "&";
	// 签名参数换行符
	static final String SEP_NEWLINE = "\n";
	//时间格式转换
	static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);

	//对应开放平台 - 开发者申请：访问键
	static final String appID = "developer_hly-management";
	//对应开放平台 - 开发者申请：访问秘钥
	static final String SECRET_KEY = "69d6e5b707f54714a61ac8c631ace655";

	static final String url = "https://open.sinopharmholding.com/openapi/guokonginvoice/api/sinopharm-invoice-purchase/open/accounting/result";


	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, MalformedURLException {
		// 请求头参数：X-HMAC-SIGNATURE
		calcRequestSign("POST", appID, SECRET_KEY, url, getDate());
	}

	/**
	 * 生成签名的时间
	 * @return
	 */
	public static String getDate(){
		LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT+8"));
		// 请求头参数：Date
		return now.format(DATE_TIME_FORMATTER);
	}

	/**
	 * hmacSha256签名字符串
	 * 
	 * @param message 带签名的信息
	 * @param secret  秘钥信息
	 * @return 签名的数组
	 * @throws NoSuchAlgorithmException 算法不存在时返回该错误信息
	 * @throws InvalidKeyException      非法的秘钥信息
	 */
	public static byte[] calculateHmacSha256(String message, String secret)
			throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256Hmac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
		sha256Hmac.init(secretKey);
		return sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * calc request sign
	 *
	 * @param reqMethod    req method: GET、POST、PUT、DELETE
	 * @param accessKey    access key
	 * @param accessSecret access secret
	 * @param url          url
	 * @param date         date
	 * @return {@link String}
	 * @throws NoSuchAlgorithmException no such algorithm exception
	 * @throws InvalidKeyException      invalid key exception
	 */
	public static String calcRequestSign(String reqMethod, String accessKey, String accessSecret, String url, String date) throws NoSuchAlgorithmException, InvalidKeyException, MalformedURLException {
		System.out.println("accessKey:"+accessKey+", url:"+url+", method:" + reqMethod);

		URL uriComponents = new URL(url);
		StringBuilder sb = new StringBuilder();
		sb.append(reqMethod).append(SEP_NEWLINE); // 如果方法为POST,则此处为POST
		sb.append(uriComponents.getPath()).append(SEP_NEWLINE);// 添加uri信息
		// 参数信息
		String[] split = url.split(SEP_URL_PARAM);
		if (split.length > 1) {
			Stream<String> sorted = Arrays.stream(url.split(SEP_URL_PARAM)[1].split(SEP_PARAM_PAIR)).sorted();
			String param = sorted.collect(Collectors.joining(SEP_PARAM_PAIR));
			sb.append(param).append(SEP_NEWLINE); // 参数信息
		} else {
			sb.append(SEP_NEWLINE);
		}
		sb.append(accessKey).append(SEP_NEWLINE);
		sb.append(date).append(SEP_NEWLINE);

		byte[] sign = calculateHmacSha256(sb.toString(), accessSecret);
		String result = Base64.getEncoder().encodeToString(sign);
		System.out.println("生成签名时间戳:"+ date +", 生成签名:" + result);
		return result;
	}


}
