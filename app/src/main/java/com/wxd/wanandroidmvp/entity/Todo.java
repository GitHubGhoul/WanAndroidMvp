package com.wxd.wanandroidmvp.entity;

import java.util.List;

public class Todo {
    /**
     * curPage : 1
     * datas : [{"completeDate":null,"completeDateStr":"","content":"丢卡","date":1623945600000,"dateStr":"2021-06-18","id":26244,"priority":3,"status":0,"title":"咋回事","type":0,"userId":91568},{"completeDate":null,"completeDateStr":"","content":"我要改了","date":1621267200000,"dateStr":"2021-05-18","id":26506,"priority":2,"status":0,"title":"测试修改","type":1,"userId":91568},{"completeDate":null,"completeDateStr":"","content":"测试内容","date":1616428800000,"dateStr":"2021-03-23","id":25645,"priority":1,"status":0,"title":"测试","type":1,"userId":91568},{"completeDate":null,"completeDateStr":"","content":"test content","date":1616428800000,"dateStr":"2021-03-23","id":25646,"priority":2,"status":0,"title":"test","type":2,"userId":91568}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 4
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    /**
     * completeDate : null
     * completeDateStr :
     * content : 丢卡
     * date : 1623945600000
     * dateStr : 2021-06-18
     * id : 26244
     * priority : 3
     * status : 0
     * title : 咋回事
     * type : 0
     * userId : 91568
     */

    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private Object completeDate;
        private String completeDateStr;
        private String content;
        private long date;
        private String dateStr;
        private int id;
        private int priority;
        private int status;
        private String title;
        private int type;
        private int userId;

        public Object getCompleteDate() {
            return completeDate;
        }

        public void setCompleteDate(Object completeDate) {
            this.completeDate = completeDate;
        }

        public String getCompleteDateStr() {
            return completeDateStr;
        }

        public void setCompleteDateStr(String completeDateStr) {
            this.completeDateStr = completeDateStr;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getDateStr() {
            return dateStr;
        }

        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
