package com.zeus.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.domain.Member;
import com.zeus.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@MapperScan(basePackages = "com.zeus.mapper")
@Slf4j
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/insertForm")
	public String memberInsertForm(Model model) {
		return "member/insertForm";
	}
	
	@PostMapping("/insert")
	public String memberInsert(Member member, Model model) {
		try {
			int count = memberService.register(member);
			if (count > 0) {
				model.addAttribute("message", "등록이 완료되었습니다.");
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "등록이 실패했습니다.");
		return "member/failed";
	}

	@GetMapping("/memberList")
	public String memberList(Member board, Model model) {
		try {
			List<Member> memberList = memberService.list();
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/memberList";
	}

	@GetMapping("/detail")
	public String memberDetail(Model model, Member m) {
		try {
			Member member = memberService.read(m);
			if (member != null) {
				model.addAttribute("member", member);
				model.addAttribute("message", "상세정보 조회 성공했습니다.");
				return "member/detail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "상세정보 조회 실패했습니다.");
		return "member/failed";
	}

	@GetMapping("/delete")
	public String memberDelete(Model model, Member member) {
		try {
			int count = memberService.delete(member);
			if (count > 0) {
				model.addAttribute("message", " 삭제 성공했습니다.");
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", " 삭제 실패했습니다.");
		return "member/failed";
	}

	@GetMapping("/updateForm")
	public String memberUpdateForm(Model model, Member m) {
		Member member;
		try {
			member = memberService.read(m);
			if (member != null) {
				model.addAttribute("member",member);
				return "member/updateForm";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "고객님의 정보가 없습니다.");
		return "member/failed";
	}

	@PostMapping("/update")
	public String memberUpdate(Model model, Member member) {
		try {
			int count = memberService.update(member);
			if (count > 0) {
				model.addAttribute("message", "수정이 완료되었습니다.");
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "수정이 실패했습니다.");
		return "member/failed";
	}

//	@GetMapping("/search")
//	public String boardSearch(Member member, Model model) {
//		try {
//			List<Member> memberList = memberService.search(member);
//			model.addAttribute("memberList", memberList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "board/boardList";
//	}

}
