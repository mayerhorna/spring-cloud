package com.example.sentence.domain;

import java.util.List;

public class SentenceDto {
	private Long sentenceId;
	private List<Sentence> sentences;
	public Long getSentenceId() {
		return sentenceId;
	}
	public void setSentenceId(Long sentenceId) {
		this.sentenceId = sentenceId;
	}
	public List<Sentence> getSentences() {
		return sentences;
	}
	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}
	
}
