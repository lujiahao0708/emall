package com.lujiahao.common.pojo;

import java.util.List;


/**
 * EasyUI专用的返回格式的封装对象
 *
 * @author lujiahao
 */
public class EUDataGridResult {
    private long total;
    // ?可以是任意类型
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
