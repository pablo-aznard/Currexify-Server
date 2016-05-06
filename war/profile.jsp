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

.button-bottom {
	float: right;
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
	</a>
	<c:if test='${user != ""}'>
		<a data-route="profile" href="profile"> <iron-icon icon="face"></iron-icon>
			<span>Profile</span>
		</a> 
	</c:if>
	<c:if test='${user == ""}'>
		<a data-route="users" href="login"> <iron-icon
				icon="verified-user"></iron-icon> <span>Login</span>
		</a>
	</c:if>
	<c:if test='${user != ""}'>
		<a data-route="transaction" href="transaction"> <iron-icon
			icon="swap-horiz"></iron-icon> <span>Transactions</span>
		</a>
	</c:if> <c:if test='${user != ""}'>
		<a data-route="friends" href="friends"> <iron-icon
				icon="account-circle"></iron-icon> <span>Friend Zone</span>
		</a>
	</c:if>
	<a data-route="contact" href="contact"> <iron-icon
			icon="mail"></iron-icon> <span>Contact</span>
	</a>
	<c:if test='${user != ""}'>
		<div style="position: absolute; bottom: 0; width: 100%">
			<hr>
			<a href="<c:url value="${url}"/>"> <iron-icon
					icon="subdirectory-arrow-left"></iron-icon> <c:out
					value="${urlLinktext}" /></a>
		</div>
	</c:if>
	</paper-menu> </paper-scroll-header-panel>  <!-- Main Area --> <paper-scroll-header-panel main
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
			<c:if test='${user != ""}'>
			<a class="button-bottom" href="/modificar"> <paper-button title="Modify Profile">Modify Profile</paper-button></a>
			<a class="button-bottom" href="/addMoney"> <paper-button title="Add Money">Insert Coin</paper-button></a>
			<a class="button-bottom" href="/takeMoneyOut"> <paper-button title="Take Money Out">Give me my money</paper-button></a>
			</c:if>
		<div style="width: 100%;">
			<div
				style="display: inline-block; vertical-align: middle; margin-right: 30px">
				<img src="images/touch/user-default.png" class="img-profile">
			</div>
			<div style="display: inline-block;">
				<div class="name">
					<span><c:out value="${user}" /></span>
				</div>
				<div class="notify">
					Notificaciones de cambio: <c:out value="${notificaciones}" />
				</div>
			</div>
		</div>
		<currencies-list title="Budget of currencies" currencies='<c:out value="${currencies}"/>'></currencies-list> </paper-material>
	</paper-scroll-header-panel> </paper-drawer-panel> <paper-toast id="toast"> <span
		class="toast-hide-button" role="button" tabindex="0"
		onclick="app.$.toast.hide()">Ok</span> </paper-toast> <!-- Uncomment next block to enable Service Worker support (1/2) -->
	<!--
    <paper-toast id="caching-complete"
                 duration="6000"
                 text="Caching complete! This app will work offline.">
    </paper-toast>

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
</body>

</html>
