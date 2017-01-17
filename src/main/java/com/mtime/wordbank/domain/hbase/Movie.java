package com.mtime.wordbank.domain.hbase;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 电影信息
 * */
public class Movie implements Serializable{
    private int movieid;  //电影id
    private String keyword;   //关键字
    private String titlecn;   //中文名
    private String titleen;   //英文名
    private String othersCn;  //其他中文名
    private String othersen;  //其他英文名
    private String director;  //导演
    private String actor;     //演员
    
    private String tagName;  //标签

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitlecn() {
		return titlecn;
	}

	public void setTitlecn(String titlecn) {
		this.titlecn = titlecn;
	}

	public String getTitleen() {
		return titleen;
	}

	public void setTitleen(String titleen) {
		this.titleen = titleen;
	}

	public String getOthersCn() {
		return othersCn;
	}

	public void setOthersCn(String othersCn) {
		this.othersCn = othersCn;
	}

	public String getOthersen() {
		return othersen;
	}

	public void setOthersen(String othersen) {
		this.othersen = othersen;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"movieid=" + movieid +
				", keyword='" + keyword + '\'' +
				", titlecn='" + titlecn + '\'' +
				", titleen='" + titleen + '\'' +
				", othersCn='" + othersCn + '\'' +
				", othersen='" + othersen + '\'' +
				", director='" + director + '\'' +
				", actor='" + actor + '\'' +
				", tagName='" + tagName + '\'' +
				'}';
	}

	public String toMovieWordString(){
		StringBuilder sb = new StringBuilder();

		if(StringUtils.isNotBlank(titlecn)){
			sb.append(titlecn);
			sb.append("#");
		}
		if(StringUtils.isNotBlank(othersCn)){
			sb.append(othersCn);
			sb.append("#");
		}
		if(StringUtils.isNotBlank(keyword)){
			sb.append(keyword);
			sb.append("#");
		}
		if(StringUtils.isNotBlank(director)){
			sb.append(director);
			sb.append("#");
		}
		if(StringUtils.isNotBlank(actor)){
			sb.append(actor);
			sb.append("#");
		}
		if(StringUtils.isNotBlank(tagName)){
			sb.append(tagName);
			sb.append("#");
		}
		return sb.toString();
	}
}