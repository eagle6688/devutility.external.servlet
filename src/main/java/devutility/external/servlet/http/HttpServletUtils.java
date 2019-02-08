package devutility.external.servlet.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletUtils {
	/**
	 * Response data to client through HttpServletResponse.
	 * @param httpServletResponse HttpServletResponse object.
	 * @param data: String data.
	 */
	public static void response(HttpServletResponse httpServletResponse, String data) {
		response(httpServletResponse, data, HttpServletResponse.SC_OK);
	}

	/**
	 * Response data to client through HttpServletResponse.
	 * @param httpServletResponse HttpServletResponse object.
	 * @param data: String data.
	 * @param status: Http status.
	 */
	public static void response(HttpServletResponse httpServletResponse, String data, int status) {
		httpServletResponse.setStatus(status);
		httpServletResponse.setContentType("application/json; charset=utf-8");

		try (ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
			byte[] bytes = data.getBytes("UTF-8");
			httpServletResponse.setContentLength(bytes.length);
			servletOutputStream.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get request url with parameters.
	 * @param request HttpServletRequest object.
	 * @return String
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		return String.format("%s?%s", request.getRequestURL().toString(), request.getQueryString());
	}
}