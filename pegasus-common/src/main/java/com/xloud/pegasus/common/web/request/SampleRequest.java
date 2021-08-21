package com.xloud.pegasus.common.web.request;

import java.util.List;

import lombok.Data;

@Data
public class SampleRequest {
	private List<String> array;
	private List<SampleDTO> outletList;

	@Data
	public static class SampleDTO {
		private String outletNo;
		private List<String> codeList;
	}
}
