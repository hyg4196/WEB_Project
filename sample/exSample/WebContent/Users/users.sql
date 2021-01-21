-- 회원관리 테이블
-- 테이블 구조 `tbl_users`

create table tbl_users(
	name varchar2(20) not null,			-- 회원이름
	userid varchar2(20) primary key,			-- 회원 ID
	passwd varchar2(50) not null,			-- 비밀번호
	tel varchar2(14) not null,			-- 전화(지역/핸드폰)
	email varchar2(100),				-- 이메일
	first_time date default sysdate,		-- 가입날짜
	last_time date					-- 마지막 로그인날짜
);
create sequence tbl_users_seq_idx;
