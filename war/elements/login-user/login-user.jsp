<!--
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="import" href="../../bower_components/polymer/polymer.html">
<link rel="import" href="../../bower_components/paper-styles/typography.html">

<link rel="import" href="../../bower_components/paper-input/paper-input.html">
<link rel="import" href="../../bower_components/paper-checkbox/paper-checkbox.html">
<link rel="import" href="../../bower_components/paper-button/paper-button.html">

<dom-module id="login-user">
  <template>
    <style>
      :host {
        display: block;
      }

      span {
        @apply(--paper-font-body1);
      }

      .img-logo {
        max-width: 200px;
        display: block;
        margin: 0 auto;
      }

    </style>
    <p>Sistema de gestión de TFGs</p>
	<c:if test="${user != null}">
		<c:out value="${user}" />
	</c:if>
    <p>Puedes pulsar el siguiente enlace para salir
<a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></p>
    <div style="width: 100%;">
      <div style="margin: 0 auto;">
        <img src="../../images/touch/logotipo_tocho.png" class="img-logo">
      </div>
      <div style="width: 90%; margin-left:5%;">
        <div>
          <paper-input label="Usuario"></paper-input>
          <paper-input label="Contraseña"></paper-input>
        </div>
        <div style="margin-top: 2em;">
          <div style="display: inline-block; width: 49.5%">
            <paper-checkbox>Mantener sesión iniciada</paper-checkbox>
            <p><span>¿No estás registrado?<a href="{{baseUrl}}register">Regístrate</a></span></p>
          </div>
          <div style="display: inline-block; width: 49.5%">
            <paper-button raised style="color: white; background-color: #4AAECF; width: 25%; float:right;">Login</paper-button>
          </div>
        </div>
      </div>
    </div>
  </template>

  <script>
    (function() {
      'use strict';

      Polymer({
        is: 'login-user',

        properties: {
          username: {
            type: String,
            notify: true,
            value: "username"
          },

          password: {
            type: String,
            value: "password"
          }
        },

        ready: function() {
          var that = this;
        }
      });
    })();
  </script>
</dom-module>
