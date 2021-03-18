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

public class DoroVO extends DoroPageVO{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String rnCd ;/*도로명코드*/
	private String rn; /*도로명*/
	private String engRn; /*도로명 영문자 (로마자) */
	private String emdNo; /*읍면동일련번호*/
	private String cn; /*시도명*/
	private String engCn; /*시도명 영문자(로마자)*/
	private String sign; /*시군구명*/
	private String engSign; /*시군구 영문자(로마자)*/
	private String emdn; /*읍면동명*/
	private String engEmd; /*읍면동 영문자(로마자)*/
	private String emdSe; /*읍면동구분 0:읍 1:면 2:동*/
	private String emdCd; /*읍면동코드*/
	private String useYn; /*사용여부*/
	private String chghy; /*변경사유*/
	private String aftchInfo; /*변경 후 정보 이건 뭐지?*/
	private String ntfcDe;  /*고시일자*/
	private String ersrDe;  /*말소일자*/
	private int rnum;
	private String selectId;
	private String notChSign;
	private String notChEmdn;
	public String getRnCd() {
		return rnCd;
	}
	public void setRnCd(String rnCd) {
		this.rnCd = rnCd;
	}
	public String getRn() {
		return rn;
	}
	public void setRn(String rn) {
		this.rn = rn;
	}
	public String getEngRn() {
		return engRn;
	}
	public void setEngRn(String engRn) {
		this.engRn = engRn;
	}
	public String getEmdNo() {
		return emdNo;
	}
	public void setEmdNo(String emdNo) {
		this.emdNo = emdNo;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getEngCn() {
		return engCn;
	}
	public void setEngCn(String engCn) {
		this.engCn = engCn;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getEngSign() {
		return engSign;
	}
	public void setEngSign(String engSign) {
		this.engSign = engSign;
	}
	public String getEmdn() {
		return emdn;
	}
	public void setEmdn(String emdn) {
		this.emdn = emdn;
	}
	public String getEngEmd() {
		return engEmd;
	}
	public void setEngEmd(String engEmd) {
		this.engEmd = engEmd;
	}
	public String getEmdSe() {
		return emdSe;
	}
	public void setEmdSe(String emdSe) {
		this.emdSe = emdSe;
	}
	public String getEmdCd() {
		return emdCd;
	}
	public void setEmdCd(String emdCd) {
		this.emdCd = emdCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getChghy() {
		return chghy;
	}
	public void setChghy(String chghy) {
		this.chghy = chghy;
	}
	public String getNtfcDe() {
		return ntfcDe;
	}
	public void setNtfcDe(String ntfcDe) {
		this.ntfcDe = ntfcDe;
	}
	public String getErsrDe() {
		return ersrDe;
	}
	public void setErsrDe(String ersrDe) {
		this.ersrDe = ersrDe;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getSelectId() {
		return selectId;
	}
	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAftchInfo() {
		return aftchInfo;
	}
	public void setAftchInfo(String aftchInfo) {
		this.aftchInfo = aftchInfo;
	}
	public String getNotChSign() {
		return notChSign;
	}
	public void setNotChSign(String notChSign) {
		this.notChSign = notChSign;
	}
	public String getNotChEmdn() {
		return notChEmdn;
	}
	public void setNotChEmdn(String notChEmdn) {
		this.notChEmdn = notChEmdn;
	}

}
