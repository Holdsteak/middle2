package main;

import java.io.Serializable;
import java.sql.Date;

public class servletBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int seq;
	private java.sql.Date dateListed;
	private int year;
	private String category;
	private String duration;
	private double schoolPopulation;
	private double transferPopulation;
	private double transferRates;

	public servletBean() {

	}

	public servletBean(int seq, java.sql.Date dateListed, int year, String category, String duration,
			double schoolPopulation, double transferPopulation, double transferRates) {
		super();
		this.seq = seq;
		this.dateListed = dateListed;
		this.year = year;
		this.category = category;
		this.duration = duration;
		this.schoolPopulation = schoolPopulation;
		this.transferPopulation = transferPopulation;
		this.transferRates = transferRates;
	}

	@Override
	public String toString() {
		return "servletBean [seq=" + seq + ", dateListed=" + dateListed + ", year=" + year + ", category=" + category
				+ ", duration=" + duration + ", schoolPopulation=" + schoolPopulation + ", transferPopulation="
				+ transferPopulation + ", transferRates=" + transferRates + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public java.sql.Date getDateListed() {
		return dateListed;
	}

	public void setDateListed(java.sql.Date dateListed) {
		this.dateListed = dateListed;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public double getSchoolPopulation() {
		return schoolPopulation;
	}

	public void setSchoolPopulation(double schoolPopulation) {
		this.schoolPopulation = schoolPopulation;
	}

	public double getTransferPopulation() {
		return transferPopulation;
	}

	public void setTransferPopulation(double transferPopulation) {
		this.transferPopulation = transferPopulation;
	}

	public double getTransferRates() {
		return transferRates;
	}

	public void setTransferRates(double transferRates) {
		this.transferRates = transferRates;
	}
}
