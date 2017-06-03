package com.lujiahao.rest.testaaa;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-06-02 17:15
 */
public class ComRespon {

    private String returnCode;
    private String returnMessage;
    private Object data;

    public ComRespon() {

    }
    public ComRespon(String returnCode, String returnMessage, Object data) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.data = data;
    }
    public ComRespon(Object data) {
        this.returnCode = "0";
        this.returnMessage = "OK";
        this.data = data;
    }

    public static ComRespon ok() {
        return new ComRespon("");
    }
    public static ComRespon ok(Object data) {
        return new ComRespon(data);
    }
    public static ComRespon ok(String returnMessage,Object data){
        return new ComRespon("0",returnMessage,data);
    }
    public static ComRespon build(String returnCode, String returnMessage) {
        return new ComRespon(returnCode, returnMessage, "");
    }
    public static ComRespon build(String returnCode, String returnMessage, Object data) {
        return new ComRespon(returnCode, returnMessage, data);
    }






    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ComRespon{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
