package com.wxd.wanandroidmvp.entity;

import java.util.List;

public class ProjectList {
    /**
     * curPage : 1
     * datas : [{"apkLink":"","audit":1,"author":"miaowmiaow","canEdit":false,"chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"采用 Kotlin 语言编写，单Activity多Fragment，MVVM + ViewModel + LiveData + Retrofit + 协程 + ViewBinding等架构设计的项目，项目结构清晰，代码简介优雅","descMd":"","envelopePic":"https://www.wanandroid.com/blogimgs/6d454437-1e59-4804-9f75-7255b0e816a9.png","fresh":true,"host":"","id":18320,"link":"https://www.wanandroid.com/blog/show/3005","niceDate":"23小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"https://github.com/miaowmiaow/FragmentProject.git","publishTime":1621177682000,"realSuperChapterId":293,"selfVisible":0,"shareDate":1621177682000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"可能是东半球更简洁的玩安卓客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"SheHuan","canEdit":false,"chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"基于 Vue2 + Vant 的移动版 玩Android 客户端","descMd":"","envelopePic":"https://www.wanandroid.com/blogimgs/70ff438b-ca4e-420e-86f1-53387271d012.png","fresh":false,"host":"","id":18251,"link":"https://www.wanandroid.com/blog/show/2998","niceDate":"2021-05-09 23:44","niceShareDate":"2021-05-09 23:44","origin":"","prefix":"","projectLink":"https://github.com/SheHuan/wanandroid-vue","publishTime":1620575078000,"realSuperChapterId":293,"selfVisible":0,"shareDate":1620575078000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"基于 Vue2 + Vant 的移动版 玩Android 客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"Theoneee","canEdit":false,"chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"码云地址：https://gitee.com/theoneee/the-base-mvvm","descMd":"","envelopePic":"https://www.wanandroid.com/blogimgs/ae8bea90-df9c-42db-a6ef-2a0ef1c93120.png","fresh":false,"host":"","id":17828,"link":"https://www.wanandroid.com/blog/show/2976","niceDate":"2021-04-01 22:20","niceShareDate":"2021-04-01 22:20","origin":"","prefix":"","projectLink":"https://github.com/Theoneee/TheBase-MVVM","publishTime":1617286820000,"realSuperChapterId":293,"selfVisible":0,"shareDate":1617286820000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"QMUI+Jetpack组件搭建的一个基础框架写的一个玩安卓App","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 18
     * size : 15
     * total : 258
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    /**
     * apkLink :
     * audit : 1
     * author : miaowmiaow
     * canEdit : false
     * chapterId : 294
     * chapterName : 完整项目
     * collect : false
     * courseId : 13
     * desc : 采用 Kotlin 语言编写，单Activity多Fragment，MVVM + ViewModel + LiveData + Retrofit + 协程 + ViewBinding等架构设计的项目，项目结构清晰，代码简介优雅
     * descMd :
     * envelopePic : https://www.wanandroid.com/blogimgs/6d454437-1e59-4804-9f75-7255b0e816a9.png
     * fresh : true
     * host :
     * id : 18320
     * link : https://www.wanandroid.com/blog/show/3005
     * niceDate : 23小时前
     * niceShareDate : 23小时前
     * origin :
     * prefix :
     * projectLink : https://github.com/miaowmiaow/FragmentProject.git
     * publishTime : 1621177682000
     * realSuperChapterId : 293
     * selfVisible : 0
     * shareDate : 1621177682000
     * shareUser :
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
     * title : 可能是东半球更简洁的玩安卓客户端
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
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
        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private String host;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int realSuperChapterId;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        /**
         * name : 项目
         * url : /project/list/1?cid=294
         */

        private List<TagsBean> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDescMd() {
            return descMd;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public long getShareDate() {
            return shareDate;
        }

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
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

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
