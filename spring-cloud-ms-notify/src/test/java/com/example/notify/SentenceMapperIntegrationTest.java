package com.example.notify;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.notify.mappers.SentenceMapper;
import com.example.sentence.domain.Sentence;
import com.example.sentence.domain.SentenceDto;

@SpringBootTest
public class SentenceMapperIntegrationTest {
	  @Autowired
      private SentenceMapper sentenceMapper;

      @Test
      @Transactional
      @Rollback
      public void save_nuevaHoracion_asignaPKNotNull() throws Exception {
    	  Sentence sentence = new Sentence();
    	  sentence.setText("Hola3");
    	  sentence.setCreated(new Date());
          sentenceMapper.save(sentence);
          assertNotNull(sentence.getId());
      }
      
      @Test 
      @Transactional
      @Rollback
      public void save_fechaNull_retornaException() throws Exception {
    	  Sentence sentence = new Sentence();
    	  sentence.setText("Hola");
    	  sentence.setCreated(null);
          assertThrows(Exception.class, ()-> sentenceMapper.save(sentence));
      }
      
      
      @Test
      @Transactional 
      @Rollback
      public void getAll_sinfiltro_obtieneElUltimoGrabado() throws Exception {
    	  Sentence sentence = new Sentence();
    	  String text = "Hola3";
    	  sentence.setText(text);
    	  sentence.setCreated(new Date());
          sentenceMapper.save(sentence);
          SentenceDto sentenceDto = new SentenceDto();
          sentenceMapper.getAll(sentenceDto);
          List<Sentence> sentences = sentenceDto.getSentences();
          Sentence lastSentence = sentences.get(sentences.size() - 1);
          assertEquals(lastSentence.getText(), text);
      }
      
}
