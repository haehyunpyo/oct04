package com.phyho.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private Util util;

	public List<Map<String, Object>> boardList(int pageNo) {
		pageNo = (pageNo - 1) * 10;
		return boardDAO.boardList(pageNo);
	}

	public Map<String, Object> boardDetail(int bno) {
		boardDAO.countUp(bno);
		return boardDAO.boardDetail(bno);
	}

	public int write(Map<String, Object> map) {
		map.put("m_id", "poseidon");
		map.put("bip", util.getIP());
		return boardDAO.write(map);
	}

	public int deletePost(int bno) {
		return boardDAO.deletePost(bno);
	}

	public int update(Map<String, Object> map) {
		map.put("bip", util.getIP());
		return boardDAO.update(map);
	}

	public Map<String, Object> login(Map<String, Object> map) {
		return boardDAO.login(map);
	}

	public List<Map<String, Object>> index_members() {
		return boardDAO.index_members();
	}

	public List<Map<String, Object>> commentsList(int bno) {
		return boardDAO.commentsList(bno);
	}

	public int commentWrite(Map<String, Object> map) {
		map.put("m_id", "poseidon");
		map.put("cip", util.getIP());
		return boardDAO.commentWrite(map);
	}

	public int commentDel(int cno) {
		return boardDAO.commentDel(cno);
	}

	public Map<String, Object> commentdetail(int cno) {
		return boardDAO.commentdetail(cno);
	}

	public int commentUpdate(Map<String, Object> map) {
		map.put("cip", util.getIP());
		return boardDAO.commentUpdate(map);
	}

}
