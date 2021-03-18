/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import egovframework.example.sample.service.DoroPageVO;
import egovframework.example.sample.service.DoroVO;
import egovframework.example.sample.service.MemberVO;
import egovframework.example.sample.service.SearchVo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper
public interface DoroMapper {

	List<?> selectTest() throws Exception;

	List<DoroVO> selectDoroList(DoroVO doroVO) throws Exception;

	int selectDoroListTotCnt(DoroPageVO doroPageVO) throws Exception;

	List<DoroVO> selectCnList() throws Exception;

	List<DoroVO> selectCnSearchList(String cn)throws Exception;

	List<DoroVO> selectSignSearchList(String sign)throws Exception;

	List<DoroVO> selectEmdnSearchList(String emdn)throws Exception;

	List<DoroVO> selectDoroListAjax(SearchVo searchVo)throws Exception;

	int insertDoro(DoroVO doroVO) throws Exception;

	DoroVO selectOneDoro(Map<String,Object> paramMap) throws Exception;

	int deleteDoro(Map<String, Object> paramMap) throws Exception;

	int updateDoro(DoroVO doroVO) throws Exception;

	List<DoroVO> selectDoroList2(Map<String, Object> map) throws Exception;

	List<MemberVO> selectMemberList() throws Exception;

	int insertMember(MemberVO memberVO) throws Exception;

	MemberVO selectOneMember(MemberVO memberVO);

	boolean passwordMatch(String decodePwd);

}
