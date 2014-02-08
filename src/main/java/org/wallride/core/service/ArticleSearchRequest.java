package org.wallride.core.service;

import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.wallride.core.domain.Post;
import org.wallride.core.repository.ArticleFullTextSearchTerm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ArticleSearchRequest implements Serializable {

	private String keyword;
	private Post.Status status;
	private String language;

	public ArticleSearchRequest() {
		this.language = LocaleContextHolder.getLocale().getLanguage();
	}

	public String getKeyword() {
		return keyword;
	}

	public Post.Status getStatus() {
		return status;
	}

	public String getLanguage() {
		return language;
	}

	public boolean isEmpty() {
		if (StringUtils.hasText(getKeyword())) {
			return false;
		}
		if (getStatus() != null) {
			return false;
		}
		if (StringUtils.hasText(getLanguage())) {
			return false;
		}
		return true;
	}

	public boolean isAdvanced() {
		return false;
	}

	public ArticleFullTextSearchTerm toFullTextSearchTerm() {
		ArticleFullTextSearchTerm term = new ArticleFullTextSearchTerm();
		BeanUtils.copyProperties(this, term);
		return term;
	}

	public static class Builder  {

		private String keyword;
		private Post.Status status;
		private String language;

		public Builder() {
		}

		public Builder keyword(String keyword) {
			this.keyword = keyword;
			return this;
		}

		public Builder status(Post.Status status) {
			this.status = status;
			return this;
		}

		public Builder language(String language) {
			this.language = language;
			return this;
		}

		public ArticleSearchRequest build() {
			ArticleSearchRequest request = new ArticleSearchRequest();
			request.keyword = keyword;
			request.status = status;
			request.language = language;
			return request;
		}
	}
}
