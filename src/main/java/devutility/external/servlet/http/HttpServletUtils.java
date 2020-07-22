package devutility.external.servlet.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import devutility.internal.lang.StringUtils;

/**
 * 
 * HttpServletUtils
 * 
 * @author: Aldwin Su
 * @version: 2018-04-25 13:39:23
 */
public class HttpServletUtils {
	/**
	 * Http header names for client IP.
	 */
	private static final String[] HEADER_NAMES_FOR_CLIENT_IP = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED",
			"HTTP_VIA", "REMOTE_ADDR" };

	/**
	 * Get request url with parameters.
	 * @param request HttpServletRequest object.
	 * @return String
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		return String.format("%s?%s", request.getRequestURL().toString(), request.getQueryString());
	}

	/**
	 * Get client Ip address from HttpServletRequest object.
	 * @param request HttpServletRequest object.
	 * @return String
	 */
	public static String getClientIpAddress(HttpServletRequest request) {
		String unknowIp = "unknown";

		for (String headerName : HEADER_NAMES_FOR_CLIENT_IP) {
			String ip = request.getHeader(headerName);

			if (StringUtils.isNotEmpty(ip) && !ip.equalsIgnoreCase(unknowIp)) {
				return ip;
			}
		}

		return request.getRemoteAddr();
	}

	/**
	 * Response data to client through HttpServletResponse.
	 * @param response HttpServletResponse object.
	 * @param data Serialized response data.
	 * @param status Http status.
	 */
	public static void response(HttpServletResponse response, String data, int status) {
		response.setStatus(status);
		response.setContentType("application/json; charset=utf-8");

		try (ServletOutputStream servletOutputStream = response.getOutputStream()) {
			byte[] bytes = data.getBytes("UTF-8");
			response.setContentLength(bytes.length);
			servletOutputStream.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Response data to client through HttpServletResponse.
	 * @param response HttpServletResponse object.
	 * @param data String data, maybe Json.
	 */
	public static void okResponse(HttpServletResponse response, String data) {
		response(response, data, HttpServletResponse.SC_OK);
	}

	/**
	 * Bad request response.
	 * @param response HttpServletResponse object.
	 * @param data String data, maybe Json.
	 */
	public static void badRequestResponse(HttpServletResponse response, String data) {
		response(response, data, HttpServletResponse.SC_BAD_REQUEST);
	}

	/**
	 * Unauthorized response.
	 * @param response HttpServletResponse object.
	 * @param data String data, maybe Json.
	 */
	public static void unauthorizedResponse(HttpServletResponse response, String data) {
		response(response, data, HttpServletResponse.SC_UNAUTHORIZED);
	}

	/**
	 * Forbidden response.
	 * @param response HttpServletResponse object.
	 * @param data String data, maybe Json.
	 */
	public static void forbiddenResponse(HttpServletResponse response, String data) {
		response(response, data, HttpServletResponse.SC_FORBIDDEN);
	}
}