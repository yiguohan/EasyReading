package com.yiguohan.easyreading.Beans.DoubanBooks;

import java.util.List;

/**
 * Created by yiguo on 2017/5/4.
 * Email:yiguohan@gmail.com
 */

public class Book {
    /**
     * rating : {"max":10,"numRaters":2315,"average":"9.2","min":0}
     * subtitle :
     * author : ["弗里曼"]
     * pubdate : 2007-9
     * tags : [{"count":2391,"name":"设计模式","title":"设计模式"},{"count":643,"name":"计算机","title":"计算机"},{"count":490,"name":"编程","title":"编程"},{"count":477,"name":"软件工程","title":"软件工程"},{"count":405,"name":"软件设计","title":"软件设计"},{"count":383,"name":"Headfirst","title":"Headfirst"},{"count":316,"name":"java","title":"java"},{"count":269,"name":"软件开发","title":"软件开发"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s2686916.jpg
     * binding : 平装
     * translator : ["O'Reilly Taiwan公司"]
     * catalog : 引子
     * pages : 637
     * images : {"small":"https://img3.doubanio.com/spic/s2686916.jpg","large":"https://img3.doubanio.com/lpic/s2686916.jpg","medium":"https://img3.doubanio.com/mpic/s2686916.jpg"}
     * alt : https://book.douban.com/subject/2243615/
     * id : 2243615
     * publisher : 中国电力出版社
     * isbn10 : 7508353935
     * isbn13 : 9787508353937
     * title : Head First 设计模式（中文版）
     * url : https://api.douban.com/v2/book/2243615
     * alt_title :
     * author_intro :
     * summary : 《Head First设计模式》(中文版)共有14章，每章都介绍了几个设计模式，完整地涵盖了四人组版本全部23个设计模式。前言先介绍这本书的用法；第1章到第11章陆续介绍的设计模式为Strategy、Observer、Decorator、Abstract Factory、Factory Method、Singleton，Command、Adapter、Facade、TemplateMethod、Iterator、Composite、State、Proxy。最后三章比较特别。第12章介绍如何将两个以上的设计模式结合起来成为新的设计模式(例如著名的MVC模式)，作者称其为复合设计模式(这是作者自创的名称，并非四人组的标准名词)，第13章介绍如何进一步学习设计模式，如何发觉新的设计模式等主题，至于第14章则很快地浏览尚未介绍的设计模式，包括Bridge、Builder、Chain of Responsibility、Flyweight、Interpreter、Mediator、Memento、Prototype，Visitor。第1章还介绍了四个○○基本概念(抽象、封装、继承、多态)，而第1章到第9章也陆续介绍了九个○○原则(Principle)。千万不要轻视这些○○原则，因为每个设计模式背后都包含了几个○○原则的概念。很多时候，在设计时有两难的情况，这时候我们必须回归到○○原则，以方便判断取舍。可以这么说：○○原则是我们的目标，而设计模式是我们的做法。
     * series : {"id":"10044","title":"O'Reilly深入浅出系列"}
     * price : 98.00元
     */

    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private SeriesBean series;
    private String price;
    private List<String> author;
    private List<TagsBean> tags;
    private List<String> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public SeriesBean getSeries() {
        return series;
    }

    public void setSeries(SeriesBean series) {
        this.series = series;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 2315
         * average : 9.2
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/spic/s2686916.jpg
         * large : https://img3.doubanio.com/lpic/s2686916.jpg
         * medium : https://img3.doubanio.com/mpic/s2686916.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class SeriesBean {
        /**
         * id : 10044
         * title : O'Reilly深入浅出系列
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class TagsBean {
        /**
         * count : 2391
         * name : 设计模式
         * title : 设计模式
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
