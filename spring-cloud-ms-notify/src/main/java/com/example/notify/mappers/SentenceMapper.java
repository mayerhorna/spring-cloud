package com.example.notify.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.sentence.domain.SentenceDto;
import com.example.sentence.domain.Sentence;

@Mapper
public interface SentenceMapper {
	void save(Sentence sentence);
	List<Sentence> getAll(SentenceDto sentenceDto);
}