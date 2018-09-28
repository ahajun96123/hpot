package com.jkl.hpot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.jkl.hpot.dao.BoardDAO;
import com.jkl.hpot.vo.BoardVO;
import com.jkl.hpot.vo.CommentVO;
import com.jkl.hpot.vo.PageInfo;



@Service
public class BoardService {

	private ModelAndView mav;
	@Autowired
	private BoardDAO boardDAO;
	private BoardVO boardVO;
	private PageInfo pageInfo;
	private CommentVO commentVO;
	
	@Autowired
	private HttpSession session;

	public String boardWrite(BoardVO boardVO) {
		int maxBnum = boardDAO.boardMAX_Bnum();
		boardVO.setbNum(maxBnum);
		System.out.println("MAX_bNum ="+boardVO.getbNum());
		if(boardVO.getbNum()>=1) {
			boardVO.setbNum(boardVO.getbNum()+1);
		}else {
			boardVO.setbNum(1);
		}
		boardDAO.boardWrite(boardVO);
		return "redirect:/boardList?which="+boardVO.getbWhich();
	}
	
	public ModelAndView boardList(BoardVO boardVO,int page,int limit){
		mav = new ModelAndView();
		pageInfo = new PageInfo();
		int startrow = (page - 1) * limit + 1;
		int endrow = page * limit;
		boardVO.setStart(startrow);
		boardVO.setEnd(endrow);
		System.out.println(boardVO.getStart());
		System.out.println(boardVO.getEnd());
		System.out.println(boardVO.getbWhich());
		if(boardVO.getInCate()==1) {
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			if(boardVO.getInArray()==1) {
				boardList = boardDAO.boardListCateGrade(boardVO);
			}else if (boardVO.getInArray()==2) {
				boardList = boardDAO.boardListCateView(boardVO);
			}else if (boardVO.getInArray()==3) {
				boardList = boardDAO.boardListCateRecommend(boardVO);
			}else if (boardVO.getInArray()==4) {
				boardList = boardDAO.boardListCateBest(boardVO);
			}else {
				boardList = boardDAO.boardListCate(boardVO);
			}
			System.out.println("boardList size ="+boardList.size());
			int listCateCount = boardDAO.listCateCount(boardVO);
			// 페이지 계산을 위한 부분
			// 최대로 필요한 페이지 개수 계산
			int maxPage = (int) ((double) listCateCount / limit + 1);
			// 현재 페이지에 보여줄 시작페이지 번호
			int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;

			int endPage = startPage + 10 - 1;
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
			pageInfo.setEndPage(endPage);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setListCount(listCateCount);
			System.out.println(boardVO.getbWhich());
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("boardList", boardList);
			
		}else {
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			if(boardVO.getInArray()==1) {
				boardList = boardDAO.boardListGrade(boardVO);
			}else if (boardVO.getInArray()==2) {
				boardList = boardDAO.boardListView(boardVO);
			}else if (boardVO.getInArray()==3) {
				boardList = boardDAO.boardListRecommend(boardVO);
			}else if (boardVO.getInArray()==4) {
				boardList = boardDAO.boardListBest(boardVO);
			}else if(boardVO.getInSearch()==1) {
				boardList = boardDAO.boardSearchSubject(boardVO);
			}else if(boardVO.getInSearch()==2) {
				boardList = boardDAO.boardSearchTag(boardVO);
			}else if(boardVO.getInSearch()==3) {
				boardList = boardDAO.boardSearchId(boardVO);
			}else if(boardVO.getInSearch()==4) {
				boardList = boardDAO.boardSearchContent(boardVO);
			}else {
				boardList = boardDAO.boardList(boardVO);
			}
			int listCount = boardDAO.listCount(boardVO);
			if(boardVO.getInSearch()>=1) {
				listCount = boardDAO.listSearchCount(boardVO);
			}
			System.out.println("boardList size ="+boardList.size());
			System.out.println("listCount = " + listCount);
			// 페이지 계산을 위한 부분
			// 최대로 필요한 페이지 개수 계산
			int maxPage = (int) ((double) listCount / limit + 1);
			// 현재 페이지에 보여줄 시작페이지 번호
			int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;

			int endPage = startPage + 10 - 1;
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
			pageInfo.setEndPage(endPage);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setListCount(listCount);
			System.out.println(boardVO.getbWhich());
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("boardList", boardList);
		}
		mav.addObject("array", boardVO.getInArray());
		mav.addObject("which", boardVO.getbWhich());
		mav.addObject("category", boardVO.getbCategory());
		mav.addObject("search",boardVO.getSearchValue());
		mav.setViewName("List");
		return mav;
	}

	public ModelAndView boardView(BoardVO boardVO) {
		mav = new ModelAndView();
		BoardVO viewBoard = boardDAO.boardView(boardVO);
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		commentList = boardDAO.boardComment(boardVO);
		mav.addObject("view", viewBoard);
		mav.addObject("commentList",commentList);
		mav.setViewName("View");
		return mav;
	}

	public ModelAndView modifyForm(BoardVO boardVO) {
		mav = new ModelAndView();
		BoardVO modify = boardDAO.boardView(boardVO);
		mav.addObject("modify", modify);
		mav.setViewName("Modify");
		return mav;
	}

	public String boardModify(BoardVO boardVO) {
		boardDAO.boardModify(boardVO);
		return "redirect:/boardView?bNum="+boardVO.getbNum();
	}
}
