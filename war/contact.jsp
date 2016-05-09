<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<!--
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
-->

<html lang="en">

<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="generator" content="Currexify | Social EXchange">
<title>Currexify | Social EXchange</title>

<!-- Place favicon.ico in the `app/` directory -->

<!-- Chrome for Android theme color -->
<meta name="theme-color" content="#2E3AA1">

<!-- Web Application Manifest -->
<link rel="manifest" href="manifest.json">

<!-- Tile color for Win8 -->
<meta name="msapplication-TileColor" content="#3372DF">

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<meta name="application-name" content="PSK">
<link rel="icon" sizes="192x192" href="images/touch/logotipo_tocho.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title"
	content="Currexify | Social EXchange">
<link rel="apple-touch-icon" href="images/touch/logotipo_tocho.png">

<!-- Tile icon for Win8 (144x144) -->
<meta name="msapplication-TileImage"
	content="images/touch/logotipo_tocho.png">

<!-- build:css styles/main.css -->
<link rel="stylesheet" href="styles/main.css">
<!-- endbuild-->

<!-- build:js bower_components/webcomponentsjs/webcomponents-lite.min.js -->
<script src="bower_components/webcomponentsjs/webcomponents-lite.js"></script>
<!-- endbuild -->

<!-- Because this project uses vulcanize this should be your only html import
       in this file. All other imports should go in elements.html -->
<link rel="import" href="elements/elements.html">

<!-- For shared styles, shared-styles.html import in elements.html -->
<style is="custom-style" include="shared-styles"></style>
<style>
span { @apply (--paper-font-body1);
	
}

.img-profile {
	width: 76px;
	height: 76px;
	border-radius: 38px;
}

.name {
	margin-bottom: 10px;
	font-size: 18px;
}

.notify {
	font-size: 12px;
}

.content {
	padding: 54px 64px;
}

#submit {
	color: white;
	background-color: #4AAECF;
	width: 20%;
	display: inline-block;
	position: relative;
	box-sizing: border-box;
	min-width: 5.14em;
	margin: 0 0.29em;
	border: none;
	text-align: center;
	font: inherit;
	text-transform: uppercase;
	outline-width: 0;
	border-radius: 3px;
	-moz-user-select: none;
	-ms-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	cursor: pointer;
	z-index: 0;
	padding: 0.7em 0.57em;
	float: right;
	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0
		rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
}

.yellow-button {
	text-transform:none;
	color: #eeff41;
	margin-left: 20px;
}
</style>

<style is="custom-style">
	#send {
		--paper-toast-background-color: green;
		--paper-toast-color: white;
	}
</style>
</head>

<body unresolved>
	<!-- build:remove -->
	<span id="browser-sync-binding"></span>
	<!-- endbuild -->

	<template is="dom-bind" id="app"> <paper-drawer-panel
		id="paperDrawerPanel"> <!-- Drawer Scroll Header Panel -->
	<paper-scroll-header-panel drawer fixed> <!-- Drawer Toolbar -->
	<paper-toolbar id="drawerToolbar"> <span
		class="menu-name"><img src="images/touch/logotipo_tocho.png"
		style="width: 80px"></span> </paper-toolbar> <!-- Drawer Content --> <paper-menu
		class="app-menu" attr-for-selected="data-route" selected="[[route]]">
	<a data-route="home" href="/"> <iron-icon icon="home"></iron-icon>
		<span>Home</span>
	</a> <c:if test='${user != ""}'>
		<a data-route="profile" href="profile"> <iron-icon icon="face"></iron-icon>
			<span>Perfil</span>
		</a>
	</c:if> <c:if test='${user == ""}'>
		<a data-route="users" href="login"> <iron-icon
				icon="verified-user"></iron-icon> <span>Login</span>
		</a>
	</c:if> <c:if test='${user != ""}'>
		<a data-route="transaction" href="transaction"> <iron-icon
				icon="swap-horiz"></iron-icon> <span>Transacciones</span>
		</a>
	</c:if> <c:if test='${user != ""}'>
		<a data-route="account-circle" href="friends"> <iron-icon
				icon="account-circle"></iron-icon> <span>Amigos</span>
		</a>
	</c:if> <a data-route="contact" href="contact"> <iron-icon icon="mail"></iron-icon>
		<span>Contacto</span>
	</a> <c:if test='${user != ""}'>
		<div style="position: absolute; bottom: 0; width: 100%">
			<hr>
			<a href="<c:url value="${url}"/>"> <iron-icon
					icon="subdirectory-arrow-left"></iron-icon> <c:out
					value="${urlLinktext}" /></a>
		</div>
	</c:if> </paper-menu> </paper-scroll-header-panel> <!-- Main Area --> <paper-scroll-header-panel main
		id="headerPanelMain" condenses keep-condensed-header>
	<!-- Main Toolbar --> <paper-toolbar id="mainToolbar" class="tall">
	<paper-icon-button id="paperToggle" icon="menu" paper-drawer-toggle></paper-icon-button>

	<span class="space"></span> <!-- Toolbar icons --> <paper-icon-button
		icon="refresh"></paper-icon-button> <paper-icon-button icon="search"></paper-icon-button>

	<!-- Application name -->
	<div class="middle middle-container">
		<div class="app-name">Currexify</div>
	</div>

	<!-- Application sub title -->
	<div class="bottom bottom-container">
		<div class="bottom-title">Social Exchange para todos</div>
	</div>
	</paper-toolbar> <!-- Main Content -->
	<div class="content">
		<paper-material elevation="1"> 
		<table style="width:100%">
				<tr style="width:100%">
					<td style="width:100%">
						<paper-input id="name" label="Introduzca un título" name="titulo">
					</td>
				</tr>
				<tr style="width:100%">
					<td style="width:100%">
						<paper-input id="email" label="Introduzca su email" name="email">
					</td>
				</tr>
				<tr style="width:100%">
					<td style="width:100%">
						<paper-textarea id="text" label="Introduzca el texto" name="text">
					</td>
				</tr>
				<tr style="width:100%">
					<td style="width:100%">
			<form action="contact" method="post">
						<input type="submit" id="submit" label="Enviar">
			</form>
					</td>
				</tr> 
		</table>
		</br>
		</br>
		Puede encontrarnos en las siguientes coordenadas:
		</br>
		<div id="googleMap" style="width:100%;height:380px;"></div>
		</paper-material>
	</paper-scroll-header-panel> </paper-drawer-panel> <paper-toast id="toast"> <span
		class="toast-hide-button" role="button" tabindex="0"
		onclick="app.$.toast.hide()">Ok</span> </paper-toast> <!-- Uncomment next block to enable Service Worker support (1/2) -->
    <paper-toast id="send"
                 duration="6000"
                 text="Message send! :)">
        	<paper-button onclick="send.toggle()" class="yellow-button">Close now!</paper-button>
    </paper-toast>
<!--
    <platinum-sw-register auto-register
                          clients-claim
                          skip-waiting
                          base-uri="bower_components/platinum-sw/bootstrap"
                          on-service-worker-installed="displayInstalledToast">
      <platinum-sw-cache default-cache-strategy="fastest"
                         cache-config-file="cache-config.json">
      </platinum-sw-cache>
    </platinum-sw-register>
    --> </template>

	<!-- build:js scripts/app.js -->
	<script src="scripts/app.js"></script>
	<!-- endbuild-->
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script>
	window.onload = getUrlVars;
	function getUrlVars() {
		var vars = {};
		var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
				function(m, key, value) {
					vars[key] = value;
				});
		if (vars.send == "true")
			document.getElementById("send").open();
	}
	
		function initialize() {
		  var mapProp = {
		    center:new google.maps.LatLng(40.4526826,-3.726343),
		    zoom:17,
		    mapTypeId:google.maps.MapTypeId.ROADMAP
		  };
		  var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
		  var marker=new google.maps.Marker({
			  position:new google.maps.LatLng(40.4526826,-3.726343),
			  });

			marker.setMap(map);
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
</body>

</html>
