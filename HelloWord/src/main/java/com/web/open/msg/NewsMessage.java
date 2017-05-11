package com.web.open.msg;

import java.util.List;

public class NewsMessage extends BaseMessage {
	private int articleCount;
	private List<Article> articles;

	public int getArticleCount() {
		return this.articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		String xml = "<xml> "+ super.toString() + "<ArticleCount>" + articleCount
				+ "</ArticleCount>" + "<Articles>";
		for (Article item : articles) {
			xml = xml + item.toString();
		}
		xml = xml + "</Articles> </xml> ";
		return xml;
	}
}