<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title><g:layoutTitle default="Grails"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link rel="shortcut icon" href="${assetPath(src:'favicon.ico')}" type="image/x-icon">
  <link rel="apple-touch-icon" href="${assetPath(src:'apple-touch-icon.png')}">
  <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src:'apple-touch-icon-retina.png')}">
  <link rel="stylesheet" href="${assetPath(src:'main.css')}" type="text/css">
  <link rel="stylesheet" href="${assetPath(src:'mobile.css')}" type="text/css">

  <asset:javascript src="application.js"/>
  <asset:stylesheet src="example.css"/>

  <g:layoutHead/>
</head>

<body>
<div class="container-fluid container-content">
  <div id="grailsLogo" role="banner">
    <a href="http://grails.org"><img src="${resource(dir:'images', file:'grails_logo.png')}" alt="Grails"/></a>
    <sec:ifLoggedIn>
      <div id="logoutLink">
        <g:link controller="logout"><g:message code="logout" default="logout"/></g:link>
      </div>
    </sec:ifLoggedIn>
  </div>

  <g:layoutBody/>
  <div class="footer" role="contentinfo"></div>

  <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
  <g:javascript library="application"/>
</div>

</body>
</html>
