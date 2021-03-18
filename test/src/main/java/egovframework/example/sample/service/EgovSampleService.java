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
package egovframework.example.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : EgovSampleService.java
 * @Description : EgovSampleService Class
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
public interface EgovSampleService {

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSample(SampleVO vo) throws Exception;

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	void updateSample(SampleVO vo) throws Exception;

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteSample(SampleVO vo) throws Exception;

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	SampleVO selectSample(SampleVO vo) throws Exception;

	/**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception;

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	int selectSampleListTotCnt(SampleDefaultVO searchVO);

	/**
	 * db가 연동 되는지 안 되는지 테스트 하기 위한 메소드입니다.
	 */
	List<?> selectTest() throws Exception;

	/**
	 * 도로명 주소 데이터들을 불러오는게 되는지 테스트 하기 위한 메소드입니다.
	 * @param searchYn
	 * @param doroPageVO
	 * @param doroPageVO - 조회할 정보가 담긴 VO
	 * @return 테이블 리스트 전제 조회
	 * @exception Exception
	 */
	List<DoroVO> selelctDoroTest(DoroVO doroVO) throws Exception;

	int selectDoroListTotCnt(DoroPageVO doroPageVO) throws Exception;

	/**
	 * 가장 처음 select 문에 도로명들을 보여줍니다.
	 */
	List<DoroVO> selectCn() throws Exception;

	List<DoroVO> selectCnSearchList(String cn) throws Exception;

	List<DoroVO> selectSignSearchList(String sign) throws Exception;

	List<DoroVO> selectEmdnSearchList(String emdn) throws Exception;

	List<DoroVO> selectDoroListAjax(SearchVo searchVo) throws Exception;

	int doroInsert(DoroVO doroVO) throws Exception;

	DoroVO selectOneDoro(Map<String,Object> paramMap) throws Exception;

	int deleteDoro(Map<String, Object> paramMap) throws Exception;

	int updateDoro(DoroVO doroVO) throws Exception;

	List<DoroVO> selectDoroTest(Map<String, Object> map) throws Exception; //map을 이용한 select

	List<MemberVO> selectMemberList() throws Exception;

	int memberInsert(MemberVO memberVO) throws Exception;

	MemberVO selectOneMember(MemberVO memberVO);

	boolean passwordMatch(String decodePwd);

}
