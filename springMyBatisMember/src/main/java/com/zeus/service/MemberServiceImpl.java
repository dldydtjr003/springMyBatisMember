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
		return memberMapper.list();
	}

	@Override
	public Member read(Member member) throws Exception {
		return memberMapper.read(member);
	}

	@Override
	@Transactional
	public int update(Member member) throws Exception {
		int count = memberMapper.update(member);
		if(count > 0) {
			memberMapper.deleteAuth(member);
			List<MemberAuth> authList = member.getAuthList();
			for(int i = 0; i < authList.size(); i++) {
				MemberAuth memberAuth = authList.get(i);
				String auth = memberAuth.getAuth();
				if(auth == null || auth.trim().length() == 0) {
					continue;
				}
				memberAuth.setNo(member.getNo());
				memberMapper.createAuth(memberAuth);
			}
		}
		return count;
	}

	@Override
	@Transactional
	public int delete(Member member) throws Exception {
		int count = memberMapper.delete(member);
		if(count > 0) {
			memberMapper.deleteAuth(member);
		}
		return count;
	}

	@Override
	public List<Member> search(Member member) throws Exception {
		List<Member> memberList = memberMapper.search(member);
		return memberList;
	}
}
