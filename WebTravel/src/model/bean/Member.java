package model.bean;
// Generated 2016/1/11 �U�� 06:10:10 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Member generated by hbm2java
 */
@Entity
@Table(name = "Member", schema = "dbo", catalog = "travel", uniqueConstraints = @UniqueConstraint(columnNames = "UserName") )
public class Member implements java.io.Serializable {

	private int memberId;
	private String userName;
	private byte[] password;
	private String firstName;
	private String lastName;
	private String nickName;
	private Date birthDay;
	private String address;
	private String cellphone;
	private String telephone;
	private String email;
	private byte[] photo;
	private Set<Collect> collects = new HashSet<Collect>(0);
	private Set<Scene> scenes = new HashSet<Scene>(0);

	public Member() {
	}

	public Member(int memberId, String userName, byte[] password, String firstName, String lastName, String email) {
		this.memberId = memberId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Member(int memberId, String userName, byte[] password, String firstName, String lastName, String nickName,
			Date birthDay, String address, String cellphone, String telephone, String email, byte[] photo,
			Set<Collect> collects, Set<Scene> scenes) {
		this.memberId = memberId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.birthDay = birthDay;
		this.address = address;
		this.cellphone = cellphone;
		this.telephone = telephone;
		this.email = email;
		this.photo = photo;
		this.collects = collects;
		this.scenes = scenes;
	}

	@Id

	@Column(name = "MemberID", unique = true, nullable = false)
	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Column(name = "UserName", unique = true, nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false)
	public byte[] getPassword() {
		return this.password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Column(name = "FirstName", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "NickName", length = 50)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BirthDay", length = 10)
	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Cellphone", length = 50)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "Telephone", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "Email", nullable = false, length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Photo")
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Collect> getCollects() {
		return this.collects;
	}

	public void setCollects(Set<Collect> collects) {
		this.collects = collects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Scene> getScenes() {
		return this.scenes;
	}

	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}

}
