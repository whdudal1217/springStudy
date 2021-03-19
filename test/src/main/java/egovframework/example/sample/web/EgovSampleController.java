/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.DoroPageVO;
import egovframework.example.sample.service.DoroVO;
import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.MemberVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.example.sample.service.SearchVo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.ibatis.common.logging.Log;

import cho.util.CheckEncoding;
import cho.util.UserSha256;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
public class EgovSampleController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/*
	 * @Resource(name = "bcryptPasswordEncoder") BCryptPasswordEncoder pwdEncoder;
	 */


	/**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovSampleList.do")
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> sampleList = sampleService.selectSampleList(searchVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "sample/egovSampleList";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.GET)
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.POST)
	public String addSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		// Server-Side Validation
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		sampleService.insertSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		model.addAttribute(selectSample(sampleVO, searchVO));
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return sampleService.selectSample(sampleVO);
	}

	/**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateSample.do")
	public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		sampleService.updateSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteSample.do")
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	@Autowired
	BasicDataSource dataSource;

	@RequestMapping("/dbTest.do")
	public String dbTest(Model model) throws Exception {
		System.out.println("dbTest.do");
		List<?> selectTestList = sampleService.selectTest();
		model.addAttribute("TestList", selectTestList);
		/*
		 * Connection conn = null;
		 * Statement stmt = null;
		 * ResultSet rs = null;
		 *
		 * try {
		 * conn = dataSource.getConnection();
		 * stmt = conn.createStatement();
		 * rs = stmt.executeQuery("select * from test");
		 * while (rs.next()) {
		 * model.addAttribute("testNo",rs.getString("testNo"));
		 * model.addAttribute("testName",rs.getString("testName")); }
		 * } catchㄴ
		 * (SQLException e) {
		 * e.printStackTrace(); }
		 * finally {
		 * try { if(conn!=null) {
		 * conn.close(); } if(stmt!=null) { stmt.close(); } } catch (Exception e2) {
		 * e2.printStackTrace(); } }
		 */
			return "sample/dbTest";
		}

	@RequestMapping(value="/selectSearch.do")
	@ResponseBody //@ResponseBody가 없다면 해당 url은 String 으로 인식하여 return값을 페이지로 인식한다. 그러기에 ajax 404 오류가 발생합니다.
	public ModelAndView selectSearch(@ModelAttribute(value = "searchVo") DoroVO searchVo) throws Exception{

		/* AJAX의 이동
		 * CLIENT -> CON -> VO로 SET -> VO에서 GET -> CON -> IF문으로 어떤 SELECT인지 구분 -> SERVICE -> SERVICEIMPL -> DAO -> MAPPER
		 * */

		String selectId = searchVo.getSelectId();
		String cn = searchVo.getCn();
		String sign = searchVo.getSign();
		String emdn = searchVo.getEmdn();

		ModelAndView mav = new ModelAndView("jsonView");

		if(selectId.equals("cnSearch")) {
			List<?> signList = sampleService.selectCnSearchList(cn);
			List<?> doroList = sampleService.selelctDoroTest(searchVo);
			System.out.println(cn + "시의 군(구)를 검색합니다");
			System.out.println("cnFun > selectId : " + selectId);
			mav.addObject("dataList", signList);
			mav.addObject("doroList", doroList);
			mav.addObject("data", selectId);
			System.out.println("mav.getModelMap()? >> " + mav.getModelMap());
		}else if(selectId.equals("signSearch")) {
			List<?> emdList = sampleService.selectSignSearchList(sign);
			System.out.println(cn + "시의 " + sign + "군(구)의 동(읍/면)을 검색합니다");
			System.out.println("signFun > selectId : " + selectId);
			mav.addObject("dataList", emdList);
			mav.addObject("data", selectId);
			System.out.println("mav.getModelMap()? >> " + mav.getModelMap());
		}else if(selectId.equals("emdnSearch")) {
			List<?> rnList = sampleService.selectEmdnSearchList(emdn);
			System.out.println(cn + "시의 " + sign + "군(구)의" + emdn + "동(읍/면)의 도로명을 검색합니다");
			System.out.println("emdnFun > selectId : " + selectId);
			mav.addObject("dataList", rnList);
			mav.addObject("data", selectId);
			System.out.println("mav.getModelMap()? >> " + mav.getModelMap());
		}
		return mav;
	}

	@RequestMapping("/doro.do")
	public String doroTest(/* @ModelAttribute("doroVO") DoroVO doroVO, */
			               @RequestParam(value = "cn", required = false, defaultValue = "") String cn,
			               @RequestParam(value = "sign", required = false, defaultValue = "") String sign,
			               @RequestParam(value = "emdn", required = false, defaultValue = "") String emdn,
			               @RequestParam(value = "rn", required = false, defaultValue = "") String rn,
			               @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
			               @RequestParam(value = "selectId", required = false, defaultValue = "") String selectId,
			               @RequestParam(value = "searchYn", required = false, defaultValue = "") String searchYn ,
			               @RequestParam(value = "notChSign", required = false, defaultValue = "") String notChsign,
			               @RequestParam(value = "notChSign", required = false, defaultValue = "") String notChemdn,
			               ModelMap model) throws Exception{
		System.out.println("doro.do 시작");
		System.out.println("@RequestParam > value > cn : " + cn);
		System.out.println("@RequestParam > value > sign : " + sign);
		System.out.println("@RequestParam > value > emdn : " + emdn);
		System.out.println("@RequestParam > value > rn : " + rn);
		System.out.println("@RequestParam > value > pageIndex : " + pageIndex);
		System.out.println("@RequestParam > value > searchYn : " + searchYn);

		List<DoroVO> doroList = null;
		DoroVO doroVO = new DoroVO();
		Map<String,Object> map = new HashMap<>();
		int totCnt = 0;

		//페이지네이션을 위한 jsp에서 가져온 데이터 & 기본 설정값 매핑
		if (searchYn == "true") {
			//검색 버튼을 눌렀을 때
			// -> 검색을 위해 파라미터로 select문을 타서 결과를 돌려줘야함
		}else {
			//페이지네이션만 눌렀을 때
			//검색 조건을 들고 select를 가면 안 돼...
		}
		doroVO.setCn(cn);
		doroVO.setSign(sign);
		doroVO.setEmdn(emdn);
		doroVO.setPageUnit(20);
		doroVO.setPageSize(10);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(doroVO.getPageIndex());
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(doroVO.getPageUnit()); //한 페이지에 보이는 게시물 건 수
		paginationInfo.setPageSize(doroVO.getPageSize());
		doroVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		doroVO.setLastIndex(paginationInfo.getLastRecordIndex());
		doroVO.setRecordCountPerPage(10); //한 페이지당 나오는 데이터 건 수는 10개로 제한.
		totCnt = sampleService.selectDoroListTotCnt(doroVO);
		paginationInfo.setTotalRecordCount(totCnt);
		/* 페이지네이션이 궁금하다면 주석을 풀고 확인해보세요. 페이지네이션에서 주로 쓰는 변수들입니다. */
		/*
		 * System.out.println("-*-*-*-*-*-*-*-*-*-*");
		 * System.out.println("paginationInfo.getCurrentPageNo() >> " +
		 * paginationInfo.getCurrentPageNo());
		 * System.out.println("-*-*-*-*-*-*-*-*-*-*");
		 * System.out.println("paginationInfo.getLastPageNo() >> " +
		 * paginationInfo.getLastPageNo()); System.out.println("-*-*-*-*-*-*-*-*-*-*");
		 * System.out.println("paginationInfo.getLastPageNoOnPageList() >> " +
		 * paginationInfo.getLastPageNoOnPageList());
		 * System.out.println("-*-*-*-*-*-*-*-*-*-*");
		 * System.out.println("paginationInfo.getLastRecordIndex() >> " +
		 * paginationInfo.getLastRecordIndex());
		 * System.out.println("-*-*-*-*-*-*-*-*-*-*");
		 * System.out.println("paginationInfo.getTotalPageCount() >> " +
		 * paginationInfo.getTotalPageCount());
		 * System.out.println("-*-*-*-*-*-*-*-*-*-*");
		 * System.out.println("paginationInfo.getRecordCountPerPage() >> " +
		 * paginationInfo.getRecordCountPerPage());
		 * System.out.println("-*-*-*-*-*-*-*-*-*-*");
		 * System.out.println("paginationInfo.getTotalRecordCount() >> " +
		 * paginationInfo.getTotalRecordCount());
		 */
		 System.out.println(" 총 게시물 건 수 >> " + paginationInfo.getTotalRecordCount());

		if(paginationInfo.getCurrentPageNo() > paginationInfo.getLastPageNo()) {
			paginationInfo.setCurrentPageNo(paginationInfo.getLastPageNo());
		}
		//doroList = sampleService.selelctDoroTest(doroVO); //vo를 이용한 select

		map.put("cn", cn);
		map.put("sign", sign);
		map.put("emdn", emdn);
		map.put("rn", rn);
		map.put("selectId", selectId);
		map.put("firstIndex", doroVO.getFirstIndex());
		map.put("lastIndex", doroVO.getLastIndex());
		map.put("searchYn", searchYn);
		System.out.println("searchYn >>" + searchYn );

		doroList = sampleService.selectDoroTest(map);//map을 이용한 select
		List<DoroVO> cnList;
		cnList = sampleService.selectCn();

		model.addAttribute("searchYn", searchYn);
		model.addAttribute("selectedCn", cn);
		model.addAttribute("selectedEmdn", emdn);
		model.addAttribute("selectedSign", sign);
		model.addAttribute("cnList" , cnList);
		model.addAttribute("pageIndex","1");
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("doroList",doroList);
		model.addAttribute("doroVO",doroVO);

		return "sample/doroTest";
	}

	@RequestMapping(value="doroInsert.do", method = RequestMethod.GET)
	public String doroInsertControllerGet(@ModelAttribute("doroVO")DoroVO doroVO, ModelMap model)throws Exception{
		System.out.println("doroInsert.do >> doroVO.getRnCd() >> " + doroVO.getRnCd());
		System.out.println("doroInsert.do >> doroVO.getEmdNo() >> " + doroVO.getEmdNo());
		DoroVO oneSelect = new DoroVO();
		String viewPage = "";
		if(doroVO.getRnCd()!= null && doroVO.getEmdNo() != null) {
			System.out.println("doroInsert.do의 수정을 입력하면 나오는 컨트롤러입니다~!");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("rnCd", doroVO.getRnCd());
			map.put("emdNo", doroVO.getEmdNo());
			oneSelect = sampleService.selectOneDoro(map);
			System.out.println("doroInsert.do >> oneSelect.getCn() >> " + oneSelect.getCn());
			model.addAttribute("selectDoro", oneSelect);
			viewPage = "sample/doroInsert";
		}else {
			viewPage = "sample/doroInsert";
		}
		return viewPage;
	}

	@RequestMapping(value="doroInsert.do", method = RequestMethod.POST)
	public String doroInsertController(@ModelAttribute("doroVO")DoroVO doroVO) throws Exception {
		String viewPage = "";
		int updCnt = 0;
		updCnt =  sampleService.doroInsert(doroVO);
		if(updCnt == 0) {
			System.out.println("입력 실패");
			viewPage = "error";
		}else {
			System.out.println("입력 된 게시물 건 수 : " + updCnt);
			viewPage = "redirect:/doro.do";
		}
		return viewPage;
	}

	@RequestMapping(value = "doroView.do")
	public String doroViewController(@RequestParam(value = "rnCd") String rnCd,
									 @RequestParam(value="emdNo") String emdNo ,
									 ModelMap model) throws Exception {
		System.out.println("doroView 들어왔습니다");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rnCd", rnCd);
		paramMap.put("emdNo", emdNo);
		DoroVO oneSelect = sampleService.selectOneDoro(paramMap);
		model.addAttribute("selectDoro", oneSelect);
		return "sample/doroView";
	}

	@RequestMapping(value="doroDelete.do")
	public String doroViewController(@RequestParam(value = "rnCd") String rnCd,
									 @RequestParam(value="emdNo")String emdNo) throws Exception{

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("rnCd", rnCd);
		paramMap.put("emdNo", emdNo);
		int delCnt = sampleService.deleteDoro(paramMap);
		System.out.println("삭제된 데이터 개수 : " + delCnt);

		return "redirect:/doro.do";
	}

	@RequestMapping(value = "doroUpdate.do")
	public String doroUpdateController(@ModelAttribute("doroVO")DoroVO doroVO) throws Exception{
		int updCnt = sampleService.updateDoro(doroVO);
		String viewPage ="";
		if(updCnt > 0) {
			viewPage = "redirect:/doroView.do?rnCd="+doroVO.getRnCd()+"&emdNo="+doroVO.getEmdNo();
		}else {
			viewPage = "sample/error";
		}
		return viewPage;
	}

	@RequestMapping(value = "memberSelect.do")
	public String memberListController(Model model)throws Exception{
		List<MemberVO> memberList = null;
		memberList = sampleService.selectMemberList();
		model.addAttribute("memberList", memberList);
		return "sample/memList";
	}

	@RequestMapping(value = "memberInsert.do", method = RequestMethod.GET)
	public String memberInsertControllerGet(@ModelAttribute("memberVO")MemberVO memberVO) {
		System.out.println("나 스쳐간다");
		return "sample/memForm";
	}

	@RequestMapping(value = "memberInsert.do")
	public String memberInsertController(@ModelAttribute("memberVO")MemberVO memberVO)throws Exception{
		String viewPage = "";

		System.out.println("before encode pwd : " + memberVO.getMemPwd());
		try {
			//String enPwd = UserSha256.encrypted(memberVO.getMemPwd(), memberVO.getMemId());
			String enPwd = UserSha256.testSHA256(memberVO.getMemPwd());
			System.out.println("after encode pwd : " + enPwd);
			memberVO.setMemPwd(enPwd);
			int insertCnt = sampleService.memberInsert(memberVO);

			if(insertCnt==0) {
				viewPage = "sample/error";
			}else {
				viewPage = "redirect:/memberSelect.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewPage;
	}

	@RequestMapping(value = "/login/loginForm.do", method = RequestMethod.GET)
	public String loginFilterController(@ModelAttribute("memberVO") MemberVO memberVO) {
		return "sample/login/loginForm";
	}

	@RequestMapping(value = "/login/loginForm.do", method = RequestMethod.POST)
	public String loginFormController(@ModelAttribute("memberVO") MemberVO memberVO,
									  HttpSession session,
									  Model model) {
		System.out.println("memberVO memid >> " + memberVO.getMemId());
		String viewPage = "";
		String message = "";
		MemberVO resMember = sampleService.selectOneMember(memberVO);
		System.out.println( "resMember.getMemId() >> " + resMember.getMemId());
		if(resMember.getMemId() == null) {
			message = "회원정보가 틀렸습니다.";
			viewPage = "redirect:/login/loginForm.do";
		}else {
			//String decodePwd = UserSha256.encrypted(memberVO.getMemPwd(), resMember.getMemId());
			String decodePwd = UserSha256.testSHA256(memberVO.getMemPwd());
			System.out.println("decodePwd : " + decodePwd);
			boolean matchPwd = sampleService.passwordMatch(decodePwd); //성공하면 true가 돌아옴
			System.out.println( "matchPwd : " + matchPwd);
			if(matchPwd) {
				message = resMember.getMemName() + "님 환영합니다.";
				session.setAttribute("LOGIN_USER", resMember);
				viewPage = "redirect:/doro.do";
			}
		}
		return viewPage;
	}
	@RequestMapping(value = "/logout.do")
	public String logoutController(HttpSession session) {
		session.invalidate();
		return "redirect:/doro.do";
	}
}














