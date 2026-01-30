# 📝 MyBatis 기반 회원관리 및 권한 데이터베이스 설계 (Oracle)

이 저장소는 Java와 Database를 연동하는 **MyBatis 실습을 위한 Oracle SQL 스크립트**를 포함하고 있습니다. 
컴퓨터 전공자 및 시니어 입문자분들이 DB 구축 원리를 쉽게 이해할 수 있도록 구성되었습니다.


# SpringBoot Member 및 Auth

## 🖥️ 소개
MyBatis 방식으로 CURD 테스팅<br>

## 🕰️ 개발 기간
* 2026.01.30 - 2026.01.30

### ⚙️ 개발 환경
- 운영체제: Windows 11 home
- 개발 도구: SpringBoot 4.1.9
- JDK 버전: OpenJDK 21.0.6
- 프로그래밍 언어: Java 21
- 형상관리 도구: Git, GitHub

## 📌 주요 기능
#### 회원 관리
- 회원 가입 : 신규 입고 상품 선택 및 입고 정보 입력(텍스트 파일에 추가 및 저장)
- 회원정보 수정 : 삭제대상 상품 선택 및 입고 재고에 대한 삭제/차감 처리(텍스트 파일에 삭제/차감 및 저장)
- 회원 탈퇴 : 현재 재고 보유 상태 출력
- 회원 리스트: 조회하고자 하는 특정 상품 선택 후 재고 보유 상태 출력
- 회원 검색 : 재고 입고일자 기준으로 오름차순/내림차순 출력
- 회원 권한설정 

## 🚀 데이타 베이스 정보
```sql
-- 스크립트 모드 활성화 (12c 이상)
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

-- 기존 사용자 삭제 및 신규 생성
DROP USER KHH CASCADE; 
CREATE USER KHH IDENTIFIED BY KHH
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP; 

-- 필수 권한 부여
GRANT CONNECT, RESOURCE, DBA TO KHH;

# 🛠 2. 테이블 및 시퀀스 생성게시판의 핵심 정보를 저장할 테이블 구조와 게시글 번호 자동 증가를 위한 시퀀스입니다.
테이블 구조

CREATE TABLE MYBATISMEMBER(
    NO NUMBER, 
    ID VARCHAR2(50) NOT NULL, 
    PW VARCHAR2(50) NOT NULL, 
    NAME VARCHAR2(100) NOT NULL, 
    COIN NUMBER(10) DEFAULT 0, 
    REGDATE DATE DEFAULT SYSDATE, 
    MODDATE DATE DEFAULT SYSDATE, 
    ENABLED CHAR(1) DEFAULT '1', 
    PRIMARY KEY (NO) 
); 

DESC MYBATISMEMBER;

CREATE TABLE MYBATISMEMBERAUTH( 
    NO NUMBER NOT NULL, 
    AUTH VARCHAR2(50) NOT NULL,
    CONSTRAINT MYBATISMEMBER_AUTH_FK FOREIGN KEY(NO) REFERENCES MYBATISMEMBER(NO)
); 
 
CREATE SEQUENCE MYBATISMEMBER_SEQ 
    START WITH 1 
    INCREMENT BY 1;

⚠️ 주의사항실습 중 데이터가 잘못되었다면 ROLLBACK;
명령어로 되돌릴 수 있습니다.
데이터 확정을 위해서는 COMMIT;
