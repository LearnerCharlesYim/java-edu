package com.charles.common.execption;

@SuppressWarnings("all")
public class BizException extends RuntimeException {
    protected Integer errorCode;
    protected String errorMsg;
    protected BaseErrorInfoInterface errorInfo;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInfoInterface errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMessage();
    }

    public BizException(BaseErrorInfoInterface errorInfo, Throwable cause) {
        super(errorInfo.getMessage(), cause);
        this.errorInfo = errorInfo;
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMessage();
    }

    public BizException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BaseErrorInfoInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(BaseErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}
