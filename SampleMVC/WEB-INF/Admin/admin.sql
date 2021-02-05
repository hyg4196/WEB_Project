-- 관리자 테이블
-- 테이블 구조 `admin`

CREATE TABLE tbl_admin (
  idx number NOT NULL,			--  고유번호
  adminid varchar2(20) NOT NULL ,	--  아이디
  adminpass varchar2(20) NOT NULL ,	--  비밀번호
  regdate date default NULL,            --  등록일자
  rate number(2) default NULL,          --  권한 순위
  PRIMARY KEY  (num)
);
create sequence tbl_admin_seq_idx;
