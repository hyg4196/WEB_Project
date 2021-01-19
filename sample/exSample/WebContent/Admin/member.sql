-- 회원관리 테이블
-- 테이블 구조 `member`

create table member(
	idx number not null primary key ,		-- 기본키 자동증가
	name varchar2(20) not null,			-- 회원이름
	birth varchar2(8) not null,			-- 생년월일
	userid varchar2(20) not null,			-- 회원 ID
	passwd varchar2(20) not null,			-- 비밀번호
	addr_gubun varchar2(1) not null,		-- 주소(회사/집)
	zipcode varchar2(7) not null,			-- 우편번호
	addr1 varchar2(100) not null,			-- 주소
	addr2 varchar2(100) not null,			-- 나머지주소
	tel1 varchar2(4) not null,			-- 전화(지역/핸드폰)
	email varchar2(100),				-- 이메일
	job varchar2(20),				-- 직업
	intro varchar2(2000),				-- 자기소개
	favorite varchar2(255),				-- 관심분야
	rate number(2) not null ,			-- 회원등급(0:일반/1:관리자)
	mileage number,					-- 마일리지 점수
	first_time date,				-- 가입날짜
	last_time date					-- 마지막 로그인날짜
);
create sequence member_seq_idx;
