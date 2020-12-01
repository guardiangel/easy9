package org.colin.model.vo.resp;

import lombok.Data;

import java.util.List;
/**
 * @Description: PageVO
 * @ClassName: PageVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 21:14
 * @Version: 1.1.0
 */
public class PageVO<T> {

    /**
     * 总记录数
     */
    private Long totalRows;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 当前第几页
     */
    private Integer pageNum;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 当前页记录数
     */
    private Integer curPageSize;

    /**
     * 数据列表
     */
    private List<T> list;

    @Override
    public String toString() {
        return "PageVO{" +
                "totalRows=" + totalRows +
                ", totalPages=" + totalPages +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", curPageSize=" + curPageSize +
                ", list=" + list +
                '}';
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurPageSize() {
        return curPageSize;
    }

    public void setCurPageSize(Integer curPageSize) {
        this.curPageSize = curPageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
