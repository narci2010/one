package com.lcw.one.bidding.entity;

import javax.persistence.*;

/**
 * @version 2017-05-15.
 * @auth Licw
 */
@Entity
@Table(name = "bid_bidding_file")
public class BidBiddingFileEO {
    private String biddingFileId;
    private String projectId;
    private String fileId;
    private Integer type;
    private String title;
    private String remark;

    @Id
    @Column(name = "bidding_file_id")
    public String getBiddingFileId() {
        return biddingFileId;
    }

    public void setBiddingFileId(String biddingFileId) {
        this.biddingFileId = biddingFileId;
    }

    @Basic
    @Column(name = "project_id")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "file_id")
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidBiddingFileEO that = (BidBiddingFileEO) o;

        if (biddingFileId != null ? !biddingFileId.equals(that.biddingFileId) : that.biddingFileId != null)
            return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = biddingFileId != null ? biddingFileId.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
