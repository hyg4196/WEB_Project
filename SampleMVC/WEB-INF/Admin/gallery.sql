-- 포토 갤러시 테이블
-- 테이블 구조 'tbl_gallery`

CREATE TABLE tbl_gallery (
  idx number NOT NULL ,			--  고유번호
  gubun number(1) NOT NULL ,		--  포토종류(1:   2:  3:가족   4:프로필 )
  subject varchar2(100) NOT NULL ,	--  제목
  photo varchar2(255) NOT NULL ,	--  사진
  regdate date default NULL,		--  등록일자
  PRIMARY KEY  (num)
);
create sequence tbl_gallery_seq_idx;
