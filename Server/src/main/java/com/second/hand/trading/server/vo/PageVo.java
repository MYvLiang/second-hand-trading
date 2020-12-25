package com.second.hand.trading.server.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author myl
 * @create 2020-12-23  22:56
 */
public class PageVo <E>{
    private List<E> list;
    private int count;

    public PageVo() {
    }

    public PageVo(List<E> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"list\":")
                .append(list);
        sb.append(",\"count\":")
                .append(count);
        sb.append('}');
        return sb.toString();
    }
}
