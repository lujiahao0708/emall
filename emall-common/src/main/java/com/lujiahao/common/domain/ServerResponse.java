package com.lujiahao.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lujiahao.common.enums.EResponseCode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 服务端响应对象
 *
 * @auth lujiahao
 * @date 2017/10/19 22:07
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)// 当某个属性为null时不返回
public class ServerResponse<T> implements Serializable {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;

    private ServerResponse() {
    }

    private ServerResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(T data) {
        this.status = EResponseCode.SUCCESS.getCode();
        this.msg = EResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    // 加入这个注解后不会显示在json中
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == 200;
    }


    public static <T> ServerResponse<T> build(Integer status, String msg, T data) {
        return new ServerResponse(status, msg, data);
    }

    public static <T> ServerResponse<T> build(Integer status, String msg) {
        return new ServerResponse<T>(status, msg, null);
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<T>(data);
    }

    public static <T> ServerResponse<T> success() {
        return new ServerResponse<T>(null);
    }

    public static <T> ServerResponse<T> error(int status, String msg) {
        return new ServerResponse<T>(status, msg, null);
    }

    public static <T> ServerResponse<T> error(String msg) {
        return new ServerResponse<T>(EResponseCode.ERROR.getCode(), msg, null);
    }

    public static <T> ServerResponse<T> error() {
        return new ServerResponse<T>(EResponseCode.ERROR.getCode(), EResponseCode.ERROR.getMsg(), null);
    }

    //==================================================================================================================

    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz    TaotaoResult中的object类型
     * @return
     */
    public static ServerResponse formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ServerResponse.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ServerResponse format(String json) {
        try {
            return MAPPER.readValue(json, ServerResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static ServerResponse formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
