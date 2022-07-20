package com.example.sentence.service;

import com.example.sentence.domain.Word;

public interface WordService {
	public Word getPronoun();
	public Word getVerb();
	public Word getAdjective();
	public Word getNoun();
	public Word getArticle();
}
