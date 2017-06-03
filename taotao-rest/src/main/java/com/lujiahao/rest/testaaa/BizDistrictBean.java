package com.lujiahao.rest.testaaa;

import java.util.List;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-06-02 17:18
 */
public class BizDistrictBean {

    private String oldFencingColor;
    private double oldFencingOpacity;
    private double fencingBorderOpacity;
    private String oldFencingBorder;
    private double fencingOpacity;
    private String fencingColor;
    private String fencingBorder;
    private double oldFencingBorderOpacity;
    private List<String> data;

    public String getOldFencingColor() {
        return oldFencingColor;
    }

    public void setOldFencingColor(String oldFencingColor) {
        this.oldFencingColor = oldFencingColor;
    }

    public double getOldFencingOpacity() {
        return oldFencingOpacity;
    }

    public void setOldFencingOpacity(double oldFencingOpacity) {
        this.oldFencingOpacity = oldFencingOpacity;
    }

    public double getFencingBorderOpacity() {
        return fencingBorderOpacity;
    }

    public void setFencingBorderOpacity(double fencingBorderOpacity) {
        this.fencingBorderOpacity = fencingBorderOpacity;
    }

    public String getOldFencingBorder() {
        return oldFencingBorder;
    }

    public void setOldFencingBorder(String oldFencingBorder) {
        this.oldFencingBorder = oldFencingBorder;
    }

    public double getFencingOpacity() {
        return fencingOpacity;
    }

    public void setFencingOpacity(double fencingOpacity) {
        this.fencingOpacity = fencingOpacity;
    }

    public String getFencingColor() {
        return fencingColor;
    }

    public void setFencingColor(String fencingColor) {
        this.fencingColor = fencingColor;
    }

    public String getFencingBorder() {
        return fencingBorder;
    }

    public void setFencingBorder(String fencingBorder) {
        this.fencingBorder = fencingBorder;
    }

    public double getOldFencingBorderOpacity() {
        return oldFencingBorderOpacity;
    }

    public void setOldFencingBorderOpacity(double oldFencingBorderOpacity) {
        this.oldFencingBorderOpacity = oldFencingBorderOpacity;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BizDistrictBean{" +
                "oldFencingColor='" + oldFencingColor + '\'' +
                ", oldFencingOpacity=" + oldFencingOpacity +
                ", fencingBorderOpacity=" + fencingBorderOpacity +
                ", oldFencingBorder='" + oldFencingBorder + '\'' +
                ", fencingOpacity=" + fencingOpacity +
                ", fencingColor='" + fencingColor + '\'' +
                ", fencingBorder='" + fencingBorder + '\'' +
                ", oldFencingBorderOpacity=" + oldFencingBorderOpacity +
                ", data=" + data +
                '}';
    }
}