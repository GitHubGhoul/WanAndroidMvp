package com.wxd.wanandroidmvp.entity;

import java.util.List;

public class IntegralHistory {
    /**
     * curPage : 1
     * datas : [{"coinCount":38,"date":1621259074000,"desc":"2021-05-17 21:44:34 签到 , 积分：10 + 28","id":432030,"reason":"签到","type":1,"userId":91568,"userName":"15725106622"}]
     * offset : 0
     * over : false
     * pageCount : 2
     * size : 20
     * total : 29
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    /**
     * coinCount : 38
     * date : 1621259074000
     * desc : 2021-05-17 21:44:34 签到 , 积分：10 + 28
     * id : 432030
     * reason : 签到
     * type : 1
     * userId : 91568
     * userName : 15725106622
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
        private int coinCount;
        private long date;
        private String desc;
        private int id;
        private String reason;
        private int type;
        private int userId;
        private String userName;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
