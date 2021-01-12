package com.tc.yuxiu.model;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author DELL
 */
public class TyUsers {
    @Override
    public String toString() {
        return "TyUsers{" +
                "userId=" + userId +
                ", suppliersId=" + suppliersId +
                ", type=" + type +
                ", suppliersTime=" + suppliersTime +
                ", suppliersDistribut=" + suppliersDistribut +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", totalMoney=" + totalMoney +
                ", userMoney=" + userMoney +
                ", partnerMoney=" + partnerMoney +
                ", frozenMoney=" + frozenMoney +
                ", distributMoney=" + distributMoney +
                ", designerDistributMoney=" + designerDistributMoney +
                ", payPoints=" + payPoints +
                ", addressId=" + addressId +
                ", regTime=" + regTime +
                ", lastLogin=" + lastLogin +
                ", lastIp='" + lastIp + '\'' +
                ", qq='" + qq + '\'' +
                ", mobile='" + mobile + '\'' +
                ", mobileValidated=" + mobileValidated +
                ", oauth='" + oauth + '\'' +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", headPic='" + headPic + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", emailValidated=" + emailValidated +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", level=" + level +
                ", discount=" + discount +
                ", totalAmount=" + totalAmount +
                ", isLock=" + isLock +
                ", isDistribut=" + isDistribut +
                ", firstLeader=" + firstLeader +
                ", secondLeader=" + secondLeader +
                ", thirdLeader=" + thirdLeader +
                ", token='" + token + '\'' +
                ", openId='" + openId + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", seat=" + seat +
                ", callCate=" + callCate +
                ", callService=" + callService +
                ", callMf=" + callMf +
                ", callS=" + callS +
                ", waitNum=" + waitNum +
                ", waitType=" + waitType +
                ", inviteCode='" + inviteCode + '\'' +
                ", supperTotalMoney=" + supperTotalMoney +
                ", supperMoney=" + supperMoney +
                ", supperFrozenMoney=" + supperFrozenMoney +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                ", userName='" + userName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", isLive=" + isLive +
                ", liveRoom='" + liveRoom + '\'' +
                ", liveApplyTime='" + liveApplyTime + '\'' +
                ", ryToken='" + ryToken + '\'' +
                ", esign=" + esign +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", docId='" + docId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", flowId='" + flowId + '\'' +
                ", signUrl='" + signUrl + '\'' +
                ", signShortUrl='" + signShortUrl + '\'' +
                ", viewUrl='" + viewUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", authorizationOrgId='" + authorizationOrgId + '\'' +
                ", sealId='" + sealId + '\'' +
                ", contractType=" + contractType +
                ", llpayUrl='" + llpayUrl + '\'' +
                ", job='" + job + '\'' +
                ", income=" + income +
                ", education=" + education +
                ", industry=" + industry +
                ", marriage=" + marriage +
                ", updateTime=" + updateTime +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", appuid='" + appuid + '\'' +
                '}';
    }

    @Id
    private Integer userId;

    private Integer suppliersId;

    private Short type;

    private Integer suppliersTime;

    private BigDecimal suppliersDistribut;

    private String email;

    private String password;

    private Short sex;

    private Integer birthday;

    private BigDecimal totalMoney;

    private BigDecimal userMoney;

    private BigDecimal partnerMoney;

    private BigDecimal frozenMoney;

    private BigDecimal distributMoney;

    private BigDecimal designerDistributMoney;

    private Integer payPoints;

    private Integer addressId;

    private Integer regTime;

    private Integer lastLogin;

    private String lastIp;

    private String qq;

    private String mobile;

    private Byte mobileValidated;

    private String oauth;

    private String openid;

    private String unionid;

    private String headPic;

    private Integer province;

    private Integer city;

    private Integer district;

    private Short emailValidated;

    private String username;

    private String nickname;

    private Short level;

    private BigDecimal discount;

    private BigDecimal totalAmount;

    private Short isLock;

    private Short isDistribut;

    private Integer firstLeader;

    private Integer secondLeader;

    private Integer thirdLeader;

    private String token;

    private String openId;

    private String country;

    private String gender;

    private String nickName;

    private Integer seat;

    private Integer callCate;

    private Integer callService;

    private Integer callMf;

    private Integer callS;

    private Integer waitNum;

    private Integer waitType;

    private String inviteCode;

    private BigDecimal supperTotalMoney;

    private BigDecimal supperMoney;

    private BigDecimal supperFrozenMoney;

    private Short status;

    private String reason;

    private String userName;

    private String storeName;

    private Short isLive;

    private String liveRoom;

    private String liveApplyTime;

    private String ryToken;

    private Short esign;

    private String realName;

    private String idCard;

    private String docId;

    private String accountId;

    private String flowId;

    private String signUrl;

    private String signShortUrl;

    private String viewUrl;

    private String downloadUrl;

    private String authorizationOrgId;

    private String sealId;

    private Short contractType;

    private String llpayUrl;

    private String job;

    private Short income;

    private Short education;

    private Short industry;

    private Short marriage;

    private Integer updateTime;

    private String year;

    private String month;

    private String day;

    private String appuid;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Integer suppliersId) {
        this.suppliersId = suppliersId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getSuppliersTime() {
        return suppliersTime;
    }

    public void setSuppliersTime(Integer suppliersTime) {
        this.suppliersTime = suppliersTime;
    }

    public BigDecimal getSuppliersDistribut() {
        return suppliersDistribut;
    }

    public void setSuppliersDistribut(BigDecimal suppliersDistribut) {
        this.suppliersDistribut = suppliersDistribut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getPartnerMoney() {
        return partnerMoney;
    }

    public void setPartnerMoney(BigDecimal partnerMoney) {
        this.partnerMoney = partnerMoney;
    }

    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public BigDecimal getDistributMoney() {
        return distributMoney;
    }

    public void setDistributMoney(BigDecimal distributMoney) {
        this.distributMoney = distributMoney;
    }

    public BigDecimal getDesignerDistributMoney() {
        return designerDistributMoney;
    }

    public void setDesignerDistributMoney(BigDecimal designerDistributMoney) {
        this.designerDistributMoney = designerDistributMoney;
    }

    public Integer getPayPoints() {
        return payPoints;
    }

    public void setPayPoints(Integer payPoints) {
        this.payPoints = payPoints;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getRegTime() {
        return regTime;
    }

    public void setRegTime(Integer regTime) {
        this.regTime = regTime;
    }

    public Integer getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Integer lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Byte getMobileValidated() {
        return mobileValidated;
    }

    public void setMobileValidated(Byte mobileValidated) {
        this.mobileValidated = mobileValidated;
    }

    public String getOauth() {
        return oauth;
    }

    public void setOauth(String oauth) {
        this.oauth = oauth == null ? null : oauth.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Short getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(Short emailValidated) {
        this.emailValidated = emailValidated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Short getIsLock() {
        return isLock;
    }

    public void setIsLock(Short isLock) {
        this.isLock = isLock;
    }

    public Short getIsDistribut() {
        return isDistribut;
    }

    public void setIsDistribut(Short isDistribut) {
        this.isDistribut = isDistribut;
    }

    public Integer getFirstLeader() {
        return firstLeader;
    }

    public void setFirstLeader(Integer firstLeader) {
        this.firstLeader = firstLeader;
    }

    public Integer getSecondLeader() {
        return secondLeader;
    }

    public void setSecondLeader(Integer secondLeader) {
        this.secondLeader = secondLeader;
    }

    public Integer getThirdLeader() {
        return thirdLeader;
    }

    public void setThirdLeader(Integer thirdLeader) {
        this.thirdLeader = thirdLeader;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Integer getCallCate() {
        return callCate;
    }

    public void setCallCate(Integer callCate) {
        this.callCate = callCate;
    }

    public Integer getCallService() {
        return callService;
    }

    public void setCallService(Integer callService) {
        this.callService = callService;
    }

    public Integer getCallMf() {
        return callMf;
    }

    public void setCallMf(Integer callMf) {
        this.callMf = callMf;
    }

    public Integer getCallS() {
        return callS;
    }

    public void setCallS(Integer callS) {
        this.callS = callS;
    }

    public Integer getWaitNum() {
        return waitNum;
    }

    public void setWaitNum(Integer waitNum) {
        this.waitNum = waitNum;
    }

    public Integer getWaitType() {
        return waitType;
    }

    public void setWaitType(Integer waitType) {
        this.waitType = waitType;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public BigDecimal getSupperTotalMoney() {
        return supperTotalMoney;
    }

    public void setSupperTotalMoney(BigDecimal supperTotalMoney) {
        this.supperTotalMoney = supperTotalMoney;
    }

    public BigDecimal getSupperMoney() {
        return supperMoney;
    }

    public void setSupperMoney(BigDecimal supperMoney) {
        this.supperMoney = supperMoney;
    }

    public BigDecimal getSupperFrozenMoney() {
        return supperFrozenMoney;
    }

    public void setSupperFrozenMoney(BigDecimal supperFrozenMoney) {
        this.supperFrozenMoney = supperFrozenMoney;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Short getIsLive() {
        return isLive;
    }

    public void setIsLive(Short isLive) {
        this.isLive = isLive;
    }

    public String getLiveRoom() {
        return liveRoom;
    }

    public void setLiveRoom(String liveRoom) {
        this.liveRoom = liveRoom == null ? null : liveRoom.trim();
    }

    public String getLiveApplyTime() {
        return liveApplyTime;
    }

    public void setLiveApplyTime(String liveApplyTime) {
        this.liveApplyTime = liveApplyTime;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken == null ? null : ryToken.trim();
    }

    public Short getEsign() {
        return esign;
    }

    public void setEsign(Short esign) {
        this.esign = esign;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId == null ? null : docId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl == null ? null : signUrl.trim();
    }

    public String getSignShortUrl() {
        return signShortUrl;
    }

    public void setSignShortUrl(String signShortUrl) {
        this.signShortUrl = signShortUrl == null ? null : signShortUrl.trim();
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl == null ? null : viewUrl.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    public String getAuthorizationOrgId() {
        return authorizationOrgId;
    }

    public void setAuthorizationOrgId(String authorizationOrgId) {
        this.authorizationOrgId = authorizationOrgId == null ? null : authorizationOrgId.trim();
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId == null ? null : sealId.trim();
    }

    public Short getContractType() {
        return contractType;
    }

    public void setContractType(Short contractType) {
        this.contractType = contractType;
    }

    public String getLlpayUrl() {
        return llpayUrl;
    }

    public void setLlpayUrl(String llpayUrl) {
        this.llpayUrl = llpayUrl == null ? null : llpayUrl.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Short getIncome() {
        return income;
    }

    public void setIncome(Short income) {
        this.income = income;
    }

    public Short getEducation() {
        return education;
    }

    public void setEducation(Short education) {
        this.education = education;
    }

    public Short getIndustry() {
        return industry;
    }

    public void setIndustry(Short industry) {
        this.industry = industry;
    }

    public Short getMarriage() {
        return marriage;
    }

    public void setMarriage(Short marriage) {
        this.marriage = marriage;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getAppuid() {
        return appuid;
    }

    public void setAppuid(String appuid) {
        this.appuid = appuid == null ? null : appuid.trim();
    }
}