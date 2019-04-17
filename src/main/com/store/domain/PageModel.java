package main.com.store.domain;

import java.util.ArrayList;
import java.util.List;

public class PageModel {
    private List list;
    private int pageSize;//每页页面上显示的个数
    private int currentNum;//当前页
    private String url;//
    private int totalPageNum;//总页数
    private int nextPageNumber;//下一页
    private int totalRecords;//总记录数
    private int startIndex;
    private int prePageNum;//上一页
    private int startPage;//首页
    private int endPage;//末页

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public PageModel(int pageSize, int totalRecords, int currentNum) {
        this.startPage=1;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.currentNum = currentNum;
        this.totalPageNum = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
        this.endPage=this.totalPageNum;
        if (currentNum < totalPageNum&&totalPageNum>0) {
            this.nextPageNumber = currentNum + 1;
        } else if(currentNum==totalPageNum) {
            currentNum = totalPageNum;
        }
        startIndex = (currentNum - 1) * pageSize;
        if(currentNum>1)
            this.prePageNum=currentNum-1;

    }
    public int getPrePageNum() {
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List getList() {
        return list;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "list=" + list +
                ", pageSize=" + pageSize +
                ", currentNum=" + currentNum +
                ", url='" + url + '\'' +
                ", totalPageNum=" + totalPageNum +
                ", nextPageNumber=" + nextPageNumber +
                ", totalRecords=" + totalRecords +
                ", startIndex=" + startIndex +
                ", prePageNum=" + prePageNum +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                '}';
    }

    public void setList(List list) {
        this.list = list;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentNum() {
        return currentNum;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getTotalPageNum() {
        return totalPageNum;
    }


    public int getNextPageNumber() {
        return nextPageNumber;
    }

    public void setNextPageNumber(int nextPageNumber) {
        this.nextPageNumber = nextPageNumber;
    }

}
