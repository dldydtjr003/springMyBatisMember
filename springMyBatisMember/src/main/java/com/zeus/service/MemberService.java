package com.zeus.service;

import java.util.List;

import com.zeus.domain.Member;

public interface MemberService {

	// 회원가입 -> 비즈니스 모델(회원 테이블 가입, 회원 권한 테이블 가입)
	public int register(Member member) throws Exception;

	// 회원 리스트, 정보, 수정, 삭제, 권한삭제
	public List<Member> list() throws Exception;

	public Member read(Member member) throws Exception;

	public int update(Member member) throws Exception;

	// 회원삭제 -> 비즈니스 모델(회원 테이블 삭제, 회원 권한 테이블 삭제)
	public int delete(Member member) throws Exception;
}
