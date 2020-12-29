package Tester;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import application.Source;

public class Tests {
	private Source j;
	@BeforeClass
	public void init() throws UnknownHostException, IOException {
		j = new Source();
	}
	
	@Test
	public void reTestMethod() {
		String[] kw = "Kalkulus<test>Rijal".split("<test>");
		assertEquals("Kalkulus", kw[0]);
	}
}
