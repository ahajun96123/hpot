package com.jkl.hpot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.jkl.hpot.dao.MemberDAO;
import com.jkl.hpot.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO mdao;
	
	private ModelAndView mav;
	
	private MemberVO memberVO;
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	public ModelAndView memberJoin(MemberVO memberVO){
		mav = new ModelAndView();
		int result = mdao.memberJoin(memberVO);
		if(result == 0) {
			mav.addObject("msg", "회원가입실패 다시시도해 주세요.");
			mav.setViewName("login");

		}else {
			mav.addObject("msg", "정상적으로 회원가입이 완료되었습니다.");
			session.setAttribute("id", memberVO.getId());
			mav.setViewName("main");
		}
		
		return mav;
	}

	public ModelAndView memberLogin(MemberVO memberVO) {
		mav = new ModelAndView();
		MemberVO mv = mdao.memberLogin(memberVO);
		mav.addObject("info", mv);
			if(passEncoder.matches(memberVO.getPassword(),mv.getPassword())){
				session.setAttribute("id", memberVO.getId());
				mav.setViewName("main");
			}else {
				mav.addObject("msg2", "아이디 또는 비밀번호를 확인해주세요");
				mav.setViewName("login");
			}
		return mav;
	}

	public ModelAndView memberLogout(MemberVO memberVO) {
		mav = new ModelAndView();
		session.invalidate();
		mav.addObject("msg", "정상적으로 로그아웃 되었습니다.");
		mav.setViewName("login");
		return mav;
	}

	public ModelAndView memberList(MemberVO memberVO) {
		mav = new ModelAndView();
		List<MemberVO> mv = mdao.memberList(memberVO);
		mav.addObject("list", mv);
		mav.setViewName("memberList");
		return mav;
	}

	public ModelAndView memberInfo(MemberVO memberVO) {
		mav = new ModelAndView();
		memberVO.setId((String)session.getAttribute("id"));
		MemberVO mv = new MemberVO();
		mv = mdao.memberInfo(memberVO);
		mav.addObject("info", mv);
		mav.setViewName("memberInfo");
		return mav;
	}

	public void memberDelete(String id) {
		mdao.memberDelete(id);
	}

	public ModelAndView memberalter(MemberVO memberVO) {
		mav = new ModelAndView();
		memberVO = mdao.memberAlter(memberVO);
		mav.addObject("info", memberVO);
		mav.setViewName("redirect:/memberinfomation");
		return mav;
	}

	public ModelAndView idfind(MemberVO memberVO) {
		mav = new ModelAndView();
		List<MemberVO> mv = mdao.idFind(memberVO);
		mav.addObject("idfind", mv);
		mav.setViewName("idFind2");
		return mav;
	}

	public ModelAndView pwfind(MemberVO memberVO) {
		mav = new ModelAndView();
		MemberVO mv = new MemberVO();
		mv = mdao.pwFind(memberVO);
		mav.addObject("pwfind", mv);
		mav.setViewName("pwFind2");
		return mav;
	}


}
