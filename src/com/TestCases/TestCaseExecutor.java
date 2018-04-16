package com.TestCases;

import org.testng.annotations.Test;

import com.Library.Library;

public class TestCaseExecutor extends Library {

	@Test
	public void naviagtetodesiredLink() {
		dbconnection();
		naviagate_to_top_rated_indian_movies();
	}
	
}
