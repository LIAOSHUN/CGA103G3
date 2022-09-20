package com.core.utils;

import static com.core.utils.ConstantsTime.GSON_TIME;
import static com.core.utils.ConstantsTime.JSON_MIME_TYPE;


import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSONTransTime {

	public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {

		try (BufferedReader br = request.getReader()) {
			return GSON_TIME.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
		response.setContentType(JSON_MIME_TYPE);
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON_TIME.toJson(pojo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
