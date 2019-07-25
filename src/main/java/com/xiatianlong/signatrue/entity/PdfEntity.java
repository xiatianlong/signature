package com.xiatianlong.signatrue.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * PdfEntity.
 * Created by xiatl on 2019-07-25 09:06
 */
@Entity(name = "t_pdf")
public class PdfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_no", nullable = false, length = 100)
    private String idNo;

    @Column(name = "mobile", nullable = false, length = 50)
    private String mobile;

    @Column(name = "pdf", nullable = false)
    private String pdf;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "id_no", nullable = false, length = 100)
    public String getIdNo() {
        return this.idNo;
    }

    @Column(name = "id_no", nullable = false, length = 100)
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Column(name = "mobile", nullable = false, length = 50)
    public String getMobile() {
        return this.mobile;
    }

    @Column(name = "mobile", nullable = false, length = 50)
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "pdf", nullable = false)
    public String getPdf() {
        return this.pdf;
    }

    @Column(name = "pdf", nullable = false)
    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Column(name = "created_date", nullable = false)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    @Column(name = "created_date", nullable = false)
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
