package com.example.nationaltax.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notify_mail")
public class NotifyMail {
    @Id
    private Long id;
    @Column(name = "notify_id")
    private Long notifyId;
    @Column(name = "mail")
    private String mail;

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public Long getId() {
        return id;
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public String getMail() {
        return mail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
