package com.instagram.util;

import org.springframework.ui.Model;

public class Script {

	public static String locationMsg(String locationUrl, String msg, Model model) {
		StringBuffer stringBuilder = new StringBuffer();
		stringBuilder.append("<script>");
		stringBuilder.append("alert('"+msg+"')");
		stringBuilder.append("location.href='"+locationUrl+"';");
		stringBuilder.append("</script>");
		stringBuilder.toString();
		model.addAttribute("locationMsg", stringBuilder);
		return "util/locationMsg";
	}

	
}
