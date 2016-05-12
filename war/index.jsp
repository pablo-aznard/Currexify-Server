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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
<link rel="import" href="bower_components/polymer/polymer.html">
<link rel="import" href="bower_components/paper-styles/typography.html">

<link rel="stylesheet" href="styles/home-screen.css">
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
			<a href="<c:url value='${url}'/>"> <iron-icon
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
		<div style="width: 100%; background-color: #BBDEFB">
			<div style="padding: 30px; width: 100%; display: flex">
				<img src="/images/touch/logotipo_tocho.png" class="img-home">
			</div>
		</div>
		<div
			style="width: 100%; background-image: url('https://0.s3.envato.com/files/74370248/preview.jpg'); text-align: center; color: white; height: 280px; padding-top: 50px; background-repeat: no-repeat; background-size: cover;">
			<h1 style="font-size: 54px; margin-bottom: 20px">Social Exchange
			</h1>
			<h3 style="font-size: 24px">La manera inteligente de cambiar tu
				dinero</h3>
			<h5 style="font-size: 14px">
				Cambia con otro viajero de cualquier otro país y obten más por tu
				dinero.
				</h3>
		</div>
		<section class="banner-lead bg-charcoal-dark">
			<div class="container">
				<p>En Currexify la moneda que recibes proviene de otros
					viajeros. En lugar de cambiar en el banco o casas de cambio,
					facilitamos que la gente pueda cambiar entre ellos aún estando en
					diferentes países. Una manera más barata, transparente y
					beneficiosa de obtener moneda extranjera. Lo llamamos moneda
					social.</p>
			</div>
		</section>
		<div class="row">
			<div class="lead-calculator">
				<div class="col-lead">
					<h2 class="heading">
						Precios simples y transparentes
					</h2>
					<p>Al intercambiar tu moneda con otros viajeros, te sale más
						barato. En Currexify pagarás por tu moneda extranjera un 1%, que
						viene a ser 10 veces menos de lo que te cobrarán en el banco, o en
						los aeropuertos.</p>
				</div>
				<div class="col-calculator">
					<img src="/images/changes.png" style="width: 100%;">
				</div>
			</div>
			<div class="load col" title="">
				<h2 class="heading">Carga</h2>
				<p>Carga tu cuenta con la cantidad que necesites cambiar. Puedes
					usar tu banco para hacer una transferencia a tu cuenta Currexify o
					recargarla con la tarjeta de débito de tu banco habitual.</p>
			</div>
			<div class="swap col" title="">
				<h2 class="heading">Cambia</h2>
				<p>Dinos qué moneda quieres y para cuándo, y nosotros hacemos el
					resto. Encontraremos a gente en nuestra plataforma con la moneda
					que necesites e intercambiaremos vuestro dinero automáticamente.</p>
			</div>
			<div class="spend col" title="">
				<h2 class="heading">Viaja</h2>
				<p>Enviamos a cada usuario una tarjeta MasterCard®&nbsp;de
					prepago de Currexify gratuita, lo que te permitirá utilizar tu
					dinero en cualquier punto que acepte MasterCard.</p>
			</div>
			<div style="width: 100%; clear: both; margin-bottom: 70px"></div>

			<h2 class="heading">Únete a nosotros hoy mismo</h2>
			<div class="col-reg">
				<a class="btn btn-primary btn-lg btn-block"
					href="register.jsp">Regístrate</a>
			</div>
		</div>
		</div>
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
