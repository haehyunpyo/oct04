package com.phyho.web;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//2023-09-15
@CrossOrigin
@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/board")
	public String boardList(
			@RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo) {
		System.out.println(pageNo);
		List<Map<String, Object>> list = boardService.boardList(pageNo);
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(list);
		json.put("list", arr);
		json.put("pageNo", pageNo);
		json.put("totalcount", list.get(0).get("totalcount"));
		// System.out.println(json.toString());
		return json.toString();
	}

	@GetMapping("/detail")
	public String detail(@RequestParam(name = "bno", required = true) int bno) {
		// //System.out.println("bno : " + bno);
		Map<String, Object> detail = boardService.boardDetail(bno);
		JSONObject json = new JSONObject();
		json.put("detail", detail);

		if (!(detail.get("commentcount").equals("0"))) {
			List<Map<String, Object>> commentsList = boardService.commentsList(bno);
			json.put("commentsList", commentsList);
		}
		// //System.out.println(json.toString());
		return json.toString();
	}

	@PostMapping("/write")
	public String write(@RequestBody Map<String, Object> map) {
		//System.out.println(map);
		int result = boardService.write(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}

	// 2023-09-17
	@PostMapping("/deletePost")
	public String deletepost(@RequestParam(required = true) int bno) {
		// int result = boardService.write(map);
		// //System.out.println("delete : " + bno);
		int result = boardService.deletePost(bno);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}

	// update
	@PatchMapping("/update")
	public String update(@RequestBody Map<String, Object> map) {
		// //System.out.println(map);
		int result = boardService.update(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		// //System.out.println(json.toString());
		return json.toString();
	}

	@PostMapping("/login")
	public String login(@RequestBody Map<String, Object> map) {
		//System.out.println(map);
		Map<String, Object> detail = boardService.login(map);
		JSONObject json = new JSONObject(detail);
		//json.put("result", detail);
		//System.out.println(json.toString());
		return json.toString();
	}

	@GetMapping("/index")
	public String index() {
		JSONObject json = new JSONObject();

		List<Map<String, Object>> list = boardService.boardList(1);
		JSONArray jsonList = new JSONArray(list);
		json.put("list", jsonList);

		List<Map<String, Object>> index_members = boardService.index_members();
		JSONArray members = new JSONArray(index_members);
		json.put("members", members);
		return json.toString();
	}

	@PostMapping("/commentWrite")
	public String commentWrite(@RequestBody Map<String, Object> map) {
		int result = boardService.commentWrite(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		// //System.out.println(map);
		return json.toString();
	}

	// commentDel
	@DeleteMapping("/commentDel")
	public String commentDel(@RequestParam(required = true) int cno) {
		int result = boardService.commentDel(cno);
		JSONObject json = new JSONObject();
		json.put("result", result);
		//System.out.println(json.toString());

		return json.toString();
	}

	// commentdetail  {"m_no":1,"bno":240,"c_no":144,"m_name":"윤승현","c_date":"09-22","c_ip":"172.30.1.1","m_id":"poseidon","c_comment":"더미 댓글1"}
	@GetMapping("/commentDetail")
	public String commentDetail(@RequestParam(required = true) int cno) {
		Map<String, Object> detail = boardService.commentdetail(cno);
		JSONObject json = new JSONObject(detail);
		//json.put("detail", detail);
		//System.out.println(json.toString());
		return json.toString();
	}

	//commentUpdate
	@PutMapping("/commentUpdate")
	public String commentUpdate(@RequestBody Map<String, Object> map) {
		//System.out.println(map);
		int result = boardService.commentUpdate(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	
}