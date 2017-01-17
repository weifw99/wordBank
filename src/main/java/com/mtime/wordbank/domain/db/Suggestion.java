package com.mtime.wordbank.domain.db;

import java.io.Serializable;

/**
 * 提示词库
 * 
 * @author percy
 *
 */
public class Suggestion implements Serializable{

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 提示词
	 */
	private String userWordName;
	/**
	 * 处理后的词:去掉空格,特殊符号...
	 */
	private String wordName;
	/**
	 * 拼音
	 */
	private String wordPinyin;
	/**
	 * 数据状态
	 */
	private Integer dataStatus;
	/**
	 * 创建时间
	 */
	private String createdDate;
	/**
	 * 修改时间
	 */
	private String updatedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserWordName() {
		return userWordName;
	}

	public void setUserWordName(String userWordName) {
		this.userWordName = userWordName;
	}

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public String getWordPinyin() {
		return wordPinyin;
	}

	public void setWordPinyin(String wordPinyin) {
		this.wordPinyin = wordPinyin;
	}


	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Suggestion [id=" + id + ", userWordName=" + userWordName
				+ ", wordName=" + wordName + ", wordPinyin=" + wordPinyin
				+ ", dataStatus=" + dataStatus + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}

}
