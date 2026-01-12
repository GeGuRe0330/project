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
select * from EMOTION_ENTRY order by created_at desc;
select * from EMOTION_ANALYSIS order by created_at desc;
select * from ANALYSIS_WARM_MESSAGE;

-- 1. 따뜻한 말 테이블 먼저 삭제 (가장 종속된 테이블)
DELETE FROM ANALYSIS_WARM_MESSAGE;

-- 2. 감정 분석 테이블 삭제
DELETE FROM EMOTION_ANALYSIS;

-- 3. 감정 글 테이블 삭제
DELETE FROM EMOTION_ENTRY;

-- 승인 활성화
UPDATE member
SET status = 'ACTIVE'
WHERE email = 'frog2@test.com';

UPDATE member
SET role = 'ADMIN'
WHERE email = 'frog@test.com';
