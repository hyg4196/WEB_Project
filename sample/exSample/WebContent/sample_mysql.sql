-- 데이터베이스: `sample`
-- USER/PASS : sample/ sam1234

-- 관리자 테이블
-- 테이블 구조 `sam_admin`

CREATE TABLE sam_admin (
  num int NOT NULL auto_increment,	--  고유번호 자동증가
  adminid varchar(20) NOT NULL ,	--  아이디
  adminpass varchar(20) NOT NULL ,	--  비밀번호
  indate date default NULL,             --  등록일자
  rate int(2) default NULL,             --  권한 순위
  PRIMARY KEY  (num)
);


insert into sam_admin (num,adminid,adminpass,indate,rate) values (admin_seq.nextval,'admin','1234',sysdate,0);

-- 회원관리 테이블
-- 테이블 구조 `sam_member`

create table sam_member(
	idx int not null auto_increment ,		-- 기본키 자동증가
	name varchar(20) not null,			-- 회원이름
	userid varchar(20) not null,			-- 회원 ID
	passwd varchar(20) not null,			-- 비밀번호
	zipcode varchar(7)  null,			-- 우편번호
	addr1 varchar(200)  null,			-- 주소
	addr2 varchar(100) not null,			-- 나머지주소
	tel varchar(4) not null,			-- 전화(지역/핸드폰)
	email varchar(100),				-- 이메일
	job varchar(20),				-- 직업
	intro varchar(2000),				-- 자기소개
	favorite varchar(255),				-- 관심분야
	first_time date,				-- 가입날짜
	last_time date					-- 마지막 로그인날짜
);


-- 공지사항 테이블
-- 테이블 구조 'sam_notice`

CREATE TABLE sam_notice (
  num int NOT NULL auto_increment,      --  고유번호
  subject varchar(255) NOT NULL ,	--  제목
  contents varchar(2000) NOT NULL,	--  내용
  indate date default NULL,		--  등록일자
  readcnt int default NULL,		--  조회수
  PRIMARY KEY  (num)
);


-- 답변형 게시판 테이블
-- 테이블 구조 `sam_board`

CREATE TABLE sam_board (
  num int NOT NULL auto_increment,	--  고유번호
  pass varchar(20) NOT NULL ,		--  비밀번호
  name varchar(20) NOT NULL ,		--  작성자 이름
  indate date NOT NULL ,		--  작성일자
  subject varchar(100) NOT NULL,	--  제목
  contents varchar(2000) NOT NULL,	--  내용
  parent int(2) not null,		--  최상위 부모글번호
  realparent int(2) not null,		--  바로 한수준 위의 글번호
  indent int(2) not null,		--  들여쓰기 기준
  depth int(2) not null,		--  답변글의 넘버를 저장하는 필드로 
					--  첫번째 답변글은 1, 두 번째 답변글은 2,
					--  n번째 답변글은 n의 값이 저장
  readcnt int ,				--  조회수
  ip varchar(20) not null,		--  작성자 ip주소
  PRIMARY KEY  (num)
);


-- 포토 갤러시 테이블
-- 테이블 구조 'sam_gallery`

CREATE TABLE sam_gallery (
  num int NOT NULL auto_increment,	--  고유번호
  gubun int(1) NOT NULL ,		--  포토종류(1:/백일/2:돌/3:가족/4:프로필
  subject varchar(100) NOT NULL ,	--  제목
  photo varchar(255) NOT NULL ,		--  사진
  indate date default NULL,								--  등록일자
  PRIMARY KEY  (num)
);


-- 파일 업로드용 테이블
-- 테이블 구조 `sam_pds`

CREATE TABLE sam_pds (
  num int NOT NULL auto_increment,		--  고유번호
  name varchar(20) NOT NULL ,			--  작성자 이름
  pass varchar(20) NOT NULL ,			--  비밀번호
  subject varchar(100) NOT NULL,		--  제목
  contents varchar(2000) NOT NULL,		--  내용
  indate date NOT NULL ,			--  작성일자
  readcnt int(2) ,				--  조회수
  filename varchar(255) null,			--  파일이름
  PRIMARY KEY  (num)
);


-- 방명록 테이블(답변글 없음)
-- 테이블 구조 `sam_guest`

create table sam_guest (
	idx int not null auto_increment,	-- 고유번호/자동증가
	name varchar(50) not null,		-- 작성자
	subject varchar(100) not null,		-- 제목
	contents varchar(2000) not null,	-- 내용						-- 내용
	indate date not null,			-- 작성일자
	readcnt int,											-- 조회수
	primary key(idx)										-- 기본키
);


-- 운영달력 테이블
-- 테이블 구조 `sam_calendar`

create table sam_calendar (
	num int not null auto_increment,		-- 고유번호/자동증가
	indate date not null,				-- 행사 또는 기념일
	subject varchar(100) not null,			-- 제목
	contents varchar(2000) not null,							-- 내용
	primary key(num)										-- 기본키
);


-- 설문조사 테이블
-- 테이블 구조 `sam_question'

create table sam_question (
	num int not null auto_increment,-- 고유번호/자동증가
	question varchar(255) not null,	-- 질문 내용을 저장
	reply1 varchar(100) ,		-- 답변1(사용자가 답변할 선택사항 1의 내용)
	reply2 varchar(100) ,		-- 답변2(사용자가 답변할 선택사항 2의 내용)
	reply3 varchar(100) ,		-- 답변3(사용자가 답변할 선택사항 3의 내용)
	reply4 varchar(100) ,		-- 답변4(사용자가 답변할 선택사항 4의 내용)
	replynum1 int ,			-- 답변1(답변1의 응답자 수)
	replynum2 int ,			-- 답변2(답변2의 응답자 수)
	replynum3 int ,			-- 답변3(답변3의 응답자 수)
	replynum4 int ,			-- 답변4(답변4의 응답자 수)
	replytot  int ,			-- 전체 응답자 수
	indate date not null,		-- 질문 등록일자
	primary key(num)		-- 기본키
);

