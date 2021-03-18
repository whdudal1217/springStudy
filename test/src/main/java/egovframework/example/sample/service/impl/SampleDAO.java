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
package egovframework.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import egovframework.example.sample.service.DoroPageVO;
import egovframework.example.sample.service.DoroVO;
import egovframework.example.sample.service.MemberVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.example.sample.service.SearchVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.antlr.grammar.v3.ANTLRParser.exceptionGroup_return;
import org.springframework.stereotype.Repository;

import cho.util.CheckEncoding;

/**
 * @Class Name : SampleDAO.java
 * @Description : Sample DAO Class
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

@Repository("sampleDAO")
public class SampleDAO extends EgovAbstractDAO {

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */

	@Resource
	private DoroMapper doroMapper;


	public String insertSample(SampleVO vo) throws Exception {
		return (String) insert("sampleDAO.insertSample", vo);
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateSample(SampleVO vo) throws Exception {
		update("sampleDAO.updateSample", vo);
	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteSample(SampleVO vo) throws Exception {
		delete("sampleDAO.deleteSample", vo);
	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO vo) throws Exception {
		return (SampleVO) select("sampleDAO.selectSample", vo);
	}

	/**
	 * 글 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception {
		return list("sampleDAO.selectSampleList", searchVO);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 갯수
	 * @exception
	 */
	public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
		return (Integer) select("sampleDAO.selectSampleListTotCnt", searchVO);
	}

	public List<?> selectTest() throws Exception{
		return doroMapper.selectTest();
	}

	public List<DoroVO> selectDoroList(DoroVO doroVO) throws Exception {
		return doroMapper.selectDoroList(doroVO);
	}

	public int selectDoroListTotCnt(DoroPageVO doroPageVO) throws Exception{
		return doroMapper.selectDoroListTotCnt(doroPageVO);
	}

	public List<DoroVO> selectCnList() throws Exception{
		return doroMapper.selectCnList();
	}

	public List<DoroVO> selectCnSearchList(String cn) throws Exception {
		System.out.println("Dao -> cnData :" + cn);
		return doroMapper.selectCnSearchList(cn);
	}

	public List<DoroVO> selectSignSearchList(String sign) throws Exception {
		System.out.println("Dao -> cnData :" + sign);
		return doroMapper.selectSignSearchList(sign);
	}

	public List<DoroVO> selectEmdnSearchList(String emd) throws Exception {
		System.out.println("Dao -> cnData :" + emd);
		return doroMapper.selectEmdnSearchList(emd);
	}

	public List<DoroVO> selectDoroListAjax(SearchVo searchVo) throws Exception {
		return doroMapper.selectDoroListAjax(searchVo);
	}

	public int insertDoro(DoroVO doroVO) throws Exception {
		System.out.println("doroInsertDao");
		return doroMapper.insertDoro(doroVO);
	}

	public DoroVO selectOneDoro(Map<String,Object> paramMap) throws Exception {
		return doroMapper.selectOneDoro(paramMap);
	}

	public int deleteDoro(Map<String, Object> paramMap) throws Exception {
		return doroMapper.deleteDoro(paramMap);
	}

	public int updateDoro(DoroVO doroVO) throws Exception {
		return doroMapper.updateDoro(doroVO);
	}

	public List<DoroVO> selectDoroList2(Map<String, Object> map) throws Exception {
		return doroMapper.selectDoroList2(map);
	}

	public List<MemberVO> selectMemberList() throws Exception{
		return doroMapper.selectMemberList();
	}

	public int insertMember(MemberVO memberVO) throws Exception{
		return doroMapper.insertMember(memberVO);
	}

	public MemberVO selectOneMember(MemberVO memberVO) {
		return doroMapper.selectOneMember(memberVO);
	}

	public boolean passwordMatch(String decodePwd) {
		return doroMapper.passwordMatch(decodePwd);
	}

}
