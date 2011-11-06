<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="ru.meowth.services.LetterService"%>
<%@page import="ru.meowth.services.PMF"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="ru.meowth.services.LetterService" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Сервис отправки посланий в будущее</title>
<link href="/css/styles.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>
<script type="text/javascript" src="/scripts/scriptings.js"></script>
</head>
<body>	
	<div id="container">	
		<div id="topbar" class="squizzed">	
			<div id="topbar2">
				С момента запуска сервиса было отправлено
				<% LetterService service = new LetterService();
				PersistenceManager pm =  PMF.get().getPersistenceManager();
				%>
				<%= service.getLettersSentCount(pm) %>
				писем в будущее, ожидают отправки еще <%= service.getLettersInQueue(pm) %>			
			</div>
		</div>	
		<div id="left-block">
		</div>
		<div id="header" class="squizzed">
			<div id="topbar2">				
				<img src="./images/snow-flake.png" style="float:left; margin-right:20px;"/>				
				<H1>Space Freezer			
				<h2>Отправь письмо в будущее</h2>
				</H1>
			</div>
		</div>
		<div id="separator" class="squizzed">
			<div id="topbar2">
				<% if(session.getAttribute("#marker") != null) { %>			
					<span id="thankx">Спасибо. Мы не подведем. А вы можете написать ещё что-нибудь.</span>
				<% }
				session.setAttribute("#marker", null);
				%>	
			</div>
		</div>
		<div id="send-form" class="squizzed">
			<div id="topbar2">
				<form id="inputform" action="/do" method="post" accept-charset="utf-8">
						<p>
							<span>E-mail для отправки </span><input type="text" id="email" name="email" class=""/>
							<textarea cols="80" rows="15" name="message" id="message" class=""></textarea>					
							<br/>
							<span>Хочу, чтобы пришло</span>
							<select onchange="" id="timespan" name="timespan">
								<option value="1w" >Совсем скоро</option>
								<option value="1m" >Почти скоро</option>
								<option value="3m" >Нескоро</option>		
								<option value="6m" >Совсем нескоро</option>
								<option value="1y" >Почти что и никогда</option>
							</select>				
							<input class="" type="submit" id="do" value="Отправить" title="Отправить" />
						</p>			
				</form>
			</div>
		</div>
		<div id="footer" class="squizzed">
			<div id="topbar2">
				<span class="note">(?) Не забудьте добавить i.froze.your.space[@]gmail.com в
				"белый" список отправителей</span>	
			</div>
		</div>
		<div id="port=footer" class="squizzed">
			<div id="topbar2" style="text-align:right">
				<span><a href="http://twitter.com/frozen_space">@frozen_space</a></span>
			</div>
		</div>
	</div>
</body>
</html>