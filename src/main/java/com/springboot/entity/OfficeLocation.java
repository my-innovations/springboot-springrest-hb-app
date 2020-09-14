package com.springboot.entity;

//@Entity(name = "ForeignKeyAssoOfficeLocationEntity")
//@Table(name = "OFFICE", uniqueConstraints = { @UniqueConstraint(columnNames = "OID") })
public class OfficeLocation {

	private Long officeLocationID;
	private String officeLocation;

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "OID", unique = true, nullable = false)
	public Long getOfficeLocationID() {
		return officeLocationID;
	}

	public void setOfficeLocationID(Long officeLocationID) {
		this.officeLocationID = officeLocationID;
	}

	//@Column(name = "location", nullable = false, length = 100)
	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	@Override
	public String toString() {
		return "OfficeLocation [officeLocationID=" + officeLocationID + ", officeLocation=" + officeLocation + "]";
	}

}
