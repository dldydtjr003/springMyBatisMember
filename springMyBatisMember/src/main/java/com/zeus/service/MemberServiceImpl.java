package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;
import com.zeus.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;

	@Override
	@Transactional
	public int register(Member member) throws Exception {
		int count = memberMapper.create(member);
		if(count > 0) {
			MemberAuth memberAuth = new MemberAuth();
			memberAuth.setNo(member.getNo());
			memberAuth.setAuth("ROLE_USER");
			memberMapper.createAuth(memberAuth);
		}
		return count;
	}

	@Override
	public List<Member> list() throws Exception {
		List<Member> memberList = memberMapper.list();
		return memberList;
	}

	@Override
	public Member read(Member member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int update(Member member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int delete(Member member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
