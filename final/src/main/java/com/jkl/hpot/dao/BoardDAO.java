package com.jkl.hpot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jkl.hpot.vo.BoardVO;
import com.jkl.hpot.vo.CommentVO;
import com.jkl.hpot.vo.PageInfo;

@Repository
public class BoardDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int boardWrite(BoardVO boardVO) {
		return sqlSession.insert("Board.boardWrite",boardVO);
	}
	public List<BoardVO> boardList(BoardVO boardVO){
		/*Map<Object,Object>map = new HashMap<Object,Object>();
		map.put("start", pageInfo.getStart());
		map.put("end", pageInfo.getEnd());
		map.put("bWhich", boardVO.getbWhich());*/
		System.out.println(boardVO.getStart());
		System.out.println(boardVO.getEnd());
		return sqlSession.selectList("Board.boardList",boardVO);
	}

	public int boardMAX_Bnum() {
		return sqlSession.selectOne("Board.MaxBnum");
		
	}

	public int listCount(BoardVO boardVO) {
		return sqlSession.selectOne("Board.listCount",boardVO);
	}
	public List<BoardVO> boardListCate(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListCate",boardVO);
	}
	public int listCateCount(BoardVO boardVO) {
		return sqlSession.selectOne("Board.listCateCount",boardVO);
	}
	public List<BoardVO> boardListCateGrade(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListCateGrade",boardVO);
	}
	public List<BoardVO> boardListCateView(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListCateView",boardVO);
	}
	public List<BoardVO> boardListCateRecommend(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListCateRecommend",boardVO);
	}
	public List<BoardVO> boardListCateBest(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListCateBest",boardVO);
	}
	public List<BoardVO> boardListGrade(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListGrade",boardVO);
	}
	public List<BoardVO> boardListView(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListView",boardVO);
	}
	public List<BoardVO> boardListRecommend(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListRecommend",boardVO);
	}
	public List<BoardVO> boardListBest(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardListBest",boardVO);
	}
	public List<BoardVO> boardSearchSubject(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardSearchSubject",boardVO);
	}
	public List<BoardVO> boardSearchTag(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardSearchTag",boardVO);
	}
	public List<BoardVO> boardSearchId(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardSearchId",boardVO);
	}
	public List<BoardVO> boardSearchContent(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardSearchContent",boardVO);
	}
	public int listSearchCount(BoardVO boardVO) {
		return sqlSession.selectOne("Board.listSearchCount",boardVO);
	}
	public BoardVO boardView(BoardVO boardVO) {
		return sqlSession.selectOne("Board.boardView",boardVO);
	}
	public List<CommentVO> boardComment(BoardVO boardVO) {
		return sqlSession.selectList("Board.boardComment",boardVO);
	}
	public int boardModify(BoardVO boardVO) {
		return sqlSession.update("Board.boardModify",boardVO);
		
	}
	
}
