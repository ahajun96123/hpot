package com.jkl.hpot.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jkl.hpot.service.BoardService;
import com.jkl.hpot.service.MemberService;
import com.jkl.hpot.vo.BoardVO;
import com.jkl.hpot.vo.MemberVO;


@Controller
public class HpotController {
	
	@Autowired
	private BoardService bs;
	private ModelAndView mav;
	
	@Autowired
	private MemberService ms;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////영표////////////////////////////////////////////////////

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start() {

		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}

	/*@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {

		return "main";
	}*/

	// 회원가입 처리
	@RequestMapping(value = "/memberjoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		String encPassword = passEncoder.encode(memberVO.getPassword());
		memberVO.setPassword(encPassword);
		mav = ms.memberJoin(memberVO);
		return mav;
	}

	// 로그인 처리
	@RequestMapping(value = "/memberlogin", method = RequestMethod.POST)
	public ModelAndView memberLogin(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		mav = ms.memberLogin(memberVO);
		return mav;
	}
	
	// 아이디찾기
	@RequestMapping(value = "/idfind", method = RequestMethod.GET)
	public String idFind() {
		return "idFind";
	}
	
	// 비밀번호 찾기	
	@RequestMapping(value = "/pwfind", method = RequestMethod.GET)
	public String pwFind() {
		return "pwFind";
	}

	// 로그아웃 처리
	@RequestMapping(value = "/memberlogout", method = RequestMethod.POST)
	public ModelAndView memberLogout(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		mav = ms.memberLogout(memberVO);
		return mav;
	}

	// 관리자의 회원목록 열람
	@RequestMapping(value = "/memberlist", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView memberList(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		mav = ms.memberList(memberVO);
		return mav;
	}

	// 회원의 내정보 열람
	@RequestMapping(value = "/memberinfomation", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView memberinfo(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		mav = ms.memberInfo(memberVO);
		return mav;
	}

	// 관리자의 회원 삭제
	@RequestMapping(value = "/memberdelete", method = { RequestMethod.GET, RequestMethod.POST })
	public String memberdelete(@RequestParam("id") String id) {
		ms.memberDelete(id);
		return "redirect:/memberlist";
	}

	// 회원의 내정보 열람
	@RequestMapping(value = "/memberalter", method = RequestMethod.POST)
	public ModelAndView memberalter(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		String encPassword = passEncoder.encode(memberVO.getPassword());
		memberVO.setPassword(encPassword);
		mav = ms.memberalter(memberVO);
		return mav;
	}
	
	// 아이디찾기
	@RequestMapping(value = "/idfindservice", method = RequestMethod.POST)
	public ModelAndView memberidfindservice(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		mav = ms.idfind(memberVO);
		return mav;
	}
	
	@RequestMapping(value = "/pwfindservice", method = RequestMethod.POST)
	public ModelAndView memberpwfindservice(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();
		mav = ms.pwfind(memberVO);
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////준호////////////////////////////////////////////////////
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		String which = null;
		request.setAttribute("which", which);
		return "main";
	}*/
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request) {
		String which = null;
		request.setAttribute("which", which);
		return "main";
	}
	@RequestMapping(value = "/postCheck", method = RequestMethod.GET)
	public ModelAndView post(HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		mav = new ModelAndView();
		String which = request.getParameter("which");
		if(which.equals("")) {
			mav.addObject("msg","게시판을 선택해주세요.");
		}else {
			request.setAttribute("which", which);
			mav.setViewName("post");
		}
		return mav;
	}
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BoardVO boardVO) {
		response.setContentType("text/html;charset=UTF-8");
		mav = new ModelAndView();
		int page=1;
		int limit=12;
		String category = request.getParameter("category");
		System.out.println(request.getParameter("array"));
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("search")!=null) {
			String option = request.getParameter("option");
			String search = request.getParameter("search");
			if(option.equals("제목")) {
				boardVO.setbSubject(search);
				boardVO.setSearchValue(search);
				boardVO.setColumn("BSUBJECT");
				boardVO.setInSearch(1);
			}else if(option.equals("태그")) {
				boardVO.setbTag(search);
				boardVO.setSearchValue(search);
				boardVO.setColumn("BTAG");
				boardVO.setInSearch(2);
			}else if(option.equals("작성자")) {
				boardVO.setId(search);	
				System.out.println(boardVO.getId());
				boardVO.setSearchValue(search);
				boardVO.setColumn("ID");
				boardVO.setInSearch(3);
			}else {
				boardVO.setbContent(search);
				boardVO.setSearchValue(search);
				boardVO.setColumn("BCONTENT");
				boardVO.setInSearch(4);
			}
			System.out.println("컬럼= "+boardVO.getColumn()+" 검색값 ="+boardVO.getSearchValue());
			mav = bs.boardList(boardVO,page,limit);
		}
		if (request.getParameter("array")!=null) {
			if(request.getParameter("array").equals("평점")) {
				boardVO.setInArray(1);
			}else if(request.getParameter("array").equals("조회")) {
				boardVO.setInArray(2);
			}else if(request.getParameter("array").equals("추천")){
				boardVO.setInArray(3);
			}else {
				boardVO.setInArray(4);
			}
		}
		if (category!=null) {
			boardVO.setbCategory(request.getParameter("category"));
			System.out.println("array = "+boardVO.getInArray());
			boardVO.setInCate(1);
			System.out.println(boardVO.getInCate());
			String which = request.getParameter("which");
			System.out.println(which);
			boardVO.setbWhich(which);
			mav = bs.boardList(boardVO,page,limit);
		}else if(request.getParameter("option")==null){
			System.out.println(request.getParameter("array"));
			System.out.println("array = "+boardVO.getInArray());
			System.out.println(boardVO.getInCate());
			String which = request.getParameter("which");
			System.out.println(which);
			boardVO.setbWhich(which);
			mav = bs.boardList(boardVO,page,limit);
		}
		return mav;
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String boardWrite(HttpServletRequest request, HttpServletResponse response,@ModelAttribute BoardVO boardVO) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=UTF-8");
		bs.boardWrite(boardVO);
		String which= URLEncoder.encode(boardVO.getbWhich(), "UTF-8");
		return "redirect:/boardList?which="+which;
	}
	
	@RequestMapping(value = "/boardView", method = RequestMethod.GET)
	public ModelAndView boardView(HttpServletRequest request,@ModelAttribute BoardVO boardVO) {
		mav = new ModelAndView();
		int view = Integer.parseInt(request.getParameter("bNum"));
		boardVO.setbNum(view);
		mav = bs.boardView(boardVO);
		
		return mav;
	}
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public ModelAndView modifyForm(HttpServletRequest request,@ModelAttribute BoardVO boardVO) {
		mav = new ModelAndView();
		int bnum = Integer.parseInt(request.getParameter("bNum"));
		boardVO.setbNum(bnum);
		mav = bs.modifyForm(boardVO);
		return mav;
	}
	
	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public String boardDelete(HttpServletRequest request,HttpServletResponse response,@ModelAttribute BoardVO boardVO) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=UTF-8");
		int view = Integer.parseInt(request.getParameter("bNum"));
		boardVO.setbNum(view);
		String which= URLEncoder.encode(request.getParameter("which"), "UTF-8");
		bs.boardDelete(boardVO);
		return "redirect:/boardList?which="+which;
	}
	

	@RequestMapping(value = "/boardModify", method = RequestMethod.POST)
	public String boardModify(HttpServletRequest request,@ModelAttribute BoardVO boardVO) {
		bs.boardModify(boardVO);
		return "redirect:/boardView?bNum="+boardVO.getbNum();
	}
}
