package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditfMainResult entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfMainResult implements java.io.Serializable {

	// Fields

	private MigAuditfMainResultId id;
	private Integer domain;
	private Integer faresSrcCount;
	private Integer faresDstCount;
	private Integer faresSrcPasscnt;
	private Integer faresKeyPasscnt;
	private Integer faresSrcMore;
	private Integer faresDstMore;
	private Integer faresKeyUnmatch;
	private Integer faresElseUnmatch;
	private Timestamp faresCreatetime;

	// Constructors

	/** default constructor */
	public MigAuditfMainResult() {
	}

	/** minimal constructor */
	public MigAuditfMainResult(MigAuditfMainResultId id, Integer domain) {
		this.id = id;
		this.domain = domain;
	}

	/** full constructor */
	public MigAuditfMainResult(MigAuditfMainResultId id, Integer domain,
			Integer faresSrcCount, Integer faresDstCount,
			Integer faresSrcPasscnt, Integer faresKeyPasscnt,
			Integer faresSrcMore, Integer faresDstMore,
			Integer faresKeyUnmatch, Integer faresElseUnmatch,
			Timestamp faresCreatetime) {
		this.id = id;
		this.domain = domain;
		this.faresSrcCount = faresSrcCount;
		this.faresDstCount = faresDstCount;
		this.faresSrcPasscnt = faresSrcPasscnt;
		this.faresKeyPasscnt = faresKeyPasscnt;
		this.faresSrcMore = faresSrcMore;
		this.faresDstMore = faresDstMore;
		this.faresKeyUnmatch = faresKeyUnmatch;
		this.faresElseUnmatch = faresElseUnmatch;
		this.faresCreatetime = faresCreatetime;
	}

	// Property accessors

	public MigAuditfMainResultId getId() {
		return this.id;
	}

	public void setId(MigAuditfMainResultId id) {
		this.id = id;
	}

	public Integer getDomain() {
		return this.domain;
	}

	public void setDomain(Integer domain) {
		this.domain = domain;
	}

	public Integer getFaresSrcCount() {
		return this.faresSrcCount;
	}

	public void setFaresSrcCount(Integer faresSrcCount) {
		this.faresSrcCount = faresSrcCount;
	}

	public Integer getFaresDstCount() {
		return this.faresDstCount;
	}

	public void setFaresDstCount(Integer faresDstCount) {
		this.faresDstCount = faresDstCount;
	}

	public Integer getFaresSrcPasscnt() {
		return this.faresSrcPasscnt;
	}

	public void setFaresSrcPasscnt(Integer faresSrcPasscnt) {
		this.faresSrcPasscnt = faresSrcPasscnt;
	}

	public Integer getFaresKeyPasscnt() {
		return this.faresKeyPasscnt;
	}

	public void setFaresKeyPasscnt(Integer faresKeyPasscnt) {
		this.faresKeyPasscnt = faresKeyPasscnt;
	}

	public Integer getFaresSrcMore() {
		return this.faresSrcMore;
	}

	public void setFaresSrcMore(Integer faresSrcMore) {
		this.faresSrcMore = faresSrcMore;
	}

	public Integer getFaresDstMore() {
		return this.faresDstMore;
	}

	public void setFaresDstMore(Integer faresDstMore) {
		this.faresDstMore = faresDstMore;
	}

	public Integer getFaresKeyUnmatch() {
		return this.faresKeyUnmatch;
	}

	public void setFaresKeyUnmatch(Integer faresKeyUnmatch) {
		this.faresKeyUnmatch = faresKeyUnmatch;
	}

	public Integer getFaresElseUnmatch() {
		return this.faresElseUnmatch;
	}

	public void setFaresElseUnmatch(Integer faresElseUnmatch) {
		this.faresElseUnmatch = faresElseUnmatch;
	}

	public Timestamp getFaresCreatetime() {
		return this.faresCreatetime;
	}

	public void setFaresCreatetime(Timestamp faresCreatetime) {
		this.faresCreatetime = faresCreatetime;
	}

}