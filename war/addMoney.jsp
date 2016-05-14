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

.column-6 {
	display: inline-block;
	padding: 0;
	margin: 0;
	border: 0;
	width: 49.5%;
}

paper-dropdown-menu {
	width: 100%;
}

div.recommended {
	vertical-align: bottom;
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
		<a data-route="friends" href="friends"> <iron-icon
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
		<paper-material>
		<form action="addMoney" method="post" name="Form">
			<div class="row">
				<div class="column-6">
					<paper-listbox selected="{{select}}"> 
						<paper-item>
							<img src="https://jobs.visa.com/sites/visa/images/visa_logo_blu.png"
								style="width:40px; height:20px; border-radius: 2px; margin-right: 10px;">
							VISA
						</paper-item>
						<paper-item>
							<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/MasterCard_Logo.svg/2000px-MasterCard_Logo.svg.png"
								style="width:40px; height:20px; border-radius: 2px; margin-right: 10px;">
							MASTER CARD
						</paper-item>
						<paper-item>
							<img src="http://paymentweek.com/wp-content/uploads/2015/10/American-Express-copy.png"
								style="width:40px; height:20px; border-radius: 2px; margin-right: 10px;">
							AMERICAN EXPRESS
						</paper-item>
					</paper-listbox>
					
					<input type="hidden" name="card" value="{{select}}">
				</div>
				<div class="column-6">
					<paper-input id="name" label="Introduzca nombre y apellidos" name="name">
						<paper-icon-button
							suffix onclick="clearName()" icon="clear" alt="clear"
							title="clear">
					</paper-input>
					<paper-input id="card" type="number" label="Numero de tarjeta" name="cardNumber" maxlength="16">
						<iron-icon icon="credit-card" prefix></iron-icon>
						<paper-icon-button
							suffix onclick="clearCardNumber()" icon="clear" alt="clear"
							title="clear">
					</paper-input>
				</div>
			</div>
			<div class="row">
				<div class="column-6">
					<paper-input id="expirate" label="Fecha de expiracion" name="expirate">
						<paper-icon-button
							suffix onclick="clearExpirate()" icon="clear" alt="clear"
							title="clear">
					</paper-input>
				</div>
				<div class="column-6">
					<paper-input id="cvv" type="number" label="CVV" name="cvv" maxlength="3">
						<paper-icon-button
							suffix onclick="clearCvv()" icon="clear" alt="clear"
							title="clear">
					</paper-input>
				</div>
			</div>
			<div class="row">
				<paper-input id="quantity" type="number" label="Introduzca una cantidad" name="quantity">
					<paper-icon-button
						suffix onclick="clearQuantity()" icon="clear" alt="clear"
						title="clear">
				</paper-input>
			</div>
			<div class="row">
					<paper-dropdown-menu label="Moneda"> <paper-menu
						class="dropdown-content" attr-for-selected="value"
						selected="{{curr}}"> <c:forEach
						items="${currencies}" var="curr2">
						<paper-item value="<c:out value="${curr2}"/>"> <c:out
							value="${curr2}" /></paper-item>
					</c:forEach> </paper-menu> </paper-dropdown-menu>
					<input type="hidden" name="currency" value="{{curr}}">
			</div>
			<input type="submit" value="Enviar" id="submit">
		</paper-material>
		</form>
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

<script>


	function clearCardNumber() {
		document.getElementById("card").value = "";
	}
	
	function clearCvv() {
		document.getElementById("cvv").value = "";
	}
	
	function clearName() {
		document.getElementById("name").value = "";
	}
	
	function clearExpirate() {
		document.getElementById("expirate").value = "";
	}
	
	function clearQuantity() {
		document.getElementById("quantity").value = "";
	}
	
</script>

</html>
