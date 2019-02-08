package devutility.external.servlet.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletUtils {
	/**
	 * Get request url with parameters.
	 * @param request HttpServletRequest object.
	 * @return String
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		return String.format("%s?%s", request.getRequestURL().toString(), request.getQueryString());
	}

	/**
	 * Response data to client through HttpServletResponse.
	 * @param response HttpServletResponse object.
	 * @param data: String data.
	 */
	public static void response(HttpServletResponse response, String data) {
		response(response, data, HttpServletResponse.SC_OK);
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
	 * Unauthorized response.
	 * @param response HttpServletResponse object.
	 * @param data Serialized response data.
	 */
	public static void unauthorizedResponse(HttpServletResponse response, String data) {
		response(response, data, HttpServletResponse.SC_UNAUTHORIZED);
	}

	/**
	 * Forbidden response.
	 * @param response HttpServletResponse object.
	 * @param data Serialized response data.
	 */
	public static void forbiddenResponse(HttpServletResponse response, String data) {
		response(response, data, HttpServletResponse.SC_FORBIDDEN);
	}
}