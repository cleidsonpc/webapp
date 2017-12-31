package com.cleidsonpc.webapp.service;

import com.cleidsonpc.webapp.model.Address;

public class EircodeServiceResponse {

	private String errorMessage;
	private String alertMessage;
	private Address[] addressList;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public Address[] getAddressList() {
		return addressList;
	}

	public void setAddressList(Address[] addressList) {
		this.addressList = addressList;
	}

}
