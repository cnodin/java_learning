package cn.com.fubon.springboot.starter.jwt.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/6/17
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
public class RestErrorResponse {

	private final int statusCode;
	private final String reasonPhrase;
	private final String detailMessage;

	protected RestErrorResponse(HttpStatus status, String detailMessage) {
		statusCode = status.value();
		reasonPhrase = status.getReasonPhrase();
		this.detailMessage = detailMessage;
	}

	public static RestErrorResponse of(HttpStatus httpStatus){
		return of(httpStatus, null);
	}

	public static RestErrorResponse of(HttpStatus status, Exception ex) {
		return new RestErrorResponse(status, ex.getMessage());
	}

}
