package team.benchem.demo.rediscache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "t_student")
public class Student implements Serializable {

    @Id
    @Column(name = "frowid", length = 36)
    private String rowId;

    @Column(name = "fusername")
    private String userName;

    @Column(name = "fnickname")
    private String nickName;

    @Column(name = "fregistrationdate")
    private Date registrationDate;

    public Student() {
        rowId = UUID.randomUUID().toString();
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
