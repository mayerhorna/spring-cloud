-- crear base de datos:  db_ms_notify

DROP sequence IF EXISTS seq_tb_sentence_log;
CREATE sequence seq_tb_sentence_log INCREMENT 1 START 1; 

DROP TABLE IF EXISTS tb_sentence_log;
CREATE TABLE tb_sentence_log (
  tb_sentence_log_id numeric(10,0) NOT NULL PRIMARY KEY,
  sentence_text text NOT NULL,  
  created timestamp without time zone NOT NULL
);


CREATE OR REPLACE FUNCTION sp_list_sentence(OUT result refcursor)  AS
$BODY$	
        BEGIN		
		OPEN  result FOR 
				SELECT tb_sentence_log_id, sentence_text, created FROM tb_sentence_log  ;                  
        END;
$BODY$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sp_save_sentence(IN sentence_text VARCHAR, IN created DATE, OUT tb_sentence_log_id BIGINT)
  AS
$BODY$	
		--DECLARE tb_sentence_log_id NUMERIC(10,0);		
        BEGIN	
        	SELECT nextval('seq_tb_sentence_log') into tb_sentence_log_id;        			
			INSERT INTO tb_sentence_log(tb_sentence_log_id, sentence_text, created) 
            VALUES (tb_sentence_log_id, sentence_text, created);
			--SELECT tb_sentence_log_id;
        END;
$BODY$ LANGUAGE plpgsql;
