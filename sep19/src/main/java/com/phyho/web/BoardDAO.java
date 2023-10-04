package com.phyho.web;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardDAO {
	public List<Map<String, Object>> boardList(int pageNo);

	public Map<String, Object> boardDetail(int bno);

	public int write(Map<String, Object> map);

	public void countUp(int bno);

	public int deletePost(int bno);

	public int update(Map<String, Object> map);

	public Map<String, Object> login(Map<String, Object> map);

	public List<Map<String, Object>> index_members();

	public List<Map<String, Object>> commentsList(int bno);

	public int commentWrite(Map<String, Object> map);

	public int commentDel(int cno);

	public Map<String, Object> commentdetail(int cno);

	public int commentUpdate(Map<String, Object> map);

}
