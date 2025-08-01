CREATE SEQUENCE member_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE SEQUENCE emotion_entry_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE SEQUENCE emotion_analysis_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

commit;

select * from user_tables;
select * from member;
select * from EMOTION_ENTRY;
select * from EMOTION_ANALYSIS;
