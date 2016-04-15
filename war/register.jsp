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
:host {
	display: block;
}

span { @apply (--paper-font-body1);
	
}

.img-logo {
	max-width: 200px;
	display: block;
	margin: 0 auto;
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

#clear {
	color: white;
	background-color: #4AAECF;
	width: 20%;
	float: right;
}

.content {
	padding: 54px 64px;
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
	<a data-route="home" href="{{baseUrl}}"> <iron-icon icon="home"></iron-icon>
		<span>Home</span>
	</a> <a data-route="profile" href="{{baseUrl}}profile"> <iron-icon
			icon="face"></iron-icon> <span>Profile</span>
	</a> <a data-route="users" href="{{baseUrl}}users"> <iron-icon
			icon="verified-user"></iron-icon> <span>Login</span>
	</a> <a data-route="transaction" href="{{baseUrl}}transaction"> <iron-icon
			icon="swap-horiz"></iron-icon> <span>Transactions</span>
	</a> <a data-route="contact" href="{{baseUrl}}contact"> <iron-icon
			icon="mail"></iron-icon> <span>Contact</span>
	</a> </paper-menu> </paper-scroll-header-panel> <!-- Main Area --> <paper-scroll-header-panel main
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
		<div style="width: 100%;">
			<div style="margin: 0 auto;">
				<img src="../../images/touch/logotipo_tocho.png" class="img-logo">
			</div>
			<div style="width: 90%; margin-left: 5%;">

				<form action="register" method="post">
					<div>
						<paper-input id="user" label="Usuario" name="user">
						<iron-icon icon="account-circle" prefix></iron-icon> <paper-icon-button
							suffix on-click="clearUser" icon="clear" alt="clear"
							title="clear"></paper-input>
						<paper-input id="pass" name="pass" label="Contraseña"
							type="password"> <iron-icon icon="fingerprint"
							prefix></iron-icon> <paper-icon-button suffix
							on-click="clearPass" icon="clear" alt="clear" title="clear"></paper-input>
						<paper-input label="E-mail" name="email" id="email">
						<iron-icon icon="mail" prefix></iron-icon>
						<div suffix>@gmail.com</div>
						<paper-icon-button suffix on-click="clearEmail" icon="clear"
							alt="clear" title="clear"> </paper-icon-button> </paper-input>
						<paper-input id="address" name="address" label="Address">
						<iron-icon icon="home" prefix></iron-icon> <paper-icon-button
							suffix on-click="clearAddress" icon="clear" alt="clear"
							title="clear"></paper-input>
						<paper-input id="phone" label="Phone" name="phone" type="number">
						<iron-icon icon="settings-phone" prefix></iron-icon> <paper-icon-button
							suffix on-click="clearPhone" icon="clear" alt="clear"
							title="clear"></paper-input>
						<paper-input id="cardnum" name="cardnum" label="Card Number"
							char-counter maxlength="16"> <iron-icon
							icon="credit-card" prefix></iron-icon> <paper-icon-button suffix
							on-click="clearCard" icon="clear" alt="clear" title="clear"></paper-input>
					</div>
					<div style="margin-top: 2em;">
						<div style="display: inline-block; width: 100%; margin: 0 auto">
							<paper-button id="clear" raised on-click="clearAll">Clear
							fields</paper-button>
							<input type="submit" value="submit" id="submit">
						</div>
					</div>

				</form>
			</div>
		</div>
		</paper-material>
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