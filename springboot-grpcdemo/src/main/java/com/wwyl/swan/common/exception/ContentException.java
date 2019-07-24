package com.wwyl.swan.common.exception;

import com.wwyl.lark.util.exception.BaseRuntimeException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 08/02/2018
 * Time: 09:29
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ContentException extends BaseRuntimeException {
    public ContentException(String errorCode) {
        super(errorCode);
    }

    public ContentException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ContentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
